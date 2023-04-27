package AndroidTest.tests;

import static androidx.test.InstrumentationRegistry.getInstrumentation;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.scrollToPosition;
import static androidx.test.espresso.matcher.ViewMatchers.assertThat;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static AndroidTest.data.DataHelper.RecyclerViewMatcher.withRecyclerView;
import static AndroidTest.pages.ClimesPage.filterClimes;
import static AndroidTest.pages.MainPage.goToClaimesPage;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.recyclerview.widget.RecyclerView;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.uiautomator.By;
import androidx.test.uiautomator.UiDevice;
import androidx.test.uiautomator.UiObject;
import androidx.test.uiautomator.UiObject2;
import androidx.test.uiautomator.UiObjectNotFoundException;
import androidx.test.uiautomator.UiScrollable;
import androidx.test.uiautomator.UiSelector;
import androidx.test.uiautomator.Until;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicReference;

import AndroidTest.data.DataHelper;
import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.junit4.DisplayName;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.AppActivity;
import androidx.test.espresso.matcher.ViewMatchers;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.Espresso.onView;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.DataInteraction;
import androidx.recyclerview.widget.RecyclerView;


//@RunWith(AndroidJUnit4.class)
@RunWith(AllureAndroidJUnit4.class)
public class ClaimesPageTest {

//  @Before
//  public void login() {
//    successLogin();
//  }

//  @After
//  public void logOutApp() {
//    logOut();
//  }

