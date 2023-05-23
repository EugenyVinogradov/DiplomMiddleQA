package AndroidTest.tests;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.doubleClick;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.contrib.RecyclerViewActions.scrollToPosition;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static com.google.firebase.crashlytics.internal.Logger.TAG;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static java.lang.Thread.sleep;
import static AndroidTest.data.Data.categoryForth;
import static AndroidTest.data.Data.dateNews;
import static AndroidTest.data.Data.dateNewsNextDay;
import static AndroidTest.data.Data.dateNewsPreviousDay;
import static AndroidTest.data.Data.descriptionNews;
import static AndroidTest.data.Data.timeNews;
import static AndroidTest.data.Data.tittleNews;
import static AndroidTest.data.DataHelper.getRecyclerViewItemCount;
import static AndroidTest.data.DataHelper.getTextFromNews;
import static AndroidTest.data.DataHelper.getViewHeight;
import static AndroidTest.data.DataHelper.waitDisplayed;
import static AndroidTest.data.DataHelper.waitElement;
import static AndroidTest.pages.AuthPage.successLogin;
import static AndroidTest.pages.MainPage.goToNewsPage;
import static AndroidTest.pages.MainPage.logOut;
import static AndroidTest.pages.NewsEditingPage.scrollNews;
import static AndroidTest.pages.NewsPage.filterNewsByDate;
import static AndroidTest.pages.NewsPage.goToNewsEditingPage;
import static AndroidTest.pages.NewsPage.sortingNews;
import static AndroidTest.pages.AddingNewsPage.addNews;

import androidx.recyclerview.widget.RecyclerView;
import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.NoMatchingViewException;
import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;
import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.BoundedMatcher;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.CountDownLatch;

import AndroidTest.data.DataHelper;
import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.junit4.DisplayName;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.AppActivity;

@RunWith(AllureAndroidJUnit4.class)

public class NewsPageTest {


  @Before
  public void login() {
//    successLogin();
    goToNewsPage();
  }

//  @After
//  public void logOutApp() {
//    logOut();
//  }

  @Rule
  public ActivityScenarioRule<AppActivity> myActivityScenarioRule =
      new ActivityScenarioRule<>(AppActivity.class);

  @Test
  @DisplayName("Сортировка новостей")
  public void testSortingNews() {
    waitElement(R.id.news_list_recycler_view);
    onView(withId(R.id.news_list_recycler_view)).check(matches(isDisplayed()))
        .perform(RecyclerViewActions.scrollToPosition(0));
    int itemCount = getRecyclerViewItemCount(R.id.news_list_recycler_view);
    String firstDateBeforeSorting = getTextFromNews(R.id.news_item_date_text_view, 0);
    onView(withId(R.id.news_list_recycler_view)).perform(RecyclerViewActions.scrollToPosition(itemCount - 1));
    String lastDateBeforeSorting = getTextFromNews(R.id.news_item_date_text_view, itemCount - 1);
    sortingNews();
    onView(withId(R.id.news_list_recycler_view)).perform(RecyclerViewActions.scrollToPosition(0));
    String firstDateAfterSorting = getTextFromNews(R.id.news_item_date_text_view, 0);
    onView(withId(R.id.news_list_recycler_view)).perform(RecyclerViewActions.scrollToPosition(itemCount - 1));
    String lastDateAfterSorting = getTextFromNews(R.id.news_item_date_text_view, itemCount - 1);
    assertEquals(firstDateBeforeSorting, lastDateAfterSorting);
    assertEquals(lastDateBeforeSorting, firstDateAfterSorting);

  }

  @Test
  @DisplayName("Развернуть новость")
  public void testExpandNews() {
    ViewInteraction recyclerView = onView(withId(R.id.news_list_recycler_view));
    recyclerView.perform(scrollToPosition(0));
    int[] heightBeforeClick = {0};
    recyclerView.perform(new DataHelper.GetHeightAfterClickViewAction(heightBeforeClick));
    recyclerView.perform(actionOnItemAtPosition(0, click()));
    int[] heightAfterClick = {0};
    recyclerView.perform(new DataHelper.GetHeightAfterClickViewAction(heightAfterClick));
    Assert.assertTrue(heightBeforeClick[0] < heightAfterClick[0]);
  }

  @Test
  @DisplayName("Свернуть новость")
  public void testCollapseNews() {
    ViewInteraction recyclerView = onView(withId(R.id.news_list_recycler_view));
    recyclerView.perform(scrollToPosition(0));
    int[] heightBeforeClick = {0};
    recyclerView.perform(new DataHelper.GetHeightAfterClickViewAction(heightBeforeClick));
    recyclerView.perform(actionOnItemAtPosition(0, doubleClick()));
    int[] heightAfterClick = {0};
    recyclerView.perform(new DataHelper.GetHeightAfterClickViewAction(heightAfterClick));
    assertEquals(heightBeforeClick[0], heightAfterClick[0]);
  }

  @Test
  @DisplayName("Фильтрация новостей по дате")
  public void testFilterNewsByDate()  {
    goToNewsEditingPage();
     addNews(categoryForth, tittleNews, dateNews, timeNews, descriptionNews);
     filterNewsByDate(dateNews, dateNews);
    ActivityScenario<AppActivity> scenario = myActivityScenarioRule.getScenario();
    final int[] itemCount = new int[1];
    scenario.onActivity(activity -> {
      RecyclerView recyclerView = activity.findViewById(R.id.news_list_recycler_view);
      itemCount[0] = recyclerView.getAdapter().getItemCount();
    });
    for (int i = 0; i < itemCount[0]; i++) {
      scrollNews(i);
      String actualDate = getTextFromNews(R.id.news_item_publication_date_text_view,i);
      assertEquals(dateNews, actualDate);
    }
  }


}
