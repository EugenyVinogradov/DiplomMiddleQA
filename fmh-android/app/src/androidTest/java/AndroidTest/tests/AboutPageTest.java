package AndroidTest.tests;


import static AndroidTest.Steps.AllureSteps.goToAboutPageStep;
import static AndroidTest.Steps.AllureSteps.isAppDeveloperDisplayed;
import static AndroidTest.Steps.AllureSteps.isAppVersionDisplayed;
import static AndroidTest.Steps.AllureSteps.isWebPagePrivacyPolicyExistence;
import static AndroidTest.Steps.AllureSteps.isWebPageTermsOfUseExistence;
import static AndroidTest.Steps.AllureSteps.logOutFromApp;
import static AndroidTest.Steps.AllureSteps.pressBack;
import static AndroidTest.Steps.AllureSteps.successLoginStep;
import static AndroidTest.data.DataHelper.getUniqueScreenshotName;

import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.android.rules.ScreenshotRule;
import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.Attachment;
import io.qameta.allure.kotlin.Epic;
import io.qameta.allure.kotlin.junit4.DisplayName;
import io.qameta.allure.kotlin.junit4.Tag;
import ru.iteco.fmhandroid.ui.AppActivity;

@Epic("Тестирование страницы О приложении")

@RunWith(AllureAndroidJUnit4.class)
public class AboutPageTest {

  @Before
  public void login() {
    successLoginStep();
    goToAboutPageStep();
  }

  @After
  public void logOutApp() {
    logOutFromApp();
  }

  @Rule
  public ActivityScenarioRule<AppActivity> myActivityScenarioRule =
      new ActivityScenarioRule<>(AppActivity.class);

  @Rule
  public ScreenshotRule screenshotRule =
      new ScreenshotRule(ScreenshotRule.Mode.FAILURE, getUniqueScreenshotName() );


  @Test
  @DisplayName("Видимость сведений о версии приложения")
  @Attachment
  public void testVisibleVersion() {
    isAppVersionDisplayed();
    pressBack();
  }

  @Test
  @DisplayName("Видимость сведений о  разработчике приложения")
  @Attachment
  public void testVisibleDeveloper() {
    isAppDeveloperDisplayed();
    pressBack();
  }

  @Test
  @DisplayName("Существование веб-страницы с политикой конфиденциальности")
  @Attachment
  public void testPrivacyPolicyPageExistence() {
    isWebPagePrivacyPolicyExistence();
    pressBack();
  }

  @Test
  @Attachment
  @DisplayName("Существование веб-страницы с условиями использования")
  public void testЕTermsOfUsePageExistence() {
    isWebPageTermsOfUseExistence();
    pressBack();
  }
}
