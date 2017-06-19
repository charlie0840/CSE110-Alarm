/*

Scenario #1:
Given the alarms list page
When the user clicks add button
Then it will go to the alarm setting page
When the user clicks on the start time button or end time button
Then it will go to a new page for the user to set up the time
When the user click the confirm button
Then it will change the information of the alarm on the alarm list page

Scenario #2:
Given an already set up alarm
When the current system time matches the alarm time
Then it will pop up a puzzle page

Scenario #3:
Given an unsolved puzzle
When the user solved the puzzle
Then it will pop up a message which says "You Win!"
When the user clicks on the confirm button
Then it will go back to the alarms list page

 */
package com.example.stanley.alarm;
import org.junit.Rule;

import android.os.SystemClock;
import android.support.test.espresso.ViewAction;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.widget.Spinner;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Calendar;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.PickerActions.setTime;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
/**
 * Created by Xuan on 12/7/2015.
 */
@RunWith(AndroidJUnit4.class)
public class PuzzleTest {
    @Rule
    public ActivityTestRule<AlarmListActivity> addClock = new ActivityTestRule<AlarmListActivity>(AlarmListActivity.class);
    public ActivityTestRule<PopupAlarmPuzzleActivity> addPuzzle = new ActivityTestRule<PopupAlarmPuzzleActivity>(PopupAlarmPuzzleActivity.class);

    @Test
    public void testing() {
        // Given the alarms list page
        Calendar ca = Calendar.getInstance();

        // When the user clicks add button
        onView(withId(R.id.btn_add_clock)).perform(click());

        // Then it will go to the alarm setting page
        //When the user clicks on the start time button or end time button
        onView(withId(R.id.start_time_button)).perform(click());

        //Then it will go to a new page for the user to set up the time
        onView(withId(R.id.timePicker)).perform(setTime(ca.get(Calendar.HOUR), ca.get(Calendar.MINUTE) + 1));
        onView(withId(R.id.set_clock_confirm_button)).perform(click());

            if(ca.get(Calendar.HOUR) < 10 && (ca.get(Calendar.MINUTE) + 1) < 10) {
                onView(withId(R.id.start_time_button)).check(ViewAssertions.matches(withText("0" + ca.get(Calendar.HOUR) + ":" + "0" + (ca.get(Calendar.MINUTE) + 1) + (ca.get(Calendar.HOUR) > 12 ?" PM" : " AM"))));
            }
            else if(ca.get(Calendar.HOUR) >= 10 && (ca.get(Calendar.MINUTE) + 1) >= 10) {
                onView(withId(R.id.start_time_button)).check(ViewAssertions.matches(withText(ca.get(Calendar.HOUR) + ":" + (ca.get(Calendar.MINUTE) + 1) + (ca.get(Calendar.HOUR) > 12 ?" PM" : " AM"))));
            }
            else if(ca.get(Calendar.HOUR) < 10 && (ca.get(Calendar.MINUTE) + 1) >= 10) {
                onView(withId(R.id.start_time_button)).check(ViewAssertions.matches(withText("0" + ca.get(Calendar.HOUR) + ":" + (ca.get(Calendar.MINUTE) + 1) + (ca.get(Calendar.HOUR) > 12 ?" PM" : " AM"))));
            }
            else if(ca.get(Calendar.HOUR) >= 10 && (ca.get(Calendar.MINUTE) + 1) < 10) {
                onView(withId(R.id.start_time_button)).check(ViewAssertions.matches(withText(ca.get(Calendar.HOUR) + ":" + "0" + (ca.get(Calendar.MINUTE) + 1) + (ca.get(Calendar.HOUR) > 12 ?" PM" : " AM"))));
            }

        onView(withId(R.id.end_time_button)).perform(click());
        onView(withId(R.id.timePicker)).perform(setTime(ca.get(Calendar.HOUR), ca.get(Calendar.MINUTE) + 2));
        onView(withId(R.id.set_clock_confirm_button)).perform(click());

        if(ca.get(Calendar.HOUR) < 10 && (ca.get(Calendar.MINUTE) + 2) < 10) {
            onView(withId(R.id.end_time_button)).check(ViewAssertions.matches(withText("0" + ca.get(Calendar.HOUR) + ":" + "0" + (ca.get(Calendar.MINUTE) + 2) + (ca.get(Calendar.HOUR) > 12 ?" PM" : " AM"))));
        }
        else if(ca.get(Calendar.HOUR) >= 10 && (ca.get(Calendar.MINUTE) + 2) >= 10) {
            onView(withId(R.id.end_time_button)).check(ViewAssertions.matches(withText(ca.get(Calendar.HOUR) + ":" + (ca.get(Calendar.MINUTE) + 2) + (ca.get(Calendar.HOUR) > 12 ?" PM" : " AM"))));
        }
        else if(ca.get(Calendar.HOUR) < 10 && (ca.get(Calendar.MINUTE) + 2) >= 10) {
            onView(withId(R.id.end_time_button)).check(ViewAssertions.matches(withText("0" + ca.get(Calendar.HOUR) + ":" + (ca.get(Calendar.MINUTE) + 2) + (ca.get(Calendar.HOUR) > 12 ?" PM" : " AM"))));
        }
        else if(ca.get(Calendar.HOUR) >= 10 && (ca.get(Calendar.MINUTE) + 2) < 10) {
            onView(withId(R.id.end_time_button)).check(ViewAssertions.matches(withText(ca.get(Calendar.HOUR) + ":" + "0" + (ca.get(Calendar.MINUTE) + 2) + (ca.get(Calendar.HOUR) > 12 ?" PM" : " AM"))));
        }

        onView(withId(R.id.toggle_mon)).perform(click());
        onView(withId(R.id.toggle_tue)).perform(click());

        //When the user click the confirm button
        onView(withId(R.id.alarm_setting_confirm_button)).perform(click());
        //Then it will change the information of the alarm on the alarm list page

        while(true) {
            Calendar ca1 = Calendar.getInstance();

            //Given an already set up alarm
            //When the current system time matches the alarm time
            //Then it will pop up a puzzle page
            if(ca1.get(Calendar.MINUTE)  == (ca.get(Calendar.MINUTE) + 1)) {
                //Given an unsolved puzzle
         //       SystemClock.sleep(3000);

                //When the user solved the puzzle
 //               onView(withId(R.id.button2)).perform(click());
   //             onView(withId(R.id.button4)).perform(click());
     //           onView(withId(R.id.button6)).perform(click());
       //         onView(withId(R.id.button8)).perform(click());

                // Then it will pop up a message which says "You Win!"
                onView(withId(R.id.indication)).check(ViewAssertions.matches((withText("You Win!"))));
                break;
            }
        }

        /* When the user clicks on the confirm button
        Then it will go back to the alarms list page */
        onView(withId(R.id.confirm_btn)).perform(click());

        }

}
