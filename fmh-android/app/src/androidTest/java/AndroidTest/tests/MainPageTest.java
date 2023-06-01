package AndroidTest.tests;

import static AndroidTest.Steps.AllureSteps.logOutFromApp;
import static AndroidTest.Steps.AllureSteps.successLoginStep;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.After;
import org.junit.Before;
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

@Epic("Тестирование главной страницы приложения")

@RunWith(AllureAndroidJUnit4.class)
public class MainPageTest {

  @Before
  public void login() {
    successLoginStep();
  }

  @After
  public void logOutApp() {
    logOutFromApp();
  }

  @Rule
  public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
      new ActivityScenarioRule<>(AppActivity.class);

  @Rule
  public ScreenshotRule screenshotRule =
      new ScreenshotRule(ScreenshotRule.Mode.FAILURE, "test_fail");


  @Test
  @DisplayName("Переход в раздел Заявки с помощью кнопки в меню навигации приложения")
  @Attachment
  public void isItPossibleToGoToClaimsSectionWithNavigationMenuButton() {
    AllureSteps.goToClaimsPageWithPressNavigationMenuButton();
    AllureSteps.isFilterButtonDisplayed();
  }

  @Test
  @DisplayName("Переход в раздел Заявки с помощью кнопки на главной странице")
  @Attachment
  public void isItPossibleToGoToClaimsSectionWithMainPageButton() {
    AllureSteps.goToClaimsPageWithPressButtonOnMainPage();
    AllureSteps.isFilterButtonDisplayed();
  }

  @Test
  @DisplayName("Возврат из раздела Заявки на предыдущую страницу приложения при тапе на BACK")
  @Attachment
  public void returnFromClaimsPageToPreviousPageByTapBack() {
    AllureSteps.goToNewsPageWithPressButtonOnMainPage();
    AllureSteps.goToClaimsPageWithPressNavigationMenuButton();
    AllureSteps.pressBack();
    AllureSteps.isEditingNewsButtonDisplayed();
  }

  @Test
  @DisplayName("Переход в раздел Новости с помощью кнопки в меню навигации приложения")
  @Attachment
  public void isItPossibleToGoToNewsSectionWithNavigationMenuButton() {
    AllureSteps.goToNewsPageWithPressNavigationMenuButton();
    AllureSteps.isEditingNewsButtonDisplayed();
  }

  @Test
  @DisplayName("Переход в раздел Новости с помощью кнопки на главной странице")
  @Attachment
  public void isItPossibleToGoToNewsSectionWithMainPageButton() {
    AllureSteps.goToNewsPageWithPressButtonOnMainPage();
    AllureSteps.isEditingNewsButtonDisplayed();
  }

  @Test
  @DisplayName("Возврат из раздела Новости на предыдущую страницу приложения при тапе на BACK")
  @Attachment
  public void returnFromNewsPageToPreviousPageByTapBack() {
    AllureSteps.goToClaimsPageWithPressButtonOnMainPage();
    AllureSteps.goToNewsPageWithPressNavigationMenuButton();
    AllureSteps.pressBack();
    AllureSteps.isFilterButtonDisplayed();
  }

  @Test
  @DisplayName("Переход в раздел О приложении с помощью кнопки в меню навигации приложения")
  @Attachment
  public void isItPossibleToGoToAboutSectionWithNavigationMenuButton() {
    AllureSteps.goToAboutPageWithPressNavigationMenuButton();
    AllureSteps.isDeveloperTextViewDisplayed();
    AllureSteps.pressBack();
  }

  @Test
  @DisplayName("Возврат из раздела О приложении на предыдущую страницу приложения при тапе на BACK")
  @Attachment
  public void returnFromAboutPageToPreviousPageByTapBack() {
    AllureSteps.goToAboutPageWithPressNavigationMenuButton();
    AllureSteps.pressBack();
    AllureSteps.isAllClaimsButtonDisplayed();
  }

  @Test
  @DisplayName("Переход в раздел Цитаты с помощью кнопки на главной странице")
  @Attachment
  public void isItPossibleToGoToQuotesSectionWithMainPageButton() {
    AllureSteps.goToQuotesPageWithPressButtonOnMainPage();
    AllureSteps.isHeaderQuotesPageDisplayed();
  }

  @Test
  @DisplayName("Возврат из раздела Цитаты на предыдущую страницу приложения при тапе на BACK")
  @Attachment
  public void returnFromQuotesPageToPreviousPageByTapBack() {
    AllureSteps.goToQuotesPageWithPressButtonOnMainPage();
    AllureSteps.pressBack();
    AllureSteps.isAllClaimsButtonDisplayed();
  }

  @Test
  @DisplayName("Создание Заявки с помощью кнопки на главной странице")
  @Attachment
  public void createNewClaimWithButtonOnMainPage() throws InterruptedException {
    AllureSteps.addClaim();
    AllureSteps.goToClaimsPageWithPressButtonOnMainPage();
    AllureSteps.isClaimExist();
  }

}
