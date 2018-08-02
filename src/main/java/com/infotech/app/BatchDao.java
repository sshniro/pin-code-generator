package com.infotech.app;

import com.infotech.app.entities.Batch;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Date;

@Repository
public class BatchDao {

    @Autowired
    private SessionFactory sessionFactory;

    public void createNewBatch() {
        Session session= null;
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            int ID = (Integer) session.save(new Batch(getCurrentTimeStamp()));
            System.out.println("New batch has been created with the batch id of " + ID);
            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getLastUpdatedId() {
        Session session= null;
        try {
            session = sessionFactory.openSession();
            BigInteger lastId = (BigInteger) session.createSQLQuery("SELECT LAST_INSERT_ID()")
                    .uniqueResult();
            System.out.println("last id is" + lastId);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public String getCurrentTimeStamp() {
        Date date = new Date();
        long time = date.getTime();

        Timestamp ts = new Timestamp(time);
        return ts.toString();
    }
}
