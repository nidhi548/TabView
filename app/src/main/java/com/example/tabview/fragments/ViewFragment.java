package com.example.tabview.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.os.DropBoxManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tabview.models.Contacts;
import com.example.tabview.R;
import com.example.tabview.acttivities.DeleteContactsActivity;
import com.example.tabview.acttivities.MainActivity;
import com.example.tabview.adapters.ViewContactsAdapter;
import com.example.tabview.repository.ContactsDatabase;
import com.example.tabview.viewmodels.ContactsViewModel;
//import com.example.tabview.adapters.ContactsRecyclerAdapter;

import java.util.ArrayList;
import java.util.List;

import static java.util.Locale.filter;


public class ViewFragment extends Fragment {

    Button addUser,delContact;
    TextView textView,textViewSearch;
    RecyclerView recyclerView;
    ArrayList<Contacts> contactsArrayList = new ArrayList<>();
    ViewContactsAdapter contactsRecyclerAdapter;
    List<Contacts> entryList;
    ContactsViewModel contactsViewModel;
//    private Object List;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_view, container, false);

        ContactsDatabase db = ContactsDatabase.getDbInstance(this.getContext());
//        entryList = db.contactsDAO().getAllContacts();

//        textView = view.findViewById(R.id.tv_read);
        delContact = view.findViewById(R.id.button_delete_contacts);
        delContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(view.getContext(), DeleteContactsActivity.class);
                v.getContext().startActivity(intent);
            }
        });

        textViewSearch = view.findViewById(R.id.et_search_text);


        recyclerView = view.findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));


        List<Contacts> contacts = MainActivity.contactsDatabase.contactsDAO().getAllContacts();
        contactsArrayList.addAll(contacts);
        contactsRecyclerAdapter = new ViewContactsAdapter(getContext(), contactsArrayList);
        recyclerView.setAdapter(contactsRecyclerAdapter);

        textViewSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                // TODO Auto-generated method stub
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                // TODO Auto-generated method stub
            }

            @Override
            public void afterTextChanged(Editable s) {

                // filter your list from your input
                filter(s.toString());
                //you can use runnable postDelayed like 500 ms to delay search text
            }

        }

        );


        return view;

    }
    void filter(String text){
        ArrayList<Contacts> temp = new ArrayList<>();
        for(Contacts d: contactsArrayList){
            if(d.getName().contains(text)){
                temp.add(d);
            }
        }
        //update recyclerview
//        recyclerView.setAdapter(temp);
//        ViewContactsAdapter.
    }


}