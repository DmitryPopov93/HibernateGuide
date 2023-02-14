package org.guide.sessionfactory;

import org.guide.entities.Doctor;
import org.guide.entities.Speciality;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import java.io.IOException;
import java.util.Properties;
import java.util.Scanner;

public class GetSessionFactory {
    //Способ первый — используем файл hibernate.properties.
    static SessionFactory getSessionPropAuto (){
        SessionFactory sessionFactory = new Configuration()
                .addAnnotatedClass(Speciality.class)
                .addAnnotatedClass(Doctor.class)
                .buildSessionFactory();
        return sessionFactory;
    }

    // Способ второй — конфигурация с помощью hibernate.cfg.xml.
    public static  SessionFactory getSessionXmlAuto (){
        SessionFactory sessionFactory = new Configuration().configure()
                .addAnnotatedClass(Speciality.class)
                .addAnnotatedClass(Doctor.class)
                .buildSessionFactory();
        return sessionFactory;
    }

    //Способ третий — задаем файл с конфигурацией вручную.
    //Иногда в процессе разработки возникает необходимость переключиться на тестовую базу или поменять какие-то другие
    // настройки для работы с базой, для этого можно задать файл конфигурации вручную:
    static  SessionFactory getSessionXmlCustom (){
        SessionFactory sessionFactory = new Configuration().configure("hibernate-hand.cfg.xml").buildSessionFactory();
        return sessionFactory;
    }

    //Способ четвертый — используем кастомный файл hibernate.properties
     public static  SessionFactory getSessionCustomProp (){
        ClassLoader classLoader = GetSessionFactory.class.getClassLoader();
        Properties properties = new Properties();
        try {
            properties.load(classLoader.getResourceAsStream("hibernate-hand.properties"));
        } catch (IOException e) {
            System.out.println("Что то пошло не так с ClassLoader");;
        }
        SessionFactory sessionFactory = new Configuration()
                .addAnnotatedClass(Speciality.class)
                .addAnnotatedClass(Doctor.class)
                .addProperties(properties).buildSessionFactory();
        return sessionFactory;
    }

    //Способ пятый — можно просто зашить все нужные параметры прямо в код
    public static  SessionFactory getSessionCustom (){
        Properties properties = new Properties();
        properties.put(Environment.DRIVER, "org.postgresql.Driver");
        properties.put(Environment.URL, "jdbc:postgresql://localhost:5432/Polyclinic");
        properties.put(Environment.USER, "postgres");
        properties.put(Environment.PASS, "299681");

        properties.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
        properties.put(Environment.HBM2DDL_AUTO, "update");
        properties.put(Environment.SHOW_SQL, "true");
        properties.put(Environment.FORMAT_SQL, "true");
        properties.put(Environment.DIALECT, "org.hibernate.dialect.PostgreSQLDialect");

        SessionFactory sessionFactory = new Configuration()
                .addAnnotatedClass(Speciality.class)
                .addAnnotatedClass(Doctor.class)
                .setProperties(properties).buildSessionFactory();
        return sessionFactory;
    }

}
