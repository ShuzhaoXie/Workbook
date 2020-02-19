package com.xsz.workbook.data;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.xsz.workbook.AppExecutors;

import java.util.List;

@Database(entities = {Book.class, User.class}, version = 2)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase sInstance;

    public abstract BookDao bookDao();

    public abstract UserDao userDao();

    private final MutableLiveData<Boolean> mIsDatabaseCreated = new MutableLiveData<>();

    private static String DATABASE_NAME = "book_and_user";

    public static AppDatabase getInstance(final Context context, final AppExecutors executors) {
        if (sInstance == null) {
            synchronized (AppDatabase.class) {
                if(sInstance == null) {
                    sInstance = buildDatabase(context.getApplicationContext(), executors);
                    sInstance.updateDatabaseCreated(context.getApplicationContext());
                }
            }
        }
        return sInstance;
    }

    private static AppDatabase buildDatabase(final Context appContext,
                                             final AppExecutors executors) {
        return Room.databaseBuilder(appContext, AppDatabase.class, DATABASE_NAME)
                .build();
    }

    private void updateDatabaseCreated(final Context context) {
        if (context.getDatabasePath(DATABASE_NAME).exists()) {

        }
    }

    private void setDatabaseCreatd() {
        mIsDatabaseCreated.postValue(true);
    }

    private static void insertData(final AppDatabase database, final List<Book> books,
                                   final List<User> users) {
        database.runInTransaction(new Runnable() {
            @Override
            public void run() {
                database.bookDao().insertAll(books);
                database.userDao().insertAll(users);
            }
        });
    }

    public LiveData<Boolean> getDatabaseCreated() {
        return mIsDatabaseCreated;
    }

}
