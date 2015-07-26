package seg.user.interface3125.thehominator;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nnamdi on 15-07-21.
 */
public class Feed_Fragment extends Fragment {
    private RecyclerView mRecyclerView;
    private FeedAdapter fAdapter;
    public Feed_Fragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_feed, container, false);
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.feed_recycler_view);

        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(llm);

        fAdapter = new FeedAdapter(setUpFeed());
        mRecyclerView.setAdapter(fAdapter);

        return rootView;
    }

    private List setUpFeed() {
        List<FeedContent> result = new ArrayList<FeedContent>();
        result.add( new FeedContent(getString(R.string.feed_water_title), getString(R.string.feed_water_desc)) );
        result.add( new FeedContent(getString(R.string.feed_pool_title), getString(R.string.feed_pool_desc)) );
        result.add( new FeedContent(getString(R.string.feed_fire_title), getString(R.string.feed_fire_desc)) );
        result.add( new FeedContent(getString(R.string.feed_garage_title), getString(R.string.feed_garage_desc)) );
        result.add( new FeedContent(getString(R.string.feed_bike_title), getString(R.string.feed_bike_desc)) );
        result.add( new FeedContent(getString(R.string.feed_bbq_title), getString(R.string.feed_bbq_desc)) );
        result.add( new FeedContent(getString(R.string.feed_canada_title), getString(R.string.feed_canada_desc)) );
        return result;
    }

}
