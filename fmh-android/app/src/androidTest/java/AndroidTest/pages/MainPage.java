package AndroidTest.pages;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.allOf;
import static AndroidTest.data.DataHelper.waitElement;
import static AndroidTest.pages.ClimesPage.fillingFieldsNewClime;
import static AndroidTest.pages.ClimesPage.listClaimsId;
import static AndroidTest.pages.NewClaimPage.saveButton;

import androidx.test.espresso.ViewInteraction;

import ru.iteco.fmhandroid.R;

public class MainPage {
  public static ViewInteraction mainMenuButton =
      onView(allOf(withId(R.id.main_menu_image_button), withContentDescription(R.string.main_menu)));
  public static int mainMenuButtonId = R.id.main_menu_image_button;
  public static ViewInteraction climesButton = onView(withText(R.string.claims));
  public static ViewInteraction newsButton = onView(withText(R.string.news));
  static ViewInteraction aboutButton = onView(withText(R.string.about));
  public static ViewInteraction allClimesButton = onView(allOf(withId(R.id.all_claims_text_view),
      withText(R.string.all_claims)));
  public static int allClimesButtonId = R.id.all_claims_text_view;
  static ViewInteraction allNewsButton = onView(allOf(withId(R.id.all_news_text_view),
      withText(R.string.all_news)));
  public static int allNewsButtonId = R.id.all_news_text_view;

  public static ViewInteraction logOutButton = onView(allOf(withId(R.id.authorization_image_button)));
  public static int LogOutId = R.id.authorization_image_button;

  public static ViewInteraction quotesButton = onView(allOf(withId(R.id.our_mission_image_button)));
  public static int quotesButtonID = R.id.our_mission_image_button;

  public static ViewInteraction addNewClaimButton = onView(allOf(withId(R.id.add_new_claim_material_button)));
  public static int addNewClaimButtonID = R.id.add_new_claim_material_button;


  public static void logOut() {
    onView(withId(LogOutId)).perform(click());
    onView(withId(android.R.id.title)).check(matches(isDisplayed()));
    onView(withId(android.R.id.title)).perform(click());
  }

  public static void goToClaimesPage() {
    waitElement(allClimesButtonId);
    allClimesButton.perform(click());
    waitElement(listClaimsId);
  }

  public static void goToClaimesPageByNavigationMenu() {
    waitElement(mainMenuButtonId);
    mainMenuButton.perform(click());
    climesButton.check(matches(isDisplayed()));
    climesButton.perform(click());
  }

  public static void goToNewsPage() {
    waitElement(allNewsButtonId);
    allNewsButton.perform(click());
  }

  public static void goToNewsPageByNavigationMenu() {
    waitElement(mainMenuButtonId);
    mainMenuButton.perform(click());
    newsButton.check(matches(isDisplayed()));
    newsButton.perform(click());
  }

  public static void goToAboutPage() {
    waitElement(mainMenuButtonId);
    mainMenuButton.perform(click());
    aboutButton.check(matches(isDisplayed()));
    aboutButton.perform(click());
  }

  public static void goToQuotesPage() {
    waitElement(quotesButtonID);
    quotesButton.perform(click());
  }

  public static void addNewClaim(String tittle, String date, String time, String description) {
    waitElement(addNewClaimButtonID);
    addNewClaimButton.perform(click());
    fillingFieldsNewClime(tittle, date, time, description);
    saveButton.perform(scrollTo()).perform(click());
  }
}
