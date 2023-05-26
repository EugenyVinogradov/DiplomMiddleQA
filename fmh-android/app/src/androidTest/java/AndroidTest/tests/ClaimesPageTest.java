package AndroidTest.tests;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.doesNotExist;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.not;
import static java.lang.Thread.sleep;
import static AndroidTest.Steps.AllureSteps.scrollToClaimWithTrimmedTittleAndClick;
import static AndroidTest.data.Data.commentClaim;
import static AndroidTest.data.Data.commentClaimEditind;
import static AndroidTest.data.Data.dateClaim;
import static AndroidTest.data.Data.descriptionClaim;
import static AndroidTest.data.Data.newDateClaim;
import static AndroidTest.data.Data.newDescriptionClaim;
import static AndroidTest.data.Data.newTimeClaim;
import static AndroidTest.data.Data.newTittleClaim;
import static AndroidTest.data.Data.timeClaim;
import static AndroidTest.data.Data.tittleClaim;
import static AndroidTest.data.Data.tittleClaimFiftyCharacter;
import static AndroidTest.data.Data.tittleClaimFiftyOneCharacter;
import static AndroidTest.data.Data.tittleClaimFortyNineCharacter;
import static AndroidTest.data.Data.tittleClaimOneCharacter;
import static AndroidTest.data.DataHelper.getTextFromViewInteraction;
import static AndroidTest.data.DataHelper.waitUntilVisible;
import static AndroidTest.pages.AuthPage.successLogin;
import static AndroidTest.pages.ClimesPage.addNewClime;
import static AndroidTest.pages.ClimesPage.addNewClimeWithoutSomething;
import static AndroidTest.pages.ClimesPage.cancelAddingNewClimeWhenPressedBack;
import static AndroidTest.pages.ClimesPage.cancelAddingNewClimeWhenPressedCancel;
import static AndroidTest.pages.ClimesPage.checkFilteredClimes;
import static AndroidTest.pages.ClimesPage.filterClimes;
import static AndroidTest.pages.ClimesPage.scrollAndClickToClaimWithTittle;
import static AndroidTest.pages.CommentPage.addNewComment;
import static AndroidTest.pages.CommentPage.cancelAddingNewCommentWhenPressedBack;
import static AndroidTest.pages.CommentPage.cancelAddingNewCommentWhenPressedCancel;
import static AndroidTest.pages.CommentPage.cancelУEditCommentWhenPressedBack;
import static AndroidTest.pages.CommentPage.cancelУEditCommentWhenPressedCancel;
import static AndroidTest.pages.CommentPage.editComment;
import static AndroidTest.pages.EditingClaim.changeStatusToInCanceled;
import static AndroidTest.pages.EditingClaim.changeStatusToInExecuted;
import static AndroidTest.pages.EditingClaim.changeStatusToInProgress;
import static AndroidTest.pages.EditingClaim.closeButton;
import static AndroidTest.pages.EditingClaim.editingDateClaim;
import static AndroidTest.pages.EditingClaim.editingDescriptionClaim;
import static AndroidTest.pages.EditingClaim.editingTimeClaim;
import static AndroidTest.pages.EditingClaim.editingTittleClaim;
import static AndroidTest.pages.EditingClaim.fillingFieldsWhenEditingClaimAndPressBack;
import static AndroidTest.pages.EditingClaim.fillingFieldsWhenEditingClaimAndPressCancel;
import static AndroidTest.pages.MainPage.goToClaimesPage;
import static AndroidTest.pages.MainPage.logOut;
import static AndroidTest.pages.NewClaimPage.errorWithAddingNewClaimWithoutSomeParameters;


import androidx.test.espresso.Espresso;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import AndroidTest.Steps.AllureSteps;
import AndroidTest.data.DataHelper;
import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.junit4.DisplayName;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.AppActivity;


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
  public ActivityScenarioRule<AppActivity> myActivityScenarioRule =
      new ActivityScenarioRule<>(AppActivity.class);

// Закомментированные тесты производят фильтрацию по всем существующим заявкам. Так как тестирование
// производится на продакшене, время прохождения каждого из них очень велико (около 30 минут самый быстрый).
// При переносе тестирования из продакшена в тетсовую среду, будет возможно использование этих тестов

