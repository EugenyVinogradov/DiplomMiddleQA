package AndroidTest.steps;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.doesNotExist;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.not;
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
import static AndroidTest.data.DataHelper.waitElement;
import static AndroidTest.data.DataHelper.waitUntilVisible;
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
import static AndroidTest.pages.CommentPage.cancelEditCommentWhenPressedBack;
import static AndroidTest.pages.CommentPage.cancelEditCommentWhenPressedCancel;
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
import static AndroidTest.pages.NewClaimPage.errorWithAddingNewClaimWithoutSomeParameters;

import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;

import AndroidTest.data.DataHelper;
import io.qameta.allure.kotlin.Allure;
import io.qameta.allure.kotlin.Step;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.AppActivity;

public class ClaimsPageSteps {


  /* Шаги для страницы Заявок  */

  @Step("Производим фильтрацию заявок, не указав ни одного из статусов")
  public static void filterClimesWithoutStatus() {
    Allure.step("Производим фильтрацию заявок, не указав ни одного из статусов");
    filterClimes(false, false, false, false);
  }

  @Step("Производим фильтрацию заявок со статусом Открыта")
  public static void filterClimesWithStatusOpen() {
    Allure.step("Производим фильтрацию заявок со статусом Открыта");
    filterClimes(true, false, false, false);
  }

  @Step("Производим фильтрацию заявок со статусом Отменена")
  public static void filterClimesWithStatusCanceled() {
    Allure.step("Производим фильтрацию заявок со статусом Отменена");
    filterClimes(false, false, false, true);
  }

  @Step("Производим фильтрацию заявок со статусом В работе")
  public static void filterClimesWithStatusInProgress() {
    Allure.step("Производим фильтрацию заявок со статусом В работе");
    filterClimes(false, true, false, false);
  }

  @Step("Производим фильтрацию заявок со статусом Исполнена")
  public static void filterClimesWithStatusExecuted() {
    Allure.step("Производим фильтрацию заявок со статусом Исполнена");
    filterClimes(false, false, true, false);
  }

  @Step("Проверяем появление страницы с сообщением об отсутствии заявок")
  public static void messageThereIsNothingHereYet(ActivityScenarioRule<AppActivity> myActivityScenarioRule) {
    Allure.step("Проверяем появление страницы с сообщением об отсутствии заявок");
    checkFilteredClimes(myActivityScenarioRule, false, false, false, false);
  }

  @Step("Добавляем новую заявку")
  public static void addClaim() {
    Allure.step("Добавляем новую заявку");
    addNewClime(tittleClaim, dateClaim, timeClaim, descriptionClaim);
  }

  @Step("Добавляем новую заявку с 1 символом в поле Тема")
  public static void addClaimWithOneCharacterInFieldTittle() {
    Allure.step("Добавляем новую заявку с 1 символом в поле Тема");
    addNewClime(tittleClaimOneCharacter, dateClaim, timeClaim, descriptionClaim);
  }

  @Step("Добавляем новую заявку с 49 символами в поле Тема")
  public static void addClaimWith49CharactersInFieldTittle() {
    Allure.step("Добавляем новую заявку с 49 символами в поле Тема");
    addNewClime(tittleClaimFortyNineCharacter, dateClaim, timeClaim, descriptionClaim);
  }

  @Step("Добавляем новую заявку с 50 символами в поле Тема")
  public static void addClaimWith50CharactersInFieldTittle() {
    Allure.step("Добавляем новую заявку с 50 символами в поле Тема");
    addNewClime(tittleClaimFiftyCharacter, dateClaim, timeClaim, descriptionClaim);
  }

  @Step("Добавляем новую заявку с 51 символом в поле Тема")
  public static void addClaimWith51CharactersInFieldTittle() {
    Allure.step("Добавляем новую заявку с 51 символами в поле Тема");
    addNewClime(tittleClaimFiftyOneCharacter, dateClaim, timeClaim, descriptionClaim);
  }

  @Step("Пытаемся добавить новую заявку c незаполненным полем Тема")
  public static void addClaimWithEmptyFieldTittle() {
    Allure.step("Пытаемся добавить новую заявку с незаполненным полем Тема");
    addNewClimeWithoutSomething("", dateClaim, timeClaim, descriptionClaim);
  }

  @Step("Пытаемся добавить новую заявку с полем Тема, заполненным только пробелами")
  public static void addClaimWitOnlySpacesInFieldTittle() {
    Allure.step("Пытаемся добавить новую заявку с полем Тема, заполненным только пробелами");
    addNewClimeWithoutSomething("   ", dateClaim, timeClaim, descriptionClaim);
  }

