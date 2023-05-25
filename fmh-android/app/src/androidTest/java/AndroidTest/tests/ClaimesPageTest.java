package AndroidTest.tests;

import static android.app.PendingIntent.getActivity;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.doesNotExist;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.hasToString;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static java.lang.Thread.sleep;
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
import static AndroidTest.pages.CommentPage.commentSaveButton;
import static AndroidTest.pages.CommentPage.commentText;
import static AndroidTest.pages.CommentPage.editComment;
import static AndroidTest.pages.EditingClaim.changeStatusToInCanceled;
import static AndroidTest.pages.EditingClaim.changeStatusToInExecuted;
import static AndroidTest.pages.EditingClaim.changeStatusToInProgress;
import static AndroidTest.pages.EditingClaim.closeButton;
import static AndroidTest.pages.EditingClaim.commentField;
import static AndroidTest.pages.EditingClaim.editClaimButton;
import static AndroidTest.pages.EditingClaim.editCommentButton;
import static AndroidTest.pages.EditingClaim.editingDateClaim;
import static AndroidTest.pages.EditingClaim.editingDescriptionClaim;
import static AndroidTest.pages.EditingClaim.editingTimeClaim;
import static AndroidTest.pages.EditingClaim.editingTittleClaim;
import static AndroidTest.pages.EditingClaim.fillingFieldsWhenEditingClaimAndPressBack;
import static AndroidTest.pages.EditingClaim.fillingFieldsWhenEditingClaimAndPressCancel;
import static AndroidTest.pages.EditingClaim.saveEditingButton;
import static AndroidTest.pages.EditingClaim.tittleEditField;
import static AndroidTest.pages.MainPage.goToClaimesPage;
import static AndroidTest.pages.MainPage.logOut;
import static AndroidTest.pages.NewClaimPage.errorWithAddingNewClaimWithoutSomeParameters;


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


//@RunWith(AndroidJUnit4.class)
@RunWith(AllureAndroidJUnit4.class)
public class ClaimesPageTest {

  @Before
  public void login() throws InterruptedException {
    sleep(2000);
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
    filterClimes(false, false, false, false);
    checkFilteredClimes(myActivityScenarioRule, false, false, false, false);
  }

  @Test
  @DisplayName("Фильтрация заявок - статус 'Открыта'")
  public void testAllItemsHaveOpenStatus() throws InterruptedException {
    addNewClime(tittleClaim, dateClaim, timeClaim, descriptionClaim);
    filterClimes(true, false, false, false);
    scrollAndClickToClaimWithTittle(tittleClaim);
    onView(withId(R.id.status_label_text_view)).check(matches(allOf(isDisplayed(), withText(R.string.status_open))));
  }

  @Test
  @DisplayName("Отмена создания новой заявки при нажатии кнопки 'Cancel'")
  public void testNotCreatingNewClaimWhenPressingCancelButton() throws InterruptedException {
    cancelAddingNewClimeWhenPressedCancel(tittleClaim, dateClaim, timeClaim, descriptionClaim);
    onView(withId(R.id.claim_list_recycler_view))
        .check(matches(not(hasDescendant(withText(tittleClaim)))));
  }

  @Test
  @DisplayName("Отмена создания новой заявки при нажатии системной кнопки 'Назад'")
  public void testNotCreatingNewClaimWhenPressingBackButton() throws InterruptedException {
    cancelAddingNewClimeWhenPressedBack(tittleClaim, dateClaim, timeClaim, descriptionClaim);
    onView(withId(R.id.claim_list_recycler_view))
        .check(matches(not(hasDescendant(withText(tittleClaim)))));
  }

  @Test
  @DisplayName("Фильтрация заявок - статус 'В работе'")
  public void testAllItemsHaveInProgressStatus() throws InterruptedException {
    addNewClime(tittleClaim, dateClaim, timeClaim, descriptionClaim);
    scrollAndClickToClaimWithTittle(tittleClaim);
    changeStatusToInProgress();
//    sleep(3000);
    filterClimes(false, true, false, false);
//    sleep(5000);
    scrollAndClickToClaimWithTittle(tittleClaim);
    onView(withId(R.id.status_label_text_view)).check(matches(allOf(isDisplayed(), withText(R.string.in_progress))));
  }

