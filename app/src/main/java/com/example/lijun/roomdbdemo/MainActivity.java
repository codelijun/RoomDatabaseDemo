package com.example.lijun.roomdbdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.example.lijun.roomdbdemo.room.User;
import com.example.lijun.roomdbdemo.room.UserDao;
import com.example.lijun.roomdbdemo.room.UserDatabase;

import java.util.List;
import java.util.concurrent.Callable;

import bolts.Continuation;
import bolts.Task;

public class MainActivity extends AppCompatActivity {

    private TextView mFirstUserId;
    private TextView mFirstUserName;
    private TextView mFirstUserAge;
    private TextView mSecondUserId;
    private TextView mSecondUserName;
    private TextView mSecondUserAge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        loadData();
        displayDataFromDatabase();
    }

    private void displayDataFromDatabase() {
        Task.call(new Callable<List<User>>() {
            @Override
            public List<User> call() throws Exception {
                List<User> userDaoList = UserDatabase.getInstance(MainActivity.this).getUserDao().getAll();
                return userDaoList;
            }
        }, Task.BACKGROUND_EXECUTOR).continueWith(new Continuation<List<User>, Object>() {
            @Override
            public Object then(Task<List<User>> task) throws Exception {
                List<User> userDaoList = task.getResult();
                if (true) {
                    Log.d("MainActivity", " then() userDaoList==null? " + (userDaoList == null ? true : "false" + userDaoList.size()));
                }
                if (userDaoList != null) {
                    if (true) {
                        Log.d("MainActivity", " then() " + userDaoList.get(0).userId);
                    }
                    mFirstUserId.setText(String.valueOf(userDaoList.get(0).userId));
                    mFirstUserName.setText(userDaoList.get(0).userName);
                    mFirstUserAge.setText(String.valueOf(userDaoList.get(0).userAge));

                    mSecondUserId.setText(String.valueOf(userDaoList.get(1).userId));
                    mSecondUserName.setText(userDaoList.get(1).userName);
                    mSecondUserAge.setText(String.valueOf(userDaoList.get(1).userAge));
                }
                return null;
            }
        }, Task.UI_THREAD_EXECUTOR);
    }

    private void loadData() {
        Task.call(new Callable<Object>() {
            @Override
            public Object call() throws Exception {
                UserDao userDao = UserDatabase.getInstance(MainActivity.this).getUserDao();
                User firstUser = new User();
                firstUser.userId = 0;
                firstUser.userName = "lijun";
                firstUser.userAge = 24;
                User secondUser = new User();
                secondUser.userId = 1;
                secondUser.userName = "zm";
                secondUser.userAge = 23;
                userDao.insertAll(firstUser, secondUser);
                return null;
            }
        }, Task.BACKGROUND_EXECUTOR);
    }

    private void initView() {
        mFirstUserId = findViewById(R.id.first_user_id);
        mFirstUserName = findViewById(R.id.first_user_name);
        mFirstUserAge = findViewById(R.id.first_user_age);
        mSecondUserId = findViewById(R.id.second_user_id);
        mSecondUserName = findViewById(R.id.second_user_name);
        mSecondUserAge = findViewById(R.id.second_user_age);
    }
}