  @Rule
  public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();
  @Rule
  public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
      new ActivityScenarioRule<>(AppActivity.class);

//  @Test
//  @DisplayName("Фильтрация заявок - статус 'Открыта'")
//  public void createNewClaimWithButtonOnMainPage() {
//    goToClaimesPage();
//    filterClimes(true, false, false, false);
//    final int[] itemCount = new int[1];
//    List<ViewInteraction> myList = new ArrayList<>();
//    AtomicReference<RecyclerView.Adapter> myAdapter = null;
//    AtomicReference<RecyclerView> recyclerView = null;
//
//
//    mActivityScenarioRule.getScenario().onActivity(activity -> {
//      recyclerView.set(activity.findViewById(R.id.claim_list_recycler_view));
//      itemCount[0] = recyclerView.get().getAdapter().getItemCount();
//      myAdapter.set(recyclerView.get().getAdapter());
//    });
//    for (int i = 0; i < itemCount[0]; i++) {
////        onView(withId(R.id.claim_list_recycler_view))
////              .perform(RecyclerViewActions.actionOnItemAtPosition(i, ViewActions.scrollTo()));
////        ViewInteraction card = onView(allOf(withId(R.id.claim_list_card), withParentIndex(i)));
////            card.check(matches(hasDescendant(allOf(withId(R.id.status_label_text_view), withText("Open")))));
//
//    }
//  }


//    onView(withId(R.id.claim_list_recycler_view))
//        .check(matches(hasDescendant(allOf(withId(R.id.claim_list_card),
//            hasDescendant(allOf(withId(R.id.status_label_text_view), withText("Open")))))));
//        onView(withId(R.id.claim_list_recycler_view)).check(matches(hasItem(hasDescendant(withText("Open")))));
//    onView(withId(R.id.claim_list_recycler_view))
//        .check(matches(hasDescendant(allOf(
//            withId(R.id.claim_list_card),
//            hasDescendant(allOf(
//                withId(R.id.status_label_text_view),
//                withText("Open")
//            ))
//        ))));


//
//    mActivityScenarioRule.getScenario().onActivity(activity -> {
//          RecyclerView recyclerView = activity.findViewById(R.id.claim_list_recycler_view);
//          itemCount[0] = recyclerView.getAdapter().getItemCount();
//        });
//    for (int i = 0; i < itemCount[0]; i++) {
//      onView(withId(R.id.claim_list_recycler_view)).perform(RecyclerViewActions
//          .scrollToPosition(itemCount[0])).perform(click());
//      onView(withId(R.id.claim_list_card))
//          .check(matches(hasDescendant(allOf(withId(R.id.status_label_text_view), withText("Open")))));
//    }

//  @Test
//  public void testRecyclerView() {
//    goToClaimesPage();
//    filterClimes(true, false, false, false);
//
//    mActivityScenarioRule.getScenario().onActivity(activity -> {
//      RecyclerView recyclerView = activity.findViewById(R.id.claim_list_recycler_view);
//      if (recyclerView == null) {
//        throw new AssertionError("RecyclerView is null");
//      }
//      RecyclerView.Adapter adapter = recyclerView.getAdapter();
//      if (adapter == null) {
//        throw new AssertionError("Adapter is null");
//      }
//
//      List<ViewInteraction> viewInteractionsList = new ArrayList<>();
//      CountDownLatch latch = new CountDownLatch(adapter.getItemCount());
//      for (int i = 0; i < adapter.getItemCount(); i++) {
//        int finalI = i;
//        new Thread(() -> {
//          onView(withId(R.id.claim_list_recycler_view))
//              .perform(RecyclerViewActions.scrollToPosition(finalI))
//              .perform(RecyclerViewActions.actionOnItemAtPosition(finalI, click()))
//              .check(matches(isDisplayed()));
//          Espresso.pressBack();
//          latch.countDown();
//        }).start();
//      }
//      try {
//        latch.await();
//      } catch (InterruptedException e) {
//        e.printStackTrace();
//      }
//    });
//  }


//  @Test
//  public void testRecyclerView() throws UiObjectNotFoundException {
//    UiDevice mDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
//    UiScrollable recyclerView = new UiScrollable(new UiSelector().resourceId("ru.iteco.fmhandroid:id/claim_list_recycler_view"));
//
//    int index = 0;
//    while (recyclerView.scrollIntoView(new UiSelector()
//        .resourceId("ru.iteco.fmhandroid:id/status_label_text_view").text("Open").instance(index))) {
//      UiObject2 statusTextView = mDevice
//          .findObject(By.res("ru.iteco.fmhandroid:id/status_label_text_view")
//              .text("Open").instance(index));
//      if (!statusTextView.getParent().hasObject(By.res("ru.iteco.fmhandroid:id/claim_item_layout")
//          .isSelected(true))) {
//        fail("Item at position " + index + " does not match the criteria");
//      }
//      index++;
//    }
//  }
@Test
public void testAllItemsHaveOpenStatus() {
  goToClaimesPage();
  filterClimes(true, false, false, false);
  UiDevice uiDevice = UiDevice.getInstance(getInstrumentation());
  UiObject2 recyclerView = uiDevice.findObject(By.res("ru.iteco.fmhandroid:id/claim_list_recycler_view"));
  List<UiObject2> items = recyclerView.getChildren();
  for (UiObject2 item : items) {
    UiObject2 claimItemLayout = item.findObject(By.res("ru.iteco.fmhandroid:id/claim_item_layout"));    UiObject2 statusTextView = claimItemLayout.findObject(By.res("ru.iteco.fmhandroid:id/status_label_text_view"));
    String status = statusTextView.getText();
    if (!status.equals("Open")) {
      fail("Item with non-Open status found");
    }
  }
}









//  @Test
//  public void testRecyclerView() {
//    goToClaimesPage();
//    filterClimes(true, false, false, false);
//
//    // Ожидание загрузки RecyclerView с помощью Espresso Idling Resources
//    onView(withId(R.id.claim_list_recycler_view)).check(matches(isDisplayed()));
//    onView(withId(R.id.claim_list_recycler_view)).perform(RecyclerViewActions.scrollToPosition(4));
//    Espresso.onView(ViewMatchers.withId(R.id.claim_list_recycler_view));
//
//    // Проверка первых нескольких элементов RecyclerView
//    onView(withRecyclerView(R.id.claim_list_recycler_view).atPosition(0))
//        .check(matches(hasDescendant(withId(R.id.status_label_text_view))))
//        .check(matches(hasDescendant(withText("Open"))));
//    onView(withRecyclerView(R.id.claim_list_recycler_view).atPosition(1))
//        .check(matches(hasDescendant(withId(R.id.status_label_text_view))))
//        .check(matches(hasDescendant(withText("Open"))));
//    onView(withRecyclerView(R.id.claim_list_recycler_view).atPosition(2))
//        .check(matches(hasDescendant(withId(R.id.status_label_text_view))))
//        .check(matches(hasDescendant(withText("Open"))));
//
//  }
}