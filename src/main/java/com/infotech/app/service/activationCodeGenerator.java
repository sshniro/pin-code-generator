package com.infotech.app.service;

import java.util.Map;

public interface activationCodeGenerator {

    public Map<String, Integer> generatePINCodeBatch(int pinCodeLength, int batchSize, int value);
}
