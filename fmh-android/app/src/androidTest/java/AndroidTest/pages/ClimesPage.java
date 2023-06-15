package AndroidTest.pages;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItem;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.isChecked;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isNotChecked;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.Matchers.anyOf;
import static AndroidTest.data.DataHelper.waitDisplayed;
import static AndroidTest.data.DataHelper.waitElement;
import static AndroidTest.pages.NewClaimPage.cancelButton;
import static AndroidTest.pages.NewClaimPage.confirmCancelButtonOk;
import static AndroidTest.pages.NewClaimPage.dateField;
import static AndroidTest.pages.NewClaimPage.descriptionField;
import static AndroidTest.pages.NewClaimPage.saveButton;
import static AndroidTest.pages.NewClaimPage.timeField;
import static AndroidTest.pages.NewClaimPage.tittleField;

import androidx.recyclerview.widget.RecyclerView;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;


import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.AppActivity;

public class ClimesPage {

  public static ViewInteraction newClimeButton = onView(withId(R.id.add_new_claim_material_button));
  public static ViewInteraction filterClimesButton = onView(withId(R.id.filters_material_button));
  public static int filterClimesButtonID = R.id.filters_material_button;
  public static int listClaimsId = R.id.claim_list_recycler_view;

  public static ViewInteraction refreshZoneClaims = onView(withId(R.id.filters_material_button));


  public static void fillingFieldsNewClime(String tittle, String date, String time, String description) {
    tittleField.perform(replaceText(tittle));
    dateField.perform(replaceText(date));
    timeField.perform(replaceText(time));
    descriptionField.perform(replaceText(description));
    ViewActions.closeSoftKeyboard();
  }

  public static void isClaimExistWithParams(String tittle, String date, String time, String description){
    scrollAndClickToClaimWithTittle(tittle);
    onView(withId(R.id.title_text_view)).check(matches(withText(tittle)));
    onView(withId(R.id.plane_date_text_view)).check(matches(withText(date)));
    onView(withId(R.id.plan_time_text_view)).check(matches(withText(time)));
    onView(withId(R.id.description_text_view)).check(matches(withText(description)));
  }

  public static void filterClimes(boolean open, boolean inProgress, boolean executed, boolean cancelled) {
    waitElement(R.id.claim_list_recycler_view);
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
    waitElement(R.id.claim_list_recycler_view);
  }

  public static void checkFilteredClimes(ActivityScenarioRule<AppActivity> myActivityScenarioRule, boolean open, boolean inProgress, boolean executed, boolean cancelled) {
    onView(withId(R.id.claim_list_recycler_view))
        .perform(RecyclerViewActions.scrollToPosition(0));
    onView(withId(R.id.claim_list_recycler_view))
        .check(matches(isDisplayed()));
    final int itemCount = getClaimsCount(myActivityScenarioRule);

    for (int i = 0; i < itemCount; i++) {
      waitDisplayed(R.id.claim_list_recycler_view, 5000);
      scrollToPositionClaim(itemCount);
      waitDisplayed(R.id.status_label_text_view, 15000);
      if (!open && !inProgress && !executed && !cancelled) {
        onView(withId(R.id.empty_claim_list_text_view))
            .check(matches(withText(R.string.empty_claim_list_text)));
      }
      if (open && !inProgress && !executed && !cancelled) {
        onView(withId(R.id.status_label_text_view))
            .check(matches(withText(R.string.status_open)));
      }
      if (open && inProgress && !executed && !cancelled) {
        onView(withId(R.id.status_label_text_view))
            .check(matches(anyOf(withText(R.string.status_open), withText(R.string.in_progress))));
      }
      if (open && inProgress && executed && !cancelled) {
        onView(withId(R.id.status_label_text_view))
            .check(matches(anyOf(withText(R.string.status_open),
                withText(R.string.in_progress), withText(R.string.executed))));
      }
      if (open && inProgress && executed && cancelled) {
        onView(withId(R.id.status_label_text_view))
            .check(matches(anyOf(withText(R.string.status_open),
                withText(R.string.in_progress), withText(R.string.executed),
                withText(R.string.status_claim_canceled))));
      }
      if (open && inProgress && !executed && cancelled) {
        onView(withId(R.id.status_label_text_view))
            .check(matches(anyOf(withText(R.string.status_open),
                withText(R.string.in_progress), withText(R.string.status_claim_canceled))));
      }
      if (open && !inProgress && executed && cancelled) {
        onView(withId(R.id.status_label_text_view))
            .check(matches(anyOf(withText(R.string.status_open),
                withText(R.string.executed), withText(R.string.status_claim_canceled))));
      }

      if (open && !inProgress && executed && !cancelled) {
        onView(withId(R.id.status_label_text_view))
            .check(matches(anyOf(withText(R.string.status_open), withText(R.string.executed))));
      }

      if (open && !inProgress && !executed && cancelled) {
        onView(withId(R.id.status_label_text_view))
            .check(matches(anyOf(withText(R.string.status_open), withText(R.string.status_claim_canceled))));
      }
      if (!open && inProgress && !executed && !cancelled) {
        onView(withId(R.id.status_label_text_view))
            .check(matches(anyOf(withText(R.string.in_progress))));
      }
      if (!open && inProgress && executed && !cancelled) {
        onView(withId(R.id.status_label_text_view))
            .check(matches(anyOf(withText(R.string.in_progress), withText(R.string.executed))));
      }
      if (!open && inProgress && !executed && cancelled) {
        onView(withId(R.id.status_label_text_view))
            .check(matches(anyOf(withText(R.string.in_progress), withText(R.string.status_claim_canceled))));
      }
      if (!open && inProgress && executed && cancelled) {
        onView(withId(R.id.status_label_text_view))
            .check(matches(anyOf(withText(R.string.in_progress), withText(R.string.executed),
                withText(R.string.status_claim_canceled))));
      }
      if (!open && !inProgress && executed && !cancelled) {
        onView(withId(R.id.status_label_text_view))
            .check(matches(anyOf(withText(R.string.executed))));
      }
      if (!open && !inProgress && executed && cancelled) {
        onView(withId(R.id.status_label_text_view))
            .check(matches(anyOf(withText(R.string.executed), withText(R.string.status_claim_canceled))));
      }
      if (!open && !inProgress && !executed && cancelled) {
        onView(withId(R.id.status_label_text_view))
            .check(matches(withText(R.string.status_claim_canceled)));
      }
      Espresso.pressBack();
    }
  }

