package AndroidTest.tests;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static java.lang.Thread.sleep;
import static AndroidTest.data.Data.categoryForth;
import static AndroidTest.data.Data.dateNews;
import static AndroidTest.data.Data.dateNewsNextDay;
import static AndroidTest.data.Data.descriptionNews;
import static AndroidTest.data.Data.newDescriptionNews;
import static AndroidTest.data.Data.newTimeNews;
import static AndroidTest.data.Data.newTittleNews;
import static AndroidTest.data.Data.statusActive;
import static AndroidTest.data.Data.statusNotActive;
import static AndroidTest.data.Data.timeNews;
import static AndroidTest.data.Data.tittleNews;
import static AndroidTest.data.DataHelper.getRecyclerViewItemCount;
import static AndroidTest.data.DataHelper.getTextFromNews;
import static AndroidTest.data.DataHelper.waitElement;
import static AndroidTest.data.DataHelper.waitUntilVisible;
import static AndroidTest.pages.AddingNewsPage.addNews;
import static AndroidTest.pages.AddingNewsPage.cancelButton;
import static AndroidTest.pages.AddingNewsPage.cancelMessage;
import static AndroidTest.pages.AddingNewsPage.confirmCancelAddingNewsButton;
import static AndroidTest.pages.AddingNewsPage.fillingNewsFields;
import static AndroidTest.pages.AuthPage.successLogin;
import static AndroidTest.pages.EditingNews.changeNewsAttribute;
import static AndroidTest.pages.MainPage.goToNewsPage;
import static AndroidTest.pages.MainPage.logOut;
import static AndroidTest.pages.NewClaimPage.errorAddingMessageId;
import static AndroidTest.pages.NewsEditingPage.changeNewsStatus;
import static AndroidTest.pages.NewsEditingPage.deleteNews;
import static AndroidTest.pages.NewsEditingPage.editNews;
import static AndroidTest.pages.NewsEditingPage.scrollAndClickToNewsWithTittle;
import static AndroidTest.pages.NewsEditingPage.scrollNews;
import static AndroidTest.pages.NewsPage.filterNewsByStatus;
import static AndroidTest.pages.NewsPage.filterNewsByStatusAndDate;
import static AndroidTest.pages.NewsPage.getNewsCount;
import static AndroidTest.pages.NewsPage.goToNewsEditingPage;
import static AndroidTest.pages.NewsPage.scrollNewsToPosition;
import static AndroidTest.pages.NewsPage.sortingNews;

import androidx.test.espresso.Espresso;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.After;
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
public class NewsEditingPageTest {

  @Before
  public void login() {
    successLogin();
    goToNewsPage();
    goToNewsEditingPage();
  }

  @After
  public void logOutApp() {
    logOut();
  }

  @Rule
  public ActivityScenarioRule<AppActivity> myActivityScenarioRule =
      new ActivityScenarioRule<>(AppActivity.class);

  @Test
  @DisplayName("Сортировка новостей в разделе редактирования новостей")
  public void testSortingNewsInEditingNews() {
    int itemCount = getRecyclerViewItemCount(R.id.news_list_recycler_view);
    String firstDateBeforeSorting = getTextFromNews(R.id.news_item_publication_date_text_view, 0);
    scrollNewsToPosition(itemCount - 1);
    String lastDateBeforeSorting = getTextFromNews(R.id.news_item_publication_date_text_view, itemCount - 1);
    sortingNews();
    scrollNewsToPosition(0);
    String firstDateAfterSorting = getTextFromNews(R.id.news_item_publication_date_text_view, 0);
    scrollNewsToPosition(itemCount - 1);
    String lastDateAfterSorting = getTextFromNews(R.id.news_item_publication_date_text_view, itemCount - 1);
    assertEquals(firstDateBeforeSorting, lastDateAfterSorting);
    assertEquals(lastDateBeforeSorting, firstDateAfterSorting);
  }

