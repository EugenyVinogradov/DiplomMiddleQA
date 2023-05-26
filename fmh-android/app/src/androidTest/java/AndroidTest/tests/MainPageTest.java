package AndroidTest.tests;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static AndroidTest.data.Data.dateClaim;
import static AndroidTest.data.Data.descriptionClaim;
import static AndroidTest.data.Data.timeClaim;
import static AndroidTest.data.Data.tittleClaim;
import static AndroidTest.data.DataHelper.waitElement;
import static AndroidTest.pages.AboutPage.aboutInfo;
import static AndroidTest.pages.AboutPage.backButton;
import static AndroidTest.pages.AuthPage.successLogin;
import static AndroidTest.pages.ClimesPage.filterClimesButton;
import static AndroidTest.pages.ClimesPage.filterClimesButtonID;
import static AndroidTest.pages.ClimesPage.isClaimExistWithParams;
import static AndroidTest.pages.MainPage.addNewClaim;
import static AndroidTest.pages.MainPage.allClimesButton;
import static AndroidTest.pages.MainPage.climesButton;
import static AndroidTest.pages.MainPage.goToAboutPage;
import static AndroidTest.pages.MainPage.goToClaimesPage;
import static AndroidTest.pages.MainPage.goToClaimesPageByNavigationMenu;
import static AndroidTest.pages.MainPage.goToNewsPage;
import static AndroidTest.pages.MainPage.goToNewsPageByNavigationMenu;
import static AndroidTest.pages.MainPage.goToQuotesPage;
import static AndroidTest.pages.MainPage.logOut;
import static AndroidTest.pages.MainPage.mainMenuButton;
import static AndroidTest.pages.MainPage.newsButton;
import static AndroidTest.pages.NewsPage.editNewsButton;
import static AndroidTest.pages.QuotesPage.header;

import androidx.test.espresso.Espresso;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.junit4.DisplayName;
import ru.iteco.fmhandroid.ui.AppActivity;


@RunWith(AllureAndroidJUnit4.class)

public class MainPageTest {

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


  @Test
  @DisplayName("Переход в раздел Заявки с помощью кнопки в меню навигации приложения")
  public void isItPossibleToGoToClaimsSectionWithNavigationMenuButton() {
    goToClaimesPageByNavigationMenu();
    filterClimesButton.check(matches(isDisplayed()));
  }

  @Test
  @DisplayName("Переход в раздел Заявки с помощью кнопки на главной странице")
  public void isItPossibleToGoToClaimsSectionWithMainPageButton() {
    goToClaimesPage();
    filterClimesButton.check(matches(isDisplayed()));
  }

  @Test
  @DisplayName("Возврат из раздела Заявки на предыдущую страницу приложения при тапе на BACK")
  public void returnFromClaimsPageToPreviousPageByTapBack() {
    goToNewsPage();
    mainMenuButton.perform(click());
    climesButton.perform(click());
    Espresso.pressBack();
    editNewsButton.check(matches(isDisplayed()));
  }

  @Test
  @DisplayName("Переход в раздел Новости с помощью кнопки в меню навигации приложения")
  public void isItPossibleToGoToNewsSectionWithNavigationMenuButton() {
    goToNewsPageByNavigationMenu();
    editNewsButton.check(matches(isDisplayed()));
  }

  @Test
  @DisplayName("Переход в раздел Новости с помощью кнопки на главной странице")
  public void isItPossibleToGoToNewsSectionWithMainPageButton() {
    goToNewsPage();
    editNewsButton.check(matches(isDisplayed()));
  }

  @Test
  @DisplayName("Возврат из раздела Новости на предыдущую страницу приложения при тапе на BACK")
  public void returnFromNewsPageToPreviousPageByTapBack() {
    goToClaimesPage();
    mainMenuButton.perform(click());
    newsButton.perform(click());
    Espresso.pressBack();
    waitElement(filterClimesButtonID);
    filterClimesButton.check(matches(isDisplayed()));
  }

  @Test
  @DisplayName("Переход в раздел О приложении с помощью кнопки в меню навигации приложения")
  public void isItPossibleToGoToAboutSectionWithNavigationMenuButton() {
    goToAboutPage();
    aboutInfo.check(matches(isDisplayed()));
    backButton.perform(click());
  }

  @Test
  @DisplayName("Возврат из раздела О приложении на предыдущую страницу приложения при тапе на BACK")
  public void returnFromAboutPageToPreviousPageByTapBack() {
    goToAboutPage();
    Espresso.pressBack();
    allClimesButton.check(matches(isDisplayed()));
  }

  @Test
  @DisplayName("Переход в раздел Цитаты с помощью кнопки на главной странице")
  public void isItPossibleToGoToQuotesSectionWithMainPageButton() {
    goToQuotesPage();
    header.check(matches(isDisplayed()));
  }

  @Test
  @DisplayName("Возврат из раздела Цитаты на предыдущую страницу приложения при тапе на BACK")
  public void returnFromQuotesPageToPreviousPageByTapBack() {
    goToQuotesPage();
    Espresso.pressBack();
    allClimesButton.check(matches(isDisplayed()));
  }

  @Test
  @DisplayName("Создание Заявки с помощью кнопки на главной странице")
  public void createNewClaimWithButtonOnMainPage() throws InterruptedException {
    addNewClaim(tittleClaim, dateClaim, timeClaim, descriptionClaim);
    goToClaimesPage();
    isClaimExistWithParams(tittleClaim, dateClaim, timeClaim, descriptionClaim);
  }

}
