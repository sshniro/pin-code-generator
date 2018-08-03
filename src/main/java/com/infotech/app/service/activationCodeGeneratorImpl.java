package com.infotech.app.service;

import org.springframework.stereotype.Service;

@Service
public class activationCodeGeneratorImpl implements activationCodeGenerator{

    @Override
    public boolean generatePINCodeBatch() {
        return false;
    }
}
