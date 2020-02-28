package com.xsz.workbook.ui.book;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.xsz.workbook.BasicApp;
import com.xsz.workbook.DataRepository;
import com.xsz.workbook.data.Book;
import com.xsz.workbook.ui.home.HomeViewModel;

public class BookViewModel extends AndroidViewModel {
    // TODO: Implement the ViewModel
    private final LiveData<Book> mObservableBook;

    private BookViewModel(@NonNull Application application, DataRepository repository,
                          final int bookId) {
        super(application);
        mObservableBook = repository.loadBookByBookId(bookId);
    }

    public LiveData<Book> getText() {
        return mObservableBook;
    }

    public static class Factory extends ViewModelProvider.NewInstanceFactory {

        @NonNull
        private final Application mApplication;

        private final int mUserId;

        private final DataRepository mRepository;

        public Factory(@NonNull Application application, int userId) {
            mApplication = application;
            mUserId = userId;
            mRepository = ((BasicApp) application).getRepository();
        }

        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            return (T) new BookViewModel(mApplication, mRepository, mUserId);
        }
    }
}
