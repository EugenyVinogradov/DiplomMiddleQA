package AndroidTest.steps;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
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
import static AndroidTest.pages.EditingNews.changeNewsAttribute;
import static AndroidTest.pages.NewClaimPage.errorAddingMessageId;
import static AndroidTest.pages.NewsEditingPage.changeNewsStatus;
import static AndroidTest.pages.NewsEditingPage.deleteNews;
import static AndroidTest.pages.NewsEditingPage.editNews;
import static AndroidTest.pages.NewsEditingPage.refreshListOfNews;
import static AndroidTest.pages.NewsEditingPage.scrollAndClickToNewsWithTittle;
import static AndroidTest.pages.NewsEditingPage.scrollNews;
import static AndroidTest.pages.NewsPage.filterNewsByStatus;
import static AndroidTest.pages.NewsPage.filterNewsByStatusAndDate;
import static AndroidTest.pages.NewsPage.scrollNewsToPosition;
import static AndroidTest.pages.NewsPage.sortingNews;

import AndroidTest.data.DataHelper;
import io.qameta.allure.kotlin.Allure;
import io.qameta.allure.kotlin.Step;
import ru.iteco.fmhandroid.R;

public class NewsEditingPageSteps {

  /*   Шаги для  страницы Редактирование новостей */

  @Step("Получаем количество элементов в списке новостей")
  public static int getItemCount() {
    Allure.step("Получаем количество элементов в списке новостей");
    int itemCount = getRecyclerViewItemCount(R.id.news_list_recycler_view);
    return itemCount;
  }

  @Step("Получаем дату первой новости из списка до сортировки")
  public static String getFirstDateBeforeSorting() {
    Allure.step("Получаем дату первой новости из списка до сортировки");
    String firstDateBeforeSorting = getTextFromNews(R.id.news_item_publication_date_text_view, 0);
    return firstDateBeforeSorting;
  }

  @Step("Получаем дату последней новости из списка до сортировки")
  public static String getLastDateBeforeSorting(int position) {
    Allure.step("Получаем дату последней новости из списка до сортировки");
    String lastDateBeforeSorting = getTextFromNews(R.id.news_item_publication_date_text_view, position);
    return lastDateBeforeSorting;
  }

  @Step("Прокручиваем список новостей до последнего элемента")
  public static void scrollNewsToLastPosition(int itemCount) {
    Allure.step("Прокручиваем список новостей до последнего элемента");
    scrollNewsToPosition(itemCount);
  }

  @Step("Производим сортировку новостей ")
  public static void sortingNewsStep() {
    Allure.step("Производим сортировку новостей");
    sortingNews();
  }

  @Step("Прокручиваем список новостей до первого элемента")
  public static void scrollNewsToFirstPosition() {
    Allure.step("Прокручиваем список новостей до первого элемента");
    scrollNewsToPosition(0);
  }

  @Step("Получаем дату первой новости из списка после сортировки")
  public static String getFirstDateAfterSorting() {
    Allure.step("Получаем дату первой новости из списка после сортировки");
    String firstDateBeforeSorting = getTextFromNews(R.id.news_item_publication_date_text_view, 0);
    return firstDateBeforeSorting;
  }

  @Step("Получаем дату последней новости из списка после сортировки")
  public static String getLastDateAfterSorting(int position) {
    Allure.step("Получаем дату последней новости из списка после сортировки");
    String lastDateBeforeSorting = getTextFromNews(R.id.news_item_publication_date_text_view, position);
    return lastDateBeforeSorting;
  }

  @Step("Проверяем, что дата первой новости до сортировки равна дате последней новости после сортировки")
  public static void checkDateAfterSortingOne(String firstDateBeforeSorting, String lastDateAfterSorting) {
    Allure.step("Проверяем, что дата первой новости до сортировки равна дате последней новости после сортировки");
    assertEquals(firstDateBeforeSorting, lastDateAfterSorting);
  }

  @Step("Проверяем, что дата последней новости до сортировки равна дате первой новости после сортировки")
  public static void checkDateAfterSortingTwo(String lastDateBeforeSorting, String firstDateAfterSorting) {
    Allure.step("Проверяем, что дата последней новости до сортировки равна дате первой новости после сортировки");
    assertEquals(lastDateBeforeSorting, firstDateAfterSorting);
  }

  @Step("Добавляем новость")
  public static void addingNews() {
    Allure.step("Добавляем новость");
    addNews(categoryForth, tittleNews, dateNews, timeNews, descriptionNews);
    waitElement(R.id.news_list_recycler_view);
    refreshListOfNews();
  }

