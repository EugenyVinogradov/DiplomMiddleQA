package AndroidTest.tests;

import static AndroidTest.pages.AuthPage.successLogin;
import static AndroidTest.pages.MainPage.logOut;

import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.junit4.DisplayName;
import ru.iteco.fmhandroid.ui.AppActivity;


//@RunWith(AndroidJUnit4.class)
@RunWith(AllureAndroidJUnit4.class)
public class ClaimesPageTest {

    @Before
    public void login() {
        successLogin();
    }

    @After
    public void logOutApp() {
        logOut();
    }

    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);



}
