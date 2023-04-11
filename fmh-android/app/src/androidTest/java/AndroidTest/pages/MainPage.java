package AndroidTest.pages;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.allOf;

import androidx.test.espresso.ViewInteraction;

import ru.iteco.fmhandroid.R;

public class MainPage {

    public static ViewInteraction loginOutButton = onView(allOf(withId(R.id.authorization_image_button)));
    public static int LogOutId = R.id.authorization_image_button;

    public static void logOut() {
        onView(withId(LogOutId)).perform(click());
        onView(withId(android.R.id.title)).check(matches(isDisplayed()));
        onView(withId(android.R.id.title)).perform(click());
    }


}
