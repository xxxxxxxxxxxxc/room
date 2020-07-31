package com.thoughtworks.room.person;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Person {

    @PrimaryKey
    public Long id;

    public String name;
    public int gender;
    public int age;
}
