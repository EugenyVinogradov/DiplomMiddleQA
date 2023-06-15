package AndroidTest.steps;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.contrib.RecyclerViewActions.scrollToPosition;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import androidx.test.espresso.ViewInteraction;

import io.qameta.allure.kotlin.Allure;
import io.qameta.allure.kotlin.Step;
import ru.iteco.fmhandroid.R;

public class QuotesPageSteps {

  /*   Шаги для страницы Цитаты */

  @Step("Получаем recyclerView для раздела Цитаты и прокручиваем до его первой позиции")
  public static ViewInteraction getQuotesRecyclerViewAndScrollToFirstPosition() {
    Allure.step("Получаем recyclerView для раздела Цитаты и прокручиваем до его первой позиции");
    ViewInteraction recyclerView = onView(withId(R.id.our_mission_item_list_recycler_view));
    recyclerView.perform(scrollToPosition(0));
    return recyclerView;
  }
}
