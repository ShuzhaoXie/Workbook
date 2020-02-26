package com.xsz.workbook;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;

import com.xsz.workbook.data.AppDatabase;
import com.xsz.workbook.data.Book;
import com.xsz.workbook.data.User;

import java.util.List;

public class DataRepository {
    private static DataRepository sInstance;

    private final AppDatabase mDatabase;

    private MediatorLiveData<List<Book>> mObservableBooks;

    private DataRepository(final AppDatabase database) {
        mDatabase = database;
        mObservableBooks = new MediatorLiveData<>();

        mObservableBooks.addSource(mDatabase.bookDao().getAll(),
                Observer<Book> {
            if (mDatabase.getDatabaseCreated().getValue() != null) {
                mObservableBooks.postValue(books);
            }
        });

    }

    public static DataRepository getInstance(final AppDatabase database) {
        if (sInstance == null) {
            synchronized (DataRepository.class) {
                if (sInstance == null) {
                    sInstance = new DataRepository(database);
                }
            }
        }
        return sInstance;
    }

    public LiveData<List<Book>> getBooks() {
        return mObservableBooks;
    }

    public LiveData<Book> loadBook(final int bookId) {
        return mDatabase.bookDao().loadBookByIds(bookId);
    }

    public String loadUserPasswordByUsername(final String username) {
        return mDatabase.userDao().findUserPassword(username);
    }

    public LiveData<List<Book>> loadBookByUserId(final int userId) {
        return mDatabase.bookDao().loadBooksByUserId(userId);
    }
    //要用再加吧
}
