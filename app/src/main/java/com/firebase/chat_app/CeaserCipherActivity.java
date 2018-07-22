package com.firebase.chat_app;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import ceasercipher.CeaserCipher;
import onetimepad.OneTimePad;

public class CeaserCipherActivity extends Activity {

    private static final String TAG = MainActivity.class.getName();

    private EditText metText;
    private Button mbtSent;
    private DatabaseReference mFirebaseRefceaser,mFirebaseRefonetime;

    private List<Chat> mChats;
    private RecyclerView mRecyclerView;
    private ChatAdapter mAdapter;
    private String mId;
    private StringBuilder key = new StringBuilder("ZXYSMMVBSTTVJ".toLowerCase());


    @SuppressLint("HardwareIds")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        Intent intent = getIntent();


        metText = (EditText) findViewById(R.id.etText);
        mbtSent = (Button) findViewById(R.id.btSent);
        mRecyclerView = (RecyclerView) findViewById(R.id.rvChat);
        mChats = new ArrayList<>();

        mId = Settings.Secure.getString(this.getContentResolver(), Settings.Secure.ANDROID_ID);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        //mRecyclerView.setItemAnimator(new SlideInOutLeftItemAnimator(mRecyclerView));
        mAdapter = new ChatAdapter(mChats, mId);
        mRecyclerView.setAdapter(mAdapter);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        mFirebaseRefceaser = database.getReference("CeaserMessages");

        mbtSent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = metText.getText().toString();

                if (!message.isEmpty()) {
                    /**
                     * Firebase - Send message
                     */

                        Log.e("OnClickListenerM",message);

                        String cipherText = CeaserCipher.encrypt(message.toLowerCase(),3);
                        Log.e("OnClickListener",cipherText);
                        Chat model = new Chat(cipherText, mId,3);

                        mFirebaseRefceaser.push().setValue(model);
                    }

                metText.setText("");
            }
        });


        /**
         * Firebase - Receives message
         */
//        mFirebaseRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                if (dataSnapshot != null && dataSnapshot.getValue() != null) {
//                    try {
//
//                        Chat model = dataSnapshot.getValue(Chat.class);
//
//                        mChats.add(model);
//                        mRecyclerView.scrollToPosition(mChats.size() - 1);
//                        mAdapter.notifyItemInserted(mChats.size() - 1);
//                    } catch (Exception ex) {
//                        Log.e(TAG, ex.getMessage());
//                    }
//                }
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });

        mFirebaseRefceaser.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                if (dataSnapshot != null && dataSnapshot.getValue() != null) {
                    try {

                        Chat model = dataSnapshot.getValue(Chat.class);

                            //model.setMessage(CeaserCipher.decrypt(model.getMessage(),model.getKey()));
                            Log.e("mRecyclerView",mChats.size()+"");
                            mChats.add(model);
                            mRecyclerView.scrollToPosition(mChats.size() - 1);
                            mAdapter.notifyItemInserted(mChats.size() - 1);


                    } catch (Exception ex) {
                        Log.e(TAG, ex.getMessage());
                    }
                }
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d(TAG, databaseError.getMessage());
            }
        });


    }
}
