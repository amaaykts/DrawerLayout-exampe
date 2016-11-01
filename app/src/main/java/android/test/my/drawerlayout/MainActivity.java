package android.test.my.drawerlayout;


import android.content.res.Configuration;
import android.os.PersistableBundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public static String STRINGS_ARRAY = "STRINGS_ARRAY";
    private Fragment fragment;
    private FragmentManager fragmentManager;
    private ListView listViewMenu;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle drawerListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listViewMenu = (ListView) findViewById(R.id.list_view_menu);

        fragment = new MyStartFragment();
        fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.frame_layout, fragment).commit();

        listViewMenu.setAdapter(new MyMenuAdapter(this));

        listViewMenu.setOnItemClickListener(new MyMenuClickListener());

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
//        drawerLayout.setDrawerShadow(R.drawable.ic_drawer, GravityCompat.START); //Тень можно сделать


//        Toolbar toolbar = new Toolbar(this);
//        setSupportActionBar(toolbar);

//        toolbar.setNavigationIcon(R.drawable.ic_drawer);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setIcon(R.drawable.ic_drawer);

        drawerListener = new ActionBarDrawerToggle(this, drawerLayout, R.string.drawer_open, R.string.drawer_close){

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
//                Toast.makeText(MainActivity.this,"Close",Toast.LENGTH_SHORT).show();
                supportInvalidateOptionsMenu();
            }
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
//                Toast.makeText(MainActivity.this,"Open",Toast.LENGTH_SHORT).show();
                supportInvalidateOptionsMenu();
            }

        };

        drawerLayout.addDrawerListener(drawerListener);

    }

    @Override
    public void onPostCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onPostCreate(savedInstanceState, persistentState);
        drawerListener.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerListener.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (drawerListener.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private class MyMenuClickListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            changeFragment(position);
        }

        public void changeFragment(int position) {
            String[] strings = {(position + 1) + "-1", (position + 1) + "-2", (position + 1) + "-3"};
            fragment = new MyFragment();
            fragmentManager = getSupportFragmentManager();
            Bundle bundle = new Bundle();

            bundle.putStringArray(STRINGS_ARRAY, strings);
            fragment.setArguments(bundle);

            fragmentManager.beginTransaction().replace(R.id.frame_layout, fragment).commit();

            listViewMenu = (ListView) findViewById(R.id.list_view_menu);
            drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

            getSupportActionBar().setTitle((position + 1) + "");
            drawerLayout.closeDrawer(listViewMenu);
//            listViewMenu.setItemChecked(position, true);
//            drawerLayout.addDrawerListener();
        }
    }
}
