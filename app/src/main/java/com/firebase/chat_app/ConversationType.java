package com.firebase.chat_app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import ceasercipher.CeaserCipher;
import onetimepad.OneTimePad;

public class ConversationType extends Activity {


    Button oneTimePad,ceaserCipher;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.conversation_type);
        init ();
                                                                                    //ZXYSMMVBSTTVJ
        Log.e("ConversationType",OneTimePad.encrypt("youtube",new StringBuilder("zxysmmvbsttvj")));

        oneTimePad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ConversationType.this,MainActivity.class);
                startActivity(intent);
            }
        });


        ceaserCipher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ConversationType.this,CeaserCipherActivity.class);
                intent.putExtra("type","ceasercipher");
                startActivity(intent);
            }
        });

    }


    void init (){
        oneTimePad = findViewById(R.id.One_Time_Pad);
        ceaserCipher = findViewById(R.id.Ceaser_Cipher);
    }


}
