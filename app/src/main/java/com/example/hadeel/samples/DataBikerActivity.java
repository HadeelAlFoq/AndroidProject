package com.example.hadeel.samples;

import android.Manifest;
import android.app.DatePickerDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.provider.CalendarContract;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class DataBikerActivity extends AppCompatActivity {

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
        locationET=(EditText)findViewById(R.id.locationCET);



        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        dateET.setInputType(InputType.TYPE_NULL);
        timeET.setInputType(InputType.TYPE_NULL);
        dateET.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                updateDate();
            }
        });
        timeET.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                updateTime();
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
                boolean submit= submitForm();
                if(submit==false){
                    Toast.makeText(getApplication(),"please check all record in not Empty",Toast.LENGTH_LONG).show();

                }else {
                    if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.WRITE_CALENDAR) == PackageManager.PERMISSION_GRANTED) {

                        Calendar cal = Calendar.getInstance();
                        Intent schedule = new Intent(Intent.ACTION_EDIT);
                        schedule.setType("vnd.android.cursor.item/event");
                        schedule.putExtra("beginTime", dateTime.getTimeInMillis());
                        schedule.putExtra(CalendarContract.EXTRA_EVENT_END_TIME, dateTime.getTimeInMillis());
                        schedule.putExtra(CalendarContract.EXTRA_EVENT_ALL_DAY, false);
                        schedule.putExtra(CalendarContract.Events.TITLE, title);

                        schedule.putExtra(CalendarContract.Events.EVENT_LOCATION, location);
                        schedule.putExtra(CalendarContract.Events.DESCRIPTION, descr);

                        startActivity(schedule);
                        notificationCalender();
                        timeET.setText("");
                        dateET.setText("");
                        titleET.setText("");
                        locationET.setText("");
                        descET.setText("");

                    } else {
                        ActivityCompat.requestPermissions(DataBikerActivity.this, new String[]{Manifest.permission.WRITE_CALENDAR}, 200);
                    }
                }
            }
        });

    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1) {
            if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.WRITE_CALENDAR) == PackageManager.PERMISSION_GRANTED) {
                Calendar cal = Calendar.getInstance();
                Intent schedule = new Intent(Intent.ACTION_EDIT);
                schedule.setType("vnd.android.cursor.item/event");
                schedule.putExtra("beginTime", dateTime.getTimeInMillis());
                schedule.putExtra(CalendarContract.EXTRA_EVENT_END_TIME, dateTime.getTimeInMillis());
                schedule.putExtra(CalendarContract.EXTRA_EVENT_ALL_DAY, false);
                schedule.putExtra(CalendarContract.Events.TITLE, title);

                schedule.putExtra(CalendarContract.Events.EVENT_LOCATION, location);
                schedule.putExtra(CalendarContract.Events.DESCRIPTION, descr);

                startActivity(schedule);
                notificationCalender();
            } else {
                Toast.makeText(getApplication(), "no permission", Toast.LENGTH_LONG).show();
            }
        }
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


        }
    };

    TimePickerDialog.OnTimeSetListener t = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            dateTime.set(Calendar.HOUR_OF_DAY, hourOfDay);
            dateTime.set(Calendar.MINUTE, minute);


            timeET.setText(hourOfDay+ ":"+minute);


        }
    };
    private void notificationCalender(){
        Intent intent= new Intent();
        PendingIntent pendingIntent = PendingIntent.getService(DataBikerActivity.this, 0, intent, 0);
        Notification noti= new Notification.Builder(DataBikerActivity.this)
                .setTicker("Calender")
                .setContentTitle(title)
                .setContentText(descr)
                .setSmallIcon(R.drawable.ic_notification)

                .setContentIntent(pendingIntent).getNotification();
        noti.flags=Notification.FLAG_AUTO_CANCEL;
        NotificationManager nm=(NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        nm.notify(0,noti);
    }
    private boolean submitForm() {
        if (!validateTime()) {
            return false;
        }


        if (!validateDate()) {
            return false;
        }

        if (!validateDescri()) {
            return false;
        }

        if (!validateTitle()) {
            return false;
        }
        if (!validateLocation()) {
            return false;
        }
        else return true;


    }

    private boolean validateTitle() {
        if (titleET.getText().toString().trim().isEmpty()) {
            Toast.makeText(getApplication(),"please check all record in not Empty",Toast.LENGTH_LONG).show();

            return false;
        } else {
        }

        return true;
    }

    private boolean validateDescri() {
        if (descET.getText().toString().trim().isEmpty()) {

            return false;
        } else {
        }

        return true;
    }

    private boolean validateDate() {
        if (dateET.getText().toString().trim().isEmpty()) {

            return false;
        } else {
        }

        return true;
    }
    private boolean validateLocation() {
        if (locationET.getText().toString().trim().isEmpty()) {
            Toast.makeText(getApplication(),"please check all record in not Empty",Toast.LENGTH_LONG).show();

            return false;
        } else {
        }

        return true;
    }


    private boolean validateTime() {
        if (timeET.getText().toString().trim().isEmpty()) {
            Toast.makeText(getApplication(),"please check all record in not Empty",Toast.LENGTH_LONG).show();

            return false;
        } else {
        }

        return true;
    }




}
