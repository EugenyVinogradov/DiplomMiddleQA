package AndroidTest.tests;

import static AndroidTest.pages.AuthPage.successLogin;
import static AndroidTest.pages.MainPage.goToAboutPage;
import static AndroidTest.pages.MainPage.logOut;

import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.runner.RunWith;

import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import ru.iteco.fmhandroid.ui.AppActivity;

@RunWith(AllureAndroidJUnit4.class)
public class NewsEditingPageTest {

  @Before
  public void login() {
    successLogin();
    goToAboutPage();
  }

  @After
  public void logOutApp() {
    logOut();
  }

  @Rule
  public ActivityScenarioRule<AppActivity> myActivityScenarioRule =
      new ActivityScenarioRule<>(AppActivity.class);


}
