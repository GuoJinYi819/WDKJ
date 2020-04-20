package com.wd.tech;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.wd.tech.util.RsaCoder;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            String s = RsaCoder.encryptByPublicKey("qzj1234567");
            Log.d("==", "onCreate: "+s);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
