package AndroidTest.pages;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.RootMatchers.isPlatformPopup;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.Matchers.is;

import android.widget.Button;

import androidx.test.espresso.ViewInteraction;

import ru.iteco.fmhandroid.R;

public class NewClaim {

    public static ViewInteraction tittleField = onView(withId(R.id.title_edit_text));
    public static int tittleFieldID = R.id.title_edit_text;
    public static ViewInteraction executorField = onView(withId(R.id.textinput_placeholder));
    public static ViewInteraction executorMenuButton = onView(allOf(withId(R.id.text_input_end_icon),
        withContentDescription("Show dropdown menu")));
    public static ViewInteraction dateField = onView(withId(R.id.date_in_plan_text_input_edit_text));
    public static ViewInteraction timeField = onView(withId(R.id.time_in_plan_text_input_edit_text));
    public static ViewInteraction descriptionField = onView(withId(R.id.description_edit_text));
    public static ViewInteraction saveButton = onView(withId(R.id.save_button));
    public static ViewInteraction cancelButton = onView(withId(R.id.cancel_button));
    public static ViewInteraction confirmCancelButtonOk = onView(allOf(withId(android.R.id.button1)));
    public static ViewInteraction notConfirmCancelButtonOk = onView(allOf(withId(android.R.id.button2)));
}