  @Step("Прокручиваем список до созданной новости и кликаем на нее")
  public static void scrollToNewsWithTittleAndClick() {
    Allure.step("Прокручиваем список до созданной новости и кликаем на нее");
    scrollAndClickToNewsWithTittle(tittleNews);
  }

  @Step("Открываем новость на редактирование")
  public static void editingNews() {
    Allure.step("Открываем новость на редактирование");
    editNews(tittleNews);
  }

  @Step("Проверяем, что все атрибуты новости соответствуют заданным при ее создании")
  public static void checkAttributesNews() {
    Allure.step("Проверяем, что все атрибуты новости соответствуют заданным при ее создании");
    onView(withText(tittleNews)).check(matches(isDisplayed()));
    onView(withText(dateNews)).check(matches(isDisplayed()));
    onView(withText(timeNews)).check(matches(isDisplayed()));
    onView(withText(descriptionNews)).check(matches(isDisplayed()));
  }

  @Step("Производим фильтрацию новостей по статусу Активна")
  public static void filterNewsByStatusActive() {
    Allure.step("Производим фильтрацию новостей по статусу Активна");
    filterNewsByStatus(true, false);
  }

  @Step("Проверяем, что во всех элементах списка новостей статус соответствует Активна")
  public static void isStatusActive(int itemCount) {
    Allure.step("Проверяем, что во всех элементах списка новостей статус соответствует Активна");
    for (int i = 0; i < itemCount; i++) {
      scrollNews(i);
      String actualStatus = getTextFromNews(R.id.news_item_published_text_view, i);
      assertEquals(statusActive, actualStatus);
    }
  }

  @Step("Производим фильтрацию новостей по статусу Неактивна")
  public static void filterNewsByStatusNotActive() {
    Allure.step("Производим фильтрацию новостей по статусу Неактивна");
    filterNewsByStatus(false, true);
  }

  @Step("Проверяем, что во всех элементах списка новостей статус соответствует Неактивна")
  public static void isStatusNotActive(int itemCount) {
    Allure.step("Проверяем, что во всех элементах списка новостей статус соответствует Неактивна");
    for (int i = 0; i < itemCount; i++) {
      scrollNews(i);
      String actualStatus = getTextFromNews(R.id.news_item_published_text_view, i);
      assertEquals(statusNotActive, actualStatus);
    }
  }

  @Step("Производим фильтрацию новостей по статусу Активна и дате публикации")
  public static void filterNewsByStatusActiveAndPublishDate() {
    Allure.step("Производим фильтрацию новостей по статусу Активна и дате публикации");
    filterNewsByStatusAndDate(true, false, dateNews, dateNews);
  }

  @Step("Проверяем, что во всех элементах списка новостей статус соответствует Активна и дата публикации равна дате фильтрации")
  public static void isStatusActiveAndPublishDateEqualsFilterDate(int itemCount) {
    Allure.step("Проверяем, что во всех элементах списка новостей статус соответствует Активна и дата публикации равна дате фильтрации");
    for (int i = 0; i < itemCount; i++) {
      scrollNews(i);
      String actualStatus = getTextFromNews(R.id.news_item_published_text_view, i);
      String actualDate = getTextFromNews(R.id.news_item_publication_date_text_view, i);
      assertEquals(statusActive, actualStatus);
      assertEquals(dateNews, actualDate);
    }
  }

  @Step("Производим смену статуса новости на Неактивна")
  public static void changeStatusNewsToNotActive() {
    Allure.step("Производим смену статуса новости на Неактивна");
    changeNewsStatus(tittleNews);
  }

  @Step("Проверяем, что статус новости соответствует Неактивна")
  public static void checkNotActiveStatus() {
    Allure.step("Проверяем, что статус новости соответствует Неактивна");
    onView(withText(statusNotActive)).check(matches(isDisplayed()));
  }

  @Step("Производим фильтрацию новостей по статусу Неактивна и дате публикации")
  public static void filterNewsByStatusNotActiveAndPublishDate() {
    Allure.step("Производим фильтрацию новостей по статусу Неактивна и дате публикации");
    filterNewsByStatusAndDate(false, true, dateNews, dateNews);
  }

  @Step("Проверяем, что во всех элементах списка новостей статус соответствует Неактивна и дата публикации равна дате фильтрации")
  public static void isStatusNotActiveAndPublishDateEqualsFilterDate(int itemCount) {
    Allure.step("Проверяем, что во всех элементах списка новостей статус соответствует Неактивна и дата публикации равна дате фильтрации");
    for (int i = 0; i < itemCount; i++) {
      scrollNews(i);
      String actualStatus = getTextFromNews(R.id.news_item_published_text_view, i);
      String actualDate = getTextFromNews(R.id.news_item_publication_date_text_view, i);
      assertEquals(statusNotActive, actualStatus);
      assertEquals(dateNews, actualDate);
    }
  }

