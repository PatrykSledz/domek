package com.example.domek;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;


public class MainActivity extends AppCompatActivity {

    String LIKESSS="LIKESSS";
    String KEY = "KEY";
    int likes = 0;

    SharedPreferences settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        settings = getSharedPreferences(LIKESSS, Context.MODE_PRIVATE);
        likes = settings.getInt(KEY, likes);
        TextView tekst = findViewById(R.id.likes);
        tekst.setText(likes+" polubień");
    }
    protected void onStop(){
        super.onStop();
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt(KEY, likes);
        editor.apply();
        Toast.makeText(this, "Liczba polubień zapisana!", Toast.LENGTH_SHORT).show();
    }

    public void umow(View view) {
        startActivity(new Intent(MainActivity.this, MainActivity2.class));
    }
    public void addLike(View view){
        likes= likes+1;
        TextView tekst = findViewById(R.id.likes);
        tekst.setText(likes + " polubień");
    }
    public void deleteLike(View view){
        if(likes > 0){
            likes=likes-1;
            TextView tekst = findViewById(R.id.likes);
            tekst.setText(likes + " polubień");
        }
    }
    public void saveLike(View view){
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt(KEY, likes);
        editor.apply();
        Toast.makeText(this, "Liczba polubień zapisana!", Toast.LENGTH_SHORT).show();
    }
}