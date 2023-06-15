package AndroidTest.pages;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.allOf;
import static AndroidTest.data.Data.commentClaim;
import static AndroidTest.data.DataHelper.waitElement;
import static AndroidTest.pages.MainPage.goToClaimesPage;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.ViewInteraction;

import ru.iteco.fmhandroid.R;

public class EditingClaim extends NewClaimPage {
  public static ViewInteraction closeButton = onView(withId(R.id.close_image_button));
  public static ViewInteraction changeStatusToWorkButton = onView(withText(R.string.take_to_work));
  public static ViewInteraction changeStatusToCancelButton = onView(withText(R.string.cancel));
  public static ViewInteraction changeStatusToExecutedButton = onView(withText(R.string.to_execute));
  public static ViewInteraction commentOkButton = onView(allOf(withId(android.R.id.button1)));
  public static ViewInteraction commentCancelButton = onView(allOf(withId(android.R.id.button2)));
  public static ViewInteraction addCommentButton = onView(withId(R.id.add_comment_image_button));
  public static ViewInteraction editCommentButton = onView(withId(R.id.edit_comment_image_button));
  public static ViewInteraction commentField = onView(allOf(withId(R.id.comment_description_text_view)));
  public static ViewInteraction editClaimButton = onView(withId(R.id.edit_processing_image_button));
  public static ViewInteraction tittleEditField = onView(withId(R.id.title_edit_text));
  public static ViewInteraction dateEditField = onView(withId(R.id.date_in_plan_text_input_edit_text));
  public static ViewInteraction timeEditField = onView(withId(R.id.time_in_plan_text_input_edit_text));
  public static ViewInteraction descriptionEditField = onView(withId(R.id.description_edit_text));
  public static ViewInteraction saveEditingButton = onView(withId(R.id.save_button));
  public static ViewInteraction cancelEditingButton = onView(withId(R.id.cancel_button));
  public static ViewInteraction confirmCancelEditingButton = onView(allOf(withId(android.R.id.button1)));

  public static void changeStatusToInProgress() {
    changeStatusButton.perform(click());
    changeStatusToWorkButton.perform(click());
    closeButton.perform(click());
    Espresso.pressBack();
    goToClaimesPage();
    waitElement(R.id.claim_list_recycler_view);
  }

  public static void changeStatusToInCanceled() {
    changeStatusButton.perform(click());
    changeStatusToCancelButton.perform(click());
    closeButton.perform(click());
    Espresso.pressBack();
    goToClaimesPage();
    waitElement(R.id.claim_list_recycler_view);
  }

  public static void changeStatusToInExecuted() {
    changeStatusButton.perform(click());
    changeStatusToExecutedButton.perform(click());
    commentField.perform(replaceText(commentClaim));
    commentOkButton.perform(click());
    closeButton.perform(click());
    Espresso.pressBack();
    goToClaimesPage();
    waitElement(R.id.claim_list_recycler_view);
  }

  public static void editingTittleClaim(String newTittle) {
    editClaimButton.perform(click());
    tittleEditField.perform(replaceText(newTittle));
    saveEditingButton.perform(click());
  }

  public static void editingDateClaim(String newDate) {
    editClaimButton.perform(click());
    dateEditField.perform(replaceText(newDate));
    saveEditingButton.perform(click());
  }

  public static void editingTimeClaim(String newTime) {
    editClaimButton.perform(click());
    timeEditField.perform(replaceText(newTime));
    saveEditingButton.perform(click());
  }

  public static void editingDescriptionClaim(String newDescription) {
    editClaimButton.perform(click());
    descriptionEditField.perform(replaceText(newDescription));
    saveEditingButton.perform(click());
  }

  public static void fillingFieldsWhenEditingClaimAndPressCancel(String newTittle, String newDate,
                                                                 String newTime, String newDescription) {
    editClaimButton.perform(click());
    tittleEditField.perform(replaceText(newTittle));
    dateEditField.perform(replaceText(newDate));
    timeEditField.perform(replaceText(newTime));
    descriptionEditField.perform(replaceText(newDescription));
    cancelEditingButton.perform(click());
    confirmCancelEditingButton.perform(click());
  }

  public static void fillingFieldsWhenEditingClaimAndPressBack(String newTittle, String newDate,
                                                               String newTime, String newDescription) {
    editClaimButton.perform(click());
    tittleEditField.perform(replaceText(newTittle));
    dateEditField.perform(replaceText(newDate));
    timeEditField.perform(replaceText(newTime));
    descriptionEditField.perform(replaceText(newDescription));
    Espresso.pressBack();
  }
}
