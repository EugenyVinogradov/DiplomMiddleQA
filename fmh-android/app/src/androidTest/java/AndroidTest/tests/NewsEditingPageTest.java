package AndroidTest.tests;

import static AndroidTest.data.DataHelper.getUniqueScreenshotName;
import static AndroidTest.pages.AuthPage.checkLogInAndLogInIfNot;
import static AndroidTest.steps.BaseSteps.goToNewsEditingPageStep;
import static AndroidTest.steps.BaseSteps.goToNewsPageStep;

import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import AndroidTest.steps.BaseSteps;
import AndroidTest.steps.NewsEditingPageSteps;
import io.qameta.allure.android.rules.ScreenshotRule;
import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.Epic;
import io.qameta.allure.kotlin.junit4.DisplayName;
import ru.iteco.fmhandroid.ui.AppActivity;

@Epic("Тестирование страницы редактирования новостей")

@RunWith(AllureAndroidJUnit4.class)
public class NewsEditingPageTest {

  @Before
  public void setUp() {
    checkLogInAndLogInIfNot();
    goToNewsPageStep();
    goToNewsEditingPageStep();
  }


  @Rule
  public ActivityScenarioRule<AppActivity> myActivityScenarioRule =
      new ActivityScenarioRule<>(AppActivity.class);

  @Rule
  public ScreenshotRule screenshotRule =
      new ScreenshotRule(ScreenshotRule.Mode.FAILURE, getUniqueScreenshotName());

  @Test
  @DisplayName("Сортировка новостей в разделе редактирования новостей")
  public void testSortingNewsInEditingNews() {
    int itemCount = NewsEditingPageSteps.getItemCount();
    String firstDateBeforeSorting = NewsEditingPageSteps.getFirstDateBeforeSorting();
    NewsEditingPageSteps.scrollNewsToLastPosition(itemCount - 1);
    String lastDateBeforeSorting = NewsEditingPageSteps.getLastDateBeforeSorting(itemCount - 1);
    NewsEditingPageSteps.sortingNewsStep();
    NewsEditingPageSteps.scrollNewsToFirstPosition();
    String firstDateAfterSorting = NewsEditingPageSteps.getFirstDateAfterSorting();
    NewsEditingPageSteps.scrollNewsToLastPosition(itemCount - 1);
    String lastDateAfterSorting = NewsEditingPageSteps.getLastDateAfterSorting(itemCount - 1);
    NewsEditingPageSteps.checkDateAfterSortingOne(firstDateBeforeSorting, lastDateAfterSorting);
    NewsEditingPageSteps.checkDateAfterSortingTwo(lastDateBeforeSorting, firstDateAfterSorting);
  }

  @Test
  @DisplayName("Добавление новости")
  public void testAddingNews() {
    NewsEditingPageSteps.addingNews();
    NewsEditingPageSteps.scrollToNewsWithTittleAndClick();
    NewsEditingPageSteps.editingNews();
    NewsEditingPageSteps.checkAttributesNews();
  }

  @Test
  @DisplayName("Фильтрация новостей по статусу Активна")
  public void testFilterNewsByStatusActive() {
    NewsEditingPageSteps.filterNewsByStatusActive();
    int itemCount = NewsEditingPageSteps.getItemCount();
    NewsEditingPageSteps.isStatusActive(itemCount);
  }

  @Test
  @DisplayName("Фильтрация новостей по статусу Неактивна")
  public void testFilterNewsByStatusNotActive() {
    NewsEditingPageSteps.filterNewsByStatusNotActive();
    int itemCount = NewsEditingPageSteps.getItemCount();
    NewsEditingPageSteps.isStatusNotActive(itemCount);
  }

  @Test
  @DisplayName("Фильтрация новостей по статусу Активна и дате публикации")
  public void testFilterNewsByStatusActiveAndDatePublish() {
    NewsEditingPageSteps.addingNews();
    NewsEditingPageSteps.filterNewsByStatusActiveAndPublishDate();
    int itemCount = NewsEditingPageSteps.getItemCount();
    NewsEditingPageSteps.isStatusActiveAndPublishDateEqualsFilterDate(itemCount);
  }

