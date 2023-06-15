package AndroidTest.steps;

import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static AndroidTest.data.Data.dateClaim;
import static AndroidTest.data.Data.descriptionClaim;
import static AndroidTest.data.Data.timeClaim;
import static AndroidTest.data.Data.tittleClaim;
import static AndroidTest.pages.AboutPage.aboutInfo;
import static AndroidTest.pages.ClimesPage.filterClimesButton;
import static AndroidTest.pages.ClimesPage.isClaimExistWithParams;
import static AndroidTest.pages.MainPage.addNewClaim;
import static AndroidTest.pages.MainPage.allClimesButton;
import static AndroidTest.pages.MainPage.goToAboutPage;
import static AndroidTest.pages.MainPage.goToClaimesPage;
import static AndroidTest.pages.MainPage.goToClaimesPageByNavigationMenu;
import static AndroidTest.pages.MainPage.goToNewsPage;
import static AndroidTest.pages.MainPage.goToNewsPageByNavigationMenu;
import static AndroidTest.pages.MainPage.goToQuotesPage;
import static AndroidTest.pages.NewsPage.editNewsButton;
import static AndroidTest.pages.QuotesPage.header;

import io.qameta.allure.kotlin.Allure;
import io.qameta.allure.kotlin.Step;

public class MainPageSteps {


  /*   Шаги для Главной страницы  */

  @Step("Переходим в раздел Заявки с помощью кнопки в меню навигации приложения ")
  public static void goToClaimsPageWithPressNavigationMenuButton() {
    Allure.step("Переходим в раздел Заявки с помощью кнопки в меню навигации приложения");
    goToClaimesPageByNavigationMenu();
  }

  @Step("Переходим в раздел Заявки с помощью кнопки на главной странице приложения ")
  public static void goToClaimsPageWithPressButtonOnMainPage() {
    Allure.step("Переходим в раздел Заявки с помощью кнопки на главной странице приложения");
    goToClaimesPage();
  }

  @Step("Переходим в раздел Новости с помощью кнопки в меню навигации приложения ")
  public static void goToNewsPageWithPressNavigationMenuButton() {
    Allure.step("Переходим в раздел Новости с помощью кнопки в меню навигации приложения");
    goToNewsPageByNavigationMenu();
  }

  @Step("Переходим в раздел Новости с помощью кнопки на главной странице приложения ")
  public static void goToNewsPageWithPressButtonOnMainPage() {
    Allure.step("Переходим в раздел Новости с помощью кнопки на главной странице приложения");
    goToNewsPage();
  }

  @Step("Переходим в раздел О приложении с помощью кнопки в меню навигации приложения ")
  public static void goToAboutPageWithPressNavigationMenuButton() {
    Allure.step("Переходим в раздел О приложении с помощью кнопки в меню навигации приложения");
    goToAboutPage();
  }

  @Step("Переходим в раздел Цитаты с помощью кнопки на главной странице приложения ")
  public static void goToQuotesPageWithPressButtonOnMainPage() {
    Allure.step("Переходим в раздел Цитаты с помощью кнопки на главной странице приложения");
    goToQuotesPage();
  }

  @Step("Проверяем, что видна кнопка фильтрации заявок")
  public static void isFilterButtonDisplayed() {
    Allure.step("Проверяем, что видна кнопка фильтрации заявок");
    filterClimesButton.check(matches(isDisplayed()));
  }

  @Step("Проверяем, что видна кнопка перехода в раздел редактирования новостей")
  public static void isEditingNewsButtonDisplayed() {
    Allure.step("Проверяем, что видна кнопка перехода в раздел редактирования новостей");
    editNewsButton.check(matches(isDisplayed()));
  }

  @Step("Проверяем, что видна информация о разработчике приложения")
  public static void isDeveloperTextViewDisplayed() {
    Allure.step("Проверяем, что видна информация о разработчике приложения");
    aboutInfo.check(matches(isDisplayed()));
  }

  @Step("Проверяем, что видна кнопка перехода в раздел Заявки")
  public static void isAllClaimsButtonDisplayed() {
    Allure.step("Проверяем, что видна кнопка перехода в раздел Заявки");
    allClimesButton.check(matches(isDisplayed()));
  }

  @Step("Проверяем, что виден заголовок раздела Цитаты")
  public static void isHeaderQuotesPageDisplayed() {
    Allure.step("Проверяем, что виден заголовок раздела Цитаты");
    header.check(matches(isDisplayed()));
  }
  @Step("Добавляем новую заявку")
  public static void addClaim() {
    Allure.step("Добавляем новую заявку");
    addNewClaim(tittleClaim, dateClaim, timeClaim, descriptionClaim);
  }

  @Step("Проверяем, что созданная заявка существует")
  public static void isClaimExist() {
    Allure.step("Проверяем, что созданная заявка существует");
    isClaimExistWithParams(tittleClaim, dateClaim, timeClaim, descriptionClaim);
  }
}
