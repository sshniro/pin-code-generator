package com.infotech.app.service;

import com.infotech.app.BatchDao;
import com.infotech.app.PINDao;
import com.infotech.app.constants.GeneratorConstants;
import com.infotech.app.entities.Batch;
import com.infotech.app.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Service
public class activationCodeGeneratorImpl implements activationCodeGenerator{

    @Autowired
    private PINDao pinDao;

    @Autowired
    private BatchDao batchDao;

    @Autowired
    private Util util;

    @Override
    public Map<String, Integer> generatePINCodeBatch(int pinCodeLength, int batchSize, int value) {

        // create a new batch ID
        Batch batch = new Batch(util.getCurrentTimeStamp(), value);


        Map<String, Integer> pinCodeMap = new HashMap<>();

        Random rand = new Random();
        int generatedPinCodesCount = 0;

        int groupDigitCount = pinCodeLength / GeneratorConstants.groupByCount;
        int leftOverDigitsCount = pinCodeLength % GeneratorConstants.groupByCount;


        while (generatedPinCodesCount < batchSize) {
            String pinCode = "";
            String paddedPinCode = "";
            if (groupDigitCount != 0) {
                for (int i = 0; i < groupDigitCount; i++) {
                    paddedPinCode = addPaddingToNumbers(
                            String.valueOf(generateRandomNumbers(((int) Math.pow(10, GeneratorConstants.groupByCount))) - 1),
                            GeneratorConstants.groupByCount
                    );
                    pinCode = pinCode + paddedPinCode;
                }
            }
            if (leftOverDigitsCount != 0) {
                paddedPinCode = addPaddingToNumbers(
                        String.valueOf(generateRandomNumbers(((int) Math.pow(10, leftOverDigitsCount))) - 1),
                        leftOverDigitsCount);
                pinCode = pinCode + paddedPinCode;
            }


            // If it passes all the rules

            // If it is not present in the data base
            if (!pinDao.isActivationCodeExists(pinCode)) {
                // persist to DB
                pinDao.createPinCode(pinCode, String.valueOf(generatedPinCodesCount));
                pinCodeMap.put(pinCode, generatedPinCodesCount);
                generatedPinCodesCount++;
            } else {
                System.out.println("duplicate entry found");
            }
        }


        for (Map.Entry<String, Integer> entry : pinCodeMap.entrySet()) {
            System.out.println("Item : " + entry.getKey() + " Count : " + entry.getValue());
        }

        return pinCodeMap;
    }

    private boolean customRules(String pinCode) {
        return true;
    }

    private String addPaddingToNumbers(String pinCode, int paddingLength) {
        String padding = "";
        if (pinCode.length() < paddingLength) {
            int difference = paddingLength - pinCode.length();
            for (int i = 0; i < difference; i++) {
                padding = padding + "0";
            }

        } else if (pinCode.length() == paddingLength) {
            return pinCode;
        }
        return padding + pinCode;
    }

    private int generateRandomNumbers(int max) {
        return new Random().nextInt(max);
    }
}
