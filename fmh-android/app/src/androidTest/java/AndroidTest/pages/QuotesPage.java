package AndroidTest.pages;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.CoreMatchers.allOf;

import androidx.test.espresso.ViewInteraction;

import ru.iteco.fmhandroid.R;

public class QuotesPage {

  public static ViewInteraction header = onView(allOf(withText(R.string.our_mission_title_text)));
  public static ViewInteraction quoteText = onView(allOf(withId(R.id.our_mission_item_title_text_view)));
  public static ViewInteraction quoteExpandButton = onView(allOf(withId(R.id.our_mission_item_open_card_image_button)));

}
