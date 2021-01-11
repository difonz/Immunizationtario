package ca.georgebrown.comp2074.capstone2;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.github.sundeepk.compactcalendarview.domain.Event;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class CalendarActivity extends AppCompatActivity {

    private AccountViewModel accountViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calender);

        accountViewModel = new ViewModelProvider(this).get(AccountViewModel.class);

        Intent i = getIntent();
        long memberID = i.getLongExtra("memberID", 0);
        String accType = i.getStringExtra("accType");
        long accID = i.getLongExtra("accID",0);
        long docID = i.getLongExtra("docID", 0);
        long schoolID = i.getLongExtra("schoolID", 0);

        MemberAccount member = accountViewModel.getMemberById(memberID);
        TextView title = findViewById(R.id.txtCalendarTitle);
        title.setText("Vaccine Calendar for " + member.getName());
        TextView monthTitle = findViewById(R.id.txtCalendarMonth);
        TextView dateDetails = findViewById(R.id.txtDateDetails);
        monthTitle.setText("April-2020");

        CompactCalendarView  calendarView = findViewById(R.id.calendarView);
        calendarView.setUseThreeLetterAbbreviation(true);
        calendarView.setFirstDayOfWeek(Calendar.SUNDAY);
        calendarView.setListener(new CompactCalendarView.CompactCalendarViewListener() {
            @Override
            public void onDayClick(Date dateClicked) {
                List<Event> dateEvents = calendarView.getEvents(dateClicked); // get all events on the date clicked
                dateDetails.setText("Events on " + dateClicked);
                for (Event e: dateEvents) {
                    dateDetails.append("\n" + e.getData()); // add each event on this date to the dateDetails text view
                }
            }

            @Override
            public void onMonthScroll(Date firstDayOfNewMonth) {
                // just set the title of the calendar to the correct month
                SimpleDateFormat monthFormat = new SimpleDateFormat("MMMM-yyyy", Locale.getDefault());
                monthTitle.setText(monthFormat.format(firstDayOfNewMonth));
            }
        });

        String memberDOB = member.getDob();
        Date dob;
        try {
            dob = new SimpleDateFormat("dd/MM/yyyy").parse(memberDOB);
            Log.d("Date parse success", "" + dob);

            List<Event> eventList = new ArrayList<Event>(); // will hold all our created Events
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(dob); // set starting calendar date to member DOB
            calendar.add(calendar.MONTH, 1); // add 1 month to member DOB
            Date month1Date = calendar.getTime(); // Hep B vaccine 1 month after DOB
            Event birthday = new Event(Color.GREEN, dob.getTime(), member.getName() + "'s birthday");
            Event hepB1Event = new Event(Color.RED, month1Date.getTime(), "Hepatitis B First Dose");

            calendar.add(calendar.MONTH, 1); // now up to 2 months after DOB
            Date month2Date = calendar.getTime();
            Event DTAP1Event = new Event(Color.RED, month2Date.getTime(), "DTaP First Dose");
            Event Rota1Event = new Event(Color.RED, month2Date.getTime(), "Rotavirus First Dose");
            Event HIB1Event = new Event(Color.RED, month2Date.getTime(), "Haemophilus influenzae type b First Dose");
            Event PCV1Event = new Event(Color.RED, month2Date.getTime(), "Pneumococcal conjugate First Dose");
            Event IPV1Event = new Event(Color.RED, month2Date.getTime(), "Inactivated poliovirus First Dose");
            Event hepB2Event = new Event(Color.RED, month2Date.getTime(), "Hepatitis B Second Dose");

            eventList.add(birthday);
            eventList.add(hepB1Event);
            eventList.add(DTAP1Event);
            eventList.add(Rota1Event);
            eventList.add(HIB1Event);
            eventList.add(PCV1Event);
            eventList.add(IPV1Event);
            eventList.add(hepB2Event);

            calendarView.addEvents(eventList); // add all events to the calendarView
        } catch (ParseException e) {
            Log.d("Date Parse Exception", "" + e.getMessage());
        }

        Button btnHome = findViewById(R.id.btnCalendarHome);
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                // send back to the appropriate View activity, since all 3 account types redirect to this one activity
                if (accType.equals("school")) {
                    intent = new Intent(v.getContext(), home_school.class);
                    intent.putExtra("id", schoolID);
                } else if (accType.equals("doctor")) {
                    intent = new Intent(v.getContext(), home_doctor.class);
                    intent.putExtra("id", docID);
                } else { // accType.equals("personal")
                    intent = new Intent(v.getContext(), home_personal.class);
                    intent.putExtra("id", accID);
                }
                startActivity(intent);
                finish();
            }
        });

        Button btnViewRecord = findViewById(R.id.btnCalendarViewRecord);
        btnViewRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                intent = new Intent(v.getContext(), ViewStudentsActivity.class);
                intent.putExtra("memberID", memberID);
                intent.putExtra("accType", accType);
                intent.putExtra("accID", accID);
                intent.putExtra("docID", docID);
                intent.putExtra("schoolID", schoolID);
                startActivity(intent);
                finish();
            }
        });
    }
}
