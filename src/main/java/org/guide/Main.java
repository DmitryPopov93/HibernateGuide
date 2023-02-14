package org.guide;

import org.guide.entities.Doctor;
import org.guide.entities.Speciality;
import org.guide.sessionfactory.Factory;
import org.guide.sessionfactory.GetSessionFactory;
import org.hibernate.*;

public class Main {
    public static void main(String[] args) throws InterruptedException {
//  Когда есть hibernate.properties java сходит с ума, и читает всегда настройки из него, но по факту применяет
//  все, как надо в зависемости от выбранного способа

/**------------------------------Получаем SessionFactory--------------------------------*/

//        Factory.INSTANCE.getSessionMappingXML().close();
//        Factory.INSTANCE.getSessionAddAnnotated().close();
//        Factory.INSTANCE.getSession(GetSessionFactory::getSessionCustom);
//        Factory.INSTANCE.getSession(GetSessionFactory::getSessionXmlAuto);
//        Factory.INSTANCE.getSession(GetSessionFactory::getSessionCustomProp);

/**-------------------------getCurrentSessionTest() and get(), load(), find()------------------------------*/

//        Session currentSession = Factory.INSTANCE.getCurrentSessionTest();

//  Метод get() в качестве второго параметра принимает ID (Primary Key) объекта, который ему нужно вернуть.
//  Затем загружает этот объект из базы и возвращает его.

//        System.out.println(currentSession.getStatistics() + "------Выполняем первый запрос к базе данных---------");
//        Transaction transaction = currentSession.beginTransaction();
//        Doctor doctor1 = currentSession.get(Doctor.class, 3);
//
//        // Мы не закрыли транзакцию, следовательно в нашем контексте осталась сущность доктора с ID = 3,
//        // поэтому при последующем запросе этого же доктора, Select в базу выполнен не будет, а произойдет подгрузка
//        // объекта из кэша первого уровня (Смотрим лог)
//
//        System.out.println(currentSession.getStatistics() + "------Здесь должен быть второй запрос к базе данных-----"
//        + " Сессия открыта: "+ currentSession.isOpen());
//        Doctor doctor2 = currentSession.get(Doctor.class, 3);
//
//        System.out.println(currentSession.getStatistics() + "------Здесь должен быть  запрос На специальность Doctor c ID = 3-----"
//                + " Сессия открыта: "+ currentSession.isOpen());
//        Speciality speciality = currentSession.get(Speciality.class, 1);
//        System.out.println(currentSession.getStatistics() + " Статистика после запроса специальности");
//        // Запросим еще 1го доктора той же специальности
//        System.out.println(currentSession.getStatistics() + "------Здесь должен быть запрос на доктора уже существующей специальности-----"
//                + " Сессия открыта: "+ currentSession.isOpen());
//        doctor2 = currentSession.get(Doctor.class, 1);
//        System.out.println(currentSession.getStatistics() + " Статистика после всех запросов");
//        transaction.commit();
//        System.out.println(currentSession.getStatistics() +"Сессия открыта: "+ currentSession.isOpen());

//        // Раньше при вызове метода get, можно было не открывать транзакцию самому, Hibernate это делал за нас
//        // Это убрали. После Commit в 39 строке Hibernate закрыл сессию и поэтому нужно открыть новую.
//        // После закрытия транзакции - > закрылась сессия - > Следовательно был удален и контекст (кэш 1го уровня)
//        currentSession = Factory.INSTANCE.getCurrentSessionTest();
//        transaction = currentSession.beginTransaction();
//        System.out.println("-----------------Выполняем третий запрос к базе данных---------------------");
//        Doctor doctor3 = currentSession.get(Doctor.class, 3);
//        transaction.commit();
//
//        // Пердадим не существующий ID
//        Session currentSession = Factory.INSTANCE.getCurrentSessionTest();
//        Transaction transaction = currentSession.beginTransaction();
//        System.out.println("---------------Выполняем запрос к базе данных с неверным ID, через get()---------------");
//        Doctor doctor4 = currentSession.get(Doctor.class, 4);
//        System.out.println(currentSession.getStatistics() + " Статистика после запроса не существующего доктора");
//        transaction.commit();
//        System.out.println("doctor4 = " + doctor4);

//  Метод find() достался интерфейсу Session от стандарта JPA. Этот метод работает точно так же, как и метод get().
//  Если объект по переданному ключу не был найден, то метод просто вернет null.

//        currentSession = Factory.INSTANCE.getCurrentSessionTest();
//        transaction = currentSession.beginTransaction();
//        System.out.println("---------------Выполняем запрос к базе данных с неверным ID, через find()---------------");
//        Doctor doctor5 = currentSession.find(Doctor.class, 5);
//        System.out.println(currentSession.getStatistics() + " Статистика после запроса не существующего доктора");
//        transaction.commit();
//        System.out.println("doctor5 = " + doctor5);

////  Метод load(), возвращает не реальный объект, а proxy: виртуальную заглушку. При использовании метода load()
////  не происходит проверка, есть ли такая запись в базе. Вместо этого Hibernate сразу создает proxy-объект
////  с переданным ID и возвращает его. Вся работа с базой данных будет происходить при вызове методов proxy-объекта.
////  Дебагер заставляет load() сразу подкачивать объект
//
//        Session currentSession = Factory.INSTANCE.getCurrentSessionTest();
//        Transaction transaction = currentSession.beginTransaction();
//        System.out.println("---------------Выполняем запрос к базе данных  через load()---------------");
//        Doctor doctor6 = currentSession.load(Doctor.class, 2);
//        System.out.println("----------------После load()--------------------");
//        System.out.println(currentSession.getStatistics() +"\n"
//                + "---------------Обращаемся к прокси, что бы узнать имя доктора------------------");
//        doctor6.getName();
//        System.out.println(currentSession.getStatistics() +"\n"
//                + "---------------после обращения------------------");
//        transaction.commit();
//
//        // Пердадим не существующий ID
//        currentSession = Factory.INSTANCE.getCurrentSessionTest();
//        transaction = currentSession.beginTransaction();
//        System.out.println("---------------Выполняем запрос к базе данных с неверным ID, через load()---------------");
//        Doctor doctor7 = currentSession.load(Doctor.class, 4);
//
//        System.out.println(currentSession.getStatistics() + "------------------После метода load()--------------------");
//        try {
//            doctor7.getName();
//        } catch (ObjectNotFoundException ex) {
//            System.out.println("-----Вызов имени у несуществуещего доктора привел к ошибке-------\n" + ex.getMessage());}
//
//        transaction.commit();
//
//        // Вызов объекта после закрытия транзакции у метода load()
//        currentSession = Factory.INSTANCE.getCurrentSessionTest();
//        transaction = currentSession.beginTransaction();
//        System.out.println("---------------Выполняем запрос к базе данных через load()---------------");
//        Doctor doctor8 = currentSession.load(Doctor.class, 1);
//        System.out.println("--------------------------После метода load()----------------------------\n"
//        + currentSession.getStatistics());
//        // doctor8.toString(); приведет к обращению за объектом в базу
//        transaction.commit();
//
//        try {
//            doctor8.getName();
//        } catch (LazyInitializationException ex) {
//            System.out.println("Если в рамках транзакции, после load() мы к прокси не обращаемся" +
//                    " объект не подкачевается из базы, и обращение к нему после закрытия транзакции приведет к ошибке");}

/**----------------------------------------openSession()---------------------------------------------*/

//        Session openSession = Factory.INSTANCE.openSessionTest();
//        Transaction transaction = openSession.beginTransaction();
//
//        System.out.println(openSession.getStatistics() + "------Выполняем первый запрос к базе данных---------");
//        Doctor doctor1 = openSession.get(Doctor.class, 3);
//
//        System.out.println(openSession.getStatistics() + "------Здесь должен быть второй запрос к базе данных-----"
//        + " Сессия открыта: "+ openSession.isOpen());
//        Doctor doctor2 = openSession.get(Doctor.class, 3);
//        transaction.commit();
//        System.out.println("После закрытия транзакции " + openSession.getStatistics() +"Сессия открыта: "+ openSession.isOpen());
//
//        //Мы закрыли транзакцию, но сессия продолжает быть открыта, попробуем запросить того же самого доктора
//        // в рамках этой же сессии, но уже другой транзакции
//        transaction = openSession.beginTransaction();
//        System.out.println(openSession.getStatistics() + "------Здесь должен быть третий запрос к базе данных-----"
//                + " Сессия открыта: "+ openSession.isOpen());
//        Doctor doctor3 = openSession.get(Doctor.class, 3);
//
//        transaction.commit();
//        openSession.close();
//        System.out.println(openSession.getStatistics() +"Сессия открыта: "+ openSession.isOpen());
//
//        // Как мы види в рамка сессии открытой методом openSession(), контекст(кэш 1го уровня)
//        // прикрепляется к самой сессии и не очищается после завершения транзакции.

/**-------------------------------Контест сохранения в области транзакции--------------------------------------------*/

//        // Получим объект doctor для дальнейших манипуляций
//        Session openSession = Factory.INSTANCE.openSessionTest();
//        Transaction transaction = openSession.beginTransaction();
//        Doctor doctor = openSession.get(Doctor.class, 3);
//        transaction.commit();
//        System.out.println("doctor до изменений " + doctor);
//
//        //doctor по прежнему прикремплен к контексту сессии, изменим его, так, что бы наш обьект в Java отличался
//        // от сущности находящейся в контексте.
//
//        transaction = openSession.beginTransaction();
//        doctor.setName("Ivanova Valentina Petrovna1111111");
//        System.out.println("doctor после изменений " + doctor);
//
//        transaction.commit();
//        System.out.println("doctor после закрытия транзакции, при открытой сессии " + doctor);
//        openSession.close();
//        System.out.println("doctor после закрытия сессии " + doctor);
//
//        // Изменения произошли в рамках транзакции, поэтому Hibernate сам произвел update в базу данных из контекста


//        // Понаблюдаем за состоянием объекта в рамках контекста и ссылками на него
//        Session openSession = Factory.INSTANCE.openSessionTest();
//        Transaction transaction = openSession.beginTransaction();
//        Doctor doctor1 = openSession.get(Doctor.class, 3);
//        transaction.commit();
//        // doctor1 привязан к контексту сессии ID = 3
//        System.out.println("doctor1 до изменений" + doctor1);
//
//        // между транзакциями мы будем изменять наши сущности
//
//        transaction = openSession.beginTransaction();
//
//        // Запросим doctor2 с ID = 3 и изменим его, что бы автоматически произошел update в базу
//        Doctor doctor2 = openSession.get(Doctor.class, 3);
//        doctor2.setName("Ivanova Valentina Petrovna");
//        doctor2.setName("Ivanova Valentina Petrovna");
//        System.out.println("doctor2 после изменения " + doctor2);
//        System.out.println("doctor1 внутри другой транзакции, после изменения doctor2" + doctor1);
//
//        transaction.commit();
//
//        System.out.println("doctor1 после завершения транзакции, при открытой сессии " + doctor1);
//        openSession.close();
//        System.out.println((doctor2==doctor1) + " doctor2 = doctor1");
//
//        System.out.println(doctor2.equals(doctor1) + " = doctor1 = doctor2");


//        // Поведение сущности в контексте, при изменениях в базе
//        Session openSession = Factory.INSTANCE.openSessionTest();
//        Transaction transaction = openSession.beginTransaction();
//        Doctor doctor = openSession.get(Doctor.class, 3);
//        transaction.commit();
//        // doctor привязан к контексту сессии ID = 3
//        System.out.println("doctor до изменений" + doctor);
//
//        Thread.sleep(15000);
//        // Откроем новую транзакцию, что бы в ее рамках сработал контекст сохранения, при изменениях в самой базе
//        transaction = openSession.beginTransaction();
//
//        //Если не делать запрос, к доктору с ID = 3 в рамках контекста, но изменить его в базе, изменений в контексте не будет
//        Doctor doctor2 = openSession.get(Doctor.class, 3);
//        System.out.println("doctor2 после изменений" + doctor2);
//
//        System.out.println("doctor внутри другой транзакции, после изменения Доктора с ID = 3 в базе" + doctor);
//        transaction.commit();
//        System.out.println("doctor после закрытия транзакции, сессия открыта" + doctor);
//        openSession.close();
//        System.out.println("doctor после закрытия сессии" + doctor);

/**----------------------------------------openStatelessSession()---------------------------------------------*/

        // StatelessSession() не поддерживает кэширование(контекст), нет грязного состояния объектов,
        // отложенной синхронизации, он не включен в JPA, и является функционалом Hibernate, который позволяет нам
        // контролировать все процессы самостоятельно.

//        final StatelessSession statelessSession =  Factory.INSTANCE.openStatelessSessionTest();
//        statelessSession.getTransaction().begin();

//        final Speciality speciality = Speciality.builder().name("newSpeciality").build();
//        System.out.println("Добавляем созданную Speciality в базу");
//        statelessSession.insert(speciality);
//
//        speciality.setName("Lor");
//        System.out.println("Вносим изменения Speciality в базу");
//        statelessSession.update(speciality);
//
//        // Внесем изменения еще раз, что бы показать, что не каких действий Hibernate самостоятельно выполянть уже не будет
//        speciality.setName("Error");

        // Поучаем LazyInitializationException при попытке вытащить специальность из доктора
//        Doctor doctor = statelessSession.get(Doctor.class, 3);
//        System.out.println(doctor);
//        Speciality speciality = doctor.getSpeciality();
//        System.out.println(speciality);

//        Speciality speciality2 = statelessSession.get(Speciality.class, 1);
//        System.out.println(speciality2);
//        statelessSession.getTransaction().commit();
//
//        statelessSession.close();
    }
}
