package AndroidTest.tests;

import static AndroidTest.pages.AuthPage.checkLogInAndLogInIfNot;
import static AndroidTest.data.DataHelper.getUniqueScreenshotName;

import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import AndroidTest.steps.BaseSteps;
import AndroidTest.steps.MainPageSteps;
import io.qameta.allure.android.rules.ScreenshotRule;
import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.Epic;
import io.qameta.allure.kotlin.junit4.DisplayName;
import ru.iteco.fmhandroid.ui.AppActivity;

@Epic("Тестирование главной страницы приложения")

@RunWith(AllureAndroidJUnit4.class)
public class MainPageTest {

  @Before
  public void setUp() {
    checkLogInAndLogInIfNot();
  }


  @Rule
  public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
      new ActivityScenarioRule<>(AppActivity.class);

  @Rule
  public ScreenshotRule screenshotRule =
      new ScreenshotRule(ScreenshotRule.Mode.FAILURE, getUniqueScreenshotName());


  @Test
  @DisplayName("Переход в раздел Заявки с помощью кнопки в меню навигации приложения")
  public void isItPossibleToGoToClaimsSectionWithNavigationMenuButton() {
    MainPageSteps.goToClaimsPageWithPressNavigationMenuButton();
    MainPageSteps.isFilterButtonDisplayed();
  }

  @Test
  @DisplayName("Переход в раздел Заявки с помощью кнопки на главной странице")
  public void isItPossibleToGoToClaimsSectionWithMainPageButton() {
    MainPageSteps.goToClaimsPageWithPressButtonOnMainPage();
    MainPageSteps.isFilterButtonDisplayed();
  }

  @Test
  @DisplayName("Возврат из раздела Заявки на предыдущую страницу приложения при тапе на BACK")
  public void returnFromClaimsPageToPreviousPageByTapBack() {
    MainPageSteps.goToNewsPageWithPressButtonOnMainPage();
    MainPageSteps.goToClaimsPageWithPressNavigationMenuButton();
    BaseSteps.pressBack();
    MainPageSteps.isEditingNewsButtonDisplayed();
  }

  @Test
  @DisplayName("Переход в раздел Новости с помощью кнопки в меню навигации приложения")
  public void isItPossibleToGoToNewsSectionWithNavigationMenuButton() {
    MainPageSteps.goToNewsPageWithPressNavigationMenuButton();
    MainPageSteps.isEditingNewsButtonDisplayed();
  }

  @Test
  @DisplayName("Переход в раздел Новости с помощью кнопки на главной странице")
  public void isItPossibleToGoToNewsSectionWithMainPageButton() {
    MainPageSteps.goToNewsPageWithPressButtonOnMainPage();
    MainPageSteps.isEditingNewsButtonDisplayed();
  }

  @Test
  @DisplayName("Возврат из раздела Новости на предыдущую страницу приложения при тапе на BACK")
  public void returnFromNewsPageToPreviousPageByTapBack() {
    MainPageSteps.goToClaimsPageWithPressButtonOnMainPage();
    MainPageSteps.goToNewsPageWithPressNavigationMenuButton();
    BaseSteps.pressBack();
    MainPageSteps.isFilterButtonDisplayed();
  }

  @Test
  @DisplayName("Переход в раздел О приложении с помощью кнопки в меню навигации приложения")
  public void isItPossibleToGoToAboutSectionWithNavigationMenuButton() {
    MainPageSteps.goToAboutPageWithPressNavigationMenuButton();
    MainPageSteps.isDeveloperTextViewDisplayed();
  }

  @Test
  @DisplayName("Возврат из раздела О приложении на предыдущую страницу приложения при тапе на BACK")
  public void returnFromAboutPageToPreviousPageByTapBack() {
    MainPageSteps.goToAboutPageWithPressNavigationMenuButton();
    BaseSteps.pressBack();
    MainPageSteps.isAllClaimsButtonDisplayed();
  }

  @Test
  @DisplayName("Переход в раздел Цитаты с помощью кнопки на главной странице")
  public void isItPossibleToGoToQuotesSectionWithMainPageButton() {
    MainPageSteps.goToQuotesPageWithPressButtonOnMainPage();
    MainPageSteps.isHeaderQuotesPageDisplayed();
  }

  @Test
  @DisplayName("Возврат из раздела Цитаты на предыдущую страницу приложения при тапе на BACK")
  public void returnFromQuotesPageToPreviousPageByTapBack() {
    MainPageSteps.goToQuotesPageWithPressButtonOnMainPage();
    BaseSteps.pressBack();
    MainPageSteps.isAllClaimsButtonDisplayed();
  }

  @Test
  @DisplayName("Создание Заявки с помощью кнопки на главной странице")
  public void createNewClaimWithButtonOnMainPage() {
    MainPageSteps.addClaim();
    MainPageSteps.goToClaimsPageWithPressButtonOnMainPage();
    MainPageSteps.isClaimExist();
  }

}
