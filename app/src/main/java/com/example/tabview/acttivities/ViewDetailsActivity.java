package com.example.tabview.acttivities;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.tabview.R;
import com.github.drjacky.imagepicker.ImagePicker;
import com.squareup.picasso.Picasso;

public class ViewDetailsActivity extends AppCompatActivity {

        @TargetApi(Build.VERSION_CODES.LOLLIPOP)
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.view_contact_details);

            ImageView roundedimag = (ImageView) findViewById(R.id.roundedimag);
            ImagePicker.Companion.with(this)
                    .crop()	    			//Crop image(Optional), Check Customization for more option
                    .cropOval()				//Allow dimmed layer to have a circle inside
                    .compress(1024)			//Final image size will be less than 1 MB(Optional)
                    .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
                    .start();
        }
    }

