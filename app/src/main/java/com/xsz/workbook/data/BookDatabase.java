package com.xsz.workbook.data;

import androidx.room.RoomDatabase;

public abstract class BookDatabase extends RoomDatabase {
    public abstract BookDao bookDao();
}
