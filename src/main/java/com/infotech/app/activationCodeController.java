package com.infotech.app;

import com.infotech.app.constants.GeneratorConstants;
import com.infotech.app.service.activationCodeGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


@RestController
public class activationCodeController {

    @Autowired
    private activationCodeGenerator generator;

    @RequestMapping(path = "/hello", method = RequestMethod.GET)
    public static String getNiro() {
        return "hello world";
    }

    @RequestMapping(path = "/activateBatch", method = RequestMethod.GET)
    public static boolean activateBatch(@RequestParam("batchSize") int batchID) {
        // check if the batch exits

        // check if already activated

        // if not activate and send a success message

        return true;
    }

    @RequestMapping(path = "/generateBatch", method = RequestMethod.GET)
    public Map<String, Integer> generateBatch(@RequestParam("digitCount") int digitCount,
                                              @RequestParam("batchSize") int batchSize,
                                              @RequestParam("value") int value) {

        return generator.generatePINCodeBatch(digitCount, batchSize, value);
    }
}
