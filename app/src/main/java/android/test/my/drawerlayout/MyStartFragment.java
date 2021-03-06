package android.test.my.drawerlayout;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class MyStartFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_for_dynamics_fragment, container, false);
        ListView listView = (ListView)view.findViewById(R.id.list_view_content);
        String[] strings = {"First content", "Second content", "Third content"};

        listView.setAdapter(new MyContentAdapter(getContext(), strings));
        return view;
    }
}
