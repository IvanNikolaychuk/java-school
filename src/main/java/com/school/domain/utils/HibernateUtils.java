package com.school.domain.utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtils {
    private static SessionFactory factory = new Configuration()
            .configure("hibernate/hibernate.cfg.xml")
            .buildSessionFactory();

    private HibernateUtils() {}

    public static SessionFactory getInstance() {
        return factory;
    }


    public static Session openSession() {
        return HibernateUtils.getInstance().openSession();
    }
}
