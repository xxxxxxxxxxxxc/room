package com.thoughtworks.room;

import com.thoughtworks.room.person.Person;

import java.util.List;

import io.reactivex.Maybe;
import io.reactivex.Single;

public class LocalDataSource {
    public static Maybe<Long> insertPerson(Person person) {
        return MyApplication.getInstance().appDatabase.personDao().insertPerson(person);
    }

    public static Single<List<Person>> getAllPerson() {
        return MyApplication.getInstance().appDatabase.personDao().getAllPerson();

    }
}
