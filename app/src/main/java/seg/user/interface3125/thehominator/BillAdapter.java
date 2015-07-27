package seg.user.interface3125.thehominator;

import android.app.AlertDialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by nnamdi on 15-07-27.
 */
public class BillAdapter extends RecyclerView.Adapter<BillAdapter.BillViewHolder> {

    private List<BillContent> billList;

    public BillAdapter(List<BillContent> billList) {
        this.billList = billList;
    }

    @Override
    public BillViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.bill_card_layout, parent, false);
        // set the view's size, margins, paddings and layout parameters
        return new BillViewHolder(v);
    }

    @Override
    public void onBindViewHolder(BillViewHolder holder, int position) {
        BillContent bc = billList.get(position);
        holder.title.setText(bc.title);
        holder.amount.setText("$"+bc.amount + "");
        if ( bc.isPast ){
            holder.dueDate.setTextColor(Color.RED);
        }
        holder.dueDate.setText(bc.getDueDate());
    }

    @Override
    public int getItemCount() {
        return billList.size();
    }

    public static class BillViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView title;
        public TextView dueDate;
        public TextView amount;

        public BillViewHolder(View v) {
            super(v);
            title = (TextView) v.findViewById(R.id.bill_title_textView);
            dueDate = (TextView) v.findViewById(R.id.bill_dueTextView);
            amount = (TextView) v.findViewById(R.id.bill_amountTextView);
        }

        @Override
        public void onClick(View v) {
            if (v instanceof CardView ) {
//                pop(View v);
            }
        }

    }

}
