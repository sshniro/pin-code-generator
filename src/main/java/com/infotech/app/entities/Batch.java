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

    public Batch() {
    }

    public Batch(String createdDate) {
        this.createdDate = createdDate;
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
}
