package com.xsz.workbook.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface BookDao {

    @Query("SELECT * FROM book")
    LiveData<List<Book>> getAll();

    @Query("SELECT * FROM book WHERE user_id = :userId")
    LiveData<List<Book>> loadBooksByUserId(int userId);

    @Query("SELECT * FROM book WHERE bid = :bookid")
    LiveData<Book> loadBookByIds(int bookid);

    @Query("SELECT * FROM book WHERE bid IN (:bookIds)")
    LiveData<List<Book>> loadAllByIds(int[] bookIds);

    @Query("SELECT * FROM book WHERE book_name LIKE :booknm LIMIT 1")
    Book findByBookName(String booknm);

    @Insert
    void insertAll(Book... books);

    @Insert
    void insertAll(List<Book> books);

    @Delete
    void delete(Book book);
}
