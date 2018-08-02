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

    @Column(name = "SERIAL_CODE")
    private String serialCode;

    public PIN() { }

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
}
