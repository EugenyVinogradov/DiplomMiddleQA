package AndroidTest.pages;

import static androidx.test.espresso.Espresso.closeSoftKeyboard;
import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withSpinnerText;

import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.hasToString;
import static java.lang.Thread.sleep;
import static AndroidTest.pages.FilterNews.categoryNewsButton;
import static AndroidTest.pages.FilterNews.categoryNewsField;
import static AndroidTest.pages.FilterNews.dateEndPublish;
import static AndroidTest.pages.FilterNews.dateStartPublish;
import static AndroidTest.pages.FilterNews.filterButton;

import androidx.test.espresso.ViewInteraction;

import com.google.android.material.textview.MaterialTextView;

import ru.iteco.fmhandroid.R;

public class NewsPage {
    public static ViewInteraction sortingNewsButton = onView(withId(R.id.sort_news_material_button));
    public static ViewInteraction editNewsButton = onView(withId(R.id.edit_news_material_button));
    public static int editNewsButtonID = R.id.edit_news_material_button;
    public static ViewInteraction filterNewsButton = onView(withId(R.id.filter_news_material_button));

    public static ViewInteraction tittleText = onView(withId(R.id.news_item_title_text_view));
    public static ViewInteraction descriptionText = onView(withId(R.id.news_item_description_text_view));
    public static void sortingNews() {
        sortingNewsButton.perform(click());
    }

    public static void filterNews(String text) throws InterruptedException {
        filterNewsButton.perform(click());
        categoryNewsField.perform(replaceText(text));
        filterButton.perform(click());
    }
    public static void filterNewsByDate(String startDate, String endDate) {
        filterNewsButton.perform(click());
        dateStartPublish.perform(replaceText(startDate));
        dateEndPublish.perform(replaceText(endDate));
        filterButton.perform(click());
    }

    public static void goToNewsEditingPage() {
        editNewsButton.perform(click());
    }
}
