package com.xsz.workbook.data;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface BookDao {

    @Query("SELECT * FROM book")
    List<Book> getAll();

    @Query("SELECT * FROM book WHERE bid IN (:bookIds)")
    List<Book> loadAllByIds(int[] bookIds);

    @Query("SELECT * FROM book WHERE book_name LIKE :booknm LIMIT 1")
    Book findByBookName(String booknm);

    @Insert
    void insertAll(Book... books);

    @Delete
    void delete(Book book);
}
