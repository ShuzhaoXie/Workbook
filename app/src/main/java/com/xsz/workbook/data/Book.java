package com.xsz.workbook.data;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * 记录会议材料的数据库
 */

@Entity(tableName = "book",
        foreignKeys = @ForeignKey(entity = User.class, parentColumns = "uid", childColumns = "user_id"))
public class Book {

    @PrimaryKey
    private int bid;

    @ColumnInfo(name = "user_id")
    private int uid;
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
    @ColumnInfo(name = "book_summary")
    private String bookSummary;
    @ColumnInfo(name = "book_news")
    private String bookNews;

    public Book() {
        bookName = null;
        location = null;
        bookSummary = null;
        bookNews = null;
        localDateTime = LocalDateTime.now();
        localDate = localDateTime.toLocalDate();
        localTime = localDateTime.toLocalTime();
    }

    public String getBookNews() {
        return bookNews;
    }

    public String getBookSummary() {
        return bookSummary;
    }

    public void setBookNews(String bookNews) {
        this.bookNews = bookNews;
    }

    public void setBookSummary(String bookSummary) {
        this.bookSummary = bookSummary;
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
