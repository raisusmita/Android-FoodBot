package com.example.android_foodbot;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android_foodbot.AccountActivity.LoginActivity;
import com.example.android_foodbot.AccountActivity.User;
import com.example.android_foodbot.Common.Common;
import com.example.android_foodbot.Interface.ItemClickListener;
import com.example.android_foodbot.Model.Category;
import com.example.android_foodbot.Service.ListenOrder;
import com.example.android_foodbot.ViewHolder.MenuViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;


public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private FirebaseAuth.AuthStateListener mAuthListener;


    RecyclerView recycler_menu;
    RecyclerView.LayoutManager layoutManager;


    FirebaseDatabase database;
    DatabaseReference category;
    FirebaseRecyclerAdapter<Category, MenuViewHolder> adapter;

    TextView txtUserName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Menu");
        setSupportActionBar(toolbar);

                //        Init Firebase
        database = FirebaseDatabase.getInstance();
        category = database.getReference("Category");

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cartIntent = new Intent(HomeActivity.this, CartActivity.class);
                startActivity(cartIntent);
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        final NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
////
//        View headerView = navigationView.getHeaderView(0);
//        txtUserName = (TextView)findViewById(R.id.txtName);
//        txtUserName.setText(Common.currentUser.getName());


//                Set Name for User
        String uid;
        FirebaseAuth auth;
        auth = FirebaseAuth.getInstance();
        uid = auth.getCurrentUser().getUid();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        FirebaseDatabase.getInstance().getReference().child("Users").child(uid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
                View headerView = navigationView.getHeaderView(0);
                txtUserName = (TextView)findViewById(R.id.txtName);
                        txtUserName.setText(user.getName());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                throw databaseError.toException();

            }
        });



//      Loading menu
        recycler_menu = findViewById(R.id.recycler_menu);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 2);
        recycler_menu.setLayoutManager(layoutManager);
        recycler_menu.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));

        //Register Service
        Intent service = new Intent(HomeActivity.this, ListenOrder.class);
        startService(service);
    }


    @Override
    protected void onStart() {
        super.onStart();
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
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.logout:
                FirebaseAuth.getInstance().signOut();
                finish();
                startActivity(new Intent(this, LoginActivity.class));
                break;
            case R.id.refresh:
                adapter = new FirebaseRecyclerAdapter<Category, MenuViewHolder>(Category.class,
                        R.layout.menu_item,
                        MenuViewHolder.class,
                        category) {
                    @Override
                    protected void populateViewHolder(MenuViewHolder viewHolder, Category model, int position) {
                        viewHolder.txtMenuName1.setText(model.getName1());
                        Picasso.with(getBaseContext()).load(model.getImage1())
                                .into(viewHolder.imageView1);

                        final Category clickItem = model;
                        viewHolder.setItemClickListener(new ItemClickListener() {
                            @Override
                            public void onClick(View view, int position, boolean isLongClick) {
//
                                // Get Category id and Start new activity
                                Intent foodList = new Intent(HomeActivity.this, FoodListActivity.class);
                                foodList.putExtra("CategoryId", adapter.getRef(position).getKey());
                                startActivity(foodList);

                            }
                        });

                    }
                };
                recycler_menu.setAdapter(adapter);

        }

        return true;

//        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_menu) {
            Intent chat = new Intent(HomeActivity.this, HomeActivity.class);
            startActivity(chat);

        } else if (id == R.id.nav_orders) {
            Intent orderIntent = new Intent(HomeActivity.this, OrderStatusActivity.class);
            startActivity(orderIntent);

        } else if (id == R.id.nav_cart) {
            Intent cartIntent = new Intent(HomeActivity.this, CartActivity.class);
            startActivity(cartIntent);

        } else if (id == R.id.nav_logout) {
            Intent mainIntent = new Intent(HomeActivity.this, MainActivity.class);
            startActivity(mainIntent);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();

        if(Common.isConnectedToInternet(this))
        {
            adapter = new FirebaseRecyclerAdapter<Category, MenuViewHolder>(Category.class,
                    R.layout.menu_item,
                    MenuViewHolder.class,
                    category) {
                @Override
                protected void populateViewHolder(MenuViewHolder viewHolder, Category model, int position) {
                    viewHolder.txtMenuName1.setText(model.getName1());
//                viewHolder.txtMenuPrice1.setText(model.getPrice1());
                    Picasso.with(getBaseContext()).load(model.getImage1())
                            .into(viewHolder.imageView1);

                    final Category clickItem = model;
                    viewHolder.setItemClickListener(new ItemClickListener() {
                        @Override
                        public void onClick(View view, int position, boolean isLongClick) {
//
                            // Get Category id and Start new activity
                            Intent foodList = new Intent(HomeActivity.this, FoodListActivity.class);
                            foodList.putExtra("CategoryId", adapter.getRef(position).getKey());
                            startActivity(foodList);

                        }
                    });

                }
            };
            recycler_menu.setAdapter(adapter);

        }
        else
        {
            Toast.makeText(HomeActivity.this, "Please check your internet connection !!", Toast.LENGTH_SHORT).show();
            return;
        }
    }
}
