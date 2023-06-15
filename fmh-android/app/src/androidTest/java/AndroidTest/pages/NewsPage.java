package AndroidTest.pages;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static AndroidTest.data.DataHelper.waitElement;
import static AndroidTest.pages.FilterNews.categoryNewsField;
import static AndroidTest.pages.FilterNews.checkboxActive;
import static AndroidTest.pages.FilterNews.checkboxNotActive;
import static AndroidTest.pages.FilterNews.dateEndPublish;
import static AndroidTest.pages.FilterNews.dateStartPublish;
import static AndroidTest.pages.FilterNews.filterButton;

import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.contrib.RecyclerViewActions;

import ru.iteco.fmhandroid.R;

public class NewsPage {
  public static ViewInteraction sortingNewsButton = onView(withId(R.id.sort_news_material_button));
  public static ViewInteraction editNewsButton = onView(withId(R.id.edit_news_material_button));
  public static ViewInteraction filterNewsButton = onView(withId(R.id.filter_news_material_button));

  public static ViewInteraction tittleText = onView(withId(R.id.news_item_title_text_view));
  public static ViewInteraction descriptionText = onView(withId(R.id.news_item_description_text_view));

  public static void sortingNews() {
    waitElement(R.id.news_list_recycler_view);
    sortingNewsButton.perform(click());
  }


  public static void filterNewsByDate(String startDate, String endDate) {
    filterNewsButton.perform(click());
    dateStartPublish.perform(replaceText(startDate));
    dateEndPublish.perform(replaceText(endDate));
    filterButton.perform(click());
  }

  public static void filterNewsByStatus(boolean active, boolean notActive) {
    filterNewsButton.perform(click());
    if (!active) {
      checkboxActive.perform(click());
    }
    if (!notActive) {
      checkboxNotActive.perform(click());
    }
    filterButton.perform(click());
    waitElement(R.id.news_list_recycler_view);
  }

  public static void filterNewsByCategory(String category) {
    filterNewsButton.perform(click());
    categoryNewsField.perform(replaceText(category));
    filterButton.perform(click());
  }

  public static void filterNewsByStatusAndDate(boolean active, boolean notActive, String startDate, String endDate) {
    filterNewsButton.perform(click());
    if (!active) {
      checkboxActive.perform(click());
    }
    if (!notActive) {
      checkboxNotActive.perform(click());
    }
    dateStartPublish.perform(replaceText(startDate));
    dateEndPublish.perform(replaceText(endDate));
    filterButton.perform(click());
  }

  public static void goToNewsEditingPage() {
    editNewsButton.perform(click());
  }

  public static void scrollNewsToPosition(int position) {
    waitElement(R.id.news_list_recycler_view);
    onView(withId(R.id.news_list_recycler_view)).perform(RecyclerViewActions.scrollToPosition(position));
  }


}
