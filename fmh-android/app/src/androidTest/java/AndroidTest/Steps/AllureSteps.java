package AndroidTest.Steps;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.doesNotExist;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.Matchers.allOf;
import static AndroidTest.data.Data.commentClaim;
import static AndroidTest.data.Data.dateClaim;
import static AndroidTest.data.Data.descriptionClaim;
import static AndroidTest.data.Data.timeClaim;
import static AndroidTest.data.Data.tittleClaim;
import static AndroidTest.data.Data.tittleClaimFiftyCharacter;
import static AndroidTest.data.Data.tittleClaimFiftyOneCharacter;
import static AndroidTest.data.Data.tittleClaimFortyNineCharacter;
import static AndroidTest.data.Data.tittleClaimOneCharacter;
import static AndroidTest.data.Data.urlPrivacyPolicy;
import static AndroidTest.data.Data.urlTermsOfUse;
import static AndroidTest.data.DataHelper.waitElement;
import static AndroidTest.data.DataHelper.waitUntilVisible;
import static AndroidTest.pages.AboutPage.aboutInfo;
import static AndroidTest.pages.AboutPage.versionInfo;
import static AndroidTest.pages.AboutPage.versionText;
import static AndroidTest.pages.AboutPage.webPageExistence;
import static AndroidTest.pages.AuthPage.loginButton;
import static AndroidTest.pages.AuthPage.loginField;
import static AndroidTest.pages.AuthPage.passwordField;
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
import static AndroidTest.pages.EditingClaim.changeStatusToInProgress;
import static AndroidTest.pages.MainPage.logOutButton;
import static AndroidTest.pages.NewClaimPage.errorWithAddingNewClaimWithoutSomeParameters;

import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.hamcrest.CoreMatchers;
import org.hamcrest.Matchers;
import org.junit.Assert;

import AndroidTest.data.DataHelper;
import AndroidTest.pages.AuthPage;
import AndroidTest.pages.EditingClaim;
import io.qameta.allure.kotlin.Step;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.AppActivity;

public class AllureSteps {


  /* Шаги для страницы авторизации */

  @Step("Ввести логин и пароль")
  public static void login(String login, String password) {
    waitElement(AuthPage.idSignInButton);
    loginField.perform(replaceText(login));
    passwordField.perform(replaceText(password));
    loginButton.check(matches(isDisplayed())).perform(click());
  }

  @Step("Проверяем открытие главной страницы - видимость кнопки LogOut")
  public static void logOutIsVisible() {
    logOutButton.check(matches(isDisplayed()));
  }

  @Step("Проверяем сообщение о недопустимости незаполненных логина или пароля")
  public static void loginOrPasswordDoesntBeEmpty() {
    waitUntilVisible(DataHelper.checkToast(R.string.empty_login_or_password, true));
  }

  @Step("Проверяем сообщение о неверном логине или пароле")
  public static void loginOrPasswordIsWrong() {
    waitUntilVisible(DataHelper.checkToast(R.string.wrong_login_or_password, true));
  }

  /* Шаги для страницы О нас */

  @Step("Проверяем наличие сведений о версии приложения")
  public static void isAppVersionDisplayed() {
    versionText.check(matches(allOf(isDisplayed(), withText(Matchers.not("")))));
    versionInfo.check(matches(allOf(isDisplayed(), withText(Matchers.not("")))));
  }
  @Step("Проверяем наличие сведений о разработчике приложения")
  public static void isAppDeveloperDisplayed() {
    aboutInfo.check(matches(allOf(isDisplayed(), withText(Matchers.not("")))));
  }
  @Step("Проверяем наличие страницы с политикой конфиденциальности")
  public static void isWebPagePrivacyPolicyExistence() {
    webPageExistence(urlPrivacyPolicy);
  }
  @Step("Проверяем наличие страницы с условиями использования")
  public static void isWebPageTermsOfUseExistence() {
    webPageExistence(urlTermsOfUse);
  }


  /* Шаги для страницы Заявок  */

