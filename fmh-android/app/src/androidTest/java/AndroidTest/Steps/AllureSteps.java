package AndroidTest.Steps;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.doubleClick;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.doesNotExist;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.contrib.RecyclerViewActions.scrollToPosition;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.Matchers.allOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static AndroidTest.data.Data.categoryForth;
import static AndroidTest.data.Data.commentClaim;
import static AndroidTest.data.Data.commentClaimEditind;
import static AndroidTest.data.Data.dateClaim;
import static AndroidTest.data.Data.dateNews;
import static AndroidTest.data.Data.dateNewsNextDay;
import static AndroidTest.data.Data.descriptionClaim;
import static AndroidTest.data.Data.descriptionNews;
import static AndroidTest.data.Data.newDateClaim;
import static AndroidTest.data.Data.newDescriptionClaim;
import static AndroidTest.data.Data.newDescriptionNews;
import static AndroidTest.data.Data.newTimeClaim;
import static AndroidTest.data.Data.newTimeNews;
import static AndroidTest.data.Data.newTittleClaim;
import static AndroidTest.data.Data.newTittleNews;
import static AndroidTest.data.Data.statusActive;
import static AndroidTest.data.Data.statusNotActive;
import static AndroidTest.data.Data.timeClaim;
import static AndroidTest.data.Data.timeNews;
import static AndroidTest.data.Data.tittleClaim;
import static AndroidTest.data.Data.tittleClaimFiftyCharacter;
import static AndroidTest.data.Data.tittleClaimFiftyOneCharacter;
import static AndroidTest.data.Data.tittleClaimFortyNineCharacter;
import static AndroidTest.data.Data.tittleClaimOneCharacter;
import static AndroidTest.data.Data.tittleNews;
import static AndroidTest.data.Data.urlPrivacyPolicy;
import static AndroidTest.data.Data.urlTermsOfUse;
import static AndroidTest.data.DataHelper.getRecyclerViewItemCount;
import static AndroidTest.data.DataHelper.getTextFromNews;
import static AndroidTest.data.DataHelper.waitElement;
import static AndroidTest.data.DataHelper.waitUntilVisible;
import static AndroidTest.pages.AboutPage.aboutInfo;
import static AndroidTest.pages.AboutPage.versionInfo;
import static AndroidTest.pages.AboutPage.versionText;
import static AndroidTest.pages.AboutPage.webPageExistence;
import static AndroidTest.pages.AddingNewsPage.addNews;
import static AndroidTest.pages.AddingNewsPage.cancelButton;
import static AndroidTest.pages.AddingNewsPage.cancelMessage;
import static AndroidTest.pages.AddingNewsPage.confirmCancelAddingNewsButton;
import static AndroidTest.pages.AddingNewsPage.fillingNewsFields;
import static AndroidTest.pages.AuthPage.loginButton;
import static AndroidTest.pages.AuthPage.loginField;
import static AndroidTest.pages.AuthPage.passwordField;
import static AndroidTest.pages.AuthPage.successLogin;
import static AndroidTest.pages.ClimesPage.addNewClime;
import static AndroidTest.pages.ClimesPage.addNewClimeWithoutSomething;
import static AndroidTest.pages.ClimesPage.cancelAddingNewClimeWhenPressedBack;
import static AndroidTest.pages.ClimesPage.cancelAddingNewClimeWhenPressedCancel;
import static AndroidTest.pages.ClimesPage.checkFilteredClimes;
import static AndroidTest.pages.ClimesPage.filterClimes;
import static AndroidTest.pages.ClimesPage.filterClimesButton;
import static AndroidTest.pages.ClimesPage.isClaimExistWithParams;
import static AndroidTest.pages.ClimesPage.scrollAndClickToClaimWithTittle;
import static AndroidTest.pages.CommentPage.addNewComment;
import static AndroidTest.pages.CommentPage.cancelAddingNewCommentWhenPressedBack;
import static AndroidTest.pages.CommentPage.cancelAddingNewCommentWhenPressedCancel;
import static AndroidTest.pages.CommentPage.cancelEditCommentWhenPressedBack;
import static AndroidTest.pages.CommentPage.cancelEditCommentWhenPressedCancel;
import static AndroidTest.pages.CommentPage.editComment;
import static AndroidTest.pages.EditingClaim.editingDateClaim;
import static AndroidTest.pages.EditingClaim.editingDescriptionClaim;
import static AndroidTest.pages.EditingClaim.editingTimeClaim;
import static AndroidTest.pages.EditingClaim.editingTittleClaim;
import static AndroidTest.pages.EditingClaim.fillingFieldsWhenEditingClaimAndPressBack;
import static AndroidTest.pages.EditingClaim.fillingFieldsWhenEditingClaimAndPressCancel;
import static AndroidTest.pages.EditingNews.changeNewsAttribute;
import static AndroidTest.pages.MainPage.allClimesButton;
import static AndroidTest.pages.MainPage.goToAboutPage;
import static AndroidTest.pages.MainPage.goToClaimesPage;
import static AndroidTest.pages.MainPage.goToClaimesPageByNavigationMenu;
import static AndroidTest.pages.MainPage.goToNewsPage;
import static AndroidTest.pages.MainPage.goToNewsPageByNavigationMenu;
import static AndroidTest.pages.MainPage.goToQuotesPage;
import static AndroidTest.pages.MainPage.logOut;
import static AndroidTest.pages.MainPage.logOutButton;
import static AndroidTest.pages.NewClaimPage.errorAddingMessageId;
import static AndroidTest.pages.NewClaimPage.errorWithAddingNewClaimWithoutSomeParameters;
import static AndroidTest.pages.NewsEditingPage.changeNewsStatus;
import static AndroidTest.pages.NewsEditingPage.deleteNews;
import static AndroidTest.pages.NewsEditingPage.editNews;
import static AndroidTest.pages.NewsEditingPage.scrollAndClickToNewsWithTittle;
import static AndroidTest.pages.NewsEditingPage.scrollNews;
import static AndroidTest.pages.NewsPage.editNewsButton;
import static AndroidTest.pages.NewsPage.filterNewsByDate;
import static AndroidTest.pages.NewsPage.filterNewsByStatus;
import static AndroidTest.pages.NewsPage.filterNewsByStatusAndDate;
import static AndroidTest.pages.NewsPage.goToNewsEditingPage;
import static AndroidTest.pages.NewsPage.scrollNewsToPosition;
import static AndroidTest.pages.NewsPage.sortingNews;
import static AndroidTest.pages.QuotesPage.header;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.ViewInteraction;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.hamcrest.CoreMatchers;
import org.hamcrest.Matchers;
import org.junit.Assert;

