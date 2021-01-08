package edu.pe.idat.bibliotecarikkazo.ui.libros;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class LibrosViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public LibrosViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}