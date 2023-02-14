package org.guide.entities;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "Doctors")

public class Doctor {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "FULL_NAME")
    private String name;

    //С помощью аннотации указываем, что много объектов Doctor могут ссылаться на один объект типа Speciality.
    @ManyToOne
    //Также с помощью аннотации  мы указали, в какой колонке нашей таблицы хранится id объекта Speciality.
    @JoinColumn(name = "SPECIALITY")
    Speciality speciality;

    @Override
    public String toString() {
        return "Doctor{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
