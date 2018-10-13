package com.bereda.mateusz;

import com.bereda.mateusz.model.Car;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.Date;

public class Main {
    private static SessionFactory factory;
    public static void main(String[] args) {
        factory = new Configuration().configure().buildSessionFactory();

        Car car = new Car();
        car.setBrand("BMW");
        car.setModel("3");
        car.setCapacity(3.3);
        car.setPower(5);
        car.setDate(new Date());

        Main.persistCar(car);
    }

    public static void persistCar(Car car) {
        Session session = Main.factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(car);
            tx.commit();
        } catch (HibernateException e) {
            if(tx != null) tx.rollback();
        } finally {
            session.close();
        }
    }
}
