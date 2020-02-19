package com.xsz.workbook.util;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.xsz.workbook.MainActivity;
import com.xsz.workbook.R;
import com.xsz.workbook.data.Book;
import com.xsz.workbook.data.BookDatabase;

public class NewBookActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText newBookName, newBookLocation;
    private Button newBookSummaryBtn, newBookNewsBtn, addBookImagesBtn, completeNewBookBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_book);
        init();
    }

    private void init() {
        newBookName = findViewById(R.id.newBookName);
        newBookLocation = findViewById(R.id.newBookLocation);
        completeNewBookBtn = findViewById(R.id.completeNewBook);
        completeNewBookBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.completeNewBook:
                completeNewBook();
                break;
            default:
                break;
        }
    }

    private boolean completeNewBook() {
        String bookName = newBookName.getText().toString();
        String bookLocation = newBookLocation.getText().toString();
        if (bookName.length() == 0) {
            Toast.makeText(NewBookActivity.this, "活动名称不能为空", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (bookLocation.length() == 0) {
            Toast.makeText(NewBookActivity.this, "活动地点不能为空", Toast.LENGTH_SHORT).show();
            return false;
        }
        BookDatabase bookdb = Room.databaseBuilder(getApplicationContext(), BookDatabase.class, "book").build();
        Book book = new Book();
        book.setBookName(bookName);
        book.setLocation(bookLocation);
        book.setBookNews("");
        book.setBookSummary("");
        bookdb.bookDao().insertAll(book);
        return true;
    }
}
