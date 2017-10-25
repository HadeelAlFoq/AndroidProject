package com.example.hadeel.samples;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.RippleDrawable;
import android.icu.util.Calendar;
import android.os.Build;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;

import static android.R.id.primary;

public class InsertDataActivity extends AppCompatActivity {

    SQLiteDatabase sqLiteDatabaseObj;
    private EditText inputName, inputEmail, inputPhone,inputAge;
    private TextInputLayout inputLayoutName, inputLayoutEmail, inputLayoutPhone,inputLayoutAge;
    private Button insert;
    String NameHolder, NumberHolder, SQLiteDataBaseQueryHolder;

    Boolean EditTextEmptyHold;
    SqliteHelper db;
    String list,Name,Phone,Email,Age;
    SQLiteDatabase sqLiteDatabase;
    String query,getintent;
    Cursor cursor;
    TextView name;
    ArrayList<String> NAME_Array;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_data);
        db = new SqliteHelper(this);

        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar);
        toolbar.setTitle(getResources().getString(R.string.insertTitle));
        toolbar.setTitleTextColor(getResources().getColor(R.color.login));

        setSupportActionBar(toolbar);
        if(getSupportActionBar()!=null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);

        }
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();

                finish();
            }
        });

        inputLayoutName = (TextInputLayout) findViewById(R.id.input_layout_name);
        inputLayoutEmail = (TextInputLayout) findViewById(R.id.input_layout_email);
        inputLayoutPhone = (TextInputLayout) findViewById(R.id.input_layout_Phone);
        inputLayoutAge = (TextInputLayout) findViewById(R.id.input_layout_age);

        inputName = (EditText) findViewById(R.id.input_name);
        inputEmail = (EditText) findViewById(R.id.input_email);
        inputPhone = (EditText) findViewById(R.id.input_phone);
        inputAge = (EditText) findViewById(R.id.input_age);

        insert = (Button) findViewById(R.id.btn_signup);

        inputName.addTextChangedListener(new MyTextWatcher(inputName));
        inputEmail.addTextChangedListener(new MyTextWatcher(inputEmail));
        inputPhone.addTextChangedListener(new MyTextWatcher(inputPhone));
        inputAge.addTextChangedListener(new MyTextWatcher(inputAge));


        NAME_Array = new ArrayList<String>();





        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                boolean submit= submitForm();
                if(submit==true) {

                    Insert();
                    LAST();
                    share();
                }






            }
        });


    }

    public void SQLiteDataBaseBuild() {

        sqLiteDatabaseObj = openOrCreateDatabase(SqliteHelper.DATABASE_NAME, Context.MODE_PRIVATE, null);

    }

    public void SQLiteTableBuild() {

            sqLiteDatabaseObj.execSQL("CREATE TABLE IF NOT EXISTS " + SqliteHelper.TABLE_NAME + "(" + SqliteHelper.Table_Column_ID + " INTEGER PRIMARY KEY, " + SqliteHelper.Table_Column_1_Name + " VARCHAR, " + SqliteHelper.Table_Column_2_PhoneNumber + " VARCHAR,"+ SqliteHelper.Table_Column_3_Email + " VARCHAR);");

    }


    public void Insert() {


        java.util.Calendar now = java.util.Calendar.getInstance();
        int year = now.get(java.util.Calendar.YEAR);
         Name = inputName .getText().toString();
         Phone = inputPhone.getText().toString();
         Email=inputEmail.getText().toString();
         String Year = inputAge.getText().toString();
         int enterYear = Integer.parseInt(Year);
         int result = year - enterYear;
         Age = String.valueOf(result);






        db.insert(Name, Phone,Email,Age);
        Toast.makeText(this, "SECCESSFUL INSERT " + Name, Toast.LENGTH_LONG).show();
        Intent d = new Intent(InsertDataActivity.this, DisplaySQLiteDataActivity.class);

        startActivity(d);

    }
    public void LAST() {
        sqLiteDatabase = db.getWritableDatabase();
        query = ("SELECT name FROM OrangeTable");
        cursor = sqLiteDatabase.rawQuery(query, null);


        if (cursor.moveToFirst()) {
            do {

                NAME_Array.add(cursor.getString(cursor.getColumnIndex(SqliteHelper.Table_Column_1_Name)));


            } while (cursor.moveToNext());
        }


        list = NAME_Array.get(NAME_Array.size() - 1);
        Toast.makeText(this, "hello" + list, Toast.LENGTH_LONG).show();


    }
    public void share(){
        SharedPreferences prefer=getSharedPreferences("DATA",MODE_PRIVATE);
        SharedPreferences.Editor editor=prefer.edit();
        editor.putString("NAME",list );
        editor.commit();


    }
    private boolean submitForm() {
        if (!validateName()) {
            return false;
        }


        if (!validateEmail()) {
            return false;
        }

        if (!validatePhone()) {
            return false;
        }

        if (!validateYear()) {
            return false;
        }
        else return true;


    }



    private boolean validateName() {
        if (inputName.getText().toString().trim().isEmpty()) {
            inputLayoutName.setError(getString(R.string.err_msg_name));
            requestFocus(inputName);
            return false;
        } else {
            inputLayoutName.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validateEmail() {
        String email = inputEmail.getText().toString().trim();

        if (email.isEmpty() || !isValidEmail(email)) {
            inputLayoutEmail.setError(getString(R.string.err_msg_email));
            requestFocus(inputEmail);
            return false;
        } else {
            inputLayoutEmail.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validatePhone() {
        if (inputPhone.getText().toString().trim().length()<10) {
            inputLayoutPhone.setError(getString(R.string.err_msg_phone));
            requestFocus(inputPhone);
            return false;
        } else {
            inputLayoutPhone.setErrorEnabled(false);
        }

        return true;
    }
    private boolean validateYear() {
        if (inputAge.getText().toString().trim().isEmpty()) {
            inputLayoutAge.setError(getString(R.string.err_msg_age));
            requestFocus(inputAge);
            return false;
        } else {
            inputLayoutAge.setErrorEnabled(false);
        }

        return true;
    }

    private static boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }
    private class MyTextWatcher implements TextWatcher {

        private View view;

        private MyTextWatcher(View view) {
            this.view = view;
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void afterTextChanged(Editable editable) {
            switch (view.getId()) {
                case R.id.input_name:
                    validateName();
                    break;
                case R.id.input_email:
                    validateEmail();
                    break;
                case R.id.input_phone:
                    validatePhone();
                    break;
                case R.id.input_age:
                    validateYear();
                    break;
            }
        }
    }


}