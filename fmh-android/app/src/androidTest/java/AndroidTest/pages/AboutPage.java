package AndroidTest.pages;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.allOf;

import androidx.test.espresso.ViewInteraction;

import ru.iteco.fmhandroid.R;

public class AboutPage {

    public static ViewInteraction aboutInfo = onView(allOf(withId(R.id.about_company_info_label_text_view)));
    public static ViewInteraction backButton = onView(allOf(withId(R.id.about_back_image_button)));
}
