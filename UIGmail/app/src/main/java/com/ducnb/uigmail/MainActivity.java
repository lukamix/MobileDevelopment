package com.ducnb.uigmail;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.ducnb.uigmail.data.Gmail;

import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    private ArrayList<Gmail> mGmails ;
    private RecyclerView mRecyclerGmail;
    private GmailAdapter mGmailAdapter ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerGmail = findViewById(R.id.gmail_view);
        mGmails = new ArrayList<>();
        createGmailList();
        mGmailAdapter = new GmailAdapter(this,mGmails);
        mRecyclerGmail.setAdapter(mGmailAdapter);
        mRecyclerGmail.setLayoutManager(new LinearLayoutManager(this));
    }
    private void createGmailList() {
        mGmails.add(new Gmail("HeadHunterz",R.drawable.unnamed,"Thư khẩn",new Date()));
        mGmails.add(new Gmail("Binz",R.drawable.image1,"Thư khẩn",new Date()));
        mGmails.add(new Gmail("Anais Intertaiment",R.drawable.image2,"Thư khẩn",new Date()));
        mGmails.add(new Gmail("Nguyễn Bá Đức",R.drawable.image3,"Thư khẩn",new Date()));
        mGmails.add(new Gmail("Ny Ny",R.drawable.image4,"Thư khẩn",new Date()));
        mGmails.add(new Gmail("HeadHunterz",R.drawable.unnamed,"Thư khẩn",new Date()));
        mGmails.add(new Gmail("Binz",R.drawable.image1,"Thư khẩn",new Date()));
        mGmails.add(new Gmail("Anais Intertaiment",R.drawable.image2,"Thư khẩn",new Date()));
        mGmails.add(new Gmail("Nguyễn Bá Đức",R.drawable.image3,"Thư khẩn",new Date()));
        mGmails.add(new Gmail("Ny Ny",R.drawable.image4,"Thư khẩn",new Date()));
    }
}