  @Step("Пытаемся добавить новую заявку незаполненным полем Дата")
  public static void addClaimWithEmptyFieldDate() {
    Allure.step("Пытаемся добавить новую заявку с незаполненным полем Дата");
    addNewClimeWithoutSomething(tittleClaim, "", timeClaim, descriptionClaim);
  }

  @Step("Пытаемся добавить новую заявку незаполненным полем Время")
  public static void addClaimWithEmptyFieldTime() {
    Allure.step("Пытаемся добавить новую заявку с незаполненным полем Время");
    addNewClimeWithoutSomething(tittleClaim, dateClaim, "", descriptionClaim);
  }

  @Step("Пытаемся добавить новую заявку незаполненным полем Описание")
  public static void addClaimWithEmptyFieldDescription() {
    Allure.step("Пытаемся добавить новую заявку с незаполненным полем Описание");
    addNewClimeWithoutSomething(tittleClaim, dateClaim, timeClaim, "");
  }

  @Step("Пытаемся добавить новую заявку с полем Описание, заполненным только пробелами")
  public static void addClaimWithOnlySpacesInFieldDescription() {
    Allure.step("Пытаемся добавить новую заявку с полем Описание, заполненным только пробелами");
    addNewClimeWithoutSomething(tittleClaim, dateClaim, timeClaim, "    ");
  }

  @Step("Прокручиваем к заявке с заданным заголовком и кликаем на нее")
  public static void scrollToClaimWithTittleAndClick() {
    Allure.step("Прокручиваем к заявке с заданным заголовком и кликаем на нее");
    scrollAndClickToClaimWithTittle(tittleClaim);
  }

  @Step("Прокручиваем к заявке с заголовком, состоящем из 1 символа, и кликаем на нее")
  public static void scrollToClaimWithOneCharacterTittleAndClick() {
    Allure.step("Прокручиваем к заявке c заголовком, состоящем из 1 символа, и кликаем на нее");
    scrollAndClickToClaimWithTittle(tittleClaimOneCharacter);
  }

  @Step("Прокручиваем к заявке с заголовком, состоящем из 49 символов, и кликаем на нее")
  public static void scrollToClaimWith49CharacterTittleAndClick() {
    Allure.step("Прокручиваем к заявке c заголовком, состоящем из 49 символов, и кликаем на нее");
    scrollAndClickToClaimWithTittle(tittleClaimFortyNineCharacter);
  }

  @Step("Прокручиваем к заявке с заголовком, состоящем из 50 символов, и кликаем на нее")
  public static void scrollToClaimWith50CharacterTittleAndClick() {
    Allure.step("Прокручиваем к заявке c заголовком, состоящем из 50 символов, и кликаем на нее");
    scrollAndClickToClaimWithTittle(tittleClaimFiftyCharacter);
  }

  @Step("Прокручиваем к заявке с заголовком, обрезанном до 50 символов и кликаем на нее")
  public static void scrollToClaimWithTrimmedTittleAndClick(String tittle) {
    Allure.step("Прокручиваем к заявке c заголовком, состоящем из 50 символов, и кликаем на нее");
    scrollAndClickToClaimWithTittle(tittle);
  }

  @Step("Проверяем, что Тема заявки обрезана до 50 символов")
  public static void checkThatTittleTrimmedTo50Characters(String expected, String actual) {
    Allure.step("Проверяем, что Тема заявки обрезана до 50 символов");
    Assert.assertEquals(expected, actual);
  }

  @Step("Проверяем, что при фильтации заявок со статусом Открыта, отобразилась созданная заявка")
  public static void checkClaimWithStatusOpen() {
    Allure.step("Проверяем, что при фильтации заявок со статусом Открыта, отобразилась созданная заявка");
    onView(withId(R.id.status_label_text_view)).
        check(matches(CoreMatchers.allOf(isDisplayed(), withText(R.string.status_open))));
  }

  @Step("Проверяем, что при фильтации заявок со статусом В работе, отобразилась созданная заявка")
  public static void checkClaimWithStatusInProgress() {
    Allure.step("Проверяем, что при фильтации заявок со статусом В работе, отобразилась созданная заявка");
    onView(withId(R.id.status_label_text_view))
        .check(matches(CoreMatchers.allOf(isDisplayed(), withText(R.string.in_progress))));
  }

  @Step("Проверяем, что при фильтации заявок со статусом Отменена, отобразилась созданная заявка")
  public static void checkClaimWithStatusCanceled() {
    Allure.step("Проверяем, что при фильтации заявок со статусом Отменена, отобразилась созданная заявка");
    onView(withId(R.id.status_label_text_view)).
        check(matches(CoreMatchers.allOf(isDisplayed(), withText(R.string.status_claim_canceled))));
  }

