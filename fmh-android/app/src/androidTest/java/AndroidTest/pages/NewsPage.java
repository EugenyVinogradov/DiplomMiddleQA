package AndroidTest.pages;

import static androidx.test.espresso.Espresso.closeSoftKeyboard;
import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static androidx.test.espresso.matcher.ViewMatchers.isChecked;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isNotChecked;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withSpinnerText;

import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.hasToString;
import static java.lang.Thread.sleep;
import static AndroidTest.data.DataHelper.getRecyclerViewItemCount;
import static AndroidTest.data.DataHelper.getTextFromNews;
import static AndroidTest.data.DataHelper.waitElement;
import static AndroidTest.pages.FilterNews.categoryNewsButton;
import static AndroidTest.pages.FilterNews.categoryNewsField;
import static AndroidTest.pages.FilterNews.checkboxActive;
import static AndroidTest.pages.FilterNews.checkboxNotActive;
import static AndroidTest.pages.FilterNews.dateEndPublish;
import static AndroidTest.pages.FilterNews.dateStartPublish;
import static AndroidTest.pages.FilterNews.filterButton;

import androidx.recyclerview.widget.RecyclerView;
import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import com.google.android.material.textview.MaterialTextView;

import java.lang.reflect.Array;
import java.util.ArrayList;

import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.AppActivity;

public class NewsPage {
    public static ViewInteraction sortingNewsButton = onView(withId(R.id.sort_news_material_button));
    public static ViewInteraction editNewsButton = onView(withId(R.id.edit_news_material_button));
    public static int editNewsButtonID = R.id.edit_news_material_button;
    public static ViewInteraction filterNewsButton = onView(withId(R.id.filter_news_material_button));

    public static ViewInteraction tittleText = onView(withId(R.id.news_item_title_text_view));
    public static ViewInteraction descriptionText = onView(withId(R.id.news_item_description_text_view));

    public static ActivityScenarioRule<AppActivity> myActivityScenarioRule =
        new ActivityScenarioRule<>(AppActivity.class);
    public static void sortingNews() {
        sortingNewsButton.perform(click());
    }

    public static void filterNewsByTittle(String text) throws InterruptedException {
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
    public static void filterNewsByStatus(boolean active, boolean notActive) {
        filterNewsButton.perform(click());
        if(!active) {
            checkboxActive.perform(click());
        }
        if(!notActive) {
            checkboxNotActive.perform(click());
        }
        filterButton.perform(click());
    }
    public static void filterNewsByStatusAndDate(boolean active, boolean notActive, String startDate, String endDate) {
        filterNewsButton.perform(click());
        if(!active) {
            checkboxActive.perform(click());
        }
        if(!notActive) {
            checkboxNotActive.perform(click());
        }
        dateStartPublish.perform(replaceText(startDate));
        dateEndPublish.perform(replaceText(endDate));
        filterButton.perform(click());
    }

    public static void goToNewsEditingPage() {
        editNewsButton.perform(click());
    }
    public static void scrollNewsToPosition (int position) {
        waitElement(R.id.news_list_recycler_view);
        onView(withId(R.id.news_list_recycler_view)).perform(RecyclerViewActions.scrollToPosition(position));
    }

    public static int getNewsCount(ActivityScenarioRule<AppActivity> myActivityScenarioRule) {
        final int[] itemCount = new int[1];
        myActivityScenarioRule.getScenario().onActivity(activity -> {
            RecyclerView recyclerView = activity.findViewById(R.id.news_list_recycler_view);
            itemCount[0] = recyclerView.getAdapter().getItemCount();
        });
        return itemCount[0];
    }

}
