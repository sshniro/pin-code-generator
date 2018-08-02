package com.infotech.app.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "PIN")
public class PIN {

    @Id
    @Column(name = "PIN_CODE")
    private String activationCode;

    @Column(name = "BATCH_ID")
    private int batchId;

    @Column(name = "SERIAL_CODE")
    private String serialCode;

    @Column(name = "IS_ACTIVATED")
    private boolean isActivated;

    @Column(name = "ACTIVATED_TIME")
    private String activatedTime;

    public PIN() {

    }

    public PIN(String pinCode, String serialId) {
        this.activationCode = pinCode;
        this.serialCode = serialId;
    }

    public String getActivationCode() {
        return activationCode;
    }

    public void setActivationCode(String activationCode) {
        this.activationCode = activationCode;
    }

    public String getSerialCode() {
        return serialCode;
    }

    public void setSerialCode(String serialCode) {
        this.serialCode = serialCode;
    }

    public int getBatchId() {
        return batchId;
    }

    public void setBatchId(int batchId) {
        this.batchId = batchId;
    }

    public boolean isActivated() {
        return isActivated;
    }

    public void setActivated(boolean activated) {
        isActivated = activated;
    }

    public String getActivatedTime() {
        return activatedTime;
    }

    public void setActivatedTime(String activatedTime) {
        this.activatedTime = activatedTime;
    }
}
