package com.example.tabview.acttivities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tabview.R;
import com.github.drjacky.imagepicker.ImagePicker;

public class ShowDetailContactsAcitivity extends AppCompatActivity {

    TextView tvName, tvBirthday, tvPhone;
    ImageView circularImageView ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_contacts_acitivity);
        init();
    }

    private void init() {
        tvName = findViewById(R.id.tv_detail_name);
        tvBirthday = findViewById(R.id.tv_detail_BirthDay);
        tvPhone = findViewById(R.id.tv_detail_phone_number);
        circularImageView = findViewById(R.id.iv_detail_image);
        ImagePicker.Companion.with(this)
                .crop()	    			//Crop image(Optional), Check Customization for more option
                .cropOval()				//Allow dimmed layer to have a circle inside
                .compress(1024)			//Final image size will be less than 1 MB(Optional)
                .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
                .start();


        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String birthday = intent.getStringExtra("Birthday");
        String phone = intent.getStringExtra("phone");

        tvName.setText("Name: "+name);
        tvBirthday.setText("BirthDay: "+birthday);
        tvPhone.setText("PhoneNumber: "+phone);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        Log.d("abc", data.toString());
        Toast.makeText(this, data.toString(), Toast.LENGTH_SHORT).show();
        if (resultCode == Activity.RESULT_OK) {
            //Image Uri will not be null for RESULT_OK
            Uri fileUri = data.getData();
            circularImageView.setImageURI(fileUri);
            Toast.makeText(this, fileUri.toString(), Toast.LENGTH_SHORT).show();
            Log.d("abc", fileUri.toString());

            //You can get File object from intent
//            File file = ImagePicker.getFile(data);

            //You can also get File Path from intent
//                    val filePath:String = ImagePicker.getFilePath(data)!!
        } else if (resultCode == ImagePicker.RESULT_ERROR) {
            Toast.makeText(this, "Error_here", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Task Cancelled", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setTitle("Message")
                .setMessage("Do you want to exit app?")
                .setNegativeButton("NO", null)
                .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        ShowDetailContactsAcitivity.super.onBackPressed();
                    }
                }).create().show();
    }
}