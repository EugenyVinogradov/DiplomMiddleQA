package AndroidTest.tests;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.doubleClick;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.contrib.RecyclerViewActions.scrollToPosition;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static com.google.firebase.crashlytics.internal.Logger.TAG;
import static AndroidTest.pages.AuthPage.successLogin;
import static AndroidTest.pages.MainPage.goToQuotesPage;
import static AndroidTest.pages.MainPage.logOut;

import android.util.Log;

import androidx.test.espresso.ViewInteraction;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

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
public class QuotesPageTest {

  @Before
  public void login() {
    successLogin();
    goToQuotesPage();
  }

  @After
  public void logOutApp() {
    logOut();
  }

  @Rule
  public ActivityScenarioRule<AppActivity> myActivityScenarioRule =
      new ActivityScenarioRule<>(AppActivity.class);

  @Test
  @DisplayName("Развернуть цитату")
  public void testExpandNews() {
    ViewInteraction recyclerView = onView(withId(R.id.our_mission_item_list_recycler_view));
    recyclerView.perform(scrollToPosition(0));
    int[] heightBeforeClick = {0};
    recyclerView.perform(new DataHelper.GetHeightAfterClickViewAction(heightBeforeClick));
    recyclerView.perform(actionOnItemAtPosition(0, click()));
    int[] heightAfterClick = {0};
    recyclerView.perform(new DataHelper.GetHeightAfterClickViewAction(heightAfterClick));
    Assert.assertTrue(heightBeforeClick[0] < heightAfterClick[0]);
  }
  @Test
  @DisplayName("Свернуть цитату")
  public void testCollapseNews() {
    ViewInteraction recyclerView = onView(withId(R.id.our_mission_item_list_recycler_view));
    recyclerView.perform(scrollToPosition(0));
    int[] heightBeforeClick = {0};
    recyclerView.perform(new DataHelper.GetHeightAfterClickViewAction(heightBeforeClick));
    recyclerView.perform(actionOnItemAtPosition(0, doubleClick()));
    int[] heightAfterClick = {0};
    recyclerView.perform(new DataHelper.GetHeightAfterClickViewAction(heightAfterClick));
    Log.d(TAG, "before " +heightBeforeClick[0]);
    Log.d(TAG, "After " +heightAfterClick[0]);
    Assert.assertEquals(heightBeforeClick[0] , heightAfterClick[0]);
  }
}