import AndroidTest.data.DataHelper;
import AndroidTest.pages.AuthPage;


import io.qameta.allure.kotlin.Allure;
import io.qameta.allure.kotlin.Step;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.AppActivity;

public class AllureSteps {

  /* Общие шаги */

  @Step("Логинимся в валидными логином и паролем")
  public static void successLoginStep() {
    Allure.step("Логинимся в валидными логином и паролем");
    successLogin();
  }

  @Step("Нажимаем системную кнопку Назад")
  public static void pressBack() {
    Allure.step("Нажимаем системную кнопку Назад");
    Espresso.pressBack();
  }

  @Step("Переходим в раздел редактирования новостей")
  public static void goToNewsEditingPageStep() {
    Allure.step("Переходим в раздел редактирования новостей");
    goToNewsEditingPage();
  }

  @Step("Переходим в раздел Новости")
  public static void goToNewsPageStep() {
    Allure.step("Переходим в раздел Новости");
    goToNewsPageByNavigationMenu();
  }

  @Step("Переходим в раздел О приложении")
  public static void goToAboutPageStep() {
    Allure.step("Переходим в раздел О приложении");
    goToAboutPage();
  }

  @Step("Переходим в раздел Цитаты")
  public static void goToQuotesPageStep() {
    Allure.step("Переходим в раздел Цитаты");
    goToQuotesPage();
  }


  @Step("Разлогиниваемся из приложения")
  public static void logOutFromApp() {
    Allure.step("Разлогиниваемся из приложения");
    logOut();
  }

  /* Шаги для страницы авторизации */

  @Step("Ввести логин и пароль")
  public static void login(String login, String password) {
    Allure.step("Вводим логин " + login + " и пароль " + password);
    waitElement(AuthPage.idSignInButton);
    loginField.perform(replaceText(login));
    passwordField.perform(replaceText(password));
    loginButton.check(matches(isDisplayed())).perform(click());
  }

  @Step("Проверяем открытие главной страницы - видимость кнопки LogOut")
  public static void logOutIsVisible() {
    Allure.step("Проверяем открытие главной страницы - видимость кнопки LogOut");
    logOutButton.check(matches(isDisplayed()));
  }

  @Step("Проверяем сообщение о недопустимости незаполненных логина или пароля")
  public static void loginOrPasswordDoesntBeEmpty() {
    Allure.step("Проверяем сообщение о недопустимости незаполненных логина или пароля");
    waitUntilVisible(DataHelper.checkToast(R.string.empty_login_or_password, true));
  }

