package com.infotech.app.entities;

import javax.persistence.*;

@Entity
@Table(name = "BATCH")
public class Batch {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int batchId;

    @Column(name = "CREATED_DATE")
    private String createdDate;

    @Column(name = "BATCH_SIZE")
    private int batchSize;

    @Column(name = "VALUE")
    private int value;

    @Column(name = "IS_ACTIVE")
    private boolean isActive;

    public Batch() {
    }

    public Batch(String createdDate, int value) {
        this.createdDate = createdDate;
        this.value = value;
   }

    public int getBatchId() {
        return batchId;
    }

    public void setBatchId(int batchId) {
        this.batchId = batchId;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public int getBatchSize() {
        return batchSize;
    }

    public void setBatchSize(int batchSize) {
        this.batchSize = batchSize;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
