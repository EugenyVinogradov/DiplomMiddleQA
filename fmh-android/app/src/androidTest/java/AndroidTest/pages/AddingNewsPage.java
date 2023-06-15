package AndroidTest.pages;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.CoreMatchers.allOf;
import static AndroidTest.data.DataHelper.waitElement;
import static AndroidTest.pages.NewsEditingPage.addNewsButton;

import androidx.test.espresso.ViewInteraction;

import ru.iteco.fmhandroid.R;

public class AddingNewsPage {

  public static ViewInteraction categoryField = onView(withId(R.id.news_item_category_text_auto_complete_text_view));
  public static ViewInteraction tittleField = onView(withId(R.id.news_item_title_text_input_edit_text));
  public static ViewInteraction dateField = onView(withId(R.id.news_item_publish_date_text_input_edit_text));
  public static ViewInteraction timeField = onView(withId(R.id.news_item_publish_time_text_input_edit_text));
  public static ViewInteraction descriptionField = onView(withId(R.id.news_item_description_text_input_edit_text));
  public static ViewInteraction statusSwitcher = onView(withId(R.id.switcher));
  public static ViewInteraction saveButton = onView(withId(R.id.save_button));
  public static ViewInteraction cancelButton = onView(withId(R.id.cancel_button));
  public static ViewInteraction confirmCancelAddingNewsButton = onView(allOf(withId(android.R.id.button1)));
  public static ViewInteraction cancelMessage = onView(withText(R.string.cancellation));
  public static ViewInteraction confirmDeleteNewsButton = onView(allOf(withId(android.R.id.button1)));


  public static void fillingNewsFields(String category, String tittle, String date, String time, String description) {
    addNewsButton.perform(click());
    categoryField.perform(replaceText(category));
    tittleField.perform(replaceText(tittle));
    dateField.perform(replaceText(date));
    timeField.perform(replaceText(time));
    descriptionField.perform(replaceText(description));
  }

  public static void addNews(String category, String tittle, String date, String time, String description) {
    fillingNewsFields(category, tittle, date, time, description);
    saveButton.perform(click());
  }

  public static void confirmDelete() {
    waitElement(android.R.id.button1);
    confirmDeleteNewsButton.perform(click());
  }
}
