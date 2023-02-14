package org.guide.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Cascade;

import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString
//Эта аннотация перед классом укажет Hibernate, что это не просто класс, а специальный класс, объекты которого нужно хранить в базе данных
@Entity
//С ее помощью можно задать имя таблицы в базе, с которой будет связан данный класс
//Если имя класса и имя таблицы совпадают, то аннотацию @Table можно не указывать
@Table(name = "Specialties")

public class Speciality {
    //Задает первичный ключ для таблицы
    @Id
    //Маппинг колонок таблицы на поля класса
    @Column(name = "ID")
    //Авто генерация ID объектов при добавлении их в базу
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    //Все значения поля должны быть уникальны, Максимальная длина (для строк) = 100
    @Column(name = "NAME", unique=true, length=100)
    //Аннотация @NotEmpty использует реализацию класса@NotNull 'isValid() и дополнительно проверяет, что размер / длина
    // предоставленного объекта больше нуля.
    @NotEmpty(message = "Name shouldn't be empty")
    private String name;

    //С помощью аннотации мы указали, что объект Speciality может хранить у себя много объектов Doctor.
    @OneToMany(mappedBy = "speciality", fetch = FetchType.LAZY)
    //означает, что если мы удаляем в базе родительский объект, то это же нужно сделать и с его зависимыми объектами
    @Cascade(org.hibernate.annotations.CascadeType.REMOVE)
    List<Doctor> doctors = new ArrayList<>();
}
