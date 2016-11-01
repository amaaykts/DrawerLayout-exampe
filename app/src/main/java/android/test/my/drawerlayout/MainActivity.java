package android.test.my.drawerlayout;


import android.content.res.Configuration;
import android.os.PersistableBundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {
    public static String STRINGS_ARRAY = "STRINGS_ARRAY";
    private Fragment fragment;
    private FragmentManager fragmentManager;
    private ListView listViewMenu;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;

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

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.drawer_open, R.string.drawer_close){
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
        };
        drawerLayout.addDrawerListener(actionBarDrawerToggle);

    }

    @Override
    public void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        actionBarDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        actionBarDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
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
