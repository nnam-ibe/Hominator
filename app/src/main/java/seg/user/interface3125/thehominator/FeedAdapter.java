package seg.user.interface3125.thehominator;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by nnamdi on 15-07-26.
 */
public class FeedAdapter extends RecyclerView.Adapter<FeedAdapter.FeedViewHolder> {

    private List<FeedContent> feedList;

    public FeedAdapter(List<FeedContent> feedList) {
        this.feedList = feedList;
    }

    @Override
    public FeedViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.feed_card_layout, parent, false);
        // set the view's size, margins, paddings and layout parameters
        return new FeedViewHolder(v);
    }

    @Override
    public void onBindViewHolder(FeedViewHolder fViewHolder, int i) {
        FeedContent ci = feedList.get(i);
        fViewHolder.title.setText(ci.title);
        fViewHolder.desc.setText(ci.desc);
    }

    @Override
    public int getItemCount() {
        return feedList.size();
    }

    public static class FeedViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public TextView desc;
        public View line;

        int rand;

        public FeedViewHolder(View v) {

            super(v);
            title = (TextView) v.findViewById(R.id.feed_title);
            desc = (TextView) v.findViewById(R.id.feed_desc);
            line = (View) v.findViewById(R.id.feedLine);

                title.setBackgroundColor(Color.parseColor("#4E7AC7"));
                line.setBackgroundColor(Color.parseColor("#4E7AC7"));

        }
    }

}
