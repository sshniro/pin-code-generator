package com.infotech.app;

import com.infotech.app.entities.Batch;
import com.infotech.app.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public class BatchDao {

    @Autowired
    private SessionFactory sessionFactory;

    public void persistBatch(Batch batch) {
        Session session= null;
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            int ID = (Integer) session.save(batch);
            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateBatch(Batch batch) {
        Session session= null;
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            session.update(batch);
            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getLastUpdatedId() throws Exception {
        Session session= null;
        try {
            session = sessionFactory.openSession();
            BigInteger lastId = (BigInteger) session.createSQLQuery("SELECT LAST_INSERT_ID()")
                    .uniqueResult();
            return lastId.intValue();
        }catch (Exception e){
            e.printStackTrace();
            throw new Exception();
        }
    }


}
