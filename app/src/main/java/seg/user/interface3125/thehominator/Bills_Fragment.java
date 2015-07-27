package seg.user.interface3125.thehominator;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by nnamdi on 15-07-21.
 */
public class Bills_Fragment extends Fragment{
    private RecyclerView mRecyclerView;
    private BillAdapter billAdapter;
    public Bills_Fragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_bills, container, false);
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.bills_recycler_view);

        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(llm);

        billAdapter = new BillAdapter(setUpBills());
        mRecyclerView.setAdapter(billAdapter);
        return rootView;
    }

    private List setUpBills(){
        List<BillContent> result = new ArrayList<BillContent>();
        int amount = Integer.valueOf(getString(R.string.amount));
        Calendar cal = Calendar.getInstance();
        cal.set(2015, 7, 29);
        result.add(new BillContent(getString(R.string.rent), cal, amount));
        return result;
    }


}