  @Test
  @DisplayName("Добавление новости")
  public void testAddingNews() throws InterruptedException {
    addNews(categoryForth, tittleNews, dateNews, timeNews, descriptionNews);
    scrollAndClickToNewsWithTittle(tittleNews);
    editNews(tittleNews);
    onView(withText(tittleNews)).check(matches(isDisplayed()));
    onView(withText(dateNews)).check(matches(isDisplayed()));
    onView(withText(timeNews)).check(matches(isDisplayed()));
    onView(withText(descriptionNews)).check(matches(isDisplayed()));
    Espresso.pressBack();
  }

  @Test
  @DisplayName("Фильтрация новостей по статусу Активна")
  public void testFilterNewsByStatusActive() {
    filterNewsByStatus(true, false);
    int itemCount = getNewsCount(myActivityScenarioRule);
    for (int i = 0; i < itemCount; i++) {
      scrollNews(i);
      String actualStatus = getTextFromNews(R.id.news_item_published_text_view, i);
      assertEquals(statusActive, actualStatus);
    }
  }

  @Test
  @DisplayName("Фильтрация новостей по статусу Неактивна")
  public void testFilterNewsByStatusNotActive() {
    filterNewsByStatus(false, true);
    int itemCount = getNewsCount(myActivityScenarioRule);
    for (int i = 0; i < itemCount; i++) {
      scrollNews(i);
      String actualStatus = getTextFromNews(R.id.news_item_published_text_view, i);
      assertEquals(statusNotActive, actualStatus);
    }
  }

  @Test
  @DisplayName("Фильтрация новостей по статусу Активна и дате публикации")
  public void testFilterNewsByStatusActiveAndDatePublish() throws InterruptedException {
    addNews(categoryForth, tittleNews, dateNews, timeNews, descriptionNews);
    filterNewsByStatusAndDate(true, false, dateNews, dateNews);
    int itemCount = getNewsCount(myActivityScenarioRule);
    for (int i = 0; i < itemCount; i++) {
      scrollNews(i);
      String actualStatus = getTextFromNews(R.id.news_item_published_text_view, i);
      String actualDate = getTextFromNews(R.id.news_item_publication_date_text_view, i);
      assertEquals(statusActive, actualStatus);
      assertEquals(dateNews, actualDate);
    }
  }

  @Test
  @DisplayName("Смена статуса новости")
  public void testChangeNewsStatus() throws InterruptedException {
    addNews(categoryForth, tittleNews, dateNews, timeNews, descriptionNews);
    changeNewsStatus(tittleNews);
    editNews(tittleNews);
    onView(withText(statusNotActive)).check(matches(isDisplayed()));
  }

  @Test
  @DisplayName("Фильтрация новостей по статусу Неактивна и дате публикации")
  public void testFilterNewsByStatusNotActiveAndDatePublish() throws InterruptedException {
    addNews(categoryForth, tittleNews, dateNews, timeNews, descriptionNews);
    changeNewsStatus(tittleNews);
    filterNewsByStatusAndDate(false, true, dateNews, dateNews);
    int itemCount = getNewsCount(myActivityScenarioRule);
    for (int i = 0; i < itemCount; i++) {
      scrollNews(i);
      String actualStatus = getTextFromNews(R.id.news_item_published_text_view, i);
      String actualDate = getTextFromNews(R.id.news_item_publication_date_text_view, i);
      assertEquals(statusNotActive, actualStatus);
      assertEquals(dateNews, actualDate);
    }
  }

  @Test
  @DisplayName("Отказ в добавление новости при незаполненном поле Категория")
  public void testRefusalAddingNewsWithEmptyFieldCategory() throws InterruptedException {
    addNews("", tittleNews, dateNews, timeNews, descriptionNews);
    waitUntilVisible(DataHelper.checkToast(errorAddingMessageId, true));
    Espresso.pressBack();
  }

