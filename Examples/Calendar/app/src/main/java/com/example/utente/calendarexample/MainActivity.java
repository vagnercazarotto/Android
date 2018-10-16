package com.example.utente.calendarexample;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.CalendarView;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;
import com.prolificinteractive.materialcalendarview.OnMonthChangedListener;

public class MainActivity extends AppCompatActivity {

//    private CalendarView calendarVIew;

    private MaterialCalendarView materialCalendarView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        materialCalendarView = findViewById(R.id.calendarView);

        materialCalendarView.state().edit().setMinimumDate(CalendarDay.from(2018,1,1)).commit();

        materialCalendarView.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                Log.e("TAG", " valor " + date);
            }
        });

        materialCalendarView.setOnMonthChangedListener(new OnMonthChangedListener() {
            @Override
            public void onMonthChanged(MaterialCalendarView widget, CalendarDay date) {
                Log.e("TAG", " valor " + date.getMonth() + 1);
            }
        });


        CharSequence meses[] = {"Jan","Fev","March","Apr","May","jun","jul","Ago","set","Out","nov","dez"};
        materialCalendarView.setTitleMonths(meses);



//        calendarVIew = findViewById(R.id.calendarView);
//        calendarVIew.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
//            @Override
//            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
//                Log.e("TAG", " valor " + dayOfMonth + " Month " + month + " Year " + year);
//            }
//        });





    }
}
