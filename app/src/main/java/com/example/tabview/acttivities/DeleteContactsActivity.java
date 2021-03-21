package com.example.tabview.acttivities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tabview.models.Contacts;
import com.example.tabview.R;
import com.example.tabview.fragments.ViewFragment;
import com.example.tabview.viewmodels.ContactsViewModel;

public class DeleteContactsActivity extends AppCompatActivity {

    TextView tvDeleteContact;
    Button btnDeleteContact;
    ContactsViewModel contactsViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_contacts);
        init();
    }

    private void init() {
        tvDeleteContact = findViewById(R.id.et_delete_name);
        btnDeleteContact = findViewById(R.id.btn_delete_name);

        btnDeleteContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = tvDeleteContact.getText().toString();
                Contacts contacts = new Contacts();
                contacts.setName(name);
//                contactsViewModel = new ContactsViewModel();
                contactsViewModel.deleteByContactsName(contacts.getName());
                MainActivity.contactsDatabase.contactsDAO().deleteByContactsName(contacts.getName());
                Toast.makeText(getApplicationContext(),
                        "Contact deleted Successfully: "+ String.valueOf(name),
                        Toast.LENGTH_SHORT ).show();
                Log.d("abc","deleted contact"+name);
                tvDeleteContact.setText("");


                getSupportFragmentManager()
                        .beginTransaction()
                        .add(R.id.view_pager, new ViewFragment())
                        .commit();

            }
        });
    }
}