package android.test.my.drawerlayout;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView listViewMenu = (ListView) findViewById(R.id.list_view_menu);
        ListView listViewContent = (ListView) findViewById(R.id.list_view_content);


        listViewMenu.setAdapter(new MyMenuAdapter(this));
        listViewContent.setAdapter(new MyContentAdapter(this));

        listViewMenu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this,"Menu " + position,Toast.LENGTH_SHORT).show();
            }
        });

        listViewContent.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this,"Content " + position,Toast.LENGTH_SHORT).show();
            }
        });
    }
}
