package AndroidTest.tests;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static java.lang.Thread.sleep;
import static AndroidTest.Steps.AllureSteps.logOutFromApp;
import static AndroidTest.Steps.AllureSteps.successLoginStep;
import static AndroidTest.data.Data.tittleClaimFiftyOneCharacter;
import static AndroidTest.data.DataHelper.getTextFromViewInteraction;
import static AndroidTest.data.DataHelper.getUniqueScreenshotName;
import static AndroidTest.pages.EditingClaim.closeButton;
import static AndroidTest.pages.MainPage.goToClaimesPage;

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

@Epic("Тестирование страницы Заявки")

@RunWith(AllureAndroidJUnit4.class)
public class ClaimesPageTest {

  @Before
  public void login() {
    successLoginStep();
    goToClaimesPage();
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

// Закомментированные тесты производят фильтрацию по всем существующим заявкам. Так как тестирование
// производится на продакшене, время прохождения каждого из них очень велико (около 30 минут самый быстрый).
// При переносе тестирования из продакшена в тетсовую среду, будет возможно использование этих тестов

//  @Test
//  @DisplayName("Фильтрация заявок - статус 'Открыта'")
//  @Attachment
//  public void testAllItemsHaveOpenStatus() throws InterruptedException {
//    filterClimes(true, false, false, false);
//    checkFilteredClimes(mActivityScenarioRule, true, false, false, false);
//  }
//  @Test
//  @DisplayName("Фильтрация заявок - статусы 'Открыта' и 'В работе'")
//  @Attachment
//  public void testAllItemsHaveOpenOrInProgressStatus() throws InterruptedException {
//    filterClimes(true, true, false, false);
//    checkFilteredClimes(mActivityScenarioRule, true, true, false, false);
//  }
//
//  @Test
//  @DisplayName("Фильтрация заявок - статусы 'Открыта', 'В работе', 'Выполнена'")
//  @Attachment
//  public void testAllItemsHaveOpenOrInProgressOrExecutedStatus() throws InterruptedException {
//    filterClimes(true, true, true, false);
//    checkFilteredClimes(mActivityScenarioRule, true, true, true, false);
//  }
  //  @Test
//  @DisplayName("Фильтрация заявок - статусы 'Открыта', 'В работе', 'Отменена'")
//  @Attachment
//  public void testAllItemsHaveOpenOrInProgressOrExecutedOrCanceledStatus() throws InterruptedException {
//    filterClimes(true, true, false, true);
//    checkFilteredClimes(mActivityScenarioRule, true, true, false, true);
//  }
//  @Test
//  @DisplayName("Фильтрация заявок - статусы 'Открыта', 'В работе', 'Выполнена', 'Отменена'")
//  @Attachment
//  public void testAllItemsHaveOpenOrInProgressOrExecutedOrCanceledStatus() throws InterruptedException {
//    filterClimes(true, true, true, true);
//    checkFilteredClimes(mActivityScenarioRule, true, true, true, true);
//  }
  //  @Test
//  @DisplayName("Фильтрация заявок - статусы 'Открыта', 'Выполнена'")
//  @Attachment
//  public void testAllItemsHaveOpenOrInProgressOrExecutedOrCanceledStatus() throws InterruptedException {
//    filterClimes(true, false, true, false);
//    checkFilteredClimes(mActivityScenarioRule, true, false, true, false);
//  }
  //  @Test
//  @DisplayName("Фильтрация заявок - статусы 'Открыта', 'Выполнена', 'Отменена'")
//  @Attachment
//  public void testAllItemsHaveOpenOrInProgressOrExecutedOrCanceledStatus() throws InterruptedException {
//    filterClimes(true, false, true, true);
//    checkFilteredClimes(mActivityScenarioRule, true, false, true, true);
//  }
  //  @Test
//  @DisplayName("Фильтрация заявок - статусы 'Открыта', 'Отменена'")
//  @Attachment
//  public void testAllItemsHaveOpenOrInProgressOrExecutedOrCanceledStatus() throws InterruptedException {
//    filterClimes(true, false, false, true);
//    checkFilteredClimes(mActivityScenarioRule, true, false, false, true);
//  }
  //  @Test
//  @DisplayName("Фильтрация заявок - статус 'В работе'")
//  @Attachment
//  public void testAllItemsHaveOpenStatus() throws InterruptedException {
//    filterClimes(false, true, false, false);
//    checkFilteredClimes(mActivityScenarioRule, false, true, false, false);
//  }
//  @Test
//  @DisplayName("Фильтрация заявок - статусы 'В работе', 'Выполнена'")
//  @Attachment
//  public void testAllItemsHaveOpenOrInProgressOrExecutedStatus() throws InterruptedException {
//    filterClimes(false, true, true, false);
//    checkFilteredClimes(mActivityScenarioRule, false, true, true, false);
//  }
//  @Test
//  @DisplayName("Фильтрация заявок - статусы 'В работе', 'Выполнена', 'Отменена'")
//  @Attachment
//  public void testAllItemsHaveOpenOrInProgressOrExecutedStatus() throws InterruptedException {
//    filterClimes(false, true, true, true);
//    checkFilteredClimes(mActivityScenarioRule, false, true, true, true);
//  }
//  @Test
//  @DisplayName("Фильтрация заявок - статусы 'В работе', 'Отменена'")
//  @Attachment
//  public void testAllItemsHaveOpenOrInProgressOrExecutedStatus() throws InterruptedException {
//    filterClimes(false, true, false, true);
//    checkFilteredClimes(mActivityScenarioRule, false, true, false, true);
//  }
//  @Test
//  @DisplayName("Фильтрация заявок - статус 'Выполнена'")
//  @Attachment
//  public void testAllItemsHaveOpenOrInProgressOrExecutedStatus() throws InterruptedException {
//    filterClimes(false, false, true, false);
//    checkFilteredClimes(mActivityScenarioRule, false, false, true, false);
//  }
//  @Test
//  @DisplayName("Фильтрация заявок - статусы 'Выполнена', 'Отменена'")
//  @Attachment
//  public void testAllItemsHaveOpenOrInProgressOrExecutedStatus() throws InterruptedException {
//    filterClimes(false, false, true, true);
//    checkFilteredClimes(mActivityScenarioRule, false, false, true, true);
//  }
//  @Test
//  @DisplayName("Фильтрация заявок - статус 'Отменена'")
//  @Attachment
//  public void testAllItemsHaveOpenOrInProgressOrExecutedStatus() throws InterruptedException {
//    filterClimes(false, false, false, true);
//    checkFilteredClimes(mActivityScenarioRule, false, false, false, true);
//  }

  @Test
  @DisplayName("Фильтрация заявок - без статусов")
  @Attachment
  public void testAllItemsWithoutStatus() throws InterruptedException {
    AllureSteps.filterClimesWithoutStatus();
    AllureSteps.messageThereIsNothingHereYet(myActivityScenarioRule);
  }

  @Test
  @DisplayName("Фильтрация заявок - статус 'Открыта'")
  @Attachment
  public void testAllItemsHaveOpenStatus() throws InterruptedException {
    AllureSteps.addClaim();
    AllureSteps.filterClimesWithStatusOpen();
    AllureSteps.scrollToClaimWithTittleAndClick();
    AllureSteps.checkClaimWithStatusOpen();
  }

  @Test
  @DisplayName("Отмена создания новой заявки при нажатии кнопки 'Cancel'")
  @Attachment
  public void testNotCreatingNewClaimWhenPressingCancelButton() {
    AllureSteps.fillingFieldsNewClimeAndPressCancel();
    AllureSteps.checkNotExistNewClaim();
  }

  @Test
  @DisplayName("Отмена создания новой заявки при нажатии системной кнопки 'Назад'")
  @Attachment
  public void testNotCreatingNewClaimWhenPressingBackButton() {
    AllureSteps.fillingFieldsNewClimeAndPressBack();
    AllureSteps.checkNotExistNewClaim();
  }

  @Test
  @DisplayName("Фильтрация заявок - статус 'В работе'")
  @Attachment
  public void testAllItemsHaveInProgressStatus() throws InterruptedException {
    AllureSteps.addClaim();
    AllureSteps.scrollToClaimWithTittleAndClick();
    AllureSteps.changeStatusToInProgress();
    AllureSteps.filterClimesWithStatusInProgress();
    AllureSteps.scrollToClaimWithTittleAndClick();
    AllureSteps.checkClaimWithStatusInProgress();
  }

  @Test
  @DisplayName("Фильтрация заявок - статус 'Отменена'")
  @Attachment
  public void testAllItemsHaveCanceledStatus() throws InterruptedException {
    AllureSteps.addClaim();
    AllureSteps.scrollToClaimWithTittleAndClick();
    AllureSteps.changeStatusToInCanceled();
    sleep(3000);
    AllureSteps.filterClimesWithStatusCanceled();
    sleep(5000);
    AllureSteps.scrollToClaimWithTittleAndClick();
    AllureSteps.checkClaimWithStatusCanceled();
  }

  @Test
  @DisplayName("Фильтрация заявок - статус 'Исполнена'")
  @Attachment
  public void testAllItemsHaveExecutedStatus() throws InterruptedException {
    AllureSteps.addClaim();
    AllureSteps.scrollToClaimWithTittleAndClick();
    AllureSteps.changeStatusToInProgress();
    sleep(3000);
    AllureSteps.scrollToClaimWithTittleAndClick();
    AllureSteps.changeStatusToInExecuted();
    AllureSteps.filterClimesWithStatusExecuted();
    sleep(5000);
    AllureSteps.scrollToClaimWithTittleAndClick();
    AllureSteps.checkClaimWithStatusExecuted();
  }

  @Test
  @DisplayName("Успешное создание новой заявки с одним символом в поле Тема")
  @Attachment
  public void testSuccessCreatedNewClaimWithOneCharterInFieldTittle() throws InterruptedException {
    AllureSteps.addClaimWithOneCharacterInFieldTittle();
    AllureSteps.scrollToClaimWithOneCharacterTittleAndClick();
    AllureSteps.checkClaimWithStatusOpen();
  }

  @Test
  @DisplayName("Успешное создание новой заявки с 49 символами в поле Тема")
  @Attachment
  public void testSuccessCreatedNewClaimWithFortyNineChartersInFieldTittle() throws InterruptedException {
    AllureSteps.addClaimWith49CharactersInFieldTittle();
    AllureSteps.scrollToClaimWith49CharacterTittleAndClick();
    AllureSteps.checkClaimWithStatusOpen();
  }

  @Test
  @DisplayName("Успешное создание новой заявки с 50 символами в поле Тема")
  @Attachment
  public void testSuccessCreatedNewClaimWithFiftyChartersInFieldTittle() throws InterruptedException {
    AllureSteps.addClaimWith50CharactersInFieldTittle();
    AllureSteps.scrollToClaimWith50CharacterTittleAndClick();
    AllureSteps.checkClaimWithStatusOpen();
  }

  @Test
  @DisplayName("Обрезание тесктового значения поля Тема до 50 символов и успешное добавление новой заявки")
  @Attachment
  public void testSuccessCreatedNewClaimWithFiftyOneChartersInFieldTittle() throws InterruptedException {
    String expectedTittle = tittleClaimFiftyOneCharacter.substring(0, 50);
    AllureSteps.addClaimWith51CharactersInFieldTittle();
    AllureSteps.scrollToClaimWithTrimmedTittleAndClick(expectedTittle);
    String actualTittle = getTextFromViewInteraction(onView(withId(R.id.title_text_view)));
    AllureSteps.checkThatTittleTrimmedTo50Characters(expectedTittle, actualTittle);
  }

  @Test
  @DisplayName("Отказ в создании новой заявки с незаполненным полем Тема")
  @Attachment
  public void testRefusalCreatedNewClaimWithEmptyFieldTittle() {
    AllureSteps.addClaimWithEmptyFieldTittle();
    AllureSteps.checkErrorMessageBySomeParameterEmpty();
  }

  @Test
  @DisplayName("Отказ в создании новой заявки с заполнением поля Тема пробелами")
  @Attachment
  public void testRefusalCreatedNewClaimWithOnlySpacesInFieldSTittle() {
    AllureSteps.addClaimWitOnlySpacesInFieldTittle();
    AllureSteps.checkErrorMessageBySomeParameterEmpty();
  }

  @Test
  @DisplayName("Отказ в создании новой заявки с незаполненным полем Дата")
  @Attachment
  public void testRefusalCreatedNewClaimWithEmptyFieldDate() {
    AllureSteps.addClaimWithEmptyFieldDate();
    AllureSteps.checkErrorMessageBySomeParameterEmpty();
  }

  @Test
  @DisplayName("Отказ в создании новой заявки с незаполненным полем Время")
  @Attachment
  public void testRefusalCreatedNewClaimWithEmptyFieldTime() {
    AllureSteps.addClaimWithEmptyFieldTime();
    AllureSteps.checkErrorMessageBySomeParameterEmpty();
  }

  @Test
  @DisplayName("Отказ в создании новой заявки с незаполнением поля Описание")
  @Attachment
  public void testRefusalCreatedNewClaimWithEmptyFieldDescription() {
    AllureSteps.addClaimWithEmptyFieldDescription();
    AllureSteps.checkErrorMessageBySomeParameterEmpty();
  }

  @Test
  @DisplayName("Отказ в создании новой заявки с заполнением поля Описание пробелами")
  @Attachment
  public void testRefusalCreatedNewClaimWithOnlySpacesInFieldDescription() {
    AllureSteps.addClaimWithOnlySpacesInFieldDescription();
    AllureSteps.checkErrorMessageBySomeParameterEmpty();
  }

  @Test
  @DisplayName("Успешное добавление комментария к заявке заполнением поля ввода символами")
  @Attachment
  public void testSuccessAddingNewCommentToClaim() throws InterruptedException {
    AllureSteps.addClaim();
    AllureSteps.scrollToClaimWithTittleAndClick();
    AllureSteps.addNewCommentToClaim();
    AllureSteps.checkAddingNewComment();
  }

  @Test
  @DisplayName("Отмена добавления комментария к заявке заполнением поля ввода символами при нажатии кнопки Отмена")
  @Attachment
  public void testCancelAddingNewCommentToClaimWithPressCancel() throws InterruptedException {
    AllureSteps.addClaim();
    AllureSteps.scrollToClaimWithTittleAndClick();
    AllureSteps.cancelAddingNewCommentToClaimWithPressCancel();
    AllureSteps.checkNotAddingNewComment();
  }

  @Test
  @DisplayName("Отмена добавления комментария к заявке заполнением поля ввода символами при нажатии кнопки Назад")
  @Attachment
  public void testCancelAddingNewCommentToClaimWithPressBack() throws InterruptedException {
    AllureSteps.addClaim();
    AllureSteps.scrollToClaimWithTittleAndClick();
    AllureSteps.cancelAddingNewCommentToClaimWithPressBack();
    AllureSteps.checkNotAddingNewComment();
  }

  @Test
  @DisplayName("Отказ добавления комментария к заявке незаполненном поле ввода")
  @Attachment
  public void testRefusalAddingNewCommentToClaimWithEmptyField() throws InterruptedException {
    AllureSteps.addClaim();
    AllureSteps.scrollToClaimWithTittleAndClick();
    AllureSteps.refusalAddingNewCommentToClaimWithEmptyField();
    AllureSteps.checkToastByEmptyField();
    AllureSteps.pressBack();
  }

  @Test
  @DisplayName("Отказ добавления комментария к заявке при заполнении поля ввода пробелами")
  @Attachment
  public void testRefusalAddingNewCommentToClaimWithOnlySpacesInField() throws InterruptedException {
    AllureSteps.addClaim();
    AllureSteps.scrollToClaimWithTittleAndClick();
    AllureSteps.refusalAddingNewCommentToClaimWithOnlySpacesInField();
    AllureSteps.checkToastByEmptyField();
    AllureSteps.pressBack();
  }

  @Test
  @DisplayName("Редактирование существующего комментария у заявки")
  @Attachment
  public void testSuccessEditingExistingCommentToClaim() throws InterruptedException {
    AllureSteps.addClaim();
    AllureSteps.scrollToClaimWithTittleAndClick();
    AllureSteps.addNewCommentToClaim();
    closeButton.perform(click());
    AllureSteps.scrollToClaimWithTittleAndClick();
    AllureSteps.editExistingCommentToClaim();
    AllureSteps.checkEditingExistingComment();
  }

  @Test
  @DisplayName("Не изменение существующего комментария у заявки при редактировании и нажатии кнопки Отмена")
  @Attachment
  public void testCancelEditingExistingCommentToClaimWithPressCancel() throws Exception {
    AllureSteps.addClaim();
    AllureSteps.scrollToClaimWithTittleAndClick();
    AllureSteps.addNewCommentToClaim();
    closeButton.perform(click());
    AllureSteps.scrollToClaimWithTittleAndClick();
    AllureSteps.cancelEditingExistingCommentToClaimWithPressCancel();
    AllureSteps.checkNotEditingExistingComment();
  }

  @Test
  @DisplayName("Не изменение существующего комментария у заявки при редактировании и нажатии кнопки Назад")
  @Attachment
  public void testCancelEditingExistingCommentToClaimWithPressBack() throws Exception {
    AllureSteps.addClaim();
    AllureSteps.scrollToClaimWithTittleAndClick();
    AllureSteps.addNewCommentToClaim();
    closeButton.perform(click());
    AllureSteps.scrollToClaimWithTittleAndClick();
    AllureSteps.cancelEditingExistingCommentToClaimWithPressBack();
    AllureSteps.checkNotEditingExistingComment();
  }

  @Test
  @DisplayName("Редактирование Темы у существующей заявки")
  @Attachment
  public void testSuccessEditingTittleToExistingClaim() throws Exception {
    AllureSteps.addClaim();
    AllureSteps.scrollToClaimWithTittleAndClick();
    AllureSteps.editTittleClaim();
    AllureSteps.checkEditingTittleClaim();
  }

  @Test
  @DisplayName("Редактирование Даты у существующей заявки")
  @Attachment
  public void testSuccessEditingDateToExistingClaim() throws Exception {
    AllureSteps.addClaim();
    AllureSteps.scrollToClaimWithTittleAndClick();
    AllureSteps.editDateClaim();
    AllureSteps.checkEditingDateClaim();
  }

  @Test
  @DisplayName("Редактирование Времени у существующей заявки")
  @Attachment
  public void testSuccessEditingTimeToExistingClaim() throws Exception {
    AllureSteps.addClaim();
    AllureSteps.scrollToClaimWithTittleAndClick();
    AllureSteps.editTimeClaim();
    AllureSteps.checkEditingTimeClaim();
  }

  @Test
  @DisplayName("Редактирование Описания у существующей заявки")
  @Attachment
  public void testSuccessEditingDescriptionToExistingClaim() throws Exception {
    AllureSteps.addClaim();
    AllureSteps.scrollToClaimWithTittleAndClick();
    AllureSteps.editDescriptionClaim();
    AllureSteps.checkEditingDescriptionClaim();
  }

  @Test
  @DisplayName("Отмена редактирования полей существующей заявки при нажатии кнопки Назад")
  @Attachment
  public void testCancelEditingToExistingClaimWithPressBack() throws Exception {
    AllureSteps.addClaim();
    AllureSteps.scrollToClaimWithTittleAndClick();
    AllureSteps.cancelEditingFieldsExistingClaimWithPressBack();
    AllureSteps.checkNotChangingAttributeClaim();
  }

  @Test
  @DisplayName("Отмена редактирования полей существующей заявки при нажатии кнопки Отмена")
  @Attachment
  public void testCancelEditingToExistingClaimWithPressCancel() throws Exception {
    AllureSteps.addClaim();
    AllureSteps.scrollToClaimWithTittleAndClick();
    AllureSteps.cancelEditingFieldsExistingClaimWithPressCancel();
    AllureSteps.checkNotChangingAttributeClaim();
  }

}