  public static void scrollAndClickToClaimWithTittle(String tittle) {
    waitElement(R.id.claim_list_recycler_view);
    onView(withId(R.id.claim_list_recycler_view))
        .check(matches(isDisplayed()))
        .perform(RecyclerViewActions.scrollTo(hasDescendant(allOf(withText(tittle)))));
    onView(withId(R.id.claim_list_recycler_view))
        .check(matches(isDisplayed()))
        .perform(actionOnItem(hasDescendant(withText(tittle)), click()));
  }

  public static void addNewClime(String tittle, String date, String time, String description) {
    newClimeButton.perform(click());
    fillingFieldsNewClime(tittle, date, time, description);
    saveButton.perform(click());
    waitElement(R.id.claim_list_recycler_view);
    refreshListOfNews();
  }

  public static void addNewClimeWithoutSomething(String tittle, String date, String time, String description) {
    newClimeButton.perform(click());
    fillingFieldsNewClime(tittle, date, time, description);
    saveButton.perform(click());
  }

  public static void cancelAddingNewClimeWhenPressedCancel(String tittle, String date, String time, String description) {
    newClimeButton.perform(click());
    fillingFieldsNewClime(tittle, date, time, description);
    cancelButton.perform(click());
    confirmCancelButtonOk.perform(click());
  }

  public static void cancelAddingNewClimeWhenPressedBack(String tittle, String date, String time, String description) {
    newClimeButton.perform(click());
    fillingFieldsNewClime(tittle, date, time, description);
    Espresso.pressBack();
  }

  public static void scrollToPositionClaim(int itemCount) {
    onView(withId(R.id.claim_list_recycler_view))
        .perform(actionOnItemAtPosition(itemCount, scrollTo()))
        .perform(actionOnItemAtPosition(itemCount, click()));

  }

  public static int getClaimsCount(ActivityScenarioRule<AppActivity> myActivityScenarioRule) {
    final int[] itemCount = new int[1];
    myActivityScenarioRule.getScenario().onActivity(activity -> {
      RecyclerView recyclerView = activity.findViewById(R.id.claim_list_recycler_view);
      itemCount[0] = recyclerView.getAdapter().getItemCount();
    });
    return itemCount[0];
  }

  public static void refreshListOfNews() {
    refreshZoneClaims.perform(ViewActions.swipeDown());
    waitElement(R.id.claim_list_recycler_view);
  }

}





