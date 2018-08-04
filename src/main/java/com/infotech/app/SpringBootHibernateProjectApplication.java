package com.infotech.app;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import com.infotech.app.entities.Batch;
import com.infotech.app.entities.PIN;
import com.infotech.app.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class SpringBootHibernateProjectApplication implements CommandLineRunner {

    private static final int maxIterations = 4;
    private static final int maxInt = 10000;
    private static final int batchSize = 100;
    private static final int groupByCount = 4;

    @Autowired
    private PINDao pinDao;

    @Autowired
    private BatchDao batchDao;

    @Autowired
    private Util util;

    public static void main(String[] args) {
        SpringApplication.run(SpringBootHibernateProjectApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

//        batchDao.persistBatch(new Batch(util.getCurrentTimeStamp(), 10));
//        batchDao.persistBatch(new Batch(util.getCurrentTimeStamp(), 10));
//        batchDao.getLastUpdatedId();

//        generatePins(7, true);
    }

    public void activatePin(String pinCode) {

        PIN pin = pinDao.getActivationCode(pinCode);
        if (pin.getActivationCode() != null) {
            if (!pin.isActivated()) {
                // activate the pin and send response with the value
                pin.setActivated(true);
                pin.setActivatedTime(util.getCurrentTimeStamp());
                pinDao.persistPINCode(pin);

                // return success call back
            }else {
                System.out.println("Card already activated");
            }
        }else {
            System.out.println("Activation Code Not Found");
        }
    }

//    public void generatePins(int pinCodeLength, boolean isOnlyNumbers, int value) {
//
//        // create a new batch ID
//        Batch batch = new Batch(util.getCurrentTimeStamp(), value);
//
//
//        Map<String, Integer> pinCodeMap = new HashMap<>();
//
//        Random rand = new Random();
//        int generatedPinCodesCount = 0;
//
//        int groupDigitCount = pinCodeLength / groupByCount;
//        int leftOverDigitsCount = pinCodeLength % groupByCount;
//
//
//        while (generatedPinCodesCount < batchSize) {
//            String pinCode = "";
//            String paddedPinCode = "";
//            if (groupDigitCount != 0) {
//                for (int i = 0; i < groupDigitCount; i++) {
//                    paddedPinCode = addPaddingToNumbers(
//                            String.valueOf(generateRandomNumbers(((int) Math.pow(10, groupByCount))) - 1),
//                            groupByCount
//                    );
//                    pinCode = pinCode + paddedPinCode;
//                }
//            }
//            if (leftOverDigitsCount != 0) {
//                paddedPinCode = addPaddingToNumbers(
//                        String.valueOf(generateRandomNumbers(((int) Math.pow(10, leftOverDigitsCount))) - 1),
//                        leftOverDigitsCount);
//                pinCode = pinCode + paddedPinCode;
//            }
//
//
//            // If it passes all the rules
//
//            // If it is not present in the data base
//            if (!pinDao.isActivationCodeExists(pinCode)) {
//                // persist to DB
//                pinDao.createPinCode(pinCode, String.valueOf(generatedPinCodesCount));
//                pinCodeMap.put(pinCode, generatedPinCodesCount);
//                generatedPinCodesCount++;
//            } else {
//                System.out.println("duplicate entry found");
//            }
//        }
//
//
//        for (Map.Entry<String, Integer> entry : pinCodeMap.entrySet()) {
//            System.out.println("Item : " + entry.getKey() + " Count : " + entry.getValue());
//        }
//    }

    private boolean customRules(String pinCode) {
        return true;
    }

    private int generateRandomNumbers(int max) {
        return new Random().nextInt(max);
    }
}
