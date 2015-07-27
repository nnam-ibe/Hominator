package seg.user.interface3125.thehominator;

import java.util.Calendar;
import java.util.concurrent.TimeUnit;

/**
 * Created by nnamdi on 15-07-27.
 */
public class BillContent {

    protected String title;
    protected Calendar dueDate;
    protected int amount;
    protected boolean isPast;

    public BillContent(String title, Calendar dueDate, int amount) {
        this.title = title;
        this.dueDate = dueDate;
        this.amount = amount;
        if( dueDate.after(Calendar.getInstance())) {
            isPast = true;
        }
        else { isPast = false;}

    }

    public String getDueDate() {
        Calendar today = Calendar.getInstance();
        if( dueDate.after(today)) {
            isPast = false;
            int diff = (int) (long) daysBetween(today, dueDate);
            if ( diff <= 7 ){
                    return "Due in " + diff + " days.";
            } else {
                return "Due on " + dueDate.get(Calendar.DAY_OF_MONTH) + "/" + dueDate.get(Calendar.MONTH) + "/" + dueDate.get(Calendar.YEAR);
            }
        } else {
            isPast = true;
            return "was due on " + dueDate.get(Calendar.DAY_OF_MONTH) + "/" + dueDate.get(Calendar.MONTH) + "/" + dueDate.get(Calendar.YEAR);
        }
    }

    public static long daysBetween(Calendar startDate, Calendar endDate) {
        long end = endDate.getTimeInMillis();
        long start = startDate.getTimeInMillis();
        return TimeUnit.MILLISECONDS.toDays(Math.abs(end - start));
    }
}
