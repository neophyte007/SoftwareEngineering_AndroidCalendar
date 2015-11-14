package com.softwareengineering_androidcalendar;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

/**
 * Created by j on 2015/11/13.
 */
public class EventActivity extends Activity implements OnClickListener, OnItemSelectedListener {

    /** The Constant tag. */
    private static final String tag = "EventActivity";

    /** The to_ date. */
    public static String to_Date;

    /** The from_ date. */
    public static String from_Date;

    /** The to_ time. */
    public static String to_Time;

    /** The from_ time. */
    public static String from_Time;

    /** The categary_from_ spinner. */
    private static String categary_from_Spinner;

    /** The categary_color. */
    private static String categary_color;

    /** The all category. */
    private static  List<Category> allCategory;

    /** The spinner array. */
    private static ArrayList<String> spinnerArray;

    /** The spinn_repeat. */
    private Spinner spinn_repeat;

    /** The repeat. */
    private String repeat;

    /** The to todays date. */
    public static Button toTodaysDate;

    /** The from todays date. */
    public static Button fromTodaysDate;

    /** The to current time. */
    public static Button toCurrentTime;

    /** The from current time. */
    public static Button fromCurrentTime;

    /** The ctg. */
    private static Category ctg;

    /** The event2. */
    public static Event event2;

    /** The spinn. */
    private Spinner spinn;

    /** The save button. */
    private Button saveButton;

    /** The cancel button. */
    private Button cancelButton;

    /** The edittext_event title. */
    private EditText edittext_eventTitle;

    /** The edittext_description. */
    private EditText edittext_description;

    /** The category_name. */
    private EditText category_name;

    /** The spinn_category_color. */
    private Spinner spinn_category_color;

    /** The _calendar. */
    private Calendar _calendar;

    /** The date formatter. */
    private final DateFormat dateFormatter = new DateFormat();

    /** The Constant dateTemplate. */
    private static final String dateTemplate = "yyyy-MM-dd";

    /** The days of month. */
    private final int[] daysOfMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        _calendar = Calendar.getInstance(Locale.getDefault());
        TextView textview = new TextView(this);
        textview.setText("This is Event tab");
        setContentView(R.layout.calendar_event);

        toTodaysDate = (Button) this.findViewById(R.id.toTodaysDate);
        toTodaysDate.setText(dateFormatter.format(dateTemplate, _calendar.getTime()));
        to_Date = dateFormatter.format(dateTemplate, _calendar.getTime()).toString();
        toTodaysDate.setOnClickListener(this);

        fromTodaysDate = (Button) this.findViewById(R.id.fromTodaysDate);
        fromTodaysDate.setText(dateFormatter.format(dateTemplate, _calendar.getTime()));
        from_Date = dateFormatter.format(dateTemplate, _calendar.getTime()).toString();
        fromTodaysDate.setOnClickListener(this);

        toCurrentTime = (Button) this.findViewById(R.id.toCurrentTime);
        toCurrentTime.setText(sdf.format(_calendar.getTime()));
        to_Time = sdf.format(_calendar.getTime()).toString();
        toCurrentTime.setOnClickListener(this);

        fromCurrentTime = (Button) this.findViewById(R.id.fromCurrentTime);
        fromCurrentTime.setText(sdf.format(_calendar.getTime()));
        from_Time = sdf.format(_calendar.getTime()).toString();
        fromCurrentTime.setOnClickListener(this);

        spinn_repeat = (Spinner) findViewById(R.id.spinner_repeat);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.repeat, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinn_repeat.setAdapter(adapter);
        spinn_repeat.setOnItemSelectedListener(this);
        spinn = (Spinner) findViewById(R.id.spinner1);
        allCategory = SimpleCalendarView.db.getAllCategories();
        spinnerArray = new ArrayList<String>();
        for (Category category : allCategory) {
            spinnerArray.add(category.getName());
        }
        spinnerArray.add("Add Category");
        ArrayAdapter spinnerArrayAdapter = new ArrayAdapter(this,
                android.R.layout.simple_spinner_dropdown_item,
                spinnerArray);

