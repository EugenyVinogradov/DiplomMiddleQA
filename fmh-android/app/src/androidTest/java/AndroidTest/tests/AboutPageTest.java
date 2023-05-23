package AndroidTest.tests;

import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static AndroidTest.data.Data.urlPrivacyPolicy;
import static AndroidTest.data.Data.urlTermsOfUse;
import static AndroidTest.pages.AboutPage.aboutInfo;
import static AndroidTest.pages.AboutPage.versionInfo;
import static AndroidTest.pages.AboutPage.versionText;
import static AndroidTest.pages.AboutPage.webPageExistence;
import static AndroidTest.pages.AuthPage.successLogin;
import static AndroidTest.pages.MainPage.goToAboutPage;
import static AndroidTest.pages.MainPage.logOut;
import androidx.test.espresso.Espresso;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
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
    versionText.check(matches(allOf(isDisplayed(), withText(Matchers.not("")))));
    versionInfo.check(matches(allOf(isDisplayed(), withText(Matchers.not("")))));
    Espresso.pressBack();
  }

  @Test
  @DisplayName("Видимость сведений о  разработчике приложения")
  public void testVisibleDeveloper() {
    aboutInfo.check(matches(allOf(isDisplayed(), withText(Matchers.not("")))));
    Espresso.pressBack();
  }
  @Test
  @DisplayName("Существование веб-страницы с политикой конфиденциальности")
  public void testPrivacyPolicyPageExistence() {
    webPageExistence(urlPrivacyPolicy);
  }
  @Test
  @DisplayName("Существование веб-страницы с условиями использования")
  public void testЕTermsOfUsePageExistence() {
    webPageExistence(urlTermsOfUse);
  }
}
