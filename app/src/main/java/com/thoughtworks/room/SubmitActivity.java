package com.thoughtworks.room;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.thoughtworks.room.person.Person;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class SubmitActivity extends AppCompatActivity {
    @BindView(R.id.submit_name)
    EditText name;

    @BindView(R.id.submit_gender)
    EditText gender;

    @BindView(R.id.submit_age)
    EditText age;
    @BindView(R.id.submit_button)
    Button submit;

    @OnTextChanged(R.id.submit_name)
    void onNameChange(CharSequence c) {
        String value = c.toString();

        if (value.length() == 0 || value.length() > 8) {
            this.name.setError("Name's length must be between 0-8");
        }
    }

    @OnTextChanged(R.id.submit_age)
    void onAgeChange(CharSequence c) {
        String value = c.toString();
        try {
            int age = Integer.parseInt(value);
            if (age <= 0) {
                this.age.setError("age must be greater than zero");
            }
        } catch (NumberFormatException e) {
            this.age.setError("age can only be number");
        }
    }

    @OnTextChanged(R.id.submit_gender)
    void onGenderChange(CharSequence c) {
        String value = c.toString();
        try {
            int gender = Integer.parseInt(value);
            if (gender != 0 && gender != 1) {
                this.gender.setError("gender can only be 0 or 1");
            }
        } catch (NumberFormatException e) {
            this.gender.setError("gender can only be number");
        }
    }

    @OnClick(R.id.submit_button)
    void submit() {


        Person person = new Person();
        person.name = this.name.getText().toString();
        person.age = Integer.parseInt(this.age.getText().toString());
        person.gender = Integer.parseInt(this.gender.getText().toString());
        Disposable subscribe = MyApplication.getInstance().appDatabase.personDao()
                .insertPerson(person)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        x -> {
                            Toast.makeText(getApplicationContext(), "insert successfully", Toast.LENGTH_SHORT).show();
                        },
                        x -> {
                            Toast.makeText(getApplicationContext(), "fail to insert", Toast.LENGTH_SHORT).show();
                        },
                        () -> {
                        });
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit);

        ButterKnife.bind(this);
    }
}
