package com.example.hadeel.samples;

import android.content.Intent;
import android.provider.CalendarContract;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.CalendarMode;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Calender extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    CalendarView calendarView;
    TextView date,timeText;
    private Spinner spinner;
    private List<String> time;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calender);
        calendarView = (CalendarView) findViewById(R.id.calenderView);
        date = (TextView) findViewById(R.id.date);
        timeText = (TextView) findViewById(R.id.time);

        spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);
        time = new ArrayList<String>();
        time.add("12:00 AM");
        time.add("1:00 AM");
        time.add("2:00 AM");
        time.add("3:00 AM");
        time.add("4:00 AM");
        time.add("5:00 AM");
        time.add("6:00 AM");
        time.add("7:00 AM");
        time.add("8:00 AM");
        time.add("9:00 AM");
        time.add("10:00 AM");
        time.add("11:00 AM");
        time.add("12:00 PM");
        time.add("1:00 PM");
        time.add("2:00 PM");
        time.add("3:00 PM");
        time.add("4:00 PM");
        time.add("5:00 PM");
        time.add("6:00 PM");
        time.add("7:00 PM");
        time.add("8:00 PM");
        time.add("9:00 PM");
        time.add("10:00 PM");
        time.add("11:00 PM");


        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, time);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setPrompt("Time");
        spinner.setAdapter(adapter);

//        calendarView
//                .setFirstDayOfWeek(Calendar.TUESDAY)
//                .setMaximumDate(CalendarDay.from(1900,1,1))
//                .setMaximumDate(CalendarDay.from(2020,12,31))
//                .setCalendarDisplayMode(CalendarMode.WEEKS)
//                .commit();
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                String date1 = dayOfMonth + "/" + (month + 1) + "/" + year;
                Toast.makeText(getApplication(), date1, Toast.LENGTH_LONG).show();
                date.setText(date1);
            }
        });
    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String Time= parent.getItemAtPosition(position).toString();
           Toast.makeText(getApplicationContext(),Time,Toast.LENGTH_LONG).show();
           timeText.setText(Time);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


}
