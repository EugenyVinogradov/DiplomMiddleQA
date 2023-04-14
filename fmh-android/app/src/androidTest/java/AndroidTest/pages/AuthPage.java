package AndroidTest.pages;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.supportsInputMethods;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.allOf;

import static AndroidTest.data.Data.correctLogin;
import static AndroidTest.data.Data.correctPassword;
import static AndroidTest.data.DataHelper.waitElement;

import androidx.test.espresso.ViewInteraction;

import ru.iteco.fmhandroid.R;

public class AuthPage {

    public static ViewInteraction loginField = onView(allOf(supportsInputMethods(), isDescendantOfA(withId(R.id.login_text_input_layout))));
    public static ViewInteraction passwordField = onView(allOf(supportsInputMethods(), isDescendantOfA(withId(R.id.password_text_input_layout))));
    public static ViewInteraction loginButton = onView(allOf(withId(R.id.enter_button)));
    public static int idSignInButton = R.id.enter_button;

    public static void login (String login, String password) {
        waitElement(AuthPage.idSignInButton);
        loginField.perform(replaceText(login));
        passwordField.perform(replaceText(password));
        loginButton.check(matches(isDisplayed())).perform(click());
    }

    public static void successLogin() {
        login(correctLogin,correctPassword);
    }

}


