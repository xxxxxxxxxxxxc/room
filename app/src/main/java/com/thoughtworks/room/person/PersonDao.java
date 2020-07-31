package com.thoughtworks.room.person;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import io.reactivex.Maybe;
import io.reactivex.Single;

@Dao
public interface PersonDao {

    @Query("SELECT * FROM person")
    Single<List<Person>> getAllPerson();


    @Insert
    Maybe<Long> insertPerson(Person person);
}
