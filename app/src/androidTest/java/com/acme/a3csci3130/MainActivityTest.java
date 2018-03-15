package com.acme.a3csci3130;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.support.test.InstrumentationRegistry;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.widget.ListView;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import static android.support.test.espresso.action.ViewActions.clearText;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static java.lang.Thread.sleep;
import static org.hamcrest.CoreMatchers.anything;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.collection.IsMapContaining.hasEntry;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Created by Elliott 15.03.2018
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    private static Intent intent = new Intent();

    @Rule
    public ActivityTestRule<MainActivity> mainActivityRule =
            new ActivityTestRule<>(MainActivity.class, false, false);

    @Before
    public void launchActivity() throws Exception {
        sleep(250);
        mainActivityRule.launchActivity(intent);
        sleep(5000);
    }

    /**
     * Tests business creation.
     *
     * @throws Exception
     */
    @Test
    public void business1create() throws Exception {
        onView(withId(R.id.createButton)).perform(click());
        onView(withId(R.id.number)).perform(typeText("987654321"));
        onView(withId(R.id.name)).perform(typeText("Test Business"));
        onView(withId(R.id.primaryBusiness)).perform(typeText("Fisher"));
        onView(withId(R.id.address)).perform(typeText("123 Fake Street, Halifax"));
        onView(withId(R.id.province)).perform(typeText("NS"));
        onView(withId(R.id.submitButton)).perform(click());
    }

    /**
     * Tests viewing the details of a business.
     *
     * @throws Exception
     */
    @Test
    public void business2view() throws Exception {
        onData(anything()).inAdapterView(withId(R.id.listView)).atPosition(5).perform(click());
        onView(withId(R.id.name)).check(matches(withText("Test Business")));
    }

    /**
     * Tests editing a business.
     *
     * @throws Exception
     */
    @Test
    public void business3edit() throws Exception {
        onData(anything()).inAdapterView(withId(R.id.listView)).atPosition(5).perform(click());
        onView(withId(R.id.name)).perform(clearText(), typeText("Test Business 2"));
        onView(withId(R.id.updateButton)).perform(click());
    }

    /**
     * Tests business deletion.
     *
     * @throws Exception
     */
    @Test
    public void business4delete() throws Exception {
        onData(anything()).inAdapterView(withId(R.id.listView)).atPosition(5).perform(click());
        onView(withId(R.id.deleteButton)).perform(click());
    }


}