  @Step("Пытаемся создать новость с незаполненным полем Категория")
  public static void addNewsWithEmptyFieldCategory() {
    Allure.step("Пытаемся создать новость с незаполненным полем Категория");
    addNews("", tittleNews, dateNews, timeNews, descriptionNews);
  }

  @Step("Пытаемся создать новость с незаполненным полем Заголовок")
  public static void addNewsWithEmptyFieldTittle() {
    Allure.step("Пытаемся создать новость с незаполненным полем Заголовок");
    addNews(categoryForth, "", dateNews, timeNews, descriptionNews);
  }

  @Step("Пытаемся создать новость с незаполненным полем Дата")
  public static void addNewsWithEmptyFieldDate() {
    Allure.step("Пытаемся создать новость с незаполненным полем Дата");
    addNews(categoryForth, tittleNews, "", timeNews, descriptionNews);
  }

  @Step("Пытаемся создать новость с незаполненным полем Время")
  public static void addNewsWithEmptyFieldTime() {
    Allure.step("Пытаемся создать новость с незаполненным полем Время");
    addNews(categoryForth, tittleNews, dateNews, "", descriptionNews);
  }

  @Step("Пытаемся создать новость с незаполненным полем Описание")
  public static void addNewsWithEmptyFieldDescription() {
    Allure.step("Пытаемся создать новость с незаполненным полем Описание");
    addNews(categoryForth, tittleNews, dateNews, timeNews, "");
  }

  @Step("Проверяем сообщение о недопустимости наличия пустых полей при создании новости")
  public static void neverFieldsDoesntBeEmptyMessage() {
    Allure.step("Проверяем сообщение о недопустимости наличия пустых полей при создании новости");
    waitUntilVisible(DataHelper.checkToast(errorAddingMessageId, true));
  }

  @Step("Заполняем все поля создаваемой новости")
  public static void fillingAllFieldsNews() {
    Allure.step("Заполняем все поля создаваемой новости");
    fillingNewsFields(categoryForth, tittleNews, dateNews, timeNews, descriptionNews);
  }

  @Step("Нажимаем Отмена")
  public static void pressCancelButton() {
    Allure.step("Нажимаем Отмена");
    cancelButton.perform(click());
  }

  @Step("Подтверждаем отмену создания новости")
  public static void confirmCancelAddingNews() {
    Allure.step("Подтверждаем отмену создания новости");
    cancelMessage.check(matches(isDisplayed()));
    confirmCancelAddingNewsButton.perform(click());
  }

  @Step("Проверяем, что новость не создана")
  public static void isNewsNotCreated(int itemCount) {
    Allure.step("Проверяем, что новость не создана");
    for (int i = 0; i < itemCount; i++) {
      scrollNews(i);
      String actualTittle = getTextFromNews(R.id.news_item_title_text_view, i);
      assertNotEquals(tittleNews, actualTittle);
    }
  }

  @Step("Удаляем созданную новость")
  public static void deleteAddedNews() {
    Allure.step("Удаляем созданную новость");
    deleteNews(tittleNews);
    waitElement(R.id.news_list_recycler_view);
  }

  @Step("Проверяем, что новость удалена")
  public static void isNewsDeleted(int itemCount) {
    Allure.step("Проверяем, что новость удалена");
    for (int i = 0; i < itemCount; i++) {
      scrollNews(i);
      String actualTittle = getTextFromNews(R.id.news_item_title_text_view, i);
      assertNotEquals(tittleNews, actualTittle);
    }
  }

  @Step("Изменяем атрибуты созданной новости")
  public static void changeCreatedNewsAttributes() {
    Allure.step("Изменяем атрибуты созданной новости");
    changeNewsAttribute(newTittleNews, dateNewsNextDay, newTimeNews, newDescriptionNews);
  }

  @Step("Проверяем, что атрибуты созданной новости изменились")
  public static void checkChangedNewsAttributes() {
    Allure.step("Проверяем, что атрибуты созданной новости изменились");
    onView(withText(newTittleNews)).check(matches(isDisplayed()));
    onView(withText(dateNewsNextDay)).check(matches(isDisplayed()));
    onView(withText(newTimeNews)).check(matches(isDisplayed()));
    onView(withText(newDescriptionNews)).check(matches(isDisplayed()));
  }

}
