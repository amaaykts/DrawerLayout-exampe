package android.test.my.drawerlayout;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {
    public static String STRINGS_ARRAY = "STRINGS_ARRAY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView listViewMenu = (ListView) findViewById(R.id.list_view_menu);
        Fragment fragment = new MyStartFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.frame_layout, fragment).commit();

        listViewMenu.setAdapter(new MyMenuAdapter(this));

        listViewMenu.setOnItemClickListener(new MyMenuClickListener());
    }

    private class MyMenuClickListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            changeFragment(position);
        }

        public void changeFragment(int position) {
            String[] strings = {(position + 1) + "-1", (position + 1) + "-2", (position + 1) + "-3"};
            Fragment fragment = new MyFragment();
            FragmentManager fragmentManager = getSupportFragmentManager();
            Bundle bundle = new Bundle();

            bundle.putStringArray(STRINGS_ARRAY, strings);
            fragment.setArguments(bundle);

            fragmentManager.beginTransaction().replace(R.id.frame_layout, fragment).commit();

            ListView listViewMenu = (ListView) findViewById(R.id.list_view_menu);
            DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

            setTitle((position + 1) + "");
            drawerLayout.closeDrawer(listViewMenu);
        }
    }
}
