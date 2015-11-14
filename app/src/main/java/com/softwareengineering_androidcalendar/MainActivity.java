package com.softwareengineering_androidcalendar;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.LocalActivityManager;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

import java.util.Calendar;

public class MainActivity  extends Activity implements OnClickListener {
/*
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }*/


    /** The Constant tag. */
    private static final String tag = "SimpleCalendarViewActivity";

    /** The calendar to journal button. */
    private ImageView calendarToJournalButton;

    /** The selected day month year button. */
    private Button selectedDayMonthYearButton;

    /** The current month. */
    private Button currentMonth;

    /** The prev month. */
    private ImageView prevMonth;

    /** The next month. */
    private ImageView nextMonth;

    /** The calendar view. */
    private GridView calendarView;

    /** The adapter. */
    private SimpleCalendarView adapter;

    /** The _calendar. */
    private Calendar _calendar;

    /** The year. */
    private int month, year;

    /** The tab host. */
    public static TabHost tabHost;

    /** The date formatter. */
    private final DateFormat dateFormatter = new DateFormat();

    /** The Constant dateTemplate. */
    private static final String dateTemplate = "MMMM yyyy";

    /** The i. */
    public static int i=0;

    /** The j. */
    public static int j =0;

    //public static MySQLiteHelper db;


    /* (non-Javadoc)
     * @see android.app.Activity#onCreate(android.os.Bundle)
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calendar_tab_view);

        Resources ressources = getResources();
        tabHost = (TabHost) findViewById(R.id.tabhost);
        LocalActivityManager mLocalActivityManager = new LocalActivityManager(this, false);
        mLocalActivityManager.dispatchCreate(savedInstanceState);
        tabHost.setup(mLocalActivityManager);

        //Monthly
        Intent intentMonthly = new Intent().setClass(this, MonthlyView.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        TabSpec tabSpecMonthly = tabHost
                .newTabSpec("Month")
                .setIndicator("", ressources.getDrawable(R.drawable.monthly_view))
                .setContent(intentMonthly);

        Intent intentWeekly = new Intent().setClass(this, WeeklyView.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        TabSpec tabSpecWeekly = tabHost
                .newTabSpec("Week")
                .setIndicator("", ressources.getDrawable(R.drawable.weekly_view))
                .setContent(intentWeekly);

        Intent intentDaily = new Intent().setClass(this, DailyView.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        TabSpec tabSpecDaily = tabHost
                .newTabSpec("Day")
                .setIndicator("", ressources.getDrawable(R.drawable.daily_view))
                .setContent(intentDaily);

        Intent intentEvent = new Intent().setClass(this, EventList.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        TabSpec tabSpecEvent = tabHost
                .newTabSpec("Event")
                .setIndicator("", ressources.getDrawable(R.drawable.calendar_add_event))
                .setContent(intentEvent);

        //addIng Tab
        tabHost.addTab(tabSpecMonthly);
        tabHost.addTab(tabSpecWeekly);
        tabHost.addTab(tabSpecDaily);
        tabHost.addTab(tabSpecEvent);

        //deafualt tab
        tabHost.setCurrentTab(0);
    }

    /* (non-Javadoc)
     * @see android.app.Activity#onCreateOptionsMenu(android.view.Menu)
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    /**
     * Gets the my tab host.
     *
     * @return the my tab host
     */
    public TabHost getMyTabHost() { return tabHost; }

    /* (non-Javadoc)
     * @see android.view.View.OnClickListener#onClick(android.view.View)
     */
    @Override
    public void onClick(View v) {
    }
    /* (non-Javadoc)
     * @see android.app.Activity#onOptionsItemSelected(android.view.MenuItem)
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.add_event:
                Intent ev= new Intent(this, EventActivity.class);
                startActivity(ev);
                return true;

            case R.id.event_date:
                Intent dt= new Intent(this, EventListByDate.class);
                startActivity(dt);
                return true;

            case R.id.event_category:
                Intent ct= new Intent(this, EventListByCategory.class);
                startActivity(ct);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /* (non-Javadoc)
     * @see android.app.Activity#onDestroy()
     */
    @SuppressLint("LongLogTag")
    @Override
    public void onDestroy() {
        SimpleCalendarView.db.closeDB();
        Log.d(tag, "Destroying View ...");
        super.onDestroy();
    }
}
