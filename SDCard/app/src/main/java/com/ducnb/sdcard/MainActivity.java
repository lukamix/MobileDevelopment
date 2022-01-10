package com.ducnb.sdcard;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final String SUB_FILE_NAME = "SUB_FILE_NAME";
    private final int REQUEST_CODE = 20183499;

    private ListView listView;
    private Button backBtn;
    private String subFileName;
    boolean isOpenFile = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getReadPermission();
        setBackBtnOnClicked();

        listView = findViewById(R.id.listView);
        String extraMessage = (String) getIntent().getSerializableExtra(SUB_FILE_NAME);
        if (extraMessage != null) {
            this.subFileName = extraMessage;
            Log.v("TAG", "Sub file name " + this.subFileName);
        }

        StringBuilder builder = new StringBuilder();
        List<File> files = getFiles(builder);
        setListViewItem(builder, files);
    }

    private void setListViewItem(StringBuilder builder, List<File> files) {
        List<String> fileNames = new ArrayList<>();
        if ( !this.isOpenFile ) {
            for (File file : files) {
                Log.v("TAG", file.getAbsolutePath());
                fileNames.add(file.getName());
            }
        } else {
            fileNames.add(builder.toString());
        }

        ArrayAdapter<String> stringArrayAdapter = new ArrayAdapter<>(this,
                R.layout.support_simple_spinner_dropdown_item,
                fileNames);
        listView.setAdapter(stringArrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainActivity.this, MainActivity.class);
                Log.v("TAG", "Clicked on " + fileNames.get(i));
                intent.putExtra(SUB_FILE_NAME, fileNames.get(i));
                startActivity(intent);
            }
        });
    }

    @NonNull
    private List<File> getFiles(StringBuilder builder) {
        List<File> files = new ArrayList<>();
        if (this.subFileName != null) {
            String folderToList = Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + this.subFileName;
            File folderFile = new File(folderToList);
            if (!folderFile.isDirectory() && folderFile.listFiles() != null) {
                files = Arrays.asList(folderFile.listFiles());
            }

            if (folderFile.isFile()) {
                Log.v("TAG", "Clicked on file");
                try {
                    FileInputStream fis = openFileInput(folderToList);
                    BufferedReader reader = new BufferedReader(new InputStreamReader(fis));

                    String line;
                    while ((line = reader.readLine()) != null) {
                        builder.append(line + "\n");
                    }
                    reader.close();
                    this.isOpenFile = true;
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        } else {
            if (Environment.getExternalStorageDirectory().listFiles() != null) {
                files = Arrays.asList(Environment.getExternalStorageDirectory().listFiles());
            }
        }
        return files;
    }

    private void getReadPermission() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED) {
                Log.v("TAG", "Permission granted");
            } else {
                requestPermissions(
                        new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        REQUEST_CODE);
            }
        }
    }

    private void setBackBtnOnClicked() {
        backBtn = findViewById(R.id.backBtn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }


    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE) {
            if (grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                Log.v("TAG", "Permission Denied");
            }
        }
    }
}