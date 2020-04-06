package nl.hr.cmtprg037.week7tst;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Rule
    public ActivityTestRule<MainActivity> heroTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void addTest_ExpectResult() throws Exception {
        onView(withId(R.id.editText_a)).perform(typeText("2"), closeSoftKeyboard());
        onView(withId(R.id.editText_b)).perform(typeText("2"), closeSoftKeyboard());
        onView(withId(R.id.button_add)).perform(click());
        onView(withId(R.id.textView_ab)).check(matches(withText("4")));
    }

}

/*
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;


@RunWith(AndroidJUnit4.class)


public class LookupTest {
    @Rule
    public ActivityTestRule<HeroActivity> heroTestRule = new ActivityTestRule<>(HeroActivity.class);

    @Test
    public void searchSpiderman_ExpectException() throws Exception {
        onView(withId(R.id.editText_hero)).perform(typeText("spiderman"), closeSoftKeyboard());
        onView(withId(R.id.button_findHero)).perform(click());
        onView(withId(R.id.textView_heroName)).check(matches(withText("Exception")));
    }

    @Test
    public void searchSpiderman_ExpectResult() throws Exception {
        onView(withId(R.id.editText_hero)).perform(typeText("spider-man"), closeSoftKeyboard());
        onView(withId(R.id.button_findHero)).perform(click());
        onView(withId(R.id.textView_heroName)).check(matches(withText("Spider-Man")));
    }
}

 */
