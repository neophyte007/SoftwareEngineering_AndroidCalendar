package com.softwareengineering_androidcalendar;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

/**
 * Created by j on 2015/11/13.
 */
public class EventListAdapter extends BaseAdapter{
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private static final SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
    private Date currentDate;
    private Date currentTime;
    private Calendar _calendar;

    /** The e context. */
    private Context eContext;

    /** The txt event. */
    private TextView[] txtEvent;

    /** The all events. */
    private final List<Event> allEvents;
    private final List<Event> futureEvents;

    /** The Constant numbers. */
    static final String[] numbers = new String[] {
            "A", "B", "C", "D", "E",
            "F", "G", "H", "I", "J",
            "K", "L", "M", "N", "O",
            "P", "Q", "R", "S", "T",
            "U", "V", "W", "X", "Y", "Z"};

    /**
     * Instantiates a new event list adapter.
     *
     * @param c the c
     */
    public EventListAdapter (Context c) {
        eContext =c;
        futureEvents =new ArrayList<Event>();
        _calendar = Calendar.getInstance(Locale.getDefault());
        try {
            currentDate = dateFormat.parse(dateFormat.format(_calendar.getTime()));
            currentTime = sdf.parse(sdf.format(_calendar.getTime()));
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        // Getting all Events
        Log.d("Get Events", "Getting All Events");

        allEvents = SimpleCalendarView.db.getAllEvents();
        for (Event event : allEvents) {
            Date eventDate =new Date();
            Date eventTime = new Date();

            try {
                eventDate = dateFormat.parse(event.getStartDate());
                eventTime = sdf.parse(event.getStartTime());
            } catch (ParseException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            if(((eventDate.compareTo(currentDate)==0)
                    && (eventTime.compareTo(currentTime)>=0))||(eventDate.compareTo(currentDate)>0)) {
                futureEvents.add(event);
                Log.d("Event:"+event.getTitle(),"ID:"+ event.getId()+"Description:"+event.getDescription());
            }
        }
    }

    /* (non-Javadoc)
     * @see android.widget.Adapter#getCount()
     */
    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return futureEvents.size();
    }

    /* (non-Javadoc)
     * @see android.widget.Adapter#getItem(int)
     */
    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return futureEvents.get(position);
    }

    /* (non-Javadoc)
     * @see android.widget.Adapter#getItemId(int)
     */
    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return 0;
    }

    /* (non-Javadoc)
     * @see android.widget.Adapter#getView(int, android.view.View, android.view.ViewGroup)
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        txtEvent = new TextView[futureEvents.size()];
        TextView txt1 = new TextView(eContext);
        txt1.setText(futureEvents.get(position).getTitle());
        txt1.setLayoutParams(new GridView.LayoutParams(GridView.LayoutParams.MATCH_PARENT, GridView.LayoutParams.WRAP_CONTENT));
        return txt1;
    }
}
