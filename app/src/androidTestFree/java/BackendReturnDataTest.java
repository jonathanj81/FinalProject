import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.IdlingRegistry;
import android.support.test.espresso.IdlingResource;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.udacity.gradle.builditbigger.MainActivityFragment;
import com.udacity.gradle.builditbigger.R;
import com.udacity.gradle.builditbigger.free.VersionActivity;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(AndroidJUnit4.class)
public class BackendReturnDataTest {

    @Rule
    public ActivityTestRule<VersionActivity> mActivityTestRule =
            new ActivityTestRule<>(VersionActivity.class);

    private IdlingResource mIdlingResource;

    private static final Intent MY_ACTIVITY_INTENT = new Intent(InstrumentationRegistry.getTargetContext(), VersionActivity.class);


    // Registers any resource that needs to be synchronized with Espresso before the test is run.
    @Before
    public void registerIdlingResource() {
        mActivityTestRule.launchActivity(MY_ACTIVITY_INTENT);

        MainActivityFragment frag = (MainActivityFragment) mActivityTestRule.getActivity().getSupportFragmentManager()
                .findFragmentById(R.id.fragment);
        mIdlingResource = frag.getmIdlingRes();

        IdlingRegistry.getInstance().register(mIdlingResource);
    }

    @Test
    public void idlingResourceTest() {
        onView(withId(R.id.joke_button)).perform(click());

        assertNotEquals("", MainActivityFragment.mJokeText);
        assertNotNull(MainActivityFragment.mJokeText);
    }

    @After
    public void unregisterIdlingResource() {
        if (mIdlingResource != null) {
            IdlingRegistry.getInstance().unregister(mIdlingResource);
        }
    }
}