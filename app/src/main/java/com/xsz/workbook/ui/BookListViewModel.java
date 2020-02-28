package com.xsz.workbook.ui;

import android.app.Application;
import android.provider.ContactsContract;
import android.text.TextUtils;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.xsz.workbook.BasicApp;
import com.xsz.workbook.DataRepository;
import com.xsz.workbook.data.Book;

import java.util.List;
import java.util.function.Function;

public class BookListViewModel extends AndroidViewModel {
    // TODO: Implement the ViewModel

    private static final String QUERY_KEY = "QUERY";
    private final SavedStateHandle mSavedStateHandler;
    private final DataRepository mRepository;
    private final LiveData<List<Book>> mBooks;

    public BookListViewModel(@NonNull Application application, @NonNull SavedStateHandle savedStateHandle) {
        super(application);
        mSavedStateHandler = savedStateHandle;

        mRepository = ((BasicApp) application).getRepository();

        mBooks = Transformations.switchMap(
                savedStateHandle.getLiveData("QUERY", null),
                (Function<CharSequence, LiveData<List<Book>>>) query -> {
                    if (TextUtils.isEmpty(query)) {
                        return mRepository.getBooks();
                    }
                    return mRepository.searchProducts("*" + query + "*");
                });
    }

    public void setQuery(CharSequence query) {
        mSavedStateHandler.set(QUERY_KEY, query);
    }

    public LiveData<List<Book>> getBooks() {
        return mBooks;
    }
}
