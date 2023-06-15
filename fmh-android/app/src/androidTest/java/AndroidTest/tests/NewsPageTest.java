package AndroidTest.tests;

import static AndroidTest.data.DataHelper.getUniqueScreenshotName;
import static AndroidTest.data.DataHelper.waitElement;
import static AndroidTest.pages.AuthPage.checkLogInAndLogInIfNot;
import static AndroidTest.steps.BaseSteps.goToNewsPageStep;

import androidx.test.espresso.ViewInteraction;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import AndroidTest.steps.BaseSteps;
import AndroidTest.steps.NewsEditingPageSteps;
import AndroidTest.steps.NewsPageSteps;
import io.qameta.allure.android.rules.ScreenshotRule;
import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.Epic;
import io.qameta.allure.kotlin.junit4.DisplayName;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.AppActivity;

@Epic("Тестирование страницы Новости")

@RunWith(AllureAndroidJUnit4.class)
public class NewsPageTest {


  @Before
  public void setUp() {
    checkLogInAndLogInIfNot();
    goToNewsPageStep();
  }

  @Rule
  public ActivityScenarioRule<AppActivity> myActivityScenarioRule =
      new ActivityScenarioRule<>(AppActivity.class);

  @Rule
  public ScreenshotRule screenshotRule =
      new ScreenshotRule(ScreenshotRule.Mode.FAILURE, getUniqueScreenshotName());

  @Test
  @DisplayName("Сортировка новостей в списке новостей")
  public void testSortingNews() {
    int itemCount = NewsEditingPageSteps.getItemCount();
    String firstDateBeforeSorting = NewsPageSteps.getFirstDateBeforeSortingNewsPage();
    NewsEditingPageSteps.scrollNewsToLastPosition(itemCount - 1);
    String lastDateBeforeSorting = NewsPageSteps.getLastDateBeforeSortingNewsPage(itemCount - 1);
    NewsEditingPageSteps.sortingNewsStep();
    NewsEditingPageSteps.scrollNewsToFirstPosition();
    String firstDateAfterSorting = NewsPageSteps.getFirstDateAfterSortingNewsPage();
    NewsEditingPageSteps.scrollNewsToLastPosition(itemCount - 1);
    String lastDateAfterSorting = NewsPageSteps.getLastDateAfterSortingNewsPage(itemCount - 1);
    NewsEditingPageSteps.checkDateAfterSortingOne(firstDateBeforeSorting, lastDateAfterSorting);
    NewsEditingPageSteps.checkDateAfterSortingTwo(lastDateBeforeSorting, firstDateAfterSorting);
  }

  @Test
  @DisplayName("Развернуть новость")
  public void testExpandNews() {
    waitElement(R.id.news_list_recycler_view);
    ViewInteraction recyclerView = NewsPageSteps.getRecyclerViewAndScrollToFirstPosition();
    int heightBeforeClick = NewsPageSteps.getHeightBeforeClick(recyclerView);
    NewsPageSteps.clickFirstItem(recyclerView);
    int heightAfterClick = NewsPageSteps.getHeightAfterClick(recyclerView);
    NewsPageSteps.checkHeightAfterClick(heightBeforeClick, heightAfterClick);
  }

  @Test
  @DisplayName("Свернуть новость")
  public void testCollapseNews() {
    waitElement(R.id.news_list_recycler_view);
    ViewInteraction recyclerView = NewsPageSteps.getRecyclerViewAndScrollToFirstPosition();
    int heightBeforeClick = NewsPageSteps.getHeightBeforeClick(recyclerView);
    NewsPageSteps.doubleClickFirstItem(recyclerView);
    int heightAfterClick = NewsPageSteps.getHeightAfterClick(recyclerView);
    NewsPageSteps.checkHeightAfterDoubleClick(heightBeforeClick, heightAfterClick);
  }

  @Test
  @DisplayName("Фильтрация новостей по дате")
  public void testFilterNewsByDate() {
    BaseSteps.goToNewsEditingPageStep();
    String expectedDate = NewsPageSteps.addingNewsAndReturnPublishDate();
    BaseSteps.goToNewsPageStep();
    NewsPageSteps.filterNewsByDateStep(expectedDate);
    int itemCount = NewsEditingPageSteps.getItemCount();
    NewsPageSteps.checkPublishDateNews(itemCount, expectedDate);
  }

}
