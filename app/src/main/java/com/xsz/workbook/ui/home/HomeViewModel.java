package com.xsz.workbook.ui.home;

import android.app.Application;
import android.provider.ContactsContract;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.xsz.workbook.BasicApp;
import com.xsz.workbook.DataRepository;
import com.xsz.workbook.data.Book;

import java.util.List;

public class HomeViewModel extends ViewModel {

    private final LiveData<List<Book>> mObservableBooks;

    private HomeViewModel(@NonNull Application application, DataRepository repository,
                         final int uid) {
        mObservableBooks = repository.loadBookByUserId(uid);
    }

    public LiveData<List<Book>> getText() {
        return mObservableBooks;
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
            return (T) new HomeViewModel(mApplication, mRepository, mUserId);
        }
    }
}