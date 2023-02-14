package org.guide.sessionfactory;

import org.guide.entities.Doctor;
import org.guide.entities.Speciality;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.StatelessSession;
import org.hibernate.cfg.Configuration;

import java.util.function.Supplier;

public class Factory {

    //Нужен нам для связывания с множествами методов возвращающих SessionFactory из GetSessionFactory
    //Если он будет приватный словим ошибку: имеет закрытый доступ в 'org.guide.sessionFactory.Factory'
    public interface GetSessionInterface {
        SessionFactory getSessionFactory ();
    }

    private Factory() {}
    public static final Factory INSTANCE = new Factory();
    private static SessionFactory sessionFactory1 = null;
    private static SessionFactory sessionFactory2 = null;
    private static SessionFactory sessionFactory3 = null;


    /**-------Указываем, где искать Entity-классы--------*/

    //Способ первый. В hibernate-hand.cfg.xml добавить строку вида: < mapping class="полное-имя класса" />
    private SessionFactory getSessionFactoryMappingXML(GetSessionInterface getSessionInterface) {
        if (sessionFactory1 == null) {
            sessionFactory1 = getSessionInterface.getSessionFactory();
        }
        return sessionFactory1;
    }

    public Session getSessionMappingXML() {
        return getSessionFactoryMappingXML(GetSessionFactory::getSessionXmlCustom).getCurrentSession();
    }

    //Способ второй. Вызвать у объекта Configuration метод addAnnotatedClass(). Вызванно в классе GetSessionFactory
    private SessionFactory getSessionFactoryAddAnnotated(GetSessionInterface getSessionInterface) {
        if (sessionFactory2 == null) {
            sessionFactory2 = getSessionInterface.getSessionFactory();
        }
        return sessionFactory2;
    }
    public Session getSessionAddAnnotated() {
        return getSessionFactoryAddAnnotated(GetSessionFactory::getSessionPropAuto).getCurrentSession();
    }

    //Способ третий. Если классов много, то можно добавлять их целыми пакетами. УСТАРЕЛО! после 5го Хибернейта не фурычит.
//    private SessionFactory getSessionFactoryAddPackage(GetSessionInterface getSessionInterface) {
//        if (sessionFactory3 == null) {
//            sessionFactory3 = getSessionInterface.getSessionFactory();
//        }
//        return sessionFactory3;
//    }
//    private Session getSessionAddPackage() {
//        return getSessionFactoryAddAnnotated(GetSessionFactory::getSessionXmlAuto).getCurrentSession();
//    }

    //Для ручного вызова других методов
    private SessionFactory getSessionFactory(GetSessionInterface getSessionInterface) {
        if (sessionFactory3 == null) {
            sessionFactory3 = getSessionInterface.getSessionFactory();
        }
        return sessionFactory3;
    }
    public Session getSession(GetSessionInterface getSessionInterface) {
        return getSessionFactoryAddAnnotated(getSessionInterface).getCurrentSession();
    }

    /**-----------------------------------openStatelessSession()------------------------------------------*/

    public Session getCurrentSessionTest() {
        return getSessionFactoryMappingXML(GetSessionFactory::getSessionXmlCustom).getCurrentSession();
    }

    /**-----------------------------------openSession()------------------------------------------*/

    public Session openSessionTest() {
        return getSessionFactoryMappingXML(GetSessionFactory::getSessionXmlCustom).openSession();
    }

    /**-----------------------------------openSession()------------------------------------------*/

    public StatelessSession openStatelessSessionTest() {
        return getSessionFactoryMappingXML(GetSessionFactory::getSessionXmlCustom).openStatelessSession();
    }

}
