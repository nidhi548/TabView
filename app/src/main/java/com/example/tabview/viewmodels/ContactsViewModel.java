package com.example.tabview.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.room.Delete;
import androidx.room.Query;
import androidx.room.Update;

import com.example.tabview.models.Contacts;
import com.example.tabview.repository.ContactsDatabase;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Flowable;

public class ContactsViewModel extends AndroidViewModel {
    private ContactsDatabase contactsDatabase;

    public ContactsViewModel(@NonNull Application application) {
        super(application);
        contactsDatabase = ContactsDatabase.getDbInstance(application);
    }

    Flowable<List<Contacts>> getList() {
        return (Flowable<List<Contacts>>) contactsDatabase.contactsDAO().getAllContacts();
    }

    public void insertRecords(Contacts modelArrayList) {
        contactsDatabase.contactsDAO().insertUser(modelArrayList);
    }

    public void deleteContacts(Contacts modelArrayList) {
        contactsDatabase.contactsDAO().deleteContacts(modelArrayList);
    }

    public void updateUser(Contacts modelArrayList) {
        contactsDatabase.contactsDAO().updateUser(modelArrayList);
    }

    public void getContactWithId(String number) {
        contactsDatabase.contactsDAO().getContactWithId(number);
    }


    public void getContactWithName(String name) {
        contactsDatabase.contactsDAO().getContactsWithName(name);
    }

    public void deleteByContactsName(String name) {
        contactsDatabase.contactsDAO().deleteByContactsName(name);
    }

    public void deleteByContactsId(int contactsId) {
        contactsDatabase.contactsDAO().deleteByContactsId(contactsId);
    }


}
