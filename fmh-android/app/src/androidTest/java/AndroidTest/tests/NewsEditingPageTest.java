package AndroidTest.tests;


import static java.lang.Thread.sleep;
import static AndroidTest.Steps.AllureSteps.goToNewsEditingPageStep;
import static AndroidTest.Steps.AllureSteps.goToNewsPageStep;
import static AndroidTest.Steps.AllureSteps.logOutFromApp;
import static AndroidTest.Steps.AllureSteps.successLoginStep;
import static AndroidTest.data.DataHelper.getUniqueScreenshotName;
import static AndroidTest.data.DataHelper.waitElement;

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
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.AppActivity;

@Epic("Тестирование страницы редактирования новостей")

@RunWith(AllureAndroidJUnit4.class)
public class NewsEditingPageTest {

  @Before
  public void login() {
    successLoginStep();
    goToNewsPageStep();
    goToNewsEditingPageStep();
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
  @DisplayName("Сортировка новостей в разделе редактирования новостей")
  @Attachment
  public void testSortingNewsInEditingNews() {
    int itemCount = AllureSteps.getItemCount();
    String firstDateBeforeSorting = AllureSteps.getFirstDateBeforeSorting();
    AllureSteps.scrollNewsToLastPosition(itemCount - 1);
    String lastDateBeforeSorting = AllureSteps.getLastDateBeforeSorting(itemCount - 1);
    AllureSteps.sortingNewsStep();
    AllureSteps.scrollNewsToFirstPosition();
    String firstDateAfterSorting = AllureSteps.getFirstDateAfterSorting();
    AllureSteps.scrollNewsToLastPosition(itemCount - 1);
    String lastDateAfterSorting = AllureSteps.getLastDateAfterSorting(itemCount - 1);
    AllureSteps.checkDateAfterSortingOne(firstDateBeforeSorting, lastDateAfterSorting);
    AllureSteps.checkDateAfterSortingTwo(lastDateBeforeSorting, firstDateAfterSorting);
  }

  @Test
  @DisplayName("Добавление новости")
  @Attachment
  public void testAddingNews() throws InterruptedException {
    AllureSteps.addingNews();
    AllureSteps.scrollToNewsWithTittleAndClick();
    AllureSteps.editingNews();
    AllureSteps.checkAttributesNews();
    AllureSteps.pressBack();
  }

  @Test
  @DisplayName("Фильтрация новостей по статусу Активна")
  @Attachment
  public void testFilterNewsByStatusActive() {
    AllureSteps.filterNewsByStatusActive();
    int itemCount = AllureSteps.getItemCount();
    AllureSteps.isStatusActive(itemCount);
  }

  @Test
  @DisplayName("Фильтрация новостей по статусу Неактивна")
  @Attachment
  public void testFilterNewsByStatusNotActive() {
    AllureSteps.filterNewsByStatusNotActive();
    int itemCount = AllureSteps.getItemCount();
    AllureSteps.isStatusNotActive(itemCount);
  }

  @Test
  @DisplayName("Фильтрация новостей по статусу Активна и дате публикации")
  @Attachment
  public void testFilterNewsByStatusActiveAndDatePublish() throws InterruptedException {
    AllureSteps.addingNews();
    AllureSteps.filterNewsByStatusActiveAndPublishDate();
    int itemCount = AllureSteps.getItemCount();
    AllureSteps.isStatusActiveAndPublishDateEqualsFilterDate(itemCount);
  }

  @Test
  @DisplayName("Смена статуса новости")
  @Attachment
  public void testChangeNewsStatus() throws InterruptedException {
    AllureSteps.addingNews();
    AllureSteps.changeStatusNewsToNotActive();
    AllureSteps.editingNews();
    AllureSteps.checkNotActiveStatus();
  }

  @Test
  @DisplayName("Фильтрация новостей по статусу Неактивна и дате публикации")
  @Attachment
  public void testFilterNewsByStatusNotActiveAndDatePublish() throws InterruptedException {
    AllureSteps.addingNews();
    AllureSteps.changeStatusNewsToNotActive();
    AllureSteps.filterNewsByStatusNotActiveAndPublishDate();
    int itemCount = AllureSteps.getItemCount();
    AllureSteps.isStatusNotActiveAndPublishDateEqualsFilterDate(itemCount);
  }

  @Test
  @DisplayName("Отказ в добавление новости при незаполненном поле Категория")
  @Attachment
  public void testRefusalAddingNewsWithEmptyFieldCategory() throws InterruptedException {
    AllureSteps.addNewsWithEmptyFieldCategory();
    AllureSteps.neverFieldsDoesntBeEmptyMessage();
    AllureSteps.pressBack();
  }

  @Test
  @DisplayName("Отказ в добавление новости при незаполненном поле Заголовок")
  @Attachment
  public void testRefusalAddingNewsWithEmptyFieldTittle() throws InterruptedException {
    AllureSteps.addNewsWithEmptyFieldTittle();
    AllureSteps.neverFieldsDoesntBeEmptyMessage();
    AllureSteps.pressBack();
  }

  @Test
  @DisplayName("Отказ в добавление новости при незаполненном поле Дата")
  @Attachment
  public void testRefusalAddingNewsWithEmptyFieldDate() throws InterruptedException {
    AllureSteps.addNewsWithEmptyFieldDate();
    AllureSteps.neverFieldsDoesntBeEmptyMessage();
    AllureSteps.pressBack();
  }

  @Test
  @DisplayName("Отказ в добавление новости при незаполненном поле Время")
  @Attachment
  public void testRefusalAddingNewsWithEmptyFieldTime() throws InterruptedException {
    AllureSteps.addNewsWithEmptyFieldTime();
    AllureSteps.neverFieldsDoesntBeEmptyMessage();
    AllureSteps.pressBack();
  }

  @Test
  @DisplayName("Отказ в добавление новости при незаполненном поле Описание")
  @Attachment
  public void testRefusalAddingNewsWithEmptyFieldDescription() throws InterruptedException {
    AllureSteps.addNewsWithEmptyFieldDescription();
    AllureSteps.neverFieldsDoesntBeEmptyMessage();
    AllureSteps.pressBack();
  }

  @Test
  @DisplayName("Отмена добавление новости при нажатии кнопки Отмена")
  @Attachment
  public void testCancelAddingNewsWithPressCancel() {
    AllureSteps.fillingAllFieldsNews();
    AllureSteps.pressCancelButton();
    AllureSteps.confirmCancelAddingNews();
    AllureSteps.pressBack();
    int itemCount = AllureSteps.getItemCount();
    AllureSteps.isNewsNotCreated(itemCount);
  }

  @Test
  @DisplayName("Отмена добавление новости при нажатии кнопки Назад")
  @Attachment
  public void testCancelAddingNewsWithPressBack() {
    AllureSteps.fillingAllFieldsNews();
    AllureSteps.pressBack();
    int itemCount = AllureSteps.getItemCount();
    AllureSteps.isNewsNotCreated(itemCount);
  }

  @Test
  @DisplayName("Удаление новости")
  @Attachment
  public void testDeleteNews() throws InterruptedException {
    AllureSteps.addingNews();
    sleep(1000);
    AllureSteps.deleteAddedNews();
    waitElement(R.id.news_list_recycler_view);
    sleep(1000);
    int itemCount = AllureSteps.getItemCount();
    AllureSteps.isNewsDeleted(itemCount);
  }

  @Test
  @DisplayName("Редактирование атрибутов новости")
  @Attachment
  public void testChangeNewsAttribute() throws InterruptedException {
    AllureSteps.addingNews();
    AllureSteps.editingNews();
    AllureSteps.changeCreatedNewsAttributes();
    AllureSteps.editingNews();
    AllureSteps.checkChangedNewsAttributes();
    AllureSteps.pressBack();
  }
}
