package com.xsz.workbook.data;

import androidx.room.RoomDatabase;

public abstract class UserDatabase extends RoomDatabase {
    public abstract UserDao userDao();
}
