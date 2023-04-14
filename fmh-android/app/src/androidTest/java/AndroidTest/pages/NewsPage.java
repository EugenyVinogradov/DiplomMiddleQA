package AndroidTest.pages;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import androidx.test.espresso.ViewInteraction;

import ru.iteco.fmhandroid.R;

public class NewsPage {
    public static ViewInteraction editNewsButton = onView(withId(R.id.edit_news_material_button));
    public static int editNewsButtonID = R.id.edit_news_material_button;
}
