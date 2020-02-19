package com.xsz.workbook.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface UserDao {

    @Query("SELECT * FROM user")
    LiveData<List<User>> selectAll();

    @Query("SELECT user_password FROM user WHERE username = :unm LIMIT 1")
    LiveData<String> findUserPassword(String unm);

    @Query("SELECT COUNT(*) FROM user")
    int totalNumberOfUsers();

    @Query("SELECT username FROM user WHERE uid = :userid")
    String loadUsernameByUid(int userid);

    @Insert
    void insertAll(User... users);

    @Insert
    void insertAll(List<User> users);
}
