package com.example.fileapp;

import androidx.appcompat.app.AppCompatActivity;

import android.icu.util.Output;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

import static android.provider.Telephony.Mms.Part.FILENAME;

public class MainActivity extends AppCompatActivity {

    private final static String FILE_NAME = "fileDemo.txt";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button buttonWrite = (Button)findViewById(R.id.button_write);
        Button buttonRead = (Button)findViewById(R.id.button_read);
        buttonWrite.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                OutputStream out = null;
                try {
                    FileOutputStream fileOutputStream = openFileOutput(FILENAME,MODE_PRIVATE);
                    out = new BufferedOutputStream(fileOutputStream);
                    String content = "XiaoZhan2018011240";
                    try {
                        out.write(content.getBytes(StandardCharsets.UTF_8));
                    }
                    finally{
                            if(out!=null)
                                out.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        buttonRead.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                InputStream in = null;
                try {
                    FileInputStream fileInputStream = openFileInput(FILENAME);
                    in = new BufferedInputStream(fileInputStream);
                    int c;
                    StringBuilder stringBuilder = new StringBuilder("");
                    try {
                        while ((c = in.read()) != -1) {
                            stringBuilder.append((char) c);
                        }
                        Toast.makeText(MainActivity.this, stringBuilder.toString(), Toast.LENGTH_LONG).show();
                    } finally {
                        if (in != null)
                            in.close();
                    }
                } catch (Exception e){
                        e.printStackTrace();
                }

            }
        });

    }
}