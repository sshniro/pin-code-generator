package com.infotech.app;

import com.infotech.app.constants.GeneratorConstants;
import com.infotech.app.entities.Batch;
import com.infotech.app.service.activationCodeGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


@RestController
public class activationCodeController {

    @Autowired
    private activationCodeGenerator generator;

    @Autowired
    private BatchDao batchDao;

    @RequestMapping(path = "/hello", method = RequestMethod.GET)
    public static String getNiro() {
        return "hello world";
    }

    @RequestMapping(path = "/activateBatch", method = RequestMethod.GET)
    public Map<String, String> activateBatch(@RequestParam("batchSize") int batchID) {
        Map<String, String> response = new HashMap<>();
        Batch batch = batchDao.getBatchById(batchID);
        if (batch == null) {
            response.put("status", "batchNotFound");
        }else {
            batch.setActive(true);
            batchDao.updateBatch(batch);
            response.put("status", "batch has been activated");
        }
        return response;
    }

    @RequestMapping(path = "/generateBatch", method = RequestMethod.GET)
    public Map<String, Integer> generateBatch(@RequestParam("digitCount") int digitCount,
                                              @RequestParam("batchSize") int batchSize,
                                              @RequestParam("value") int value) {

        return generator.generatePINCodeBatch(digitCount, batchSize, value);
    }
}
