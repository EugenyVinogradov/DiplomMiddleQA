package AndroidTest.pages;

import static androidx.test.espresso.Espresso.closeSoftKeyboard;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import androidx.test.espresso.ViewInteraction;

import ru.iteco.fmhandroid.R;

public class NewsPage {
    public static ViewInteraction sortingNewsButton = onView(withId(R.id.sort_news_material_button));
    public static ViewInteraction editNewsButton = onView(withId(R.id.edit_news_material_button));
    public static int editNewsButtonID = R.id.edit_news_material_button;

    public static ViewInteraction tittleText = onView(withId(R.id.news_item_title_text_view));
    public static ViewInteraction descriptionText = onView(withId(R.id.news_item_description_text_view));
    public static void sortingNews() {
        sortingNewsButton.perform(click());
    }
}
