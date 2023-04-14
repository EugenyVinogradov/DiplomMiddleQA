package AndroidTest.pages;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.Matchers.allOf;

import static AndroidTest.pages.NewClaim.dateField;
import static AndroidTest.pages.NewClaim.descriptionField;
import static AndroidTest.pages.NewClaim.executorField;
import static AndroidTest.pages.NewClaim.saveButton;
import static AndroidTest.pages.NewClaim.timeField;
import static AndroidTest.pages.NewClaim.tittleField;

import androidx.test.espresso.ViewInteraction;

import ru.iteco.fmhandroid.R;

public class ClimesPage {

    public static ViewInteraction newClimeButton = onView(withId(R.id.add_new_claim_material_button));
    public static ViewInteraction filterClimesButton = onView(withId(R.id.filters_material_button));
    public static int filterClimesButtonID = R.id.filters_material_button;

    public static void createNewClime(String tittle, String executor, String date, String time,String description) {
        tittleField.perform(replaceText(tittle));
        executorField.perform(replaceText(executor));
        dateField.perform(replaceText(date));
        timeField.perform(replaceText(time));
        descriptionField.perform(replaceText(description));
//        saveButton.perform(click());
    }
}
