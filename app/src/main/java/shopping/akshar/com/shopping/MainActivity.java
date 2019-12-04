package shopping.akshar.com.shopping;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


import shopping.akshar.com.shopping.activity.LoginActivity;
import shopping.akshar.com.shopping.adapter.Productadapter;
import shopping.akshar.com.shopping.cart.cart_jdbc;
import shopping.akshar.com.shopping.fragment.CartFragment;
import shopping.akshar.com.shopping.fragment.DashboardFragment;
import shopping.akshar.com.shopping.fragment.GraidFragment;
import shopping.akshar.com.shopping.fragment.HomeFragment;
import shopping.akshar.com.shopping.fragment.IncrementFragment;
import shopping.akshar.com.shopping.fragment.ProductFragment;
import shopping.akshar.com.shopping.fragment.PurchaseHistroyFragment;
import shopping.akshar.com.shopping.fragment.UploadFragment;
import shopping.akshar.com.shopping.fragment.View_profileFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    TextView username;
    FirebaseAuth auth;
    FirebaseUser firebaseUser;
    SharedPreferences sharedPreferences;
    Productadapter productadapter;

    private static final String PREF_NAME = "Shopping";
    private static final String KEY_EMAIL = "Email";
    private static final String KEY_USER_ID = "User UID";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        android.support.v4.app.FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction ft = manager.beginTransaction();
        ft.replace(R.id.frame_main, new ProductFragment());
        ft.addToBackStack(null);
        ft.commit();



        username = findViewById(R.id.username);
        auth = FirebaseAuth.getInstance();
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        sharedPreferences = getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);

        BottomNavigationView bottomnav = findViewById(R.id.navbar);
        bottomnav.setOnNavigationItemSelectedListener(navLister);


    }

    private BottomNavigationView.OnNavigationItemSelectedListener navLister = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

            Fragment selectedFragment = null;
            switch (menuItem.getItemId()) {
                case R.id.backhome:
                    android.support.v4.app.FragmentManager manager = getSupportFragmentManager();
                    FragmentTransaction ft = manager.beginTransaction();
                    ft.replace(R.id.frame_main, new ProductFragment());
                    ft.addToBackStack(null);
                    ft.commit();
                    break;
                case R.id.cardnav:
                    android.support.v4.app.FragmentManager manager1 = getSupportFragmentManager();
                    FragmentTransaction ft1 = manager1.beginTransaction();
                    ft1.replace(R.id.frame_main, new CartFragment());
                    ft1.addToBackStack(null);
                    ft1.commit();
                    break;
                case R.id.accountnav:
                    android.support.v4.app.FragmentManager manager3 = getSupportFragmentManager();
                    FragmentTransaction ft2 = manager3.beginTransaction();
                    ft2.replace(R.id.frame_main, new View_profileFragment());
                    ft2.addToBackStack(null);
                    ft2.commit();
                    break;
                case R.id.homenav:
                    android.support.v4.app.FragmentManager manager4 = getSupportFragmentManager();
                    FragmentTransaction ft4 = manager4.beginTransaction();
                    ft4.replace(R.id.frame_main, new DashboardFragment());
                    ft4.addToBackStack(null);
                    ft4.commit();
                    break;
                default:
                    break;
            }

            return true;
        }
    };

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
        // ExitDialog();
    }

    /* private void ExitDialog() {
         final AlertDialog.Builder builder = new AlertDialog.Builder(this);
         builder.setCancelable(false);
         builder.setTitle("Exit");
         builder.setMessage("Are You Sure Want To Exit !");

         builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
             @Override
             public void onClick(DialogInterface dialog, int which) {
                 finish();
             }
         });

         builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
             @Override
             public void onClick(DialogInterface dialog, int which) {
                 dialog.dismiss();
             }
         });

         builder.create().show();
 */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);


        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {


            case R.id.notification:
                android.support.v4.app.FragmentManager manager1 = getSupportFragmentManager();
                FragmentTransaction ft1 = manager1.beginTransaction();
                ft1.replace(R.id.frame_main, new CartFragment());
                ft1.addToBackStack(null);
                ft1.commit();
                break;


            default:
                break;
        }


        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        } else if (id == R.id.logout) {
            /*Logout code*/

            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.remove(KEY_EMAIL);
            Log.d("LoggedOut", KEY_EMAIL);
            editor.remove(KEY_USER_ID);
            Log.d("Key", firebaseUser.getUid());
            editor.apply();

            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();

        } else if (id == R.id.increment) {
            android.support.v4.app.FragmentManager manager = getSupportFragmentManager();
            FragmentTransaction ft = manager.beginTransaction();
            ft.replace(R.id.frame_main, new IncrementFragment());
            ft.addToBackStack(null);
            ft.commit();

        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            android.support.v4.app.FragmentManager manager = getSupportFragmentManager();
            FragmentTransaction ft = manager.beginTransaction();
            ft.replace(R.id.frame_main, new HomeFragment());
            ft.addToBackStack(null);
            ft.commit();

        } else if (id == R.id.nav_profile) {
            android.support.v4.app.FragmentManager manager = getSupportFragmentManager();
            FragmentTransaction ft = manager.beginTransaction();
            ft.replace(R.id.frame_main, new View_profileFragment());
            ft.addToBackStack(null);
            ft.commit();

        } else if (id == R.id.nav_cart) {
           /* android.support.v4.app.FragmentManager manager = getSupportFragmentManager();
            FragmentTransaction ft = manager.beginTransaction();
            ft.replace(R.id.frame_main, new CartFragment());
            ft.addToBackStack(null);
            ft.commit()*/;
            Intent intent = new Intent(MainActivity.this, cart_jdbc.class);
            startActivity(intent);
        } else if (id == R.id.nav_purchase) {
            FragmentManager manager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = manager.beginTransaction();
            fragmentTransaction.replace(R.id.frame_main, new PurchaseHistroyFragment());
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();

        } else if (id == R.id.nav_graid) {
            FragmentManager manager = getSupportFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.replace(R.id.frame_main, new GraidFragment());
            transaction.addToBackStack(null);
            transaction.commit();

        } else if (id == R.id.nav_upload) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.replace(R.id.frame_main, new UploadFragment());
            transaction.addToBackStack(null);
            transaction.commit();
        }


        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
