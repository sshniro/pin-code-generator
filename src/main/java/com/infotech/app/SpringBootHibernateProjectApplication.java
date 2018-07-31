package com.infotech.app;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import com.infotech.app.entities.PIN;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class SpringBootHibernateProjectApplication implements CommandLineRunner{

    private static final int maxIterations = 4;
    private static final int maxInt = 10000;
    private static final int batchSize = 1000;
    private static final int groupByCount = 4;

    @Autowired
    private PINDao pinDao;

	public static void main(String[] args) {
		SpringApplication.run(SpringBootHibernateProjectApplication.class, args);
	}
	@Override
	public void run(String... args) throws Exception {
        generatePins(8, true);
    }


    public void generatePins(int pinCodeLength, boolean isOnlyNumbers){

        Map<String, Integer> pinCodeMap = new HashMap<>();

        Random rand = new Random();
        int generatedPinCodesCount = 0;

        int groupDigitCount = pinCodeLength/groupByCount;
        int leftOverDigitsCount = pinCodeLength%groupByCount;


        if (leftOverDigitsCount == 0) {
            while (generatedPinCodesCount < batchSize) {
                String pinCode = "";
                String paddedPinCode = "";
                for (int i=0; i< groupDigitCount; i++){
                    paddedPinCode = addPaddingToNumbers(String.valueOf(generateRandomNumbers(((int) Math.pow(10, groupByCount))) - 1));
                    pinCode = pinCode + paddedPinCode;
                }

                // If it passes all the rules

                // If it is not present in the data base
                if (!pinDao.isActivationCodeExists(pinCode)) {
                    // persist to DB
                    pinDao.createPinCode(pinCode, String.valueOf(generatedPinCodesCount));
                    pinCodeMap.put(pinCode, generatedPinCodesCount);
                    generatedPinCodesCount++;
                }else {
                    System.out.println("duplicate entry found");
                }
            }
        }

        for (Map.Entry<String, Integer> entry : pinCodeMap.entrySet()) {
            System.out.println("Item : " + entry.getKey() + " Count : " + entry.getValue());
        }
    }

    private boolean customRules(String pinCode) {
        return true;
    }

    private String addPaddingToNumbers(String pinCode) {
        String padding = "";
        if (pinCode.length() < groupByCount) {
            int difference = groupByCount - pinCode.length();
            for (int i=0; i< difference; i++) {
                padding = padding+ "0";
            }

        }else if(pinCode.length() == groupByCount){
            return pinCode;
        }
        return padding + pinCode;
    }

    private int generateRandomNumbers(int max){
        return new Random().nextInt(max);
    }
}