  @Step("Проверяем сообщение о неверном логине или пароле")
  public static void loginOrPasswordIsWrong() {
    Allure.step("Проверяем сообщение о неверном логине или пароле");
    waitUntilVisible(DataHelper.checkToast(R.string.wrong_login_or_password, true));
  }

  /* Шаги для страницы О нас */

  @Step("Проверяем наличие сведений о версии приложения")
  public static void isAppVersionDisplayed() {
    Allure.step("Проверяем наличие сведений о версии приложения");
    versionText.check(matches(allOf(isDisplayed(), withText(Matchers.not("")))));
    versionInfo.check(matches(allOf(isDisplayed(), withText(Matchers.not("")))));
  }

  @Step("Проверяем наличие сведений о разработчике приложения")
  public static void isAppDeveloperDisplayed() {
    Allure.step("Проверяем наличие сведений о разработчике приложения");
    aboutInfo.check(matches(allOf(isDisplayed(), withText(Matchers.not("")))));
  }

  @Step("Проверяем наличие страницы с политикой конфиденциальности")
  public static void isWebPagePrivacyPolicyExistence() {
    Allure.step("Проверяем наличие страницы с политикой конфиденциальности");
    webPageExistence(urlPrivacyPolicy);
  }

  @Step("Проверяем наличие страницы с условиями использования")
  public static void isWebPageTermsOfUseExistence() {
    Allure.step("Проверяем наличие страницы с условиями использования");
    webPageExistence(urlTermsOfUse);
  }


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
  public static void messageThereIsNothingHereYet(ActivityScenarioRule<AppActivity> myActivityScenarioRule) throws InterruptedException {
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
  public static void scrollToClaimWithTittleAndClick() throws InterruptedException {
    Allure.step("Прокручиваем к заявке с заданным заголовком и кликаем на нее");
    scrollAndClickToClaimWithTittle(tittleClaim);
  }

  @Step("Прокручиваем к заявке с заголовком, состоящем из 1 символа, и кликаем на нее")
  public static void scrollToClaimWithOneCharacterTittleAndClick() throws InterruptedException {
    Allure.step("Прокручиваем к заявке c заголовком, состоящем из 1 символа, и кликаем на нее");
    scrollAndClickToClaimWithTittle(tittleClaimOneCharacter);
  }

  @Step("Прокручиваем к заявке с заголовком, состоящем из 49 символов, и кликаем на нее")
  public static void scrollToClaimWith49CharacterTittleAndClick() throws InterruptedException {
    Allure.step("Прокручиваем к заявке c заголовком, состоящем из 49 символов, и кликаем на нее");
    scrollAndClickToClaimWithTittle(tittleClaimFortyNineCharacter);
  }

  @Step("Прокручиваем к заявке с заголовком, состоящем из 50 символов, и кликаем на нее")
  public static void scrollToClaimWith50CharacterTittleAndClick() throws InterruptedException {
    Allure.step("Прокручиваем к заявке c заголовком, состоящем из 50 символов, и кликаем на нее");
    scrollAndClickToClaimWithTittle(tittleClaimFiftyCharacter);
  }

  @Step("Прокручиваем к заявке с заголовком, обрезанном до 50 символов и кликаем на нее")
  public static void scrollToClaimWithTrimmedTittleAndClick(String tittle) throws InterruptedException {
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
  public static void changeStatusToInProgress() {
    Allure.step("Меняем статус заявки с Открыта на В работе");
    changeStatusToInProgress();
  }

  @Step("Меняем статус заявки с Открыта на Отменена")
  public static void changeStatusToInCanceled() {
    Allure.step("Меняем статус заявки с Открыта на Отменена");
    changeStatusToInProgress();
  }

  @Step("Меняем статус заявки с В работе на Исполнена")
  public static void changeStatusToInExecuted() {
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

  /*   Шаги для Главной страницы  */

  @Step("Переходим в раздел Заявки с помощью кнопки в меню навигации приложения ")
  public static void goToClaimsPageWithPressNavigationMenuButton() {
    Allure.step("Переходим в раздел Заявки с помощью кнопки в меню навигации приложения");
    goToClaimesPageByNavigationMenu();
  }

  @Step("Переходим в раздел Заявки с помощью кнопки на главной странице приложения ")
  public static void goToClaimsPageWithPressButtonOnMainPage() {
    Allure.step("Переходим в раздел Заявки с помощью кнопки на главной странице приложения");
    goToClaimesPage();
  }

  @Step("Переходим в раздел Новости с помощью кнопки в меню навигации приложения ")
  public static void goToNewsPageWithPressNavigationMenuButton() {
    Allure.step("Переходим в раздел Новости с помощью кнопки в меню навигации приложения");
    goToNewsPageByNavigationMenu();
  }

  @Step("Переходим в раздел Новости с помощью кнопки на главной странице приложения ")
  public static void goToNewsPageWithPressButtonOnMainPage() {
    Allure.step("Переходим в раздел Новости с помощью кнопки на главной странице приложения");
    goToNewsPage();
  }

  @Step("Переходим в раздел О приложении с помощью кнопки в меню навигации приложения ")
  public static void goToAboutPageWithPressNavigationMenuButton() {
    Allure.step("Переходим в раздел О приложении с помощью кнопки в меню навигации приложения");
    goToAboutPage();
  }

  @Step("Переходим в раздел Цитаты с помощью кнопки на главной странице приложения ")
  public static void goToQuotesPageWithPressButtonOnMainPage() {
    Allure.step("Переходим в раздел Цитаты с помощью кнопки на главной странице приложения");
    goToQuotesPage();
  }

  @Step("Проверяем, что видна кнопка фильтрации заявок")
  public static void isFilterButtonDisplayed() {
    Allure.step("Проверяем, что видна кнопка фильтрации заявок");
    filterClimesButton.check(matches(isDisplayed()));
  }

  @Step("Проверяем, что видна кнопка перехода в раздел редактирования новостей")
  public static void isEditingNewsButtonDisplayed() {
    Allure.step("Проверяем, что видна кнопка перехода в раздел редактирования новостей");
    editNewsButton.check(matches(isDisplayed()));
  }

  @Step("Проверяем, что видна информация о разработчике приложения")
  public static void isDeveloperTextViewDisplayed() {
    Allure.step("Проверяем, что видна информация о разработчике приложения");
    aboutInfo.check(matches(isDisplayed()));
  }

  @Step("Проверяем, что видна кнопка перехода в раздел Заявки")
  public static void isAllClaimsButtonDisplayed() {
    Allure.step("Проверяем, что видна кнопка перехода в раздел Заявки");
    allClimesButton.check(matches(isDisplayed()));
  }

  @Step("Проверяем, что виден заголовок раздела Цитаты")
  public static void isHeaderQuotesPageDisplayed() {
    Allure.step("Проверяем, что виден заголовок раздела Цитаты");
    header.check(matches(isDisplayed()));
  }

  @Step("Проверяем, что созданная заявка существует")
  public static void isClaimExist() throws InterruptedException {
    Allure.step("Проверяем, что созданная заявка существует");
    isClaimExistWithParams(tittleClaim, dateClaim, timeClaim, descriptionClaim);
  }

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
  public static void addingNews() throws InterruptedException {
    Allure.step("Добавляем новость");
    addNews(categoryForth, tittleNews, dateNews, timeNews, descriptionNews);
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
  public static void addNewsWithEmptyFieldCategory() throws InterruptedException {
    Allure.step("Пытаемся создать новость с незаполненным полем Категория");
    addNews("", tittleNews, dateNews, timeNews, descriptionNews);
  }

  @Step("Пытаемся создать новость с незаполненным полем Заголовок")
  public static void addNewsWithEmptyFieldTittle() throws InterruptedException {
    Allure.step("Пытаемся создать новость с незаполненным полем Заголовок");
    addNews(categoryForth, "", dateNews, timeNews, descriptionNews);
  }

  @Step("Пытаемся создать новость с незаполненным полем Дата")
  public static void addNewsWithEmptyFieldDate() throws InterruptedException {
    Allure.step("Пытаемся создать новость с незаполненным полем Дата");
    addNews(categoryForth, tittleNews, "", timeNews, descriptionNews);
  }

  @Step("Пытаемся создать новость с незаполненным полем Время")
  public static void addNewsWithEmptyFieldTime() throws InterruptedException {
    Allure.step("Пытаемся создать новость с незаполненным полем Время");
    addNews(categoryForth, tittleNews, dateNews, "", descriptionNews);
  }

  @Step("Пытаемся создать новость с незаполненным полем Описание")
  public static void addNewsWithEmptyFieldDescription() throws InterruptedException {
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
  public static String addingNewsAndReturnPublishDate() throws InterruptedException {
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

  /*   Шаги для страницы Цитаты */

  @Step("Получаем recyclerView для раздела Цитаты и прокручиваем до его первой позиции")
  public static ViewInteraction getQuotesRecyclerViewAndScrollToFirstPosition() {
    Allure.step("Получаем recyclerView для раздела Цитаты и прокручиваем до его первой позиции");
    ViewInteraction recyclerView = onView(withId(R.id.our_mission_item_list_recycler_view));
    recyclerView.perform(scrollToPosition(0));
    return recyclerView;
  }


}