  @Test
  @DisplayName("Отказ в добавление новости при незаполненном поле Заголовок")
  public void testRefusalAddingNewsWithEmptyFieldTittle() throws InterruptedException {
    addNews(categoryForth, "", dateNews, timeNews, descriptionNews);
    waitUntilVisible(DataHelper.checkToast(errorAddingMessageId, true));
    Espresso.pressBack();
  }

  @Test
  @DisplayName("Отказ в добавление новости при незаполненном поле Дата")
  public void testRefusalAddingNewsWithEmptyFieldDate() throws InterruptedException {
    addNews(categoryForth, tittleNews, "", timeNews, descriptionNews);
    waitUntilVisible(DataHelper.checkToast(errorAddingMessageId, true));
    Espresso.pressBack();
  }

  @Test
  @DisplayName("Отказ в добавление новости при незаполненном поле Время")
  public void testRefusalAddingNewsWithEmptyFieldTime() throws InterruptedException {
    addNews(categoryForth, tittleNews, dateNews, "", descriptionNews);
    waitUntilVisible(DataHelper.checkToast(errorAddingMessageId, true));
    Espresso.pressBack();
  }

  @Test
  @DisplayName("Отказ в добавление новости при незаполненном поле Описание")
  public void testRefusalAddingNewsWithEmptyFieldDescription() throws InterruptedException {
    addNews(categoryForth, tittleNews, dateNews, timeNews, "");
    waitUntilVisible(DataHelper.checkToast(errorAddingMessageId, true));
    Espresso.pressBack();
  }

  @Test
  @DisplayName("Отмена добавление новости при нажатии кнопки Отмена")
  public void testCancelAddingNewsWithPressCancel() {
    fillingNewsFields(categoryForth, tittleNews, dateNews, timeNews, descriptionNews);
    cancelButton.perform(click());
    cancelMessage.check(matches(isDisplayed()));
    confirmCancelAddingNewsButton.perform(click());
    Espresso.pressBack();
  }

  @Test
  @DisplayName("Отмена добавление новости при нажатии кнопки Назад")
  public void testCancelAddingNewsWithPressBack() {
    fillingNewsFields(categoryForth, tittleNews, dateNews, timeNews, descriptionNews);
    Espresso.pressBack();
    int itemCount = getNewsCount(myActivityScenarioRule);
    for (int i = 0; i < itemCount; i++) {
      scrollNews(i);
      String actualTittle = getTextFromNews(R.id.news_item_title_text_view, i);
      assertNotEquals(tittleNews, actualTittle);
    }
  }

  @Test
  @DisplayName("Удаление новости")
  public void testDeleteNews() throws InterruptedException {
    addNews(categoryForth, tittleNews, dateNews, timeNews, descriptionNews);
    deleteNews(tittleNews);
    waitElement(R.id.news_list_recycler_view);
    sleep(1000);
    int itemCount = getNewsCount(myActivityScenarioRule);
    for (int i = 0; i < itemCount; i++) {
      scrollNews(i);
      String actualTittle = getTextFromNews(R.id.news_item_title_text_view, i);
      assertNotEquals(tittleNews, actualTittle);
    }
  }

  @Test
  @DisplayName("Редактирование атрибутов новости")
  public void testChangeNewsAttribute() throws InterruptedException {
    addNews(categoryForth, tittleNews, dateNews, timeNews, descriptionNews);
    editNews(tittleNews);
    changeNewsAttribute(newTittleNews, dateNewsNextDay, newTimeNews, newDescriptionNews);
    editNews(newTittleNews);
    onView(withText(newTittleNews)).check(matches(isDisplayed()));
    onView(withText(dateNewsNextDay)).check(matches(isDisplayed()));
    onView(withText(newTimeNews)).check(matches(isDisplayed()));
    onView(withText(newDescriptionNews)).check(matches(isDisplayed()));
    Espresso.pressBack();
  }
}
