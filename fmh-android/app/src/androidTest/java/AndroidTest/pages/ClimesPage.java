package AndroidTest.pages;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withSubstring;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasToString;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.startsWith;

import static AndroidTest.data.DataHelper.RecyclerViewAssertions.withRowContaining;
import static AndroidTest.data.DataHelper.checkToast;
import static AndroidTest.data.DataHelper.childAtPosition;
import static AndroidTest.data.DataHelper.waitUntil;
import static AndroidTest.pages.NewClaim.dateField;
import static AndroidTest.pages.NewClaim.descriptionField;
import static AndroidTest.pages.NewClaim.executorField;
import static AndroidTest.pages.NewClaim.saveButton;
import static AndroidTest.pages.NewClaim.timeField;
import static AndroidTest.pages.NewClaim.tittleField;

import androidx.test.espresso.DataInteraction;
import androidx.test.espresso.ViewInteraction;

import ru.iteco.fmhandroid.R;

public class ClimesPage {

  public static ViewInteraction newClimeButton = onView(withId(R.id.add_new_claim_material_button));
  public static ViewInteraction filterClimesButton = onView(withId(R.id.filters_material_button));
  public static int filterClimesButtonID = R.id.filters_material_button;

  public static void createNewClime(String tittle, int position, String date, String time, String description) {
    tittleField.perform(replaceText(tittle));
    chooseExecutor(position);
    dateField.perform(replaceText(date));
    timeField.perform(replaceText(time));
    descriptionField.perform(replaceText(description));
//        saveButton.perform(click());
  }

  public static void chooseExecutor(int position) {
    onView(allOf(withId(R.id.text_input_end_icon), withContentDescription("Show dropdown menu")))
        .perform(click());
//    onData(anything()).inAdapterView(withContentDescription("Ivanov Ivan Ivanovich"))
//        .atPosition(0).perform(click());
    onData(anything()).atPosition(0).perform(click());
  }

  public static void isClaimExistWithParams(String tittle, String executor, String date, String time, String description) {
    onView(withId(R.id.claim_list_recycler_view))
        .check(withRowContaining(withText(tittle)))
        .check(withRowContaining(withText(executor)))
        .check(withRowContaining(withText(date)))
        .check(withRowContaining(withText(time)))
        .check(withRowContaining(withText(description)));
  }
}
