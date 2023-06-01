package AndroidTest.tests;

import static AndroidTest.Steps.AllureSteps.goToNewsPageStep;
import static AndroidTest.Steps.AllureSteps.logOutFromApp;
import static AndroidTest.Steps.AllureSteps.successLoginStep;
import static AndroidTest.data.DataHelper.getUniqueScreenshotName;

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

@Epic("Тестирование страницы Новости")

@RunWith(AllureAndroidJUnit4.class)
public class NewsPageTest {


  @Before
  public void login() {
    successLoginStep();
    goToNewsPageStep();
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
      new ScreenshotRule(ScreenshotRule.Mode.FAILURE, getUniqueScreenshotName());

  @Test
  @DisplayName("Сортировка новостей в списке новостей")
  @Attachment
  public void testSortingNews() {
    int itemCount = AllureSteps.getItemCount();
    String firstDateBeforeSorting = AllureSteps.getFirstDateBeforeSortingNewsPage();
    AllureSteps.scrollNewsToLastPosition(itemCount - 1);
    String lastDateBeforeSorting = AllureSteps.getLastDateBeforeSortingNewsPage(itemCount - 1);
    AllureSteps.sortingNewsStep();
    AllureSteps.scrollNewsToFirstPosition();
    String firstDateAfterSorting = AllureSteps.getFirstDateAfterSortingNewsPage();
    AllureSteps.scrollNewsToLastPosition(itemCount - 1);
    String lastDateAfterSorting = AllureSteps.getLastDateAfterSortingNewsPage(itemCount - 1);
    AllureSteps.checkDateAfterSortingOne(firstDateBeforeSorting, lastDateAfterSorting);
    AllureSteps.checkDateAfterSortingTwo(lastDateBeforeSorting, firstDateAfterSorting);
  }

  @Test
  @DisplayName("Развернуть новость")
  @Attachment
  public void testExpandNews() {
    ViewInteraction recyclerView = AllureSteps.getRecyclerViewAndScrollToFirstPosition();
    int heightBeforeClick = AllureSteps.getHeightBeforeClick(recyclerView);
    AllureSteps.clickFirstItem(recyclerView);
    int heightAfterClick = AllureSteps.getHeightAfterClick(recyclerView);
    AllureSteps.checkHeightAfterClick(heightBeforeClick, heightAfterClick);
  }

  @Test
  @DisplayName("Свернуть новость")
  @Attachment
  public void testCollapseNews() {
    ViewInteraction recyclerView = AllureSteps.getRecyclerViewAndScrollToFirstPosition();
    int heightBeforeClick = AllureSteps.getHeightBeforeClick(recyclerView);
    AllureSteps.doubleClickFirstItem(recyclerView);
    int heightAfterClick = AllureSteps.getHeightAfterClick(recyclerView);
    AllureSteps.checkHeightAfterDoubleClick(heightBeforeClick, heightAfterClick);
  }

  @Test
  @DisplayName("Фильтрация новостей по дате")
  @Attachment
  public void testFilterNewsByDate() throws InterruptedException {
    AllureSteps.goToNewsEditingPageStep();
    String expectedDate = AllureSteps.addingNewsAndReturnPublishDate();
    AllureSteps.goToNewsPageStep();
    AllureSteps.filterNewsByDateStep(expectedDate);
    int itemCount = AllureSteps.getItemCount();
    AllureSteps.checkPublishDateNews(itemCount, expectedDate);
  }

}
