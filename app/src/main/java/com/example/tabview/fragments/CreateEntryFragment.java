package com.example.tabview.fragments;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tabview.models.Contacts;
import com.example.tabview.R;
import com.example.tabview.acttivities.MainActivity;
import com.github.drjacky.imagepicker.ImagePicker;

import java.io.File;
import java.util.Calendar;
import java.util.Date;


public class CreateEntryFragment extends Fragment implements DatePickerDialog.OnDateSetListener {
    Button buttonAddUser;
    TextView textViewName, textViewPhone, textViewBirthDay, textViewbday;
    ImageView imageView;
    Uri fileUri = null;

    public CreateEntryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_create_entry, container, false);

        textViewName = view.findViewById(R.id.et_name);
        textViewPhone = view.findViewById(R.id.et_phone);
        textViewBirthDay = view.findViewById(R.id.et_Birthday);
        textViewbday = (TextView) view.findViewById(R.id.tv_birthday);
        imageView = view.findViewById(R.id.iv_detail_image);


        //To add images
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImagePicker.Companion.with(CreateEntryFragment.this)
                        //.crop()                    //Crop image(Optional), Check Customization for more option
                        //.cropOval()                //Allow dimmed layer to have a circle inside
                        .compress(1024)            //Final image size will be less than 1 MB(Optional)
                        .maxResultSize(1080, 1080)   //Final image resolution will be less than 1080 x 1080(Optional)
                        .start();


            }
        });
        buttonAddUser = view.findViewById(R.id.btn_save);
        buttonAddUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = textViewName.getText().toString();
                String phone = textViewPhone.getText().toString();
//                Date dob = (Date) textViewBirthDay.getText();
                String dob =  textViewBirthDay.getText().toString();


                Contacts contacts = new Contacts();
                contacts.setName(name);
                contacts.setBirthday(dob);
                contacts.setPhone_Number(phone);

                if(fileUri!= null)
                {
                    contacts.setImage(fileUri.getPath());
                }


                MainActivity.contactsDatabase.contactsDAO().insertUser(contacts);
                Log.d("Saved Data successfully", "Hi");
                Toast.makeText(getActivity(), "Contact is added successfully", Toast.LENGTH_SHORT).show();

                textViewName.setText("");
                textViewPhone.setText("");
                textViewBirthDay.setText("");



            }
        });

        textViewBirthDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });
        return view;
    }

    private void showDatePickerDialog()
    {
        DatePickerDialog datePickerDialog = new DatePickerDialog(
               getContext(),
                this,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
        );
        datePickerDialog.show();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        String date = month + "/" + dayOfMonth + "/" + year;
        textViewBirthDay.setText(date);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        Log.d("abc",data.toString());
        Toast.makeText(getActivity(), data.toString(), Toast.LENGTH_SHORT).show();
        if (resultCode == Activity.RESULT_OK) {
            fileUri = data.getData();
            imageView.setImageURI(fileUri);
            Toast.makeText(getActivity(), fileUri.toString(), Toast.LENGTH_SHORT).show();
            Log.d("abc",fileUri.toString());
        }
        else if (resultCode == ImagePicker.RESULT_ERROR) {
            Toast.makeText(getActivity(), "Error_here", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(getActivity(), "Task Cancelled", Toast.LENGTH_SHORT).show();
        }
    }
}