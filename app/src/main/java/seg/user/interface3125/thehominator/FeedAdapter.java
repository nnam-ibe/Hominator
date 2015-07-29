package seg.user.interface3125.thehominator;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;
import java.util.Random;

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

    public static int randInt(int min, int max) {

        // NOTE: Usually this should be a field rather than a method
        // variable so that it is not re-seeded every call.
        Random rand = new Random();

        // nextInt is normally exclusive of the top value,
        // so add 1 to make it inclusive
        int randomNum = rand.nextInt((max - min) + 1) + min;

        return randomNum;
    }

    public static class FeedViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public TextView desc;
        public View line;

        int rand;

        public FeedViewHolder(View v) {

            super(v);
            rand = randInt(1,4);
            System.out.println("Rand: "+rand);
            title = (TextView) v.findViewById(R.id.feed_title);
            desc = (TextView) v.findViewById(R.id.feed_desc);
            line = (View) v.findViewById(R.id.feedLine);

            if(rand == 1){
                title.setBackgroundColor(Color.parseColor("#E65100"));
                line.setBackgroundColor(Color.parseColor("#E65100"));
            }
            if(rand == 2){
                title.setBackgroundColor(Color.parseColor("#388e3c"));
                line.setBackgroundColor(Color.parseColor("#388e3c"));
            }

            if(rand == 3){
                title.setBackgroundColor(Color.parseColor("#1565C0"));
                line.setBackgroundColor(Color.parseColor("#1565C0"));
            }

            if(rand == 4){
                title.setBackgroundColor(Color.parseColor("#AD1457"));
                line.setBackgroundColor(Color.parseColor("#AD1457"));
            }
            else{
                title.setBackgroundColor(Color.parseColor("#37474F"));
                line.setBackgroundColor(Color.parseColor("#37474F"));
            }
        }
    }

}
