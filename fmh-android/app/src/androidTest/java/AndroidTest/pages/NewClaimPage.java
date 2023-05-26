package AndroidTest.pages;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.CoreMatchers.allOf;
import static org.junit.Assert.assertEquals;
import static AndroidTest.data.DataHelper.getStringFromResource;
import static AndroidTest.data.DataHelper.getTextFromViewInteraction;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.ViewInteraction;

import ru.iteco.fmhandroid.R;

public class NewClaimPage {

  public static ViewInteraction tittleField = onView(withId(R.id.title_edit_text));
  public static ViewInteraction executorField = onView(withId(R.id.textinput_placeholder));
  public static ViewInteraction executorMenuButton = onView(allOf(withId(R.id.text_input_end_icon),
      withContentDescription("Show dropdown menu")));
  public static ViewInteraction dateField = onView(withId(R.id.date_in_plan_text_input_edit_text));
  public static ViewInteraction timeField = onView(withId(R.id.time_in_plan_text_input_edit_text));
  public static ViewInteraction descriptionField = onView(withId(R.id.description_edit_text));
  public static ViewInteraction saveButton = onView(withId(R.id.save_button));
  public static ViewInteraction cancelButton = onView(withId(R.id.cancel_button));
  public static ViewInteraction confirmCancelButtonOk = onView(allOf(withId(android.R.id.button1)));
  public static ViewInteraction notConfirmCancelButtonOk = onView(allOf(withId(android.R.id.button2)));
  public static ViewInteraction changeStatusButton = onView(withId(R.id.status_processing_image_button));
  public static ViewInteraction errorAddingMessage = onView(allOf(withId(android.R.id.message)));
  public static int errorAddingMessageId = R.string.empty_fields;
  public static ViewInteraction confirmErrorAddingMessageButton = onView(allOf(withId(android.R.id.button1)));

  public static void errorWithAddingNewClaimWithoutSomeParameters() {
    errorAddingMessage.check(matches(isDisplayed()));
    String actualErrorMessage = getTextFromViewInteraction(errorAddingMessage);
    String expectedErrorMessage = getStringFromResource(errorAddingMessageId);
    assertEquals(expectedErrorMessage, actualErrorMessage);
    confirmErrorAddingMessageButton.perform(click());
    Espresso.pressBack();
  }

}
