package AndroidTest.pages;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.isChecked;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isNotChecked;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withSpinnerText;
import static androidx.test.espresso.matcher.ViewMatchers.withSubstring;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasToString;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.startsWith;

import static AndroidTest.data.Data.executor;
import static AndroidTest.data.Data.tittleClaim;
import static AndroidTest.data.DataHelper.RecyclerViewAssertions.withRowContaining;
import static AndroidTest.data.DataHelper.checkToast;
import static AndroidTest.data.DataHelper.childAtPosition;
import static AndroidTest.data.DataHelper.waitUntil;
import static AndroidTest.pages.NewClaim.cancelButton;
import static AndroidTest.pages.NewClaim.dateField;
import static AndroidTest.pages.NewClaim.descriptionField;
import static AndroidTest.pages.NewClaim.executorField;
import static AndroidTest.pages.NewClaim.executorMenuButton;
import static AndroidTest.pages.NewClaim.saveButton;
import static AndroidTest.pages.NewClaim.timeField;
import static AndroidTest.pages.NewClaim.tittleField;

import android.widget.PopupWindow;

import androidx.test.espresso.DataInteraction;
import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.uiautomator.UiDevice;
import androidx.test.uiautomator.UiObject;
import androidx.test.uiautomator.UiObjectNotFoundException;
import androidx.test.uiautomator.UiScrollable;
import androidx.test.uiautomator.UiSelector;

import ru.iteco.fmhandroid.R;

public class ClimesPage {

  public static ViewInteraction newClimeButton = onView(withId(R.id.add_new_claim_material_button));
  public static ViewInteraction filterClimesButton = onView(withId(R.id.filters_material_button));
  public static int filterClimesButtonID = R.id.filters_material_button;

  public static void createNewClime(String tittle, String date, String time, String description) {
    tittleField.perform(replaceText(tittle));
    dateField.perform(replaceText(date));
    timeField.perform(replaceText(time));
    descriptionField.perform(replaceText(description));
    ViewActions.closeSoftKeyboard();
    saveButton.perform(scrollTo()).perform(click());
  }

  public static void chooseExecutor(String executor) {
    executorMenuButton.perform(click());
    onView(allOf(withId(R.id.executor_drop_menu_auto_complete_text_view)))
        .perform(replaceText(executor));
  }

  public static void isClaimExistWithParams(String tittle, String date, String time, String description) {
    onView(withId(R.id.claim_list_recycler_view))
        .perform(RecyclerViewActions.scrollTo(hasDescendant(allOf(withText(tittle)))))
        .perform(click());
    onView(withId(R.id.claim_list_recycler_view))
        .perform(RecyclerViewActions.actionOnItem(hasDescendant(withText(tittle)), click()));
    onView(withId(R.id.title_text_view)).check(matches(withText(tittle)));
    onView(withId(R.id.plane_date_text_view)).check(matches(withText(date)));
    onView(withId(R.id.plan_time_text_view)).check(matches(withText(time)));
    onView(withId(R.id.description_text_view)).check(matches(withText(description)));
  }

  public static void filterClimes (boolean open, boolean inProgress, boolean executed, boolean cancelled) {
    onView(withId(R.id.filters_material_button)).perform(click());
    if (!open) {
      onView(withId(R.id.item_filter_open)).check(matches(isChecked())).perform(click());
    }
    if (!inProgress) {
      onView(withId(R.id.item_filter_in_progress)).check(matches(isChecked())).perform(click());
    }
    if (executed) {
      onView(withId(R.id.item_filter_executed)).check(matches(isNotChecked())).perform(click());
    }
    if (cancelled) {
      onView(withId(R.id.item_filter_cancelled)).check(matches(isNotChecked())).perform(click());
    }
    onView(withId(R.id.claim_list_filter_ok_material_button)).perform(click());
  }

  public static void checkFilteredClimes(String status) throws UiObjectNotFoundException {
    UiDevice mDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
    UiScrollable recyclerView = new UiScrollable(new UiSelector().resourceId("ru.iteco.fmhandroid:id/claim_list_recycler_view"));
    recyclerView.setMaxSearchSwipes(Integer.MAX_VALUE);
    UiObject openElement = recyclerView.getChildByText(new UiSelector().resourceId("ru.iteco.fmhandroid:id/status_label_text_view"), status);
  }
}
