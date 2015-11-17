package com.softwareengineering_androidcalendar;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Neophyte on 2015/11/09.
 */
public class EventListByDateAdapter extends BaseAdapter{
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private static final SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");

    /** The e context. */
    private Context eContext;

    /** The txt event. */
    private TextView[] txtEvent;
    Category ctg;
    private String startDate;
    private String endDate;

    /** The all events. */
    private final List<Event> dayEvents;
    private final List<Event> allEvents;
    private Date stDate;
    private Date eDate;
    private Calendar _calendar;

    public EventListByDateAdapter(Context c, String startDate, String endDate) {
        eContext =c;
        this.startDate=startDate;
        this.endDate = endDate;

        // Getting all Events
        Log.d("Get Events", "Getting All Events");

        dayEvents = new ArrayList<Event>();
        try {
            stDate = dateFormat.parse(startDate);
            eDate = dateFormat.parse(endDate);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        // Getting all Events
        Log.d("Get Events", "Getting All Events");

        allEvents = SimpleCalendarView.db.getAllEvents();
        for (Event event : allEvents) {
            Date eventStartDate =new Date();
            try {
                eventStartDate = dateFormat.parse(event.getStartDate());
            } catch (ParseException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            if(((eventStartDate.compareTo(stDate)>=0)
                    && (eventStartDate.compareTo(eDate)<=0))) {
                dayEvents.add(event);
                Log.d("Event:"+event.getTitle(),"ID:"+ event.getId()+"Description:"+event.getDescription());
            }
        }
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return dayEvents.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return dayEvents.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        txtEvent = new TextView[dayEvents.size()];

        TextView txt1 = new TextView(eContext);
        txt1.setText(dayEvents.get(position).getTitle());
        txt1.setLayoutParams(new GridView.LayoutParams(GridView.LayoutParams.MATCH_PARENT, GridView.LayoutParams.WRAP_CONTENT));
        txt1.setBackgroundColor(Color.parseColor("#FFFFFF"));
        txt1.setTextColor(Color.parseColor("#000000"));
        txt1.setTag(Long.toString(dayEvents.get(position).getId()));
        return txt1;
    }
}