  @Step("Проверяем, что при фильтации заявок со статусом Исполнена, отобразилась созданная заявка")
  public static void checkClaimWithStatusExecuted() {
    Allure.step("Проверяем, что при фильтации заявок со статусом Исполнена, отобразилась созданная заявка");
    onView(withId(R.id.status_label_text_view))
        .check(matches(CoreMatchers.allOf(isDisplayed(), withText(R.string.executed))));
  }

  @Step("Проверяем сообщение о недопустимости пустых полей при создании новой заявки")
  public static void checkErrorMessageBySomeParameterEmpty() {
    Allure.step("Проверяем сообщение о недопустимости пустых полей при создании новой заявки");
    errorWithAddingNewClaimWithoutSomeParameters();
  }

  @Step("Заполняем поля при создании заявки и нажимаем Отмена")
  public static void fillingFieldsNewClimeAndPressCancel() {
    Allure.step("Заполняем поля при создании заявки и нажимаем Отмена");
    cancelAddingNewClimeWhenPressedCancel(tittleClaim, dateClaim, timeClaim, descriptionClaim);
  }

  @Step("Проверяем, что при в списке не появилось новой заявки ")
  public static void checkNotExistNewClaim() {
    Allure.step("Проверяем, что при в списке не появилось новой заявки");
    onView(withId(R.id.claim_list_recycler_view))
        .check(matches(not(hasDescendant(withText(tittleClaim)))));
  }

  @Step("Заполняем поля при создании заявки и нажимаем Назад")
  public static void fillingFieldsNewClimeAndPressBack() {
    Allure.step("Заполняем поля при создании заявки и нажимаем Назад");
    cancelAddingNewClimeWhenPressedBack(tittleClaim, dateClaim, timeClaim, descriptionClaim);
  }

  @Step("Меняем статус заявки с Открыта на В работе")
  public static void changeStatusToInProgressStep() {
    Allure.step("Меняем статус заявки с Открыта на В работе");
    changeStatusToInProgress();
    waitElement(R.id.claim_list_recycler_view);
  }

  @Step("Меняем статус заявки с Открыта на Отменена")
  public static void changeStatusToInCanceledStep() {
    Allure.step("Меняем статус заявки с Открыта на Отменена");
    changeStatusToInCanceled();
  }

  @Step("Меняем статус заявки с В работе на Исполнена")
  public static void changeStatusToInExecutedStep() {
    Allure.step("Меняем статус заявки с В работе на Исполнена");
    changeStatusToInExecuted();
  }

  @Step("Добавляем новый комментарий к заявке")
  public static void addNewCommentToClaim() {
    Allure.step("Добавляем новый комментарий к заявке");
    addNewComment(commentClaim);
  }

  @Step("Редактируем существующий комментарий к заявке")
  public static void editExistingCommentToClaim() {
    Allure.step("Редактируем существующий комментарий к заявке");
    editComment(commentClaimEditind);
  }

  @Step("Заполняем поле нового комментария к заявке и нажимаем Отмена")
  public static void cancelAddingNewCommentToClaimWithPressCancel() {
    Allure.step("Заполняем поле нового комментария к заявке и нажимаем Отмена");
    cancelAddingNewCommentWhenPressedCancel(commentClaim);
  }

  @Step("Редактируем существующий комментарий к заявке и нажимаем Отмена")
  public static void cancelEditingExistingCommentToClaimWithPressCancel() {
    Allure.step("Редактируем существующий комментарий к заявке и нажимаем Отмена");
    cancelEditCommentWhenPressedCancel(commentClaimEditind);
  }

  @Step("Заполняем поле нового комментария к заявке и нажимаем Назад")
  public static void cancelAddingNewCommentToClaimWithPressBack() {
    Allure.step("Заполняем поле нового комментария к заявке и нажимаем Назад");
    cancelAddingNewCommentWhenPressedBack(commentClaim);
  }

  @Step("Редактируем существующий комментарий к заявке и нажимаем Назад")
  public static void cancelEditingExistingCommentToClaimWithPressBack() {
    Allure.step("Редактируем существующий комментарий к заявке и нажимаем Назад");
    cancelEditCommentWhenPressedBack(commentClaimEditind);
  }

  @Step("Редактируем Дату у существующей заявки")
  public static void editDateClaim() {
    Allure.step("Редактируем Дату у существующей заявки");
    editingDateClaim(newDateClaim);
  }

  @Step("Редактируем Тему у существующей заявки")
  public static void editTittleClaim() {
    Allure.step("Редактируем Тему у существующей заявки");
    editingTittleClaim(tittleClaim + "123");
  }

  @Step("Редактируем Время у существующей заявки")
  public static void editTimeClaim() {
    Allure.step("Редактируем Время у существующей заявки");
    editingTimeClaim(newTimeClaim);
  }

