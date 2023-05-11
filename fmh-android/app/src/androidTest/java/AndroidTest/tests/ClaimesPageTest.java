package AndroidTest.tests;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItem;
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
import static AndroidTest.data.Data.dateClaim;
import static AndroidTest.data.Data.descriptionClaim;
import static AndroidTest.data.Data.timeClaim;
import static AndroidTest.data.Data.tittleClaim;
import static AndroidTest.data.DataHelper.RecyclerViewMatcher.withRecyclerView;
import static AndroidTest.data.DataHelper.atPosition;
import static AndroidTest.data.DataHelper.childAtPosition;
import static AndroidTest.data.DataHelper.waitDisplayed;
import static AndroidTest.data.DataHelper.waitElement;
import static AndroidTest.pages.AuthPage.successLogin;
import static AndroidTest.pages.ClimesPage.addNewClime;
import static AndroidTest.pages.ClimesPage.cancelAddingNewClimeWhenPressedBack;
import static AndroidTest.pages.ClimesPage.cancelAddingNewClimeWhenPressedCancel;
import static AndroidTest.pages.ClimesPage.checkFilteredClimes;
import static AndroidTest.pages.ClimesPage.fillingFieldsNewClime;
import static AndroidTest.pages.ClimesPage.filterClimes;
import static AndroidTest.pages.ClimesPage.isClaimExistWithParams;
import static AndroidTest.pages.ClimesPage.newClimeButton;
import static AndroidTest.pages.ClimesPage.scrollAndClickToClaimWithTittle;
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
import org.junit.Assert;
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

@Test
@DisplayName("Фильтрация заявок - без статусов")
public void testAllItemsWithoutStatus() throws InterruptedException {
  filterClimes(false, false, false, false);
  checkFilteredClimes(mActivityScenarioRule, false, false, false, false);
}

//  @Test
//  @DisplayName("Фильтрация заявок - статус 'Открыта'")
//  public void testAllItemsHaveOpenStatus() throws InterruptedException {
//    filterClimes(true, false, false, false);
//    checkFilteredClimes(mActivityScenarioRule, true, false, false, false);
//  }
//  @Test
//  @DisplayName("Фильтрация заявок - статус 'Отменена'")
//  public void testAllItemsHaveCanceledStatus() throws InterruptedException {
//    filterClimes(false, false, false, true);
//    checkFilteredClimes(mActivityScenarioRule, false, false, false, true);
//  }
//  @Test
//  @DisplayName("Фильтрация заявок - статусы 'Открыта' и 'В работе'")
//  public void testAllItemsHaveOpenOrInProgressStatus() throws InterruptedException {
//    filterClimes(true, true, false, false);
//    checkFilteredClimes(mActivityScenarioRule, true, true, false, false);
//  }
//
//  @Test
//  @DisplayName("Фильтрация заявок - статусы 'Открыта', 'В работе', 'Выполнена'")
//  public void testAllItemsHaveOpenOrInProgressOrExecutedStatus() throws InterruptedException {
//    filterClimes(true, true, true, false);
//    checkFilteredClimes(mActivityScenarioRule, true, true, true, false);
//  }
//  @Test
//  @DisplayName("Фильтрация заявок - статусы 'Открыта', 'В работе', 'Выполнена', 'Отменена'")
//  public void testAllItemsHaveOpenOrInProgressOrExecutedOrCanceledStatus() throws InterruptedException {
//    filterClimes(true, true, true, true);
//    checkFilteredClimes(mActivityScenarioRule, true, true, true, true);
//  }

  @Test
  @DisplayName("Фильтрация заявок - статус 'Открыта'")
  public void testAllItemsHaveOpenStatus() {
    addNewClime(tittleClaim, dateClaim, timeClaim, descriptionClaim);
    filterClimes(true, false, false, false);
    scrollAndClickToClaimWithTittle(tittleClaim);
    onView(withId(R.id.status_label_text_view)).check(matches(allOf(isDisplayed(), withText(R.string.status_open))));
  }

  @Test
  @DisplayName("Отмена создания новой заявки при нажатии кнопки 'Cancel'")
  public void testNotCreatingNewClaimWhenPressingCancelButton() throws InterruptedException {
    cancelAddingNewClimeWhenPressedCancel(tittleClaim, dateClaim, timeClaim, descriptionClaim);
    onView(withId(R.id.claim_list_recycler_view))
        .check(matches(not(hasDescendant(withText(tittleClaim)))));
  }

  @Test
  @DisplayName("Отмена создания новой заявки при нажатии системной кнопки 'Назад'")
  public void testNotCreatingNewClaimWhenPressingBackButton() throws InterruptedException {
    cancelAddingNewClimeWhenPressedBack(tittleClaim, dateClaim, timeClaim, descriptionClaim);
    onView(withId(R.id.claim_list_recycler_view))
        .check(matches(not(hasDescendant(withText(tittleClaim)))));
  }
}

