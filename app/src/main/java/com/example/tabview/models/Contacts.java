package com.example.tabview.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

//import java.util.Date;


@Entity(tableName = "contacts")
public class Contacts {
    @PrimaryKey(autoGenerate = true)
    int uid;

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

//    @ColumnInfo(typeAffinity = ColumnInfo.BLOB)
    private String image;

    public String getImage() {
        return image;
    }

    private String Name;

    private String Birthday;

    public String getBirthday() {
        return Birthday;
    }

    public void setBirthday(String birthday) {
        Birthday = birthday;
    }

    private String Phone_Number;

    public String getPhone_Number() {
        return Phone_Number;
    }

    public void setPhone_Number(String phone_Number) {
        Phone_Number = phone_Number;
    }


    public void setName(String name) {
        Name = name;
    }

    public String getName() {
        return Name;
    }


    public void setImage(String image) {
        this.image =image;
    }
}
