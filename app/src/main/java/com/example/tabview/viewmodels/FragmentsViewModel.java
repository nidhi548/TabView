package com.example.tabview.viewmodels;

import android.content.Context;
import android.graphics.PorterDuff;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.core.content.ContextCompat;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.tabview.R;
import com.example.tabview.models.Contacts;
import com.example.tabview.repository.ContactsDatabase;

import java.util.List;

import io.reactivex.CompletableObserver;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class FragmentsViewModel extends ViewModel {
    ContactsDatabase contactsDatabase;
    Toast toast;

    public static MutableLiveData<String> queryString = new MutableLiveData<>();

    public static void setQueryString(String query)
    {
        queryString.setValue(query);
    }

    public LiveData<String> getQueryString()
    {
        return queryString;
    }

    public void addContacts(Contacts contactsContacts, Context context) {
        contactsDatabase = ContactsDatabase.getDbInstance(context);
        ContactsDatabase.ContactsDao().addContacts(Contacts)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(@io.reactivex.annotations.NonNull Disposable d) {
                        Log.d("TAG", "Inside onSubscribe of addContacts in ViewModel");
                    }

                    @Override
                    public void onComplete() {
                        Log.d("TAG", "Inside onComplete of addContacts in ViewModel");
                        successToast("Contacts added successfully", context);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.d("TAG", "Inside onError of addContacts in ViewModel." + e.getMessage());
                        failureToast(e.getMessage(), context);
                    }
                });
    }

    public LiveData<List<Contacts>> getAllContacts(Context context) {
        ContactsDatabase = ContactsDatabase.getInstance(context);
        return ContactsDatabase.ContactsDao().getAllContacts();
    }

    public LiveData<List<Contacts>> queryAllContacts(Context context, String query) {
        ContactsDatabase = ContactsDatabase.getInstance(context);
        return ContactsDatabase.ContactsDao().queryAllContacts(query);
    }

    public void deleteContacts(Contacts Contacts, Context context) {
        ContactsDatabase = ContactsDatabase.getInstance(context);
        ContactsDatabase.ContactsDao().deleteContacts(Contacts)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        Log.d("TAG", "Inside onSubscribe of deleteContacts in ViewModel");
                    }

                    @Override
                    public void onComplete() {
                        Log.d("TAG", "Inside onComplete of deleteContacts in ViewModel");
                        successToast("Contacts data removed successfully", context);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.d("TAG", "Inside onError of deleteContacts in ViewModel");
                        failureToast(e.getMessage(), context);
                    }
                });
    }

    public void updateContacts(Contacts Contacts, Context context) {
        ContactsDatabase = ContactsDatabase.getInstance(context);
        ContactsDatabase.ContactsDao().updateContacts(Contacts)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        Log.d("TAG", "Inside onSubscribe of updateContacts in ViewModel");
                    }

                    @Override
                    public void onComplete() {
                        Log.d("TAG", "Inside onComplete of updateContacts in ViewModel");
                        successToast("Contacts Data updated successfully", context);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.d("TAG", "Inside onError of updateContacts in ViewModel");
                        failureToast(e.getMessage(), context);
                    }
                });
    }


    private void successToast(String message, Context context) {

        if (toast != null)
            toast.cancel();

        toast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
        View view = toast.getView();

        view.getBackground().setColorFilter(ContextCompat.getColor(context, R.color.teal_200), PorterDuff.Mode.SRC_IN);

        toast.show();
    }

    private void failureToast(String message, Context context) {

        if (toast != null)
            toast.cancel();

        toast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
        View view = toast.getView();

        view.getBackground().setColorFilter(ContextCompat.getColor(context, R.color.red), PorterDuff.Mode.SRC_IN);

        toast.show();
    }


}
