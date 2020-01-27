package com.example.android_foodbot;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.Toast;

import com.example.android_foodbot.AccountActivity.SignUpActivity;
import com.example.android_foodbot.Common.Common;
import com.example.android_foodbot.Interface.ItemClickListener;
import com.example.android_foodbot.Model.Food;
import com.example.android_foodbot.ViewHolder.FoodViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.mancj.materialsearchbar.MaterialSearchBar;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class FoodListActivity extends AppCompatActivity {

    RecyclerView recycler_food;
    RecyclerView.LayoutManager layoutManager;

    FirebaseRecyclerAdapter<Food, FoodViewHolder> adapter;

    FirebaseDatabase database;
    DatabaseReference foodList;
    String categoryId="";

    //Search Functionality
    FirebaseRecyclerAdapter<Food, FoodViewHolder> searchAdapter;
    List<String> suggestList = new ArrayList<String>();
    MaterialSearchBar materialSearchBar;
;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_list);


//        Firebase
        database = FirebaseDatabase.getInstance();
        foodList = database.getReference("Food");

        recycler_food = findViewById(R.id.recycler_food);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 2);
        recycler_food.setLayoutManager(layoutManager);
        recycler_food.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
//        recycler_food.setItemAnimator(new DefaultItemAnimator());




//   loadSuggest();

//            //search
//            materialSearchBar = (MaterialSearchBar)findViewById(R.id.searchBar);
//            materialSearchBar.setHint("Enter your food");
////        materialSearchBar.setSpeechMode(false);
//            materialSearchBar.setCardViewElevation(10);
//            materialSearchBar.addTextChangeListener(new TextWatcher() {
//                @Override
//                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//                }
//
//                @Override
//                public void onTextChanged(CharSequence s, int start, int before, int count) {
//                    //When user type their text, we will change suggest List
//
//
//
//                    List<String> suggest = new ArrayList<String>();
//                    for(String search:suggestList)
//                    {
//                        if(search.toLowerCase().contains(materialSearchBar.getText().toLowerCase()))
//                            suggest.add(search);
//
//                    }
//                    materialSearchBar.setLastSuggestions(suggest);
//                }
//
//                @Override
//                public void afterTextChanged(Editable s) {
//
//
//                }
//            });
//            materialSearchBar.setOnSearchActionListener(new MaterialSearchBar.OnSearchActionListener() {
//                @Override
//                public void onSearchStateChanged(boolean enabled) {
//                    //When Search Bar is clear
//                    //Restore original adapter
//                    if (!enabled)
//                        recycler_food.setAdapter(adapter);
//                }
//
//                @Override
//                public void onSearchConfirmed(CharSequence text) {
//                    //When search finish
//                    //Show result of search adapter
//                    startSearch(text);
//                }
//
//                @Override
//                public void onButtonClicked(int buttonCode) {
//
//                }
//            });
//


        }


//    private void startSearch(CharSequence text) {
//        searchAdapter = new FirebaseRecyclerAdapter<Food, FoodViewHolder>(
//                Food.class,
//                R.layout.food_item,
//                FoodViewHolder.class,
//                foodList.orderByChild("name").equalTo(text.toString())
//        ) {
//            @Override
//            protected void populateViewHolder(FoodViewHolder viewHolder, Food model, int position) {
//                viewHolder.food_name.setText(model.getName());
//                viewHolder.food_price.setText(model.getPrice());
//
//
////                Toast.makeText(FoodListActivity.this, "Show food name :"+model.getName(), Toast.LENGTH_SHORT).show();
//
//                Picasso.with(getBaseContext()).load(model.getImage())
//                        .into(viewHolder.food_image);
//
////                final Food local = model;
//                viewHolder.setItemClickListener(new ItemClickListener() {
//                    @Override
//                    public void onClick(View view, int position, boolean isLongClick) {
//                        Intent foodDetail = new Intent(FoodListActivity.this, FoodDetailsActivity.class);
//                        foodDetail.putExtra("FoodId", searchAdapter.getRef(position).getKey());
//
//                        startActivity(foodDetail);
//
//
//                    }
//                });
//            }
//        };
//        recycler_food.setAdapter(searchAdapter);
//    }

//    private void loadSuggest() {
//        foodList.orderByChild("menuid").equalTo(categoryId)
//                .addListenerForSingleValueEvent(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                        Log.e("Count " ,""+dataSnapshot.getChildrenCount());
//                        for(DataSnapshot postSnapshot:dataSnapshot.getChildren())
//                        {
//                            Food item = postSnapshot.getValue(Food.class);
//                            suggestList.add(item.getName());
//                        }
//                        materialSearchBar.setLastSuggestions(suggestList);
//
//                    }
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError databaseError) {
//
//                    }
//                });
//
//
//    }

    private void loadListFood(String categoryId) {
        adapter = new FirebaseRecyclerAdapter<Food, FoodViewHolder>(Food.class,
                R.layout.food_item,
                FoodViewHolder.class,
                //Select from foods where MenuId = CategoryId
                foodList.orderByChild("menuid").equalTo(categoryId)) {
            @Override
            protected void populateViewHolder(FoodViewHolder viewHolder, Food model, int position) {
                viewHolder.food_name.setText(model.getName());
                viewHolder.food_price.setText(model.getPrice());


//                Toast.makeText(FoodListActivity.this, "Show food name :"+model.getName(), Toast.LENGTH_SHORT).show();

                Picasso.with(getBaseContext()).load(model.getImage())
                        .into(viewHolder.food_image);

//                final Food local = model;
                viewHolder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {
                        Intent foodDetail = new Intent(FoodListActivity.this, FoodDetailsActivity.class);
                        foodDetail.putExtra("FoodId", adapter.getRef(position).getKey());

                        startActivity(foodDetail);


                    }
                });
            }
        };

        //Set Adapter
        Log.d("TAG",""+adapter.getItemCount());
        recycler_food.setAdapter(adapter);
    }

    /**
     * RecyclerView item decoration - give equal margin around grid item
     */
    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }

    /**
     * Converting dp to pixel
     */
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }

    @Override
    protected void onStart() {
        super.onStart();
        //Get Category Id from Intent
        if (getIntent() != null)
            categoryId = getIntent().getStringExtra("CategoryId");
        if (!categoryId.isEmpty() && categoryId != null) {
            if(Common.isConnectedToInternet(getBaseContext()))
                loadListFood(categoryId);
            else
            {
                Toast.makeText(FoodListActivity.this, "Please check your internet connection !!", Toast.LENGTH_SHORT).show();
                return;
            }


        }


    }
}
