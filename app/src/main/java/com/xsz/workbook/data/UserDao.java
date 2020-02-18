package com.xsz.workbook.data;

import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

@Dao
public interface UserDao {

    @Query("SELECT * FROM user")
    List<User> selectAll();

    @Query("SELECT user_password FROM user WHERE user_name = :unm LIMIT 1")
    String findUserPassword(String unm);

}
