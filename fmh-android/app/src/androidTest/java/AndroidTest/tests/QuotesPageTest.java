package AndroidTest.tests;

import static AndroidTest.Steps.AllureSteps.goToQuotesPageStep;
import static AndroidTest.Steps.AllureSteps.logOutFromApp;
import static AndroidTest.Steps.AllureSteps.successLoginStep;

import androidx.test.espresso.ViewInteraction;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import AndroidTest.Steps.AllureSteps;
import io.qameta.allure.android.rules.ScreenshotRule;
import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.Attachment;
import io.qameta.allure.kotlin.Epic;
import io.qameta.allure.kotlin.junit4.DisplayName;
import ru.iteco.fmhandroid.ui.AppActivity;

@Epic("Тестирование страницы Цитаты")

@RunWith(AllureAndroidJUnit4.class)
public class QuotesPageTest {

  @Before
  public void login() {
    successLoginStep();
    goToQuotesPageStep();
  }

  @After
  public void logOutApp() {
    logOutFromApp();
  }

  @Rule
  public ActivityScenarioRule<AppActivity> myActivityScenarioRule =
      new ActivityScenarioRule<>(AppActivity.class);

  @Rule
  public ScreenshotRule screenshotRule =
      new ScreenshotRule(ScreenshotRule.Mode.FAILURE, "test_fail");

  @Test
  @DisplayName("Развернуть цитату")
  @Attachment
  public void testExpandQuote() {
    ViewInteraction recyclerView = AllureSteps.getQuotesRecyclerViewAndScrollToFirstPosition();
    int heightBeforeClick = AllureSteps.getHeightBeforeClick(recyclerView);
    AllureSteps.clickFirstItem(recyclerView);
    int heightAfterClick = AllureSteps.getHeightAfterClick(recyclerView);
    AllureSteps.checkHeightAfterClick(heightBeforeClick, heightAfterClick);
  }

  @Test
  @DisplayName("Свернуть цитату")
  @Attachment
  public void testCollapseQuote() {
    ViewInteraction recyclerView = AllureSteps.getQuotesRecyclerViewAndScrollToFirstPosition();
    int heightBeforeClick = AllureSteps.getHeightBeforeClick(recyclerView);
    AllureSteps.doubleClickFirstItem(recyclerView);
    int heightAfterDoubleClick = AllureSteps.getHeightAfterClick(recyclerView);
    AllureSteps.checkHeightAfterDoubleClick(heightBeforeClick, heightAfterDoubleClick);
  }
}
