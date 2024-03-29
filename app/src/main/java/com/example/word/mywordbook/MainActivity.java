package com.example.word.mywordbook;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.amy.mywordbook.R;
import com.example.word.mywordbook.wordcontract.Words;

import org.json.JSONException;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        Button book = (Button)findViewById(R.id.my);
        book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(MainActivity.this,BookActivity.class);
                startActivity(intent);
            }
        });

        Button news = (Button)findViewById(R.id.news);
        news.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(MainActivity.this,News.class);
                startActivity(intent);
            }
        });
        Button search = (Button)findViewById(R.id.search);
        final EditText in = (EditText)findViewById(R.id.in);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ReadWordByYouDao wordByYouDao = new ReadWordByYouDao(in.getText().toString());
                wordByYouDao.start();
                try {
                    wordByYouDao.join();
                    Words.YouDaoWord youdaoWord = wordByYouDao.getYouDaoWord(wordByYouDao.getResultJson());
                    Intent i = new Intent(MainActivity.this, YouDaoActivity.class);
                    i.putExtra("youdaoWord", youdaoWord);
                    startActivity(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }


/*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }*/
}
