package seg.user.interface3125.thehominator;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by nnamdi on 15-07-21.
 */
public class Bills_Fragment extends Fragment{
    public Bills_Fragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_bills, container, false);

        return rootView;
    }
}
