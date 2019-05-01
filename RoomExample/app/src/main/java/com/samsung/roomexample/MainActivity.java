package com.samsung.roomexample;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button btn_click;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_click = (Button) findViewById(R.id.btn_click);
        btn_click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FillAsync(AppDatabase.getAppDatabase(MainActivity.this));
            }
        });

    }


    private static final String TAG = MainActivity.class.getName();

    public static void FillAsync(@NonNull final AppDatabase db) {
        FillDbAsync task = new FillDbAsync(db);
        task.execute();
    }

    public static void FillSync(@NonNull final AppDatabase db) {
        FillWithTestData(db);
    }

    private static User addUser(final AppDatabase db, User user) {
        db.userDao().insertAll(user);
        return user;
    }

    private static void FillWithTestData(AppDatabase db) {
        User user = new User();
        user.setFirstName("Ankur");
        user.setLastName("Lal");
        user.setAge(25);
        addUser(db, user);

        List<User> userList = db.userDao().getAll();
        Log.d(TAG, "Rows Count: " + userList.size());
    }

    private static class FillDbAsync extends AsyncTask<Void, Void, Void> {

        private final AppDatabase mDb;

        FillDbAsync(AppDatabase db) {
            mDb = db;
        }

        @Override
        protected Void doInBackground(final Void... params) {
            FillWithTestData(mDb);
            return null;
        }

    }


    @Override
    protected void onDestroy() {
        AppDatabase.destroyInstance();
        super.onDestroy();
    }
}
