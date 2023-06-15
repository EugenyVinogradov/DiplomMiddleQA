package AndroidTest.steps;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.doubleClick;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.assertEquals;
import static AndroidTest.data.Data.categoryForth;
import static AndroidTest.data.Data.dateNews;
import static AndroidTest.data.Data.descriptionNews;
import static AndroidTest.data.Data.timeNews;
import static AndroidTest.data.Data.tittleNews;
import static AndroidTest.data.DataHelper.getTextFromNews;
import static AndroidTest.pages.AddingNewsPage.addNews;
import static AndroidTest.pages.NewsEditingPage.scrollNews;
import static AndroidTest.pages.NewsPage.filterNewsByDate;

import androidx.test.espresso.ViewInteraction;

import org.junit.Assert;

import AndroidTest.data.DataHelper;
import io.qameta.allure.kotlin.Allure;
import io.qameta.allure.kotlin.Step;
import ru.iteco.fmhandroid.R;

public class NewsPageSteps {



  /*   Шаги для  страницы Новости */

  @Step("Получаем дату первой новости из списка до сортировки")
  public static String getFirstDateBeforeSortingNewsPage() {
    Allure.step("Получаем дату первой новости из списка до сортировки");
    String firstDateBeforeSorting = getTextFromNews(R.id.news_item_date_text_view, 0);
    return firstDateBeforeSorting;
  }

  @Step("Получаем дату последней новости из списка до сортировки")
  public static String getLastDateBeforeSortingNewsPage(int position) {
    Allure.step("Получаем дату последней новости из списка до сортировки");
    String lastDateBeforeSorting = getTextFromNews(R.id.news_item_date_text_view, position);
    return lastDateBeforeSorting;
  }

  @Step("Получаем дату первой новости из списка после сортировки")
  public static String getFirstDateAfterSortingNewsPage() {
    Allure.step("Получаем дату первой новости из списка после сортировки");
    String firstDateBeforeSorting = getTextFromNews(R.id.news_item_date_text_view, 0);
    return firstDateBeforeSorting;
  }

  @Step("Получаем дату последней новости из списка после сортировки")
  public static String getLastDateAfterSortingNewsPage(int position) {
    Allure.step("Получаем дату последней новости из списка после сортировки");
    String lastDateBeforeSorting = getTextFromNews(R.id.news_item_date_text_view, position);
    return lastDateBeforeSorting;
  }

  @Step("Получаем recyclerView для раздела Новости и прокручиваем до его первой позиции")
  public static ViewInteraction getRecyclerViewAndScrollToFirstPosition() {
    Allure.step("Получаем recyclerView для раздела Новости и прокручиваем до его первой позиции");
    ViewInteraction recyclerView = onView(withId(R.id.news_list_recycler_view));
    return recyclerView;
  }

  @Step("Получаем высоту первого элемента списка до клика")
  public static int getHeightBeforeClick(ViewInteraction recyclerView) {
    Allure.step("Получаем высоту первого элемента списка до клика");
    int[] heightBeforeClick = {0};
    recyclerView.perform(new DataHelper.GetHeightAfterClickViewAction(heightBeforeClick));
    return heightBeforeClick[0];
  }

  @Step("Кликаем на первом элементе списка, чтобы элемент развернулся")
  public static void clickFirstItem(ViewInteraction recyclerView) {
    Allure.step("Кликаем на первом элементе списка, чтобы элемент развернулся");
    recyclerView.perform(actionOnItemAtPosition(0, click()));
  }

  @Step("Получаем высоту первого элемента списка после клика")
  public static int getHeightAfterClick(ViewInteraction recyclerView) {
    Allure.step("Получаем высоту первого элемента списка после клика");
    int[] heightAfterClick = {0};
    recyclerView.perform(new DataHelper.GetHeightAfterClickViewAction(heightAfterClick));
    return heightAfterClick[0];
  }

  @Step("Проверяем, что высота первого элемента списка увеличилась после клика")
  public static void checkHeightAfterClick(int heightBeforeClick, int heightAfterClick) {
    Allure.step("Проверяем, что высота первого элемента списка увеличилась после клика");
    Assert.assertTrue(heightBeforeClick < heightAfterClick);
  }

  @Step("Кликаем дважды на первом элементе списка, чтобы элемент развернулся и свернулся")
  public static void doubleClickFirstItem(ViewInteraction recyclerView) {
    Allure.step("Кликаем дважды на первом элементе списка, чтобы элемент развернулся и свернулся");
    recyclerView.perform(actionOnItemAtPosition(0, doubleClick()));
  }

  @Step("Проверяем, что высота первого элемента списка осталась той же после двойного клика")
  public static void checkHeightAfterDoubleClick(int heightBeforeClick, int heightAfterClick) {
    Allure.step("Проверяем, что высота первого элемента списка осталась той же после двойного клика");
    assertEquals(heightBeforeClick, heightAfterClick);
  }

  @Step("Производим фильтрацию новостей по дате")
  public static void filterNewsByDateStep(String date) {
    Allure.step("Производим фильтрацию новостей по дате");
    filterNewsByDate(date, date);
  }

  @Step("Добавляем новость и возвращаем дату публикации")
  public static String addingNewsAndReturnPublishDate() {
    Allure.step("Добавляем новость и возвращаем дату публикации");
    addNews(categoryForth, tittleNews, dateNews, timeNews, descriptionNews);
    return dateNews;
  }

  @Step("Проверяем, что все новости имеют заданную при фильтрации дату публикации")
  public static void checkPublishDateNews(int itemCount, String expectedDate) {
    Allure.step("Проверяем, что все новости имеют заданную при фильтрации дату публикации");
    for (int i = 0; i < itemCount; i++) {
      scrollNews(i);
      String actualDate = getTextFromNews(R.id.news_item_date_text_view, i);
      assertEquals(expectedDate, actualDate);
    }
  }
}
