package AndroidTest.tests;


import static AndroidTest.pages.AuthPage.successLogin;
import static AndroidTest.pages.MainPage.goToAboutPage;
import static AndroidTest.pages.MainPage.logOut;

import androidx.test.espresso.Espresso;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import AndroidTest.Steps.AllureSteps;
import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.junit4.DisplayName;
import ru.iteco.fmhandroid.ui.AppActivity;

@RunWith(AllureAndroidJUnit4.class)
public class AboutPageTest {

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

  @Test
  @DisplayName("Видимость сведений о версии приложения")
  public void testVisibleVersion() {
    AllureSteps.isAppVersionDisplayed();
    Espresso.pressBack();
  }

  @Test
  @DisplayName("Видимость сведений о  разработчике приложения")
  public void testVisibleDeveloper() {
    AllureSteps.isAppDeveloperDisplayed();
    Espresso.pressBack();
  }

  @Test
  @DisplayName("Существование веб-страницы с политикой конфиденциальности")
  public void testPrivacyPolicyPageExistence() {
    AllureSteps.isWebPagePrivacyPolicyExistence();
  }

  @Test
  @DisplayName("Существование веб-страницы с условиями использования")
  public void testЕTermsOfUsePageExistence() {
    AllureSteps.isWebPageTermsOfUseExistence();
  }
}
