package com.infotech.app;

import com.infotech.app.entities.PIN;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.List;

@Repository
public class PINDao {

    @Autowired
    private SessionFactory sessionFactory;

    public void persistPINCode(PIN pin) {
        Session session= null;
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            session.save(pin);
            System.out.println("PIN Code has been persisted");
            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void createPinCode(String pinCode, String serialId) {
        Session session= null;
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            session.save(new PIN(pinCode, serialId));
            System.out.println("PIN Code has been persisted");
            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean isActivationCodeExists(String activationCode) {
        Session session= null;
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            List<PIN> pinList = (List<PIN>) session.createQuery("from  PIN p where p.activationCode= :code")
                    .setParameter("code", activationCode)
                    .list();
            session.getTransaction().commit();
            session.close();
            if (pinList.size() != 0){
                return true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public PIN getActivationCode(String activationCode) {
        PIN pin = new PIN();
        Session session= null;
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            List<PIN> pinList = (List<PIN>) session.createQuery("from  PIN p where p.activationCode= :code")
                    .setParameter("code", activationCode)
                    .list();
            session.getTransaction().commit();
            session.close();
            if (pinList.size() != 0) {
                return pinList.get(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pin;
    }
}
