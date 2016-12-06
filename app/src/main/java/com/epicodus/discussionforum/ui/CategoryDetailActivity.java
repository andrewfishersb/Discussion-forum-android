package com.epicodus.discussionforum.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
}
