package com.example.tabview.acttivities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.tabview.repository.ContactsDatabase;
import com.example.tabview.R;
import com.example.tabview.adapters.MyFragmentAdapter;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager viewPager;
    MyFragmentAdapter adapter;

    public static ContactsDatabase contactsDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {

        tabLayout = findViewById(R.id.tab_layout);
        viewPager = findViewById(R.id.view_pager);
        adapter = new MyFragmentAdapter(getSupportFragmentManager());

        contactsDatabase = Room.databaseBuilder(getApplicationContext(),ContactsDatabase.class,"contacts").allowMainThreadQueries().build();


        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.getTabAt(0).setIcon(R.drawable.border);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_launcher_foreground);


    }
}