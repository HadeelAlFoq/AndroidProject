package com.example.hadeel.samples;

import android.*;
import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.provider.CalendarContract;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.firebase.messaging.RemoteMessage;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Date_bicker extends AppCompatActivity {

    ImageButton dateBtn,timeBtn;
    EditText dateET,timeET,titleET,descET,locationET;
    Button send;
    TextView titleToolBar;
    String title,descr,location,getintent,time,date;
    Toolbar toolbar;
    Calendar dateTime = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_bicker);

        Intent g=getIntent();
        getintent=g.getStringExtra("name");
        toolbar=(Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(getintent);
        toolbar.setTitleTextColor(getResources().getColor(R.color.login));

        setSupportActionBar(toolbar);
        if(getSupportActionBar()!=null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);

        }
        dateBtn=(ImageButton)findViewById(R.id.dateBtn);
        timeBtn=(ImageButton)findViewById(R.id.timeBtn);
        send=(Button) findViewById(R.id.SendCalender);
        dateET=(EditText)findViewById(R.id.date);
        timeET=(EditText)findViewById(R.id.time);
        titleET=(EditText)findViewById(R.id.titleCET);
        descET=(EditText)findViewById(R.id.descriptionCET);
        titleToolBar=(TextView) findViewById(R.id.textTool);
        locationET=(EditText)findViewById(R.id.locationCET);



        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });



        dateBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                updateDate();
            }
        });

        timeBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                updateTime();
            }
        });
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                title=titleET.getText().toString();
                descr=descET.getText().toString();
                time=timeET.getText().toString();
                date=dateET.getText().toString();
                String dateANDtime=time+date;
                location=locationET.getText().toString();
                if(title.matches("")&&descr.matches("")&&time.matches("")&&date.matches("")&&location.matches("")){
                    Toast.makeText(getApplication(),"please check all record in not Empty",Toast.LENGTH_LONG).show();

                }else{
                    int permissionCheck = ContextCompat.checkSelfPermission(Date_bicker.this,
                            android.Manifest.permission.WRITE_CALENDAR);
                    Calendar cal = Calendar.getInstance();
                    Intent schedule = new Intent(Intent.ACTION_EDIT);
                    schedule.setType("vnd.android.cursor.item/event");
                    schedule.putExtra("beginTime", dateTime.getTimeInMillis());
                schedule.putExtra(CalendarContract.EXTRA_EVENT_END_TIME,dateTime.getTimeInMillis() );
                schedule.putExtra(CalendarContract.EXTRA_EVENT_ALL_DAY, false);
                schedule.putExtra(CalendarContract.Events.TITLE, title);

                schedule.putExtra(CalendarContract.Events.EVENT_LOCATION, location);
                schedule.putExtra(CalendarContract.Events.DESCRIPTION, descr);

                startActivity(schedule);
                notificationCalender();}
              // if(timeET.getText().toString().matches("13:05")) {


              // }
            }
        });

    }
    private void updateDate(){
        new DatePickerDialog(this, d, dateTime.get(Calendar.YEAR),dateTime.get(Calendar.MONTH),dateTime.get(Calendar.DAY_OF_MONTH)).show();
    }
    private void updateTime(){
        new TimePickerDialog(this, t, dateTime.get(Calendar.HOUR_OF_DAY), dateTime.get(Calendar.MINUTE), true).show();
    }
    DatePickerDialog.OnDateSetListener d = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            dateTime.set(Calendar.YEAR, year);
            dateTime.set(Calendar.MONTH, monthOfYear);
            dateTime.set(Calendar.DAY_OF_MONTH, dayOfMonth);

            dateET.setText(year+"/"+ (monthOfYear+1) + "/"+ dayOfMonth);
           // dateTime.set(dateTime.get(Calendar.YEAR),dateTime.get(monthOfYear),dateTime.get(dayOfMonth));


        }
    };

    TimePickerDialog.OnTimeSetListener t = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            dateTime.set(Calendar.HOUR_OF_DAY, hourOfDay);
            dateTime.set(Calendar.MINUTE, minute);

           // dateTime.set(hourOfDay,minute);
            timeET.setText(hourOfDay+ ":"+minute);


        }
    };
    private void notificationCalender(){
        Intent intent= new Intent();
        PendingIntent pendingIntent = PendingIntent.getService(Date_bicker.this, 0, intent, 0);
        Notification noti= new Notification.Builder(Date_bicker.this)
                .setTicker("Calender")
                .setContentTitle(title)
                .setContentText(descr)
                .setSmallIcon(R.drawable.ic_notification)

                .setContentIntent(pendingIntent).getNotification();
        noti.flags=Notification.FLAG_AUTO_CANCEL;
        NotificationManager nm=(NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        nm.notify(0,noti);
    }


}
