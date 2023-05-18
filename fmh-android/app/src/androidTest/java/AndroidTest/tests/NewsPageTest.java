package AndroidTest.tests;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.contrib.RecyclerViewActions.scrollToPosition;
import static androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static com.google.firebase.crashlytics.internal.Logger.TAG;

import static java.lang.Thread.sleep;
import static AndroidTest.data.DataHelper.getItemDateText;
import static AndroidTest.data.DataHelper.getRecyclerViewItemCount;
import static AndroidTest.data.DataHelper.waitElement;
import static AndroidTest.pages.MainPage.goToNewsPage;
import static AndroidTest.pages.NewsPage.sortingNews;

import android.util.Log;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;
import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;
import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.hamcrest.Matcher;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

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
  @DisplayName("Развернуть и свернуть новость")
  public void testExpandCollapseNews() throws InterruptedException {
    ViewInteraction recyclerView = onView(withId(R.id.news_list_recycler_view));
    recyclerView.perform(scrollToPosition(0));

    // Получить позицию первого элемента списка
    int position = 0;

    int[] heightBeforeClick = {0};
    recyclerView.perform(new DataHelper.GetHeightAfterClickViewAction(heightBeforeClick));

    // Кликнуть по элементу и раскрыть его
    recyclerView.perform(actionOnItemAtPosition(position, click()));

    int[] heightAfterClick = {0};
    recyclerView.perform(new DataHelper.GetHeightAfterClickViewAction(heightAfterClick));

    // Прокрутить список, чтобы элемент свернулся
    recyclerView.perform(scrollToPosition(position));
    
    // Снова кликнуть по элементу, чтобы свернуть его
    recyclerView.perform(actionOnItemAtPosition(position, click()));
    sleep(3000);



    int[] heightAfterSecondClick = {0};
    recyclerView.perform(new DataHelper.GetHeightAfterClickViewAction(heightAfterSecondClick));

    Log.d(TAG, "before " + heightBeforeClick[0]);
    Log.d(TAG, "After " + heightAfterClick[0]);
    Log.d(TAG, "AfterAfter " + heightAfterSecondClick[0]);

    Assert.assertTrue(heightBeforeClick[0] < heightAfterClick[0]);
    Assert.assertTrue(heightAfterSecondClick[0] < heightAfterClick[0]);
  }


//  @Test
//  @DisplayName("Развернуть и свернуть новость")
//  public void testExpandCollapseNews() throws InterruptedException {
//    ViewInteraction recyclerView = onView(withId(R.id.news_list_recycler_view));
//    recyclerView.perform(scrollToPosition(0));
//    int[] heightBeforeClick = {0};
//    recyclerView.perform(new DataHelper.GetHeightAfterClickViewAction(heightBeforeClick));
//    recyclerView.perform(actionOnItemAtPosition(0, click()));
//    int[] heightAfterClick = {0};
//    recyclerView.perform(new DataHelper.GetHeightAfterClickViewAction(heightAfterClick));
//    recyclerView.perform(actionOnItemAtPosition(0, click()));
//    sleep(3000);
//    int[] heightAfterSecondClick = {0};
//    recyclerView.perform(new DataHelper.GetHeightAfterClickViewAction(heightAfterSecondClick));
//    Log.d(TAG, "before " +heightBeforeClick[0]);
//    Log.d(TAG, "After " +heightAfterClick[0]);
//    Log.d(TAG, "AfterAfter " +heightAfterSecondClick[0]);
//    Assert.assertTrue(heightBeforeClick[0] < heightAfterClick[0]);
//    Assert.assertTrue(heightAfterSecondClick[0] < heightAfterClick[0]);
//  }
}