  @Test
  @DisplayName("Фильтрация заявок - статус 'Отменена'")
  public void testAllItemsHaveCanceledStatus() throws InterruptedException {
    addNewClime(tittleClaim, dateClaim, timeClaim, descriptionClaim);
    scrollAndClickToClaimWithTittle(tittleClaim);
    changeStatusToInCanceled();
    sleep(3000);
    filterClimes(false, false, false, true);
    sleep(5000);
    scrollAndClickToClaimWithTittle(tittleClaim);
    onView(withId(R.id.status_label_text_view)).check(matches(allOf(isDisplayed(), withText(R.string.status_claim_canceled))));
  }

  @Test
  @DisplayName("Фильтрация заявок - статус 'Исполнена'")
  public void testAllItemsHaveExecutedStatus() throws InterruptedException {
    addNewClime(tittleClaim, dateClaim, timeClaim, descriptionClaim);
    scrollAndClickToClaimWithTittle(tittleClaim);
    changeStatusToInProgress();
    sleep(3000);
    scrollAndClickToClaimWithTittle(tittleClaim);
    changeStatusToInExecuted();
    filterClimes(false, false, true, false);
    sleep(5000);
    scrollAndClickToClaimWithTittle(tittleClaim);
    onView(withId(R.id.status_label_text_view)).check(matches(allOf(isDisplayed(), withText(R.string.executed))));
  }

  @Test
  @DisplayName("Успешное создание новой заявки с одним символом в поле Тема")
  public void testSuccessCreatedNewClaimWithOneCharterInFieldTittle() throws InterruptedException {
    addNewClime(tittleClaimOneCharacter, dateClaim, timeClaim, descriptionClaim);
    scrollAndClickToClaimWithTittle(tittleClaimOneCharacter);
    onView(withId(R.id.status_label_text_view)).check(matches(allOf(isDisplayed(), withText(R.string.status_open))));
  }

  @Test
  @DisplayName("Успешное создание новой заявки с 49 символами в поле Тема")
  public void testSuccessCreatedNewClaimWithFortyNineChartersInFieldTittle() throws InterruptedException {
    addNewClime(tittleClaimFortyNineCharacter, dateClaim, timeClaim, descriptionClaim);
    scrollAndClickToClaimWithTittle(tittleClaimFortyNineCharacter);
    onView(withId(R.id.status_label_text_view)).check(matches(allOf(isDisplayed(), withText(R.string.status_open))));
  }

  @Test
  @DisplayName("Успешное создание новой заявки с 50 символами в поле Тема")
  public void testSuccessCreatedNewClaimWithFiftyChartersInFieldTittle() throws InterruptedException {
    addNewClime(tittleClaimFiftyCharacter, dateClaim, timeClaim, descriptionClaim);
    scrollAndClickToClaimWithTittle(tittleClaimFiftyCharacter);
    onView(withId(R.id.status_label_text_view)).check(matches(allOf(isDisplayed(), withText(R.string.status_open))));
  }

  @Test
  @DisplayName("Обрезание тесктового значения поля Тема до 50 символов и успешное добавление новой заявки")
  public void testSuccessCreatedNewClaimWithFiftyOneChartersInFieldTittle() throws InterruptedException {
    String Tittle = tittleClaimFiftyOneCharacter.substring(0, 50);
    addNewClime(tittleClaimFiftyOneCharacter, dateClaim, timeClaim, descriptionClaim);
    scrollAndClickToClaimWithTittle(Tittle);
    onView(withId(R.id.status_label_text_view)).check(matches(allOf(isDisplayed(), withText(R.string.status_open))));
  }

  @Test
  @DisplayName("Отказ в создании новой заявки с незаполненным полем Тема")
  public void testRefusalCreatedNewClaimWithEmptyFieldTittle() {
    addNewClimeWithoutSomething("", dateClaim, timeClaim, descriptionClaim);
    errorWithAddingNewClaimWithoutSomeParameters();
  }

