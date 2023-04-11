package AndroidTest.tests;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.isPlatformPopup;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;

import static AndroidTest.data.Data.correctLogin;
import static AndroidTest.data.Data.correctPassword;
import static AndroidTest.data.Data.wrongLogin;
import static AndroidTest.data.Data.wrongPassword;
import static AndroidTest.data.DataHelper.waitElement;
import static AndroidTest.data.DataHelper.waitUntilVisible;

import static AndroidTest.pages.AuthPage.login;
import static AndroidTest.pages.AuthPage.loginButton;
import static AndroidTest.pages.AuthPage.loginField;
import static AndroidTest.pages.AuthPage.passwordField;
import static AndroidTest.pages.MainPage.LogOutId;
import static AndroidTest.pages.MainPage.logOut;
import static AndroidTest.pages.MainPage.loginOutButton;

import androidx.test.espresso.Root;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.hamcrest.Matcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;


import AndroidTest.data.DataHelper;
import AndroidTest.pages.AuthPage;
import AndroidTest.pages.MainPage;
import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.junit4.DisplayName;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.AppActivity;




//@RunWith(AndroidJUnit4.class)
@RunWith(AllureAndroidJUnit4.class)
public class AutorizationTest {

//    private AuthPage authPage = new AuthPage();
//    private MainPage mainPage = new MainPage();


    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);


    @Test
    @DisplayName("Авторизация с валидными логином и паролем")
    public void correctLoginAndPasswordAuthorizationTest(){
        login(correctLogin, correctPassword);
        waitElement(LogOutId);
        loginOutButton.check(matches(isDisplayed()));
        logOut();
    }
    @Test
    @DisplayName("Авторизация с незаполненными полями логина и пароля")
    public void emptyLoginAndPasswordAuthorizationTest(){
        login("", "");
        waitUntilVisible(DataHelper.checkToast(R.string.empty_login_or_password, true));
    }

    @Test
    @DisplayName("Ввод валидного логина и невалидого пароля при авторизации")
    public void correctLoginWrongPasswordAuthorizationTest () {
        login(correctLogin, wrongPassword);
        waitUntilVisible(DataHelper.checkToast(R.string.wrong_login_or_password, true));
    }

    @Test
    @DisplayName("Ввод невалидного логина и валидого пароля при авторизации")
    public void wrongLoginWrongCorrectPasswordAuthorizationTest () {
        login(wrongLogin, correctPassword);
        waitUntilVisible(DataHelper.checkToast(R.string.wrong_login_or_password, true));
    }

    @Test
    @DisplayName("Ввод валидного логина и пустого пароля при авторизации")
    public void correctLoginEmptyPasswordAuthorizationTest (){
        login(correctLogin, "");
        waitUntilVisible(DataHelper.checkToast(R.string.empty_login_or_password, true));
    }

    @Test
    @DisplayName("Ввод пустого логина и валидного пароля при авторизации")
    public void emptyLoginCorrectPasswordAuthorizationTest (){
        login("", correctPassword);
        waitUntilVisible(DataHelper.checkToast(R.string.empty_login_or_password, true));
    }




}
