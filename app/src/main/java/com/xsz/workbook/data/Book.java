package com.xsz.workbook.data;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * 记录会议材料的数据库
 */

@Entity
public class Book {

    @PrimaryKey
    public int bid;

    @ColumnInfo(name = "book_name")
    private String bookName;
    @ColumnInfo(name = "book_location")
    private String location;
    @ColumnInfo(name = "book_local_date")
    private LocalDate localDate;
    @ColumnInfo(name = "book_local_time")
    private LocalTime localTime;
    @ColumnInfo(name = "book_local_date_time")
    private LocalDateTime localDateTime;

    public Book() {
        bookName = null;
        location = null;
        localDateTime = LocalDateTime.now();
        localDate = localDateTime.toLocalDate();
        localTime = localDateTime.toLocalTime();
    }

    public void setLocalDate(LocalDate localdate) {
        this.localDate = localdate;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    public LocalTime getLocalTime() {
        return localTime;
    }

    public void setLocalTime(LocalTime localTime) {
        this.localTime = localTime;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }
}