  @Test
  @DisplayName("Отказ в создании новой заявки с заполнением поля Тема пробелами")
  public void testRefusalCreatedNewClaimWithOnlySpacesInFieldSTittle() {
    addNewClimeWithoutSomething("   ", dateClaim, timeClaim, descriptionClaim);
    errorWithAddingNewClaimWithoutSomeParameters();
  }

  @Test
  @DisplayName("Отказ в создании новой заявки с незаполненным полем Дата")
  public void testRefusalCreatedNewClaimWithEmptyFieldDate() {
    addNewClimeWithoutSomething(tittleClaim, "", timeClaim, descriptionClaim);
    errorWithAddingNewClaimWithoutSomeParameters();
  }

  @Test
  @DisplayName("Отказ в создании новой заявки с незаполненным полем Время")
  public void testRefusalCreatedNewClaimWithEmptyFieldTime() {
    addNewClimeWithoutSomething(tittleClaim, dateClaim, "", descriptionClaim);
    errorWithAddingNewClaimWithoutSomeParameters();
  }

  @Test
  @DisplayName("Отказ в создании новой заявки с заполнением поля Описание пробелами")
  public void testRefusalCreatedNewClaimWithOnlySpacesInFieldDescription() {
    addNewClimeWithoutSomething(tittleClaim, dateClaim, timeClaim, "    ");
    errorWithAddingNewClaimWithoutSomeParameters();
  }

  @Test
  @DisplayName("Успешное добавление комментария к заявке заполнением поля ввода символами")
  public void testSuccessAddingNewCommentToClaim() throws InterruptedException {
    addNewClime(tittleClaim, dateClaim, timeClaim, descriptionClaim);
    scrollAndClickToClaimWithTittle(tittleClaim);
    addNewComment(commentClaim);
    onView(withText(commentClaim)).check(matches(isDisplayed()));
  }
  @Test
  @DisplayName("Отмена добавления комментария к заявке заполнением поля ввода символами при нажатии кнопки Отмена")
  public void testCancelAddingNewCommentToClaimWithPressCancel() throws InterruptedException {
    addNewClime(tittleClaim, dateClaim, timeClaim, descriptionClaim);
    scrollAndClickToClaimWithTittle(tittleClaim);
    cancelAddingNewCommentWhenPressedCancel(commentClaim);
    onView(allOf(withId(R.id.comment_description_text_view))).check(doesNotExist());

  }
  @Test
  @DisplayName("Отмена добавления комментария к заявке заполнением поля ввода символами при нажатии кнопки Назад")
  public void testCancelAddingNewCommentToClaimWithPressBack() throws InterruptedException {
    addNewClime(tittleClaim, dateClaim, timeClaim, descriptionClaim);

    scrollAndClickToClaimWithTittle(tittleClaim);
    cancelAddingNewCommentWhenPressedBack(commentClaim);
    onView(allOf(withId(R.id.comment_description_text_view))).check(doesNotExist());
  }
  @Test
  @DisplayName("Отказ добавления комментария к заявке незаполненном поле ввода")
  public void testRefusalAddingNewCommentToClaimWithEmptyField() throws InterruptedException {
    addNewClime(tittleClaim, dateClaim, timeClaim, descriptionClaim);
    scrollAndClickToClaimWithTittle(tittleClaim);
    addNewComment("");
    waitUntilVisible(DataHelper.checkToast(R.string.toast_empty_field, true));
    Espresso.pressBack();
  }
  @Test
  @DisplayName("Отказ добавления комментария к заявке при незаполнении поля ввода пробелами")
  public void testRefusalAddingNewCommentToClaimWithOnlySpacesInField() throws InterruptedException {
    addNewClime(tittleClaim, dateClaim, timeClaim, descriptionClaim);
    scrollAndClickToClaimWithTittle(tittleClaim);
    addNewComment("   ");
    waitUntilVisible(DataHelper.checkToast(R.string.toast_empty_field, true));
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