//  @Test
//  @DisplayName("Фильтрация заявок - статус 'Открыта'")
//  public void testAllItemsHaveOpenStatus() throws InterruptedException {
//    filterClimes(true, false, false, false);
//    checkFilteredClimes(mActivityScenarioRule, true, false, false, false);
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
//  @DisplayName("Фильтрация заявок - статусы 'Открыта', 'В работе', 'Отменена'")
//  public void testAllItemsHaveOpenOrInProgressOrExecutedOrCanceledStatus() throws InterruptedException {
//    filterClimes(true, true, false, true);
//    checkFilteredClimes(mActivityScenarioRule, true, true, false, true);
//  }
//  @Test
//  @DisplayName("Фильтрация заявок - статусы 'Открыта', 'В работе', 'Выполнена', 'Отменена'")
//  public void testAllItemsHaveOpenOrInProgressOrExecutedOrCanceledStatus() throws InterruptedException {
//    filterClimes(true, true, true, true);
//    checkFilteredClimes(mActivityScenarioRule, true, true, true, true);
//  }
  //  @Test
//  @DisplayName("Фильтрация заявок - статусы 'Открыта', 'Выполнена'")
//  public void testAllItemsHaveOpenOrInProgressOrExecutedOrCanceledStatus() throws InterruptedException {
//    filterClimes(true, false, true, false);
//    checkFilteredClimes(mActivityScenarioRule, true, false, true, false);
//  }
  //  @Test
//  @DisplayName("Фильтрация заявок - статусы 'Открыта', 'Выполнена', 'Отменена'")
//  public void testAllItemsHaveOpenOrInProgressOrExecutedOrCanceledStatus() throws InterruptedException {
//    filterClimes(true, false, true, true);
//    checkFilteredClimes(mActivityScenarioRule, true, false, true, true);
//  }
  //  @Test
//  @DisplayName("Фильтрация заявок - статусы 'Открыта', 'Отменена'")
//  public void testAllItemsHaveOpenOrInProgressOrExecutedOrCanceledStatus() throws InterruptedException {
//    filterClimes(true, false, false, true);
//    checkFilteredClimes(mActivityScenarioRule, true, false, false, true);
//  }
  //  @Test
