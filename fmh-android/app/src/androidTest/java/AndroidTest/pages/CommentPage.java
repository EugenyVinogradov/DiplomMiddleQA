package AndroidTest.pages;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.action.ViewActions.swipeUp;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.Matchers.endsWith;
import static AndroidTest.data.Data.commentClaim;
import static AndroidTest.pages.EditingClaim.addCommentButton;
import static AndroidTest.pages.EditingClaim.editCommentButton;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.contrib.RecyclerViewActions;

import ru.iteco.fmhandroid.R;

public class CommentPage {

  public static ViewInteraction commentText = onView(withClassName(endsWith("EditText")));
  public static ViewInteraction commentSaveButton = onView(withId(R.id.save_button));
  public static ViewInteraction commentCancelButton = onView(withId(R.id.cancel_button));

  public static void addNewComment(String comment) {
    fillingFieldsNewComment(comment);
    commentSaveButton.perform(click());
  }
  public static void cancelAddingNewCommentWhenPressedCancel(String comment) {
    fillingFieldsNewComment(comment);
    commentCancelButton.perform(click());
  }
  public static void cancelAddingNewCommentWhenPressedBack(String comment) {
    fillingFieldsNewComment(comment);
    Espresso.pressBack();
  }
  public static void fillingFieldsNewComment(String comment) {
    addCommentButton.perform(click());
    commentText.perform(replaceText(comment));
  }
  public static void editComment(String comment) {
    fillingFieldsNewComment(comment);
    commentSaveButton.perform(click());
  }
  public static void cancelУEditCommentWhenPressedCancel(String comment) {
    fillingFieldsNewComment(comment);
    commentCancelButton.perform(click());
  }
  public static void cancelУEditCommentWhenPressedBack(String comment) {
    fillingFieldsNewComment(comment);
    Espresso.pressBack();
  }
}