  @Test
  @DisplayName("Смена статуса новости")
  public void testChangeNewsStatus() {
    NewsEditingPageSteps.addingNews();
    NewsEditingPageSteps.changeStatusNewsToNotActive();
    NewsEditingPageSteps.editingNews();
    NewsEditingPageSteps.checkNotActiveStatus();
  }

  @Test
  @DisplayName("Фильтрация новостей по статусу Неактивна и дате публикации")
  public void testFilterNewsByStatusNotActiveAndDatePublish() {
    NewsEditingPageSteps.addingNews();
    NewsEditingPageSteps.changeStatusNewsToNotActive();
    NewsEditingPageSteps.filterNewsByStatusNotActiveAndPublishDate();
    int itemCount = NewsEditingPageSteps.getItemCount();
    NewsEditingPageSteps.isStatusNotActiveAndPublishDateEqualsFilterDate(itemCount);
  }

  @Test
  @DisplayName("Отказ в добавление новости при незаполненном поле Категория")
  public void testRefusalAddingNewsWithEmptyFieldCategory() {
    NewsEditingPageSteps.addNewsWithEmptyFieldCategory();
    NewsEditingPageSteps.neverFieldsDoesntBeEmptyMessage();
  }

  @Test
  @DisplayName("Отказ в добавление новости при незаполненном поле Заголовок")
  public void testRefusalAddingNewsWithEmptyFieldTittle() {
    NewsEditingPageSteps.addNewsWithEmptyFieldTittle();
    NewsEditingPageSteps.neverFieldsDoesntBeEmptyMessage();
  }

  @Test
  @DisplayName("Отказ в добавление новости при незаполненном поле Дата")
  public void testRefusalAddingNewsWithEmptyFieldDate() {
    NewsEditingPageSteps.addNewsWithEmptyFieldDate();
    NewsEditingPageSteps.neverFieldsDoesntBeEmptyMessage();
  }

  @Test
  @DisplayName("Отказ в добавление новости при незаполненном поле Время")
  public void testRefusalAddingNewsWithEmptyFieldTime() {
    NewsEditingPageSteps.addNewsWithEmptyFieldTime();
    NewsEditingPageSteps.neverFieldsDoesntBeEmptyMessage();
  }

  @Test
  @DisplayName("Отказ в добавление новости при незаполненном поле Описание")
  public void testRefusalAddingNewsWithEmptyFieldDescription() {
    NewsEditingPageSteps.addNewsWithEmptyFieldDescription();
    NewsEditingPageSteps.neverFieldsDoesntBeEmptyMessage();
  }

  @Test
  @DisplayName("Отмена добавление новости при нажатии кнопки Отмена")
  public void testCancelAddingNewsWithPressCancel() {
    NewsEditingPageSteps.fillingAllFieldsNews();
    NewsEditingPageSteps.pressCancelButton();
    NewsEditingPageSteps.confirmCancelAddingNews();
    BaseSteps.pressBack();
    int itemCount = NewsEditingPageSteps.getItemCount();
    NewsEditingPageSteps.isNewsNotCreated(itemCount);
  }

  @Test
  @DisplayName("Отмена добавление новости при нажатии кнопки Назад")
  public void testCancelAddingNewsWithPressBack() {
    NewsEditingPageSteps.fillingAllFieldsNews();
    BaseSteps.pressBack();
    int itemCount = NewsEditingPageSteps.getItemCount();
    NewsEditingPageSteps.isNewsNotCreated(itemCount);

  }

  @Test
  @DisplayName("Удаление новости")
  public void testDeleteNews() {
    NewsEditingPageSteps.addingNews();
    NewsEditingPageSteps.deleteAddedNews();
    int itemCount = NewsEditingPageSteps.getItemCount();
    NewsEditingPageSteps.isNewsDeleted(itemCount);
  }

  @Test
  @DisplayName("Редактирование атрибутов новости")
  public void testChangeNewsAttribute() {
    NewsEditingPageSteps.addingNews();
    NewsEditingPageSteps.editingNews();
    NewsEditingPageSteps.changeCreatedNewsAttributes();
    NewsEditingPageSteps.editingNews();
    NewsEditingPageSteps.checkChangedNewsAttributes();
  }
}