  @Step("Производим фильтрацию заявок, не указав ни одного из статусов")
  public static void filterClimesWithoutStatus() {
    filterClimes(false, false, false, false);
  }
  @Step("Производим фильтрацию заявок со статусом Открыта")
  public static void filterClimesWithStatusOpen() {
    filterClimes(true, false, false, false);
  }
  @Step("Производим фильтрацию заявок со статусом Отменена")
  public static void filterClimesWithStatusCanceled() {
    filterClimes(false, false, false, true);
  }
  @Step("Производим фильтрацию заявок со статусом В работе")
  public static void filterClimesWithStatusInProgress() {
    filterClimes(false, true, false, false);
  }
  @Step("Производим фильтрацию заявок со статусом Исполнена")
  public static void filterClimesWithStatusExecuted() {
    filterClimes(false, false, true, false);
  }
  @Step("Проверяем появление страницы с сообщением об отсутствии заявок")
  public static void messageThereIsNothingHereYet(ActivityScenarioRule<AppActivity> myActivityScenarioRule) throws InterruptedException {
    checkFilteredClimes(myActivityScenarioRule, false, false, false, false);
  }
  @Step("Добавляем новую заявку")
  public static void addClaim() {
    addNewClime(tittleClaim, dateClaim, timeClaim, descriptionClaim);
  }
  @Step("Добавляем новую заявку с 1 символом в поле Тема")
  public static void addClaimWithOneCharacterInFieldTittle() {
    addNewClime(tittleClaimOneCharacter, dateClaim, timeClaim, descriptionClaim);
  }
  @Step("Добавляем новую заявку с 49 символами в поле Тема")
  public static void addClaimWith49CharactersInFieldTittle() {
    addNewClime(tittleClaimFortyNineCharacter, dateClaim, timeClaim, descriptionClaim);
  }
  @Step("Добавляем новую заявку с 50 символами в поле Тема")
  public static void addClaimWith50CharactersInFieldTittle() {
    addNewClime(tittleClaimFiftyCharacter, dateClaim, timeClaim, descriptionClaim);
  }
  @Step("Добавляем новую заявку с 51 символом в поле Тема")
  public static void addClaimWith51CharactersInFieldTittle() {
    addNewClime(tittleClaimFiftyOneCharacter, dateClaim, timeClaim, descriptionClaim);
  }
  @Step("Пытаемся добавить новую заявку незаполненным полем Тема")
  public static void addClaimWithEmptyFieldTittle() {
    addNewClimeWithoutSomething("", dateClaim, timeClaim, descriptionClaim);
  }
  @Step("Пытаемся добавить новую заявку с полем Тема, заполненным только пробелами")
  public static void addClaimWitOnlySpacesInFieldTittle() {
    addNewClimeWithoutSomething("   ", dateClaim, timeClaim, descriptionClaim);
  }
  @Step("Пытаемся добавить новую заявку незаполненным полем Дата")
  public static void addClaimWithEmptyFieldDate() {
    addNewClimeWithoutSomething(tittleClaim, "", timeClaim, descriptionClaim);
  }
  @Step("Пытаемся добавить новую заявку незаполненным полем Время")
  public static void addClaimWithEmptyFieldTime() {
    addNewClimeWithoutSomething(tittleClaim, dateClaim, "", descriptionClaim);
  }
  @Step("Пытаемся добавить новую заявку незаполненным полем Описание")
  public static void addClaimWithEmptyFieldDescription() {
    addNewClimeWithoutSomething(tittleClaim, dateClaim, timeClaim, "");
  }
  @Step("Пытаемся добавить новую заявку с полем Описание, заполненным только пробелами")
  public static void addClaimWithOnlySpacesInFieldDescription() {
    addNewClimeWithoutSomething(tittleClaim, dateClaim, timeClaim, "    ");
  }
  @Step("Прокручиваем к заявке с заданным заголовком и кликаем на нее")
  public static void scrollToClaimWithTittleAndClick() throws InterruptedException {
    scrollAndClickToClaimWithTittle(tittleClaim);
  }
  @Step("Прокручиваем к заявке с заголовком, состоящем из 1 символа, и кликаем на нее")
  public static void scrollToClaimWithOneCharacterTittleAndClick() throws InterruptedException {
    scrollAndClickToClaimWithTittle(tittleClaimOneCharacter);
  }
  @Step("Прокручиваем к заявке с заголовком, состоящем из 49 символов, и кликаем на нее")
  public static void scrollToClaimWith49CharacterTittleAndClick() throws InterruptedException {
    scrollAndClickToClaimWithTittle(tittleClaimFortyNineCharacter);
  }
  @Step("Прокручиваем к заявке с заголовком, состоящем из 50 символов, и кликаем на нее")
  public static void scrollToClaimWith50CharacterTittleAndClick() throws InterruptedException {
    scrollAndClickToClaimWithTittle(tittleClaimFiftyCharacter);
  }
  @Step("Прокручиваем к заявке с заголовком, обрезанном до 50 символов и кликаем на нее")
  public static void scrollToClaimWithTrimmedTittleAndClick(String tittle) throws InterruptedException {
    scrollAndClickToClaimWithTittle(tittle);
  }
  @Step("Проверяем, что Тема заявки обрезана до 50 символов")
  public static void checkThatTittleTrimmedTo50Characters(String expected, String actual) {
    Assert.assertEquals(expected, actual);
  }
  @Step("Проверяем, что при фильтации заявок со статусом Открыта, отобразилась созданная заявка")
  public static void checkClaimWithStatusOpen() {
    onView(withId(R.id.status_label_text_view)).
        check(matches(CoreMatchers.allOf(isDisplayed(), withText(R.string.status_open))));
  }
  @Step("Проверяем, что при фильтации заявок со статусом В работе, отобразилась созданная заявка")
  public static void checkClaimWithStatusInProgress() {
    onView(withId(R.id.status_label_text_view))
        .check(matches(CoreMatchers.allOf(isDisplayed(), withText(R.string.in_progress))));
  }
  @Step("Проверяем, что при фильтации заявок со статусом Отменена, отобразилась созданная заявка")
  public static void checkClaimWithStatusCanceled() {
    onView(withId(R.id.status_label_text_view)).
        check(matches(CoreMatchers.allOf(isDisplayed(), withText(R.string.status_claim_canceled))));
  }
  @Step("Проверяем, что при фильтации заявок со статусом Исполнена, отобразилась созданная заявка")
  public static void checkClaimWithStatusExecuted() {
    onView(withId(R.id.status_label_text_view))
        .check(matches(CoreMatchers.allOf(isDisplayed(), withText(R.string.executed))));
  }
  @Step("Проверяем сообщение о недопустимости пустых полей при создании новой заявки")
  public static void checkErrorMessageBySomeParameterEmpty() {
    errorWithAddingNewClaimWithoutSomeParameters();
  }
  @Step("Заполняем поля при создании заявки и нажимаем Отмена")
  public static void fillingFieldsNewClimeAndPressCancel() {
    cancelAddingNewClimeWhenPressedCancel(tittleClaim, dateClaim, timeClaim, descriptionClaim);
  }
  @Step("Проверяем, что при в списке не появилось новой заявки ")
  public static void checkNotExistNewClaim() {
    onView(withId(R.id.claim_list_recycler_view))
        .check(matches(not(hasDescendant(withText(tittleClaim)))));
  }
  @Step("Заполняем поля при создании заявки и нажимаем Назад")
  public static void fillingFieldsNewClimeAndPressBack() {
    cancelAddingNewClimeWhenPressedBack(tittleClaim, dateClaim, timeClaim, descriptionClaim);
  }
  @Step("Меняем статус заявки с Открыта на В работе")
  public static void changeStatusToInProgress() {
    changeStatusToInProgress();
  }
  @Step("Меняем статус заявки с Открыта на Отменена")
  public static void changeStatusToInCanceled() {
    changeStatusToInProgress();
  }
  @Step("Меняем статус заявки с В работе на Исполнена")
  public static void changeStatusToInExecuted() {
    changeStatusToInExecuted();
  }
  @Step("Добавляем новый комментарий к заявке")
  public static void addNewCommentToClaim() {
    addNewComment(commentClaim);
  }
  @Step("Заполняем поле нового комментария к заявке и нажимаем Отмена")
  public static void cancelAddingNewCommentToClaimWithPressCancel() {
    cancelAddingNewCommentWhenPressedCancel(commentClaim);
  }
  @Step("Заполняем поле нового комментария к заявке и нажимаем Назад")
  public static void cancelAddingNewCommentToClaimWithPressBack() {
    cancelAddingNewCommentWhenPressedBack(commentClaim);
  }
  @Step("Пытаемся создать новый комментарий к заявке при пустом поле ввода")
  public static void refusalAddingNewCommentToClaimWithEmptyField() {
    addNewComment("");
  }
  @Step("Пытаемся создать новый комментарий к заявке при заполнении поля ввода только пробелами")
  public static void refusalAddingNewCommentToClaimWithOnlySpacesInField() {
    addNewComment("   ");
  }
  @Step("Проверяем, что при в заявке появился новый комментарий")
  public static void checkAddingNewComment() {
    onView(withText(commentClaim)).check(matches(isDisplayed()));
  }
  @Step("Проверяем, что при в заявке не появился новый комментарий")
  public static void checkNotAddingNewComment() {
    onView(CoreMatchers.allOf(withId(R.id.comment_description_text_view))).check(doesNotExist());
  }
  @Step("Проверяем всплывающее сообщение о недопустимости пустого поля ввода")
  public static void checkToastByEmptyField() {
    waitUntilVisible(DataHelper.checkToast(R.string.toast_empty_field, true));
  }
}
