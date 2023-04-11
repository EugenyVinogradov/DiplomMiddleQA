package AndroidTest.pages;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.supportsInputMethods;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

import static AndroidTest.data.Data.correctLogin;
import static AndroidTest.data.Data.correctPassword;
import static AndroidTest.data.DataHelper.waitElement;
import static AndroidTest.pages.MainPage.loginOutButton;

import androidx.test.espresso.ViewInteraction;

import ru.iteco.fmhandroid.R;

public class AuthPage {

    public static ViewInteraction loginField = onView(allOf(supportsInputMethods(), isDescendantOfA(withId(R.id.login_text_input_layout))));
    public static ViewInteraction passwordField = onView(allOf(supportsInputMethods(), isDescendantOfA(withId(R.id.password_text_input_layout))));
    public static ViewInteraction loginButton = onView(allOf(withId(R.id.enter_button)));
    public static int id = R.id.login_text_input_layout;

    public static void login(String login, String password) {
        waitElement(id);
        loginField.perform(replaceText(login));
        passwordField.perform(replaceText(password));
        loginButton.check(matches(isDisplayed())).perform(click());
    }

//    public static ViewInteraction errorMessageWrongLoginOrPassword = onView(withText(Data.errorMessageWrongLoginOrPassword))
//            .inRoot(withDecorView(not(getActivity().getWindow().getDecorView()))) .check(matches(isDisplayed()))





}


