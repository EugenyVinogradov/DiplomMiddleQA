package AndroidTest.tests;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.contrib.RecyclerViewActions.scrollToPosition;
import static androidx.test.espresso.matcher.ViewMatchers.Visibility.VISIBLE;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.Matchers.anyOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static java.lang.Thread.sleep;
import static AndroidTest.data.DataHelper.RecyclerViewMatcher.withRecyclerView;
import static AndroidTest.data.DataHelper.atPosition;
import static AndroidTest.data.DataHelper.childAtPosition;
import static AndroidTest.data.DataHelper.waitDisplayed;
import static AndroidTest.data.DataHelper.waitElement;
import static AndroidTest.pages.AuthPage.successLogin;
import static AndroidTest.pages.ClimesPage.checkFilteredClimes;
import static AndroidTest.pages.ClimesPage.filterClimes;
import static AndroidTest.pages.MainPage.goToClaimesPage;
import static AndroidTest.pages.MainPage.logOut;


import android.util.DebugUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

import AndroidTest.data.DataHelper;
import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.junit4.DisplayName;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.AppActivity;



//@RunWith(AndroidJUnit4.class)
@RunWith(AllureAndroidJUnit4.class)
public class ClaimesPageTest {

  @Before
  public void login() {
    successLogin();
    goToClaimesPage();
  }

  @After
  public void logOutApp() {
    logOut();
  }

  @Rule
  public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
      new ActivityScenarioRule<>(AppActivity.class);

//  @Test
//  @DisplayName("Фильтрация заявок - статус 'Открыта'")
//  public void testAllItemsHaveOpenStatus() throws InterruptedException {
//    goToClaimesPage();
//    filterClimes(true, false, false, false);
//
//    CountDownLatch latch = new CountDownLatch(1);
//    mActivityScenarioRule.getScenario().onActivity(activity -> {
//      RecyclerView recyclerView = activity.findViewById(R.id.claim_list_recycler_view);
//      assertNotNull(recyclerView);
//
//      ArrayList<RecyclerView.ViewHolder> viewHolders = new ArrayList<>();
//      recyclerView.post(() -> {
//        RecyclerView.Adapter adapter = recyclerView.getAdapter();
//        assertNotNull(adapter);
//
//        for (int i = 0; i < adapter.getItemCount(); i++) {
//          RecyclerView.ViewHolder viewHolder = adapter.createViewHolder(recyclerView, adapter.getItemViewType(i));
//          adapter.bindViewHolder(viewHolder, i);
//          viewHolders.add(viewHolder);
//        }
//
//        for (RecyclerView.ViewHolder viewHolder : viewHolders) {
//          TextView hiddenTextView = viewHolder.itemView.findViewById(R.id.status_label_text_view);
//
//          assertNotNull(hiddenTextView);
//          String text = hiddenTextView.getText().toString();
//          assertEquals("Open", text);
//        }
//        latch.countDown();
//      });
//    });
//    latch.await();
//  }


//  @Test
//  @DisplayName("Фильтрация заявок - статус 'Открыта'")
//  public void testAllItemsHaveOpenStatus111111() throws InterruptedException {
//    goToClaimesPage();
//    filterClimes(true, false, false, false);
//    onView(withId(R.id.claim_list_recycler_view))
//        .perform(scrollToPosition(0));
//    onView(withId(R.id.claim_list_recycler_view))
//        .check(matches(isDisplayed()));
//    onView(withId(R.id.claim_list_recycler_view))
//        .perform(scrollToPosition(0), click());
//    waitElement(R.id.status_label_text_view);
//    onView(withId(R.id.status_label_text_view))
//        .check(matches(withText("Open")));
//  }

@Test
@DisplayName("Фильтрация заявок - без статусов")
public void testAllItemsWithoutStatus() throws InterruptedException {
  filterClimes(false, false, false, false);
  checkFilteredClimes(mActivityScenarioRule, false, false, false, false);
}

  @Test
  @DisplayName("Фильтрация заявок - статус 'Открыта'")
  public void testAllItemsHaveOpenStatus() throws InterruptedException {
    filterClimes(true, false, false, false);
    checkFilteredClimes(mActivityScenarioRule, true, false, false, false);
  }
  @Test
  @DisplayName("Фильтрация заявок - статус 'Отменена'")
  public void testAllItemsHaveCanceledStatus() throws InterruptedException {
    filterClimes(false, false, false, true);
    checkFilteredClimes(mActivityScenarioRule, false, false, false, true);
  }
  @Test
  @DisplayName("Фильтрация заявок - статусы 'Открыта' и 'В работе'")
  public void testAllItemsHaveOpenOrInProgressStatus() throws InterruptedException {
    filterClimes(true, true, false, false);
    checkFilteredClimes(mActivityScenarioRule, true, true, false, false);
  }

  @Test
  @DisplayName("Фильтрация заявок - статусы 'Открыта', 'В работе', 'Выполнена'")
  public void testAllItemsHaveOpenOrInProgressOrExecutedStatus() throws InterruptedException {
    filterClimes(true, true, true, false);
    checkFilteredClimes(mActivityScenarioRule, true, true, true, false);
  }
  @Test
  @DisplayName("Фильтрация заявок - статусы 'Открыта', 'В работе', 'Выполнена', 'Отменена'")
  public void testAllItemsHaveOpenOrInProgressOrExecutedOrCanceledStatus() throws InterruptedException {
    filterClimes(true, true, true, true);
    checkFilteredClimes(mActivityScenarioRule, true, true, true, true);
  }
//@Test
//public void testAllItemsHaveOpenStatus() throws InterruptedException {
//  goToClaimesPage();
//  filterClimes(true, false, false, false);
//  onView(withId(R.id.claim_list_recycler_view))
//      .perform(RecyclerViewActions.scrollToPosition(0));
//  onView(withId(R.id.claim_list_recycler_view))
//      .check(matches(isDisplayed()));
//  final LinearLayoutManager[] layoutManager = new LinearLayoutManager[1];
//  final int[] itemCount = new int[1];
//  mActivityScenarioRule.getScenario().onActivity(activity -> {
//    RecyclerView recyclerView = activity.findViewById(R.id.claim_list_recycler_view);
//    layoutManager[0] = (LinearLayoutManager) recyclerView.getLayoutManager();
//    itemCount[0] = recyclerView.getAdapter().getItemCount();
//  });
//  for (int i = 0; i < itemCount[0]; i++) {
//    layoutManager[0].scrollToPositionWithOffset(i, 0);
//    onView(withId(R.id.claim_list_recycler_view)).perform(actionOnItemAtPosition(i, click()));
//    waitDisplayed(R.id.status_label_text_view, 15000);
//    onView(withId(R.id.status_label_text_view))
//        .check(matches(withText("Open")));
//    Espresso.pressBack();
//    // задержка перед проверкой следующего элемента
//    onView(withId(R.id.claim_list_recycler_view)).perform(RecyclerViewActions.scrollToPosition(i + 1));
//    onView(withId(R.id.claim_list_recycler_view)).perform(actionOnItemAtPosition(i + 1, click()));
//    onView(withId(R.id.status_label_text_view))
//        .check(matches(withText("Open")));
//    mActivityScenarioRule.getScenario().onActivity(activity -> {
//      activity.findViewById(R.id.status_label_text_view).postDelayed(() -> {
//        onView(withId(R.id.status_label_text_view))
//            .check(matches(withText("Open")));
//        Espresso.pressBack();
//      }, 1000);
//    });
//  }
//}


}

