package android.test.my.drawerlayout;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Created by ARTEM on 31.10.2016.
 */

public class MyMenuAdapter extends BaseAdapter {
    private Context context;
    private LayoutInflater inflater;
    private String[] strings = {"First Menu", "Second Menu", "Third Menu"};

    public MyMenuAdapter(Context context) {
        this.context = context;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return strings.length;
    }

    @Override
    public Object getItem(int position) {
        return strings[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = inflater.inflate(R.layout.layout_for_menu, parent, false);
            TextView textView = (TextView) view.findViewById(R.id.menu_txt);
            textView.setText(strings[position]);
        }
        return view;
    }
}