  @Step("Редактируем Описание у существующей заявки")
  public static void editDescriptionClaim() {
    Allure.step("Редактируем Описание у существующей заявки");
    editingDescriptionClaim(newDescriptionClaim);
  }

  @Step("Редактируем все атрибуты существующей заявки и нажимаем Назад")
  public static void cancelEditingFieldsExistingClaimWithPressBack() {
    Allure.step("Редактируем все атрибуты существующей заявки и нажимаем Назад");
    fillingFieldsWhenEditingClaimAndPressBack(newTittleClaim, newDateClaim, newTimeClaim, newDescriptionClaim);
  }

  @Step("Редактируем все атрибуты существующей заявки и нажимаем Отмена")
  public static void cancelEditingFieldsExistingClaimWithPressCancel() {
    Allure.step("Редактируем все атрибуты существующей заявки и нажимаем Отмена");
    fillingFieldsWhenEditingClaimAndPressCancel(newTittleClaim, newDateClaim, newTimeClaim, newDescriptionClaim);
  }

  @Step("Пытаемся создать новый комментарий к заявке при пустом поле ввода")
  public static void refusalAddingNewCommentToClaimWithEmptyField() {
    Allure.step("Пытаемся создать новый комментарий к заявке при пустом поле ввода");
    addNewComment("");
  }

  @Step("Пытаемся создать новый комментарий к заявке при заполнении поля ввода только пробелами")
  public static void refusalAddingNewCommentToClaimWithOnlySpacesInField() {
    Allure.step("Пытаемся создать новый комментарий к заявке при заполнении поля ввода только пробелами");
    addNewComment("   ");
  }

  @Step("Проверяем, что в заявке появился новый комментарий")
  public static void checkAddingNewComment() {
    Allure.step("Проверяем, что в заявке появился новый комментарий");
    onView(withText(commentClaim)).check(matches(isDisplayed()));
  }

  @Step("Проверяем, что в заявке не появился новый комментарий")
  public static void checkNotAddingNewComment() {
    Allure.step("Проверяем, что в заявке не появился новый комментарий");
    onView(CoreMatchers.allOf(withId(R.id.comment_description_text_view))).check(doesNotExist());
  }

  @Step("Проверяем, что в заявке изменился комментарий")
  public static void checkEditingExistingComment() {
    Allure.step("Проверяем, что в заявке изменился комментарий");
    onView(withText(commentClaimEditind)).check(matches(isDisplayed()));
  }

  @Step("Проверяем, что при в заявке не изменился комментарий")
  public static void checkNotEditingExistingComment() {
    Allure.step("Проверяем, что в заявке не изменился комментарий");
    onView(withText(commentClaim)).check(matches(isDisplayed()));
  }

  @Step("Проверяем, что в заявке изменилась Тема")
  public static void checkEditingTittleClaim() {
    Allure.step("Проверяем, что в заявке изменилась Тема");
    onView(withText(tittleClaim + "123")).check(matches(isDisplayed()));
  }

  @Step("Проверяем, что в заявке изменилась Дата")
  public static void checkEditingDateClaim() {
    Allure.step("Проверяем, что в заявке изменилась Дата");
    onView(withText(newDateClaim)).check(matches(isDisplayed()));
  }

  @Step("Проверяем, что в заявке изменилось Время")
  public static void checkEditingTimeClaim() {
    Allure.step("Проверяем, что в заявке изменилось Время");
    onView(withText(newTimeClaim)).check(matches(isDisplayed()));
  }

  @Step("Проверяем, что в заявке изменилось Описание")
  public static void checkEditingDescriptionClaim() {
    Allure.step("Проверяем, что в заявке изменилось Описание");
    onView(withText(newDescriptionClaim)).check(matches(isDisplayed()));
  }

  @Step("Проверяем, что атрибуты заявки не изменились")
  public static void checkNotChangingAttributeClaim() {
    Allure.step("Проверяем, что атрибуты заявки не изменились");
    onView(withText(tittleClaim)).check(matches(isDisplayed()));
    onView(withText(dateClaim)).check(matches(isDisplayed()));
    onView(withText(timeClaim)).check(matches(isDisplayed()));
    onView(withText(descriptionClaim)).check(matches(isDisplayed()));
  }

  @Step("Проверяем всплывающее сообщение о недопустимости пустого поля ввода")
  public static void checkToastByEmptyField() {
    Allure.step("Проверяем всплывающее сообщение о недопустимости пустого поля ввода");
    waitUntilVisible(DataHelper.checkToast(R.string.toast_empty_field, true));
  }

  @Step("Нажимаем кнопку Закрыть")
  public static void pressCloseButton() {
    Allure.step("Нажимаем кнопку Закрыть");
    closeButton.perform(click());
  }

}
