package com.example.chattest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity {
    //Firebase 인스턴스 변수
    private FirebaseAuth mFirebaseAuth;
    private FirebaseUser mFirebaseUser;

    //사용자 이름과 사진
    private String mUsername;
    private String mPhotoUrl;

    public static class MessageViewHolder extends RecyclerView.ViewHolder{
        TextView messageTextView;
        TextView messageImageView;
        TextView messengerTextView;
        CircleImageView messengerImageView;
        public MessageViewHolder(View v){
            super(v);
            messageTextView = itemView.findViewById(R.id.messageTextView);
            messageImageView = itemView.findViewById(R.id.messageImageView);
            messengerTextView = itemView.findViewById(R.id.messengerTextView);
            messengerImageView = itemView.findViewById(R.id.messengerImageView);

        }
    }
    private RecyclerView mMessageRecyclerview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mMessageRecyclerview = findViewById(R.id.message_recycler_view);

        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseUser = mFirebaseAuth.getCurrentUser();

        if(mFirebaseUser == null){
            //인증안되어있을시 인증화면으로 이동
            startActivity(new Intent(this,SignInActivity.class));
            finish();
            return;
        }else{
            mUsername = mFirebaseUser.getDisplayName();
            if(mFirebaseUser.getPhotoUrl()!=null){
                mPhotoUrl = mFirebaseUser.getPhotoUrl().toString();
            }
        }

    }
}
