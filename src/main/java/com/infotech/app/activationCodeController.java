package com.infotech.app;

import com.infotech.app.service.activationCodeGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class activationCodeController {

    @Autowired
    private activationCodeGenerator codeGenerator;

    @RequestMapping(path = "/hello", method = RequestMethod.GET)
    public static String getNiro() {
        return "hello world";
    }

    @RequestMapping(path = "/activateBatch", method = RequestMethod.GET)
    public static boolean activateBatch() {
        // check if the batch exits

        // check if already activated

        // if not activate and send a success message

        return true;
    }

    @RequestMapping(path = "/generateBatch", method = RequestMethod.GET)
    public static boolean generateBatch() {

        // get digit count

        // get timeout

        // get batch value

        return true;

    }

}
