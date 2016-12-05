package com.epicodus.discussionforum.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.epicodus.discussionforum.Constants;
import com.epicodus.discussionforum.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    @Bind(R.id.categoryButton) Button mCategoryButton;

    private DatabaseReference mAllCategoriesReference;
    private ValueEventListener mCategoryReferenceListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mAllCategoriesReference = FirebaseDatabase.getInstance().getReference().child(Constants.FIREBASE_CHILD_CATEGORY);

        mCategoryReferenceListener = mAllCategoriesReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot categorySnapshot : dataSnapshot.getChildren()){
                    String category = categorySnapshot.getValue().toString();
                    Log.d("Categories: ","Category: " + category);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mCategoryButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){
        if (v == mCategoryButton) {
            Intent intent = new Intent(MainActivity.this, NewCategoryActivity.class);
            startActivity(intent);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mAllCategoriesReference.removeEventListener(mCategoryReferenceListener);
    }
}