        // Specify the layout to use when the list of choices appears
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinn.setAdapter(spinnerArrayAdapter);
        spinn.setOnItemSelectedListener(this);

        saveButton = (Button) this.findViewById(R.id.saveButton);
        saveButton.setOnClickListener(this);

        cancelButton = (Button) this.findViewById(R.id.cancelButton);
        cancelButton.setOnClickListener(this);

        edittext_eventTitle = (EditText) this.findViewById(R.id.editText1);
        edittext_description = (EditText) this.findViewById(R.id.editText6);

        category_name = (EditText) this.findViewById(R.id.editText12);
        category_name.setVisibility(View.INVISIBLE);
        category_name.setText("Name");
        spinn_category_color = (Spinner) this.findViewById(R.id.spinner2);
        spinn_category_color.setVisibility(View.INVISIBLE);
        spinn_category_color.setOnItemSelectedListener(this);
    }

    /* (non-Javadoc)
     * @see android.view.View.OnClickListener#onClick(android.view.View)
     */
    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        if(v== toTodaysDate ) {
            Intent intent = new Intent(this, DatePickerActivity.class);
            intent.putExtra("Caller", "To_Date_event");
            startActivityForResult(intent, 0);
            Log.d(tag,"Inside to todays date" );
        }
        else if(v== fromTodaysDate ) {
            Intent intent = new Intent(this, DatePickerActivity.class);
            intent.putExtra("Caller", "From_Date_event");
            startActivityForResult(intent, 0);
            Log.d(tag,"Inside from todays date");
        }
        else if(v== toCurrentTime ) {
            Intent intent = new Intent(this, TimePickerActivity.class);
            intent.putExtra("Caller", "To_Time_event");
            startActivityForResult(intent, 0);
            Log.d(tag,"Inside to current time");
        }
        else if(v== fromCurrentTime ) {
            Intent intent = new Intent(this, TimePickerActivity.class);
            intent.putExtra("Caller", "From_Time_event");
            startActivityForResult(intent, 0);
            Log.d(tag,"Inside from current time");
        }
        else if(v== cancelButton ) {
            this.finish();
            Log.d(tag,"Inside cancel buttun");
        }
        else if(v== saveButton ) {
            Event event_new = new Event(edittext_eventTitle.getText().toString(),
                    to_Date,from_Date,to_Time,from_Time,
                    edittext_description.getText().toString(), repeat);
            try {
                if(repeat.equals("Repeat-ON")){
                    String[] getmonth = to_Date.split("-");
                    int daysInMonth = daysOfMonth[Integer.parseInt(getmonth[1])-1];
                    int selectedDate = Integer.parseInt(getmonth[2]);
                    boolean checkConflict=true;
                    String startDt= to_Date;

                    for(int i = selectedDate;i <= daysInMonth;i = i+7) {
                        Event ev = new Event(edittext_eventTitle.getText().toString(),
                                startDt,startDt,to_Time,from_Time,
                                edittext_description.getText().toString(), repeat);
                        checkConflict = checkConflict && SimpleCalendarView.db.checkConflictinEvents(ev);
                        String[] getDate = startDt.split("-");
                        startDt = getDate[0]+"-"+getDate[1]+"-"+TimePickerActivity.padding_str(Integer.parseInt(getDate[2])+7);
                    }
                    if(checkConflict) {
                        String start_Dt = to_Date;
                        if(EventActivity.categary_from_Spinner.equalsIgnoreCase("Add Category")) {
                            ctg = new Category(category_name.getText().toString(), EventActivity.categary_color);
                            long ctg1_id = SimpleCalendarView.db.createCategory(ctg);
                            ctg.setId(ctg1_id);
                        }

                        for(int i = selectedDate;i <= daysInMonth;i = i+7) {
                            Event ev = new Event(edittext_eventTitle.getText().toString(),
                                    start_Dt,start_Dt,to_Time,from_Time,
                                    edittext_description.getText().toString(), repeat);

                            Log.d(tag,"Inside Repeat save button:no conflict ");
                            long event_current_id = SimpleCalendarView.db.createEvent(ev, new long[] { ctg.getId() });

                            //this.finish();
                            String[] getDate = start_Dt.split("-");
                            start_Dt = getDate[0]+"-"+getDate[1]+"-"+TimePickerActivity.padding_str(Integer.parseInt(getDate[2])+7);
                        }
                        MainActivity.tabHost.setCurrentTab(0);
                        this.finish();
                    }

                    else {
                        Log.d(tag,"Inside Repeat save button: conflict ");
                            // Creating alert Dialog with one Button

                            AlertDialog alertDialog1 = new AlertDialog.Builder(
                                    this).create();

                            // Setting Dialog Title
                            alertDialog1.setTitle("Alert Dialog");

                            // Setting Dialog Message
                            alertDialog1.setMessage("Repeat Events conflict: Change the time!");

                            // Setting OK Button
                            alertDialog1.setButton("OK", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {}
                            });
                            alertDialog1.show();
                    }
                }

                else {
                    if(SimpleCalendarView.db.checkConflictinEvents(event_new)) {
                        if(EventActivity.categary_from_Spinner.equalsIgnoreCase("Add Category")) {
                            ctg = new Category(category_name.getText().toString(), EventActivity.categary_color);
                            long ctg1_id = SimpleCalendarView.db.createCategory(ctg);
                            ctg.setId(ctg1_id);
                        }
                        Log.d(tag,"Inside save button:no conflict ");
                        long event_current_id = SimpleCalendarView.db.createEvent(event_new, new long[] { ctg.getId() });
                        MainActivity.tabHost.setCurrentTab(0);
                        this.finish();
                    }
                    else {
                        Log.d(tag,"Inside save button: conflict ");
                        // Creating alert Dialog with one Button

                        AlertDialog alertDialog1 = new AlertDialog.Builder(
                                this).create();

                        // Setting Dialog Title
                        alertDialog1.setTitle("Alert Dialog");

                        // Setting Dialog Message
                        alertDialog1.setMessage("Events conflict: Change the time!");

                        // Setting Icon to Dialog
                        //    alertDialog1.setIcon(R.drawable.tick);

                        // Setting OK Button
                        alertDialog1.setButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {}
                        });
                        alertDialog1.show();
                    }
                }
            }
            catch (ParseException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    /* (non-Javadoc)
     * @see android.widget.AdapterView.OnItemSelectedListener#onItemSelected(android.widget.AdapterView, android.view.View, int, long)
     */
    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        // An item was selected. You can retrieve the selected item using
        int idnum = parent.getId();

        if(idnum == R.id.spinner1) {
            EventActivity.categary_from_Spinner =  parent.getItemAtPosition(pos).toString();
            Log.d("SpinnerListener :", EventActivity.categary_from_Spinner);
            ctg = SimpleCalendarView.db.getCategoryID(EventActivity.categary_from_Spinner);

            if(EventActivity.categary_from_Spinner.equalsIgnoreCase("Add category")) {
                category_name.setVisibility(View.VISIBLE);
                //   Spinner spinner = (Spinner) findViewById(R.id.spinner2);
                // Create an ArrayAdapter using the string array and a default spinner layout
                ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                        R.array.categary_color, android.R.layout.simple_spinner_item);
                // Specify the layout to use when the list of choices appears
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                // Apply the adapter to the spinner
                spinn_category_color.setAdapter(adapter);
                spinn_category_color.setVisibility(View.VISIBLE);
            }
        }
        else if(idnum == R.id.spinner2) {
            EventActivity.categary_color =  parent.getItemAtPosition(pos).toString();
            Log.d("SpinnerListener2 :", EventActivity.categary_color);
        }
        else if(idnum == R.id.spinner_repeat) {
            repeat =  parent.getItemAtPosition(pos).toString();
            Log.d("Repeat Spinner :", repeat);
        }
    }

    /* (non-Javadoc)
     * @see android.widget.AdapterView.OnItemSelectedListener#onNothingSelected(android.widget.AdapterView)
     */
    public void onNothingSelected(AdapterView<?> parent) {
        if(parent.getId() == R.id.spinner1){
            EventActivity.categary_from_Spinner = "Random";
            ctg = SimpleCalendarView.db.getCategoryID(EventActivity.categary_from_Spinner);
        }
    }
}
