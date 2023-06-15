package AndroidTest.steps;

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

import org.hamcrest.Matchers;

import io.qameta.allure.kotlin.Allure;
import io.qameta.allure.kotlin.Step;

public class AboutPageSteps {

  /* Шаги для страницы О приложении */

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
}
