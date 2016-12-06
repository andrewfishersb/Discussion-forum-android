package com.epicodus.discussionforum.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.epicodus.discussionforum.R;
import com.epicodus.discussionforum.models.Category;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class CategoryDetailActivity extends AppCompatActivity {
    @Bind(R.id.categoryDetailTitleTextView) TextView mCategoryDetailTitleTextView;
    @Bind(R.id.categoryDetailImageView) ImageView mCategoryDetailImageView;

    private ArrayList<Category> mCategories = new ArrayList<>();
    private Category mCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_detail);
        ButterKnife.bind(this);

        mCategories = Parcels.unwrap(getIntent().getParcelableExtra("categories"));
        int startingPosition = getIntent().getIntExtra("position", 0);
        mCategory = mCategories.get(startingPosition);

        mCategoryDetailTitleTextView.setText(mCategory.getTitle());
        Picasso.with(this).load(mCategory.getImage()).into(mCategoryDetailImageView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_add_post, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case R.id.action_add_post:
                Intent intent = new Intent(CategoryDetailActivity.this, AddPostActivity.class);
                intent.putExtra("category", Parcels.wrap(mCategory));
                startActivity(intent);
                 return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
