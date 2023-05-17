package AndroidTest.tests;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.contrib.RecyclerViewActions.scrollToPosition;
import static androidx.test.espresso.matcher.ViewMatchers.assertThat;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static androidx.test.espresso.matcher.ViewMatchers.withHint;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static java.lang.Thread.sleep;
import static AndroidTest.data.DataHelper.RecyclerViewMatcher.withRecyclerView;
import static AndroidTest.data.DataHelper.atPosition;
import static AndroidTest.data.DataHelper.getItemDateText;
import static AndroidTest.data.DataHelper.getRecyclerViewItemCount;
import static AndroidTest.data.DataHelper.getRecyclerViewItemHeight;
import static AndroidTest.data.DataHelper.waitElement;
import static AndroidTest.pages.AuthPage.successLogin;
import static AndroidTest.pages.MainPage.goToNewsPage;
import static AndroidTest.pages.MainPage.logOut;
import static AndroidTest.pages.NewsPage.descriptionText;
import static AndroidTest.pages.NewsPage.sortingNews;

import android.util.Log;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;
import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

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
    String firstDateBeforeSorting = getItemDateText(R.id.news_list_recycler_view, 0);
    onView(withId(R.id.news_list_recycler_view)).perform(RecyclerViewActions.scrollToPosition(itemCount - 1));
    String lastDateBeforeSorting = getItemDateText(R.id.news_list_recycler_view, itemCount - 1);
    sortingNews();
    onView(withId(R.id.news_list_recycler_view)).perform(RecyclerViewActions.scrollToPosition(0));
    String firstDateAfterSorting = getItemDateText(R.id.news_list_recycler_view, 0);
    onView(withId(R.id.news_list_recycler_view)).perform(RecyclerViewActions.scrollToPosition(itemCount - 1));
    String lastDateAfterSorting = getItemDateText(R.id.news_list_recycler_view, itemCount - 1);
    Assert.assertEquals(firstDateBeforeSorting, lastDateAfterSorting);
    Assert.assertEquals(lastDateBeforeSorting, firstDateAfterSorting);

  }
  @Test
  @DisplayName("Развернуть и сверуть новость")
  public void testExpandCollapseNews() throws InterruptedException {
    onView(withId(R.id.news_list_recycler_view)).check(matches(isDisplayed()))
        .perform(RecyclerViewActions.scrollToPosition(0)).perform(click());
    sleep(3000);

    myActivityScenarioRule.getScenario().onActivity(activity -> {
      RecyclerView recyclerView = activity.findViewById(R.id.news_list_recycler_view);
      onView(withId(R.id.news_list_recycler_view))
          .perform(RecyclerViewActions.scrollToPosition(0));

      ViewInteraction firstNewsItem = onView(withRecyclerView(R.id.news_list_recycler_view)
          .atPositionOnView(0, R.id.news_item_material_card_view))
          .check(matches(isDisplayed()));

      int heightBeforeClick = getRecyclerViewItemHeight(recyclerView, 0);
      firstNewsItem.perform(click());
      int heightAfterClick = getRecyclerViewItemHeight(recyclerView, 0);

      Assert.assertTrue(heightBeforeClick < heightAfterClick);
    });
  }
}
