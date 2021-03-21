package com.example.tabview.repository;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.tabview.models.Contacts;

@Database(entities = {Contacts.class}, version = 3)
//@TypeConverters({Converters.class})
public  abstract class ContactsDatabase extends RoomDatabase {
//    Context context;
    public abstract ContactsDAO contactsDAO();
    private static ContactsDatabase INSTANCE;
    public static ContactsDatabase getDbInstance(Context context) {
        if (INSTANCE == null)
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), ContactsDatabase.class, "contacts")
                    .addMigrations(MIGRATION_2_2) // Add the migration
                    .build();

        return INSTANCE;
    }

    static final Migration MIGRATION_2_2 = new Migration(2, 3) { // From version 1 to version 2
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            // Remove the table
            database.execSQL("DROP TABLE contacts"); // Use the right table name

            }
    };

}
