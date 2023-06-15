package AndroidTest.pages;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItem;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.contrib.RecyclerViewActions.scrollToPosition;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.CoreMatchers.allOf;
import static AndroidTest.data.DataHelper.clickChildViewWithId;
import static AndroidTest.data.DataHelper.waitElement;
import static AndroidTest.pages.AddingNewsPage.confirmDelete;
import static AndroidTest.pages.EditingNews.saveButton;
import static AndroidTest.pages.EditingNews.statusSwitcher;

import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.contrib.RecyclerViewActions;

import ru.iteco.fmhandroid.R;

public class NewsEditingPage extends NewsPage {

  public static ViewInteraction addNewsButton = onView(withId(R.id.add_news_image_view));
  public static ViewInteraction categoryIconImage = onView(withId(R.id.category_icon_image_view));
  public static ViewInteraction tittleNewsEditing = onView(withId(R.id.news_item_title_text_view));
  public static ViewInteraction datePublishNews = onView(withId(R.id.news_item_publication_date_text_view));
  public static ViewInteraction dateCreateNews = onView(withId(R.id.news_item_create_date_text_view));
  public static ViewInteraction authorNews = onView(withId(R.id.news_item_author_name_text_view));
  public static ViewInteraction statusNews = onView(withId(R.id.news_item_published_text_view));
  public static ViewInteraction deleteNewsButton = onView(withId(R.id.delete_news_item_image_view));
  public static ViewInteraction editNewsButton = onView(withId(R.id.edit_news_item_image_view));
  public static ViewInteraction expandNewsButton = onView(withId(R.id.view_news_item_image_view));
  public static ViewInteraction descriptionNews = onView(withId(R.id.news_item_description_text_view));
  public static ViewInteraction refreshZone = onView(withId(R.id.news_control_panel_swipe_to_refresh));


  public static void scrollNews(int i) {
    onView(withId(R.id.news_list_recycler_view))
        .perform(scrollToPosition(i))
        .perform(actionOnItemAtPosition(i, scrollTo()))
        .check(matches(isDisplayed()));
  }


  public static void scrollAndClickToNewsWithTittle(String tittle) {
    waitElement(R.id.news_list_recycler_view);
    onView(withId(R.id.news_list_recycler_view))
        .check(matches(isDisplayed()))
        .perform(RecyclerViewActions.scrollTo(hasDescendant(allOf(withText(tittle)))));
    onView(withId(R.id.news_list_recycler_view))
        .check(matches(isDisplayed()))
        .perform(actionOnItem(hasDescendant(withText(tittle)), click()));
  }

  public static void editNews(String tittle) {
    scrollAndClickToNewsWithTittle(tittle);
    onView(allOf(withId(R.id.news_item_material_card_view), hasDescendant(withText(tittle))))
        .perform(clickChildViewWithId(R.id.edit_news_item_image_view));
  }

  public static void changeNewsStatus(String tittle) {
    scrollAndClickToNewsWithTittle(tittle);
    editNews(tittle);
    statusSwitcher.perform(click());
    saveButton.perform(click());
  }

  public static void deleteNews(String tittle) {
    scrollAndClickToNewsWithTittle(tittle);
    onView(allOf(withId(R.id.news_item_material_card_view), hasDescendant(withText(tittle))))
        .perform(clickChildViewWithId(R.id.delete_news_item_image_view));
    confirmDelete();
  }

  public static void refreshListOfNews() {
    refreshZone.perform(ViewActions.swipeDown());
    waitElement(R.id.news_list_recycler_view);
  }

}
