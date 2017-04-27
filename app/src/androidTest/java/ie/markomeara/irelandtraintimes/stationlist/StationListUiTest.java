package ie.markomeara.irelandtraintimes.stationlist;

import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;

import ie.markomeara.irelandtraintimes.R;
import ie.markomeara.irelandtraintimes.testrules.MockServerTestRule;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isCompletelyDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

/**
 * Created by markomeara on 27/04/2017.
 */

@RunWith(AndroidJUnit4.class)
public class StationListUiTest {

    @Rule
    public IntentsTestRule<StationListActivity> activityRule =
            new IntentsTestRule<>(StationListActivity.class);

    @Rule
    public MockServerTestRule mockServerTestRule = new MockServerTestRule();

    @Before
    public void setup() throws IOException {
        mockServerTestRule.enqueueSuccessStationsList(InstrumentationRegistry.getContext());
        Espresso.registerIdlingResources(mockServerTestRule.getOkHttpIdlingResource());
    }

    @Test
    public void testStationAppearsOnScreen() throws InterruptedException {
        onView(allOf(withId(R.id.station_desc), withText("Howth Junction"))).check(matches(isCompletelyDisplayed()));
    }
}
