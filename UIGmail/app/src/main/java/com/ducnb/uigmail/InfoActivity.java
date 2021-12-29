package com.ducnb.uigmail;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.ducnb.uigmail.data.Users;


public class InfoActivity extends AppCompatActivity {
    private ImageView user_avatar;
    private TextView username,user,email,address,phone,company;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
        user_avatar = findViewById(R.id.info_image_avatar);
        username = findViewById(R.id.info_username);
        user = findViewById(R.id.info_name);
        email = findViewById(R.id.info_user_email);
        address = findViewById(R.id.info_address);
        phone = findViewById(R.id.info_phone_number);
        company =findViewById(R.id.info_company);
        Users mUser = (Users)getIntent().getSerializableExtra("User");

        Glide.with(this).load(Utils.BASE_URL+mUser.getAvatar().getPhoto()).into(user_avatar);
        username.setText(mUser.getUsername());
        user.setText(mUser.getName());
        email.setText(mUser.getEmail());
        address.setText(mUser.getAddress().getStreet()+" "+mUser.getAddress().getSuite()+" "+mUser.getAddress().getCity());
        phone.setText(mUser.getPhone());
        company.setText(mUser.getCompany().getName());
    }
}
