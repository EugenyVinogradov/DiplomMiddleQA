package AndroidTest.tests;


import static AndroidTest.data.Data.correctLogin;
import static AndroidTest.data.Data.correctPassword;
import static AndroidTest.data.Data.wrongLogin;
import static AndroidTest.data.Data.wrongPassword;
import static AndroidTest.data.DataHelper.waitElement;
import static AndroidTest.pages.MainPage.LogOutId;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import AndroidTest.Steps.AllureSteps;
import io.qameta.allure.android.rules.ScreenshotRule;
import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.Attachment;
import io.qameta.allure.kotlin.Epic;
import io.qameta.allure.kotlin.junit4.DisplayName;
import ru.iteco.fmhandroid.ui.AppActivity;


@Epic("Тестирование страницы авторизации приложения")

@RunWith(AllureAndroidJUnit4.class)
public class AutorizationTest {


  @Rule
  public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
      new ActivityScenarioRule<>(AppActivity.class);

  @Rule
  public ScreenshotRule screenshotRule =
      new ScreenshotRule(ScreenshotRule.Mode.FAILURE, "test_fail");



  @Test
  @DisplayName("Авторизация с валидными логином и паролем")
  public void correctLoginAndPasswordAuthorizationTest() {
    AllureSteps.login(correctLogin, correctPassword);
    waitElement(LogOutId);
    AllureSteps.logOutIsVisible();
    AllureSteps.logOutFromApp();
  }


  @Test
  @Attachment
  @DisplayName("Авторизация с незаполненными полями логина и пароля")
  public void emptyLoginAndPasswordAuthorizationTest() {
    AllureSteps.login("", "");
    AllureSteps.loginOrPasswordDoesntBeEmpty();
  }

  @Test
  @Attachment
  @DisplayName("Ввод валидного логина и невалидого пароля при авторизации")
  public void correctLoginWrongPasswordAuthorizationTest() {
    AllureSteps.login(correctLogin, wrongPassword);
    AllureSteps.loginOrPasswordIsWrong();
  }

  @Test
  @Attachment
  @DisplayName("Ввод невалидного логина и валидого пароля при авторизации")
  public void wrongLoginWrongCorrectPasswordAuthorizationTest() {
    AllureSteps.login(wrongLogin, correctPassword);
    AllureSteps.loginOrPasswordIsWrong();
  }

  @Test
  @Attachment
  @DisplayName("Ввод валидного логина и пустого пароля при авторизации")
  public void correctLoginEmptyPasswordAuthorizationTest() {
    AllureSteps.login(correctLogin, "");
    AllureSteps.loginOrPasswordDoesntBeEmpty();
  }

  @Test
  @Attachment
  @DisplayName("Ввод пустого логина и валидного пароля при авторизации")
  public void emptyLoginCorrectPasswordAuthorizationTest() {
    AllureSteps.login("", correctPassword);
    AllureSteps.loginOrPasswordDoesntBeEmpty();
  }


}
