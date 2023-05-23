package AndroidTest.pages;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.contrib.RecyclerViewActions.scrollToPosition;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import androidx.test.espresso.ViewInteraction;

import ru.iteco.fmhandroid.R;

public class NewsEditingPage extends NewsPage{

  public static ViewInteraction addNewsButton = onView(withId(R.id.add_news_image_view));


  public static void scrollNews(int i) {
    onView(withId(R.id.news_list_recycler_view))
        .perform(scrollToPosition(i))
        .perform(actionOnItemAtPosition(i, scrollTo()))
        .check(matches(isDisplayed()));
  }





}
