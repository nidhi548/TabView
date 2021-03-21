package com.example.tabview.repository;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.tabview.models.Contacts;

import java.util.List;

@Dao
public interface ContactsDAO {
    @Query("select * from contacts")
    List<Contacts> getAllContacts();

    @Insert
    void insertUser(Contacts contacts);

    @Update
    void updateUser(Contacts contacts);

    @Delete
    void deleteContacts(Contacts contacts);

    @Query("SELECT * FROM contacts WHERE Phone_Number = :number")
    public Contacts getContactWithId(String number);

    @Query("SELECT * FROM contacts WHERE Name = :name")
    public Contacts getContactsWithName(String name);

    @Query("DELETE FROM contacts WHERE Name = :name")
    abstract void deleteByContactsName(String name);


    @Query("DELETE FROM contacts WHERE Name = :contactsId")
    abstract void deleteByContactsId(int contactsId);


}
