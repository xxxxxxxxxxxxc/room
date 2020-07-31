package com.thoughtworks.room;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.thoughtworks.room.person.Person;

import java.util.List;
import java.util.stream.Collectors;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ViewActivity extends AppCompatActivity {

    @BindView(R.id.personList)
    ListView personList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        ButterKnife.bind(this);


        ArrayAdapter<String> stringArrayAdapter = new ArrayAdapter<>(this, R.layout.mypersonitem, R.id.personContent);
        this.personList.setAdapter(stringArrayAdapter);

        Disposable person1 = MyApplication.getInstance().appDatabase.personDao()
                .getAllPerson()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(personList -> {

                    List<String> collect = personList.stream().map(person -> String.format("#%s %s %s %s", person.id, person.name, person.age, person.gender)).collect(Collectors.toList());
                    stringArrayAdapter.clear();
                    stringArrayAdapter.addAll(collect);
                });
    }
}
