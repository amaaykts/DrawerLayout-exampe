package android.test.my.drawerlayout;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class MyFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        View view = inflater.inflate(R.layout.layout_for_dynamics_fragment, container, false);
        ListView listView = (ListView)view.findViewById(R.id.list_view_content);

        listView.setAdapter(new MyContentAdapter(getContext(), bundle.getStringArray(MainActivity.STRINGS_ARRAY)));
        return view;
    }
}
