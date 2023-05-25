package AndroidTest.pages;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import static org.hamcrest.CoreMatchers.allOf;

import androidx.test.espresso.ViewInteraction;

import ru.iteco.fmhandroid.R;

public class FilterNews {

  public static ViewInteraction filterNewsTittle = onView(withId(R.id.filter_news_title_text_view));
  public static ViewInteraction categoryNewsButton = onView(allOf(withId(R.id.text_input_end_icon),
      withContentDescription("Show dropdown menu")));
  public static ViewInteraction categoryNewsField = onView(withId(R.id.news_item_category_text_auto_complete_text_view));
  public static ViewInteraction dateStartPublish = onView(withId(R.id.news_item_publish_date_start_text_input_edit_text));
  public static ViewInteraction dateEndPublish = onView(withId(R.id.news_item_publish_date_end_text_input_edit_text));
  public static ViewInteraction filterButton = onView(withId(R.id.filter_button));
  public static ViewInteraction cancelButton = onView(withId(R.id.cancel_button));
  public static ViewInteraction checkboxActive = onView(withId(R.id.filter_news_active_material_check_box));
  public static ViewInteraction checkboxNotActive = onView(withId(R.id.filter_news_inactive_material_check_box));

}