//  @DisplayName("Фильтрация заявок - статус 'В работе'")
//  public void testAllItemsHaveOpenStatus() throws InterruptedException {
//    filterClimes(false, true, false, false);
//    checkFilteredClimes(mActivityScenarioRule, false, true, false, false);
//  }
//  @Test
//  @DisplayName("Фильтрация заявок - статусы 'В работе', 'Выполнена'")
//  public void testAllItemsHaveOpenOrInProgressOrExecutedStatus() throws InterruptedException {
//    filterClimes(false, true, true, false);
//    checkFilteredClimes(mActivityScenarioRule, false, true, true, false);
//  }
//  @Test
//  @DisplayName("Фильтрация заявок - статусы 'В работе', 'Выполнена', 'Отменена'")
//  public void testAllItemsHaveOpenOrInProgressOrExecutedStatus() throws InterruptedException {
//    filterClimes(false, true, true, true);
//    checkFilteredClimes(mActivityScenarioRule, false, true, true, true);
//  }
//  @Test
//  @DisplayName("Фильтрация заявок - статусы 'В работе', 'Отменена'")
//  public void testAllItemsHaveOpenOrInProgressOrExecutedStatus() throws InterruptedException {
//    filterClimes(false, true, false, true);
//    checkFilteredClimes(mActivityScenarioRule, false, true, false, true);
//  }
//  @Test
//  @DisplayName("Фильтрация заявок - статус 'Выполнена'")
//  public void testAllItemsHaveOpenOrInProgressOrExecutedStatus() throws InterruptedException {
//    filterClimes(false, false, true, false);
//    checkFilteredClimes(mActivityScenarioRule, false, false, true, false);
//  }
//  @Test
//  @DisplayName("Фильтрация заявок - статусы 'Выполнена', 'Отменена'")
//  public void testAllItemsHaveOpenOrInProgressOrExecutedStatus() throws InterruptedException {
//    filterClimes(false, false, true, true);
//    checkFilteredClimes(mActivityScenarioRule, false, false, true, true);
//  }
//  @Test
//  @DisplayName("Фильтрация заявок - статус 'Отменена'")
//  public void testAllItemsHaveOpenOrInProgressOrExecutedStatus() throws InterruptedException {
//    filterClimes(false, false, false, true);
//    checkFilteredClimes(mActivityScenarioRule, false, false, false, true);
//  }

  @Test
  @DisplayName("Фильтрация заявок - без статусов")
  public void testAllItemsWithoutStatus() throws InterruptedException {
    AllureSteps.filterClimesWithoutStatus();
    AllureSteps.messageThereIsNothingHereYet(myActivityScenarioRule);
  }

  @Test
  @DisplayName("Фильтрация заявок - статус 'Открыта'")
  public void testAllItemsHaveOpenStatus() throws InterruptedException {
    AllureSteps.addClaim();
    AllureSteps.filterClimesWithStatusOpen();
    AllureSteps.scrollToClaimWithTittleAndClick();
    AllureSteps.checkClaimWithStatusOpen();
  }

  @Test
  @DisplayName("Отмена создания новой заявки при нажатии кнопки 'Cancel'")
  public void testNotCreatingNewClaimWhenPressingCancelButton() {
    AllureSteps.fillingFieldsNewClimeAndPressCancel();
    AllureSteps.checkNotExistNewClaim();
  }

  @Test
  @DisplayName("Отмена создания новой заявки при нажатии системной кнопки 'Назад'")
  public void testNotCreatingNewClaimWhenPressingBackButton() {
    AllureSteps.fillingFieldsNewClimeAndPressBack();
    AllureSteps.checkNotExistNewClaim();
  }

  @Test
  @DisplayName("Фильтрация заявок - статус 'В работе'")
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
  public void testSuccessCreatedNewClaimWithOneCharterInFieldTittle() throws InterruptedException {
    AllureSteps.addClaimWithOneCharacterInFieldTittle();
    AllureSteps.scrollToClaimWithOneCharacterTittleAndClick();
    AllureSteps.checkClaimWithStatusOpen();
  }

  @Test
  @DisplayName("Успешное создание новой заявки с 49 символами в поле Тема")
  public void testSuccessCreatedNewClaimWithFortyNineChartersInFieldTittle() throws InterruptedException {
    AllureSteps.addClaimWith49CharactersInFieldTittle();
    AllureSteps.scrollToClaimWith49CharacterTittleAndClick();
    AllureSteps.checkClaimWithStatusOpen();
  }

  @Test
  @DisplayName("Успешное создание новой заявки с 50 символами в поле Тема")
  public void testSuccessCreatedNewClaimWithFiftyChartersInFieldTittle() throws InterruptedException {
    AllureSteps.addClaimWith50CharactersInFieldTittle();
    AllureSteps.scrollToClaimWith50CharacterTittleAndClick();
    AllureSteps.checkClaimWithStatusOpen();
  }

  @Test
  @DisplayName("Обрезание тесктового значения поля Тема до 50 символов и успешное добавление новой заявки")
  public void testSuccessCreatedNewClaimWithFiftyOneChartersInFieldTittle() throws InterruptedException {
    String expectedTittle = tittleClaimFiftyOneCharacter.substring(0, 50);
    AllureSteps.addClaimWith51CharactersInFieldTittle();
    AllureSteps.scrollToClaimWithTrimmedTittleAndClick(expectedTittle);
    String actualTittle = getTextFromViewInteraction(onView(withId(R.id.title_text_view)));
    AllureSteps.checkThatTittleTrimmedTo50Characters(expectedTittle, actualTittle);
  }

  @Test
  @DisplayName("Отказ в создании новой заявки с незаполненным полем Тема")
  public void testRefusalCreatedNewClaimWithEmptyFieldTittle() {
    AllureSteps.addClaimWithEmptyFieldTittle();
    AllureSteps.checkErrorMessageBySomeParameterEmpty();
  }

  @Test
  @DisplayName("Отказ в создании новой заявки с заполнением поля Тема пробелами")
  public void testRefusalCreatedNewClaimWithOnlySpacesInFieldSTittle() {
    AllureSteps.addClaimWitOnlySpacesInFieldTittle();
    AllureSteps.checkErrorMessageBySomeParameterEmpty();
  }

  @Test
  @DisplayName("Отказ в создании новой заявки с незаполненным полем Дата")
  public void testRefusalCreatedNewClaimWithEmptyFieldDate() {
    AllureSteps.addClaimWithEmptyFieldDate();
    AllureSteps.checkErrorMessageBySomeParameterEmpty();
  }

  @Test
  @DisplayName("Отказ в создании новой заявки с незаполненным полем Время")
  public void testRefusalCreatedNewClaimWithEmptyFieldTime() {
    AllureSteps.addClaimWithEmptyFieldTime();
    AllureSteps.checkErrorMessageBySomeParameterEmpty();
  }
  @Test
  @DisplayName("Отказ в создании новой заявки с незаполнением поля Описание")
  public void testRefusalCreatedNewClaimWithEmptyFieldDescription() {
    AllureSteps.addClaimWithEmptyFieldDescription();
    AllureSteps.checkErrorMessageBySomeParameterEmpty();
  }
  @Test
  @DisplayName("Отказ в создании новой заявки с заполнением поля Описание пробелами")
  public void testRefusalCreatedNewClaimWithOnlySpacesInFieldDescription() {
    AllureSteps.addClaimWithOnlySpacesInFieldDescription();
    AllureSteps.checkErrorMessageBySomeParameterEmpty();
  }

  @Test
  @DisplayName("Успешное добавление комментария к заявке заполнением поля ввода символами")
  public void testSuccessAddingNewCommentToClaim() throws InterruptedException {
    AllureSteps.addClaim();
    AllureSteps.scrollToClaimWithTittleAndClick();
    AllureSteps.addNewCommentToClaim();
    AllureSteps.checkAddingNewComment();
  }

  @Test
  @DisplayName("Отмена добавления комментария к заявке заполнением поля ввода символами при нажатии кнопки Отмена")
  public void testCancelAddingNewCommentToClaimWithPressCancel() throws InterruptedException {
    AllureSteps.addClaim();
    AllureSteps.scrollToClaimWithTittleAndClick();
    AllureSteps.cancelAddingNewCommentToClaimWithPressCancel();
    AllureSteps.checkNotAddingNewComment();
  }

  @Test
  @DisplayName("Отмена добавления комментария к заявке заполнением поля ввода символами при нажатии кнопки Назад")
  public void testCancelAddingNewCommentToClaimWithPressBack() throws InterruptedException {
    AllureSteps.addClaim();
    AllureSteps.scrollToClaimWithTittleAndClick();
    AllureSteps.cancelAddingNewCommentToClaimWithPressBack();
    AllureSteps.checkNotAddingNewComment();
  }

  @Test
  @DisplayName("Отказ добавления комментария к заявке незаполненном поле ввода")
  public void testRefusalAddingNewCommentToClaimWithEmptyField() throws InterruptedException {
    AllureSteps.addClaim();
    AllureSteps.scrollToClaimWithTittleAndClick();
    AllureSteps.refusalAddingNewCommentToClaimWithEmptyField();
    AllureSteps.checkToastByEmptyField();
    Espresso.pressBack();
  }

  @Test
  @DisplayName("Отказ добавления комментария к заявке при заполнении поля ввода пробелами")
  public void testRefusalAddingNewCommentToClaimWithOnlySpacesInField() throws InterruptedException {
    AllureSteps.addClaim();
    AllureSteps.scrollToClaimWithTittleAndClick();
    AllureSteps.refusalAddingNewCommentToClaimWithOnlySpacesInField();
    AllureSteps.checkToastByEmptyField();
    Espresso.pressBack();
  }

  @Test
  @DisplayName("Редактирование существующего комментария у заявки")
  public void testSuccessEditingExistingCommentToClaim() throws InterruptedException {
    addNewClime(tittleClaim, dateClaim, timeClaim, descriptionClaim);
    scrollAndClickToClaimWithTittle(tittleClaim);
    addNewComment(commentClaim);
    closeButton.perform(click());
    scrollAndClickToClaimWithTittle(tittleClaim);
    editComment(commentClaimEditind);
    onView(withText(commentClaimEditind)).check(matches(isDisplayed()));
  }

  @Test
  @DisplayName("Не изменение существующего комментария у заявки при редактировании и нажатии кнопки Отмена")
  public void testCancelEditingExistingCommentToClaimWithPressCancel() throws Exception {
    addNewClime(tittleClaim, dateClaim, timeClaim, descriptionClaim);
    scrollAndClickToClaimWithTittle(tittleClaim);
    addNewComment(commentClaim);
    closeButton.perform(click());
    scrollAndClickToClaimWithTittle(tittleClaim);
    cancelУEditCommentWhenPressedCancel(commentClaimEditind);
    onView(withText(commentClaim)).check(matches(isDisplayed()));
  }

  @Test
  @DisplayName("Не изменение существующего комментария у заявки при редактировании и нажатии кнопки Назад")
  public void testCancelEditingExistingCommentToClaimWithPressBack() throws Exception {
    addNewClime(tittleClaim, dateClaim, timeClaim, descriptionClaim);
    scrollAndClickToClaimWithTittle(tittleClaim);
    addNewComment(commentClaim);
    closeButton.perform(click());
    scrollAndClickToClaimWithTittle(tittleClaim);
    cancelУEditCommentWhenPressedBack(commentClaimEditind);
    onView(withText(commentClaim)).check(matches(isDisplayed()));
  }

  @Test
  @DisplayName("Редактирование Темы у существующей заявки")
  public void testSuccessEditingTittleToExistingClaim() throws Exception {
    addNewClime(tittleClaim, dateClaim, timeClaim, descriptionClaim);
    scrollAndClickToClaimWithTittle(tittleClaim);
    editingTittleClaim(tittleClaim + "123");
    onView(withText(tittleClaim + "123")).check(matches(isDisplayed()));
  }

  @Test
  @DisplayName("Редактирование Даты у существующей заявки")
  public void testSuccessEditingDateToExistingClaim() throws Exception {
    addNewClime(tittleClaim, dateClaim, timeClaim, descriptionClaim);
    scrollAndClickToClaimWithTittle(tittleClaim);
    editingDateClaim(newDateClaim);
    onView(withText(newDateClaim)).check(matches(isDisplayed()));
  }

  @Test
  @DisplayName("Редактирование Времени у существующей заявки")
  public void testSuccessEditingTimeToExistingClaim() throws Exception {
    addNewClime(tittleClaim, dateClaim, timeClaim, descriptionClaim);
    scrollAndClickToClaimWithTittle(tittleClaim);
    editingTimeClaim(newTimeClaim);
    onView(withText(newTimeClaim)).check(matches(isDisplayed()));
  }

  @Test
  @DisplayName("Редактирование Описания у существующей заявки")
  public void testSuccessEditingDescriptionToExistingClaim() throws Exception {
    addNewClime(tittleClaim, dateClaim, timeClaim, descriptionClaim);
    scrollAndClickToClaimWithTittle(tittleClaim);
    editingDescriptionClaim(newDescriptionClaim);
    onView(withText(newDescriptionClaim)).check(matches(isDisplayed()));
  }

  @Test
  @DisplayName("Отмена редактирования полей существующей заявки при нажатии кнопки Отмена")
  public void testCancelEditingToExistingClaimWithPressCancel() throws Exception {
    addNewClime(tittleClaim, dateClaim, timeClaim, descriptionClaim);
    scrollAndClickToClaimWithTittle(tittleClaim);
    fillingFieldsWhenEditingClaimAndPressCancel(newTittleClaim, newDateClaim, newTimeClaim, newDescriptionClaim);
    onView(withText(tittleClaim)).check(matches(isDisplayed()));
    onView(withText(dateClaim)).check(matches(isDisplayed()));
    onView(withText(timeClaim)).check(matches(isDisplayed()));
    onView(withText(descriptionClaim)).check(matches(isDisplayed()));
  }

  @Test
  @DisplayName("Отмена редактирования полей существующей заявки при нажатии кнопки Назад")
  public void testCancelEditingToExistingClaimWithPressBack() throws Exception {
    addNewClime(tittleClaim, dateClaim, timeClaim, descriptionClaim);
    scrollAndClickToClaimWithTittle(tittleClaim);
    fillingFieldsWhenEditingClaimAndPressBack(newTittleClaim, newDateClaim, newTimeClaim, newDescriptionClaim);
    onView(withText(tittleClaim)).check(matches(isDisplayed()));
    onView(withText(dateClaim)).check(matches(isDisplayed()));
    onView(withText(timeClaim)).check(matches(isDisplayed()));
    onView(withText(descriptionClaim)).check(matches(isDisplayed()));
  }

}

