package AndroidTest.data;

import android.content.Context;
import android.content.res.Resources;
import android.os.IBinder;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.TextView;


import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicReference;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.actionWithAssertions;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.core.internal.deps.guava.base.Preconditions.checkNotNull;
import static androidx.test.espresso.matcher.RootMatchers.isPlatformPopup;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import static AndroidTest.data.DataHelper.RecyclerViewMatcher.withRecyclerView;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.test.espresso.IdlingRegistry;
import androidx.test.espresso.IdlingResource;
import androidx.test.espresso.NoMatchingViewException;
import androidx.test.espresso.PerformException;
import androidx.test.espresso.Root;
import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;
import androidx.test.espresso.ViewAssertion;
import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.BoundedMatcher;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.espresso.util.HumanReadables;
import androidx.test.espresso.util.TreeIterables;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.platform.app.InstrumentationRegistry;


import net.datafaker.Faker;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.StringDescription;
import org.hamcrest.TypeSafeMatcher;

import AndroidTest.pages.AuthPage;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.AppActivity;

public class DataHelper {

    public static ActivityScenarioRule<AppActivity> mActivityScenarioRule =
        new ActivityScenarioRule<>(AppActivity.class);




    public static ViewAction waitDisplayed(final int viewId, final long millis) {
        return new ViewAction() {
            @Override
            public Matcher<View> getConstraints() {
                return isRoot();
            }

            @Override
            public String getDescription() {
                return "wait for a specific view with id <" + viewId + "> has been displayed during " + millis + " millis.";
            }

            @Override
            public void perform(final UiController uiController, final View view) {
                uiController.loopMainThreadUntilIdle();
                final long startTime = System.currentTimeMillis();
                final long endTime = startTime + millis;
                final Matcher<View> matchId = withId(viewId);
                final Matcher<View> matchDisplayed = isDisplayed();

                do {
                    for (View child : TreeIterables.breadthFirstViewTraversal(view)) {
                        if (matchId.matches(child) && matchDisplayed.matches(child)) {
                            return;
                        }
                    }
                    uiController.loopMainThreadForAtLeast(50);
                }
                while (System.currentTimeMillis() < endTime);
                throw new PerformException.Builder()
                        .withActionDescription(this.getDescription())
                        .withViewDescription(HumanReadables.describe(view))
                        .withCause(new TimeoutException())
                        .build();
            }
        };
    }



    public static ViewAction waitUntil(Matcher<View> matcher) {
        return actionWithAssertions(new ViewAction() {
            @Override public Matcher<View> getConstraints() {
                return isAssignableFrom(View.class);
            }

            @Override public String getDescription() {
                StringDescription description = new StringDescription();
                matcher.describeTo(description);
                return String.format("wait until: %s", description);
            }

            @Override public void perform(UiController uiController, View view) {
                if (!matcher.matches(view)) {
                    LayoutChangeCallback callback = new LayoutChangeCallback(matcher);
                    try {
                        IdlingRegistry.getInstance().register(callback);
                        view.addOnLayoutChangeListener(callback);
                        uiController.loopMainThreadUntilIdle();
                    } finally {
                        view.removeOnLayoutChangeListener(callback);
                        IdlingRegistry.getInstance().unregister(callback);
                    }
                }
            }
        });
    }

    private static class LayoutChangeCallback implements IdlingResource, View.OnLayoutChangeListener {

        private Matcher<View> matcher;
        private IdlingResource.ResourceCallback callback;
        private boolean matched = false;

        LayoutChangeCallback(Matcher<View> matcher) {
            this.matcher = matcher;
        }

        @Override public String getName() {
            return "Layout change callback";
        }

        @Override public boolean isIdleNow() {
            return matched;
        }

        @Override public void registerIdleTransitionCallback(ResourceCallback callback) {
            this.callback = callback;
        }

        @Override public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
            matched = matcher.matches(v);
            callback.onTransitionToIdle();
        }
    }

    public static void waitElement(int id) {
        onView(isRoot()).perform(waitDisplayed(id, 5000));
    }

    public static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }

    public static void waitUntilVisible(View view) {
        final CountDownLatch latch = new CountDownLatch(1);
        ViewTreeObserver observer = view.getViewTreeObserver();
        observer.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                if (view.isShown()) {
                    latch.countDown();
                }
            }
        });
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static class ToastMatcher extends TypeSafeMatcher<Root> {

        @Override
        public void describeTo(Description description) {
            description.appendText("is toast");
        }

        @Override
        public boolean matchesSafely(Root root) {
            int type = root.getWindowLayoutParams().get().type;
            if ((type == WindowManager.LayoutParams.TYPE_TOAST)) {
                IBinder windowToken = root.getDecorView().getWindowToken();
                IBinder appToken = root.getDecorView().getApplicationWindowToken();
                if (windowToken == appToken) {
                    return true;
                }
            }
            return false;
        }
    }

    public static ViewInteraction emptyToast(int id) {
        return onView(withText(id)).inRoot(new DataHelper.ToastMatcher());
    }

    public static ViewInteraction checkToast(int id, boolean visible) {
        if (visible) {
            emptyToast(id).check(matches(isDisplayed()));
        } else {
            emptyToast(id).check(matches(not(isDisplayed())));
        }
        return null;
    }

    public static void waitUntilVisible(ViewInteraction inRoot) {
    }
    public static Matcher<Root> isPopupWindow() {
        return isPlatformPopup();
    }

    public static class RecyclerViewAssertions {
        public static ViewAssertion withRowContaining(final Matcher<View> viewMatcher) {
            assertNotNull(viewMatcher);

            return new ViewAssertion() {


                @Override
                public void check(View view, NoMatchingViewException noViewException) {
                    if (noViewException != null) {
                        throw noViewException;
                    }

                    assertTrue(view instanceof RecyclerView);

                    RecyclerView recyclerView = (RecyclerView) view;
                    final RecyclerView.Adapter adapter = recyclerView.getAdapter();
                    for (int position = 0; position < adapter.getItemCount(); position++) {
                        int itemType = adapter.getItemViewType(position);
                        RecyclerView.ViewHolder viewHolder = adapter.createViewHolder(recyclerView, itemType);
                        adapter.bindViewHolder(viewHolder, position);

                        if (viewHolderMatcher(hasDescendant(viewMatcher)).matches(viewHolder)) {
                            return;
                        }
                    }

                    fail("No match found");
                }
            };
        }

        public static Matcher<RecyclerView.ViewHolder> viewHolderMatcher(final Matcher<View> itemViewMatcher) {
            return new TypeSafeMatcher<RecyclerView.ViewHolder>() {

                @Override
                public boolean matchesSafely(RecyclerView.ViewHolder viewHolder) {
                    return itemViewMatcher.matches(viewHolder.itemView);
                }

                @Override
                public void describeTo(Description description) {
                    description.appendText("holder with view: ");
                    itemViewMatcher.describeTo(description);
                }
            };
        }
    }
    public static int getCountFromRecyclerView(@IdRes int RecyclerViewId) {
        final int[] COUNT = {0};
        Matcher matcher = new TypeSafeMatcher<View>() {
            @Override
            protected boolean matchesSafely(View item) {
                COUNT[0] = ((RecyclerView) item).getAdapter().getItemCount();
                return true;
            }
            @Override
            public void describeTo(Description description) {}
        };
        onView(allOf(withId(RecyclerViewId),isDisplayed())).check(matches(matcher));
        return COUNT[0];
    }
    public static Matcher<View> hasItem(Matcher<View> matcher) {
        return new BoundedMatcher<View, RecyclerView>(RecyclerView.class) {

            @Override public void describeTo(Description description) {
                description.appendText("has item: ");
                matcher.describeTo(description);
            }

            @Override protected boolean matchesSafely(RecyclerView view) {
                RecyclerView.Adapter adapter = view.getAdapter();
                for (int position = 0; position < adapter.getItemCount(); position++) {
                    int type = adapter.getItemViewType(position);
                    RecyclerView.ViewHolder holder = adapter.createViewHolder(view, type);
                    adapter.onBindViewHolder(holder, position);
                    if (matcher.matches(holder.itemView)) {
                        return true;
                    }
                }
                return false;
            }
        };
    }
    public static class RecyclerViewMatcher {
        private final int recyclerViewId;

        public static RecyclerViewMatcher withRecyclerView(final int recyclerViewId) {
            return new RecyclerViewMatcher(recyclerViewId);
        }

        public RecyclerViewMatcher(int recyclerViewId) {
            this.recyclerViewId = recyclerViewId;
        }

        public Matcher<View> atPosition(final int position) {
            return atPositionOnView(position, -1);
        }

        public Matcher<View> atPositionOnView(final int position, final int targetViewId) {

            return new TypeSafeMatcher<View>() {
                Resources resources = null;
                View childView;

                public void describeTo(Description description) {
                    String idDescription = Integer.toString(recyclerViewId);
                    if (this.resources != null) {
                        try {
                            idDescription = this.resources.getResourceName(recyclerViewId);
                        } catch (Resources.NotFoundException var4) {
                            idDescription = String.format("%s (resource not found)", recyclerViewId);
                        }
                    }

                    description.appendText("with id: " + idDescription);
                }

                public boolean matchesSafely(View view) {

                    this.resources = view.getResources();

                    if (childView == null) {
                        RecyclerView recyclerView = view.getRootView().findViewById(recyclerViewId);
                        if (recyclerView != null && recyclerView.getId() == recyclerViewId) {
                            RecyclerView.ViewHolder viewHolder = recyclerView.findViewHolderForAdapterPosition(position);
                            if (viewHolder != null) {
                                childView = viewHolder.itemView;
                            }
                        }
                        else {
                            return false;
                        }
                    }

                    if (targetViewId == -1) {
                        return view == childView;
                    } else {
                        View targetView = childView.findViewById(targetViewId);
                        return view == targetView;
                    }
                }
            };
        }
    }

    public static class RecyclerViewItemCountIdlingResource implements IdlingResource {
        private final RecyclerView recyclerView;
        private final int expectedCount;
        private volatile ResourceCallback resourceCallback;

        public RecyclerViewItemCountIdlingResource(RecyclerView recyclerView) {
            this.recyclerView = recyclerView;
            this.expectedCount = recyclerView.getAdapter().getItemCount();
        }

        @Override
        public String getName() {
            return RecyclerViewItemCountIdlingResource.class.getName();
        }

        @Override
        public boolean isIdleNow() {
            int currentCount = recyclerView.getAdapter().getItemCount();
            boolean idle = currentCount == expectedCount;
            if (idle && resourceCallback != null) {
                resourceCallback.onTransitionToIdle();
            }
            return idle;
        }

        @Override
        public void registerIdleTransitionCallback(ResourceCallback callback) {
            this.resourceCallback = callback;
        }
    }
//
//    public static int getItemCount() {
//        AtomicReference<Integer> count = new AtomicReference<>(0);
//        mActivityScenarioRule.getScenario().onActivity(activity -> {
//            RecyclerView recyclerView = activity.findViewById(R.id.claim_list_recycler_view);
//            count.set(recyclerView.getAdapter().getItemCount());
//        });
//        return count.get();
//    }

    public static Matcher<View> atPosition(final int position, @NonNull final Matcher<View> itemMatcher) {
        return new BoundedMatcher<View, RecyclerView>(RecyclerView.class) {
            @Override
            public void describeTo(Description description) {
                description.appendText("has item at position " + position + ": ");
                itemMatcher.describeTo(description);
            }

            @Override
            protected boolean matchesSafely(final RecyclerView recyclerView) {
                final RecyclerView.ViewHolder viewHolder = recyclerView.findViewHolderForAdapterPosition(position);
                if (viewHolder == null) {
                    // has no item on such position
                    return false;
                }
                return itemMatcher.matches(viewHolder.itemView);
            }
        };
    }
    public static String getTextFromViewInteraction(ViewInteraction viewInteraction) {
        final String[] text = {""};
        viewInteraction.perform(new ViewAction() {
            @Override
            public Matcher<View> getConstraints() {
                return isAssignableFrom(TextView.class);
            }

            @Override
            public String getDescription() {
                return "Getting text from ViewInteraction";
            }

            @Override
            public void perform(UiController uiController, View view) {
                TextView textView = (TextView) view;
                text[0] = textView.getText().toString();
            }
        });
        return text[0];
    }

    public static String getStringFromResource(int resourceId) {
        Context targetContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        return targetContext.getResources().getString(resourceId);
    }
    public static int getRecyclerViewItemCount(@IdRes int recyclerViewId) {
        final int[] count = new int[1];
        onView(allOf(withId(recyclerViewId), isDisplayed()))
            .check((view, noViewFoundException) -> {
                if (view instanceof RecyclerView) {
                    RecyclerView recyclerView = (RecyclerView) view;
                    RecyclerView.Adapter adapter = recyclerView.getAdapter();
                    if (adapter != null) {
                        count[0] = adapter.getItemCount();
                    }
                }
            });
        return count[0];
    }
    public static String getItemDateText(int recyclerViewId, int position) {
        final String[] itemDateText = new String[1];
        onView(withRecyclerView(recyclerViewId).atPosition(position))
            .check((view, noViewFoundException) -> {
                if (noViewFoundException != null) {
                    throw noViewFoundException;
                }
                TextView dateTextView = view.findViewById(R.id.news_item_date_text_view);
                itemDateText[0] = dateTextView.getText().toString();
            });
        return itemDateText[0];
    }
    public static String getItemDateText2(int recyclerViewId, int position) {
        final String[] itemDateText = new String[1];
        onView(withRecyclerView(recyclerViewId).atPosition(position))
            .check((view, noViewFoundException) -> {
                if (noViewFoundException != null) {
                    throw noViewFoundException;
                }
                TextView dateTextView = view.findViewById(R.id.news_item_publication_date_text_view);
                itemDateText[0] = dateTextView.getText().toString();
            });
        return itemDateText[0];
    }
    public static String getTextFromNews(int fieldId, int position) {
        final String[] itemDateText = new String[1];
        onView(withRecyclerView(R.id.news_list_recycler_view).atPosition(position))
            .check((view, noViewFoundException) -> {
                if (noViewFoundException != null) {
                    throw noViewFoundException;
                }
                TextView dateTextView = view.findViewById(fieldId);
                itemDateText[0] = dateTextView.getText().toString();
            });
        return itemDateText[0];
    }
    public static class GetHeightAfterClickViewAction implements ViewAction {

        private int[] heightAfterClick;

        public GetHeightAfterClickViewAction(int[] heightAfterClick) {
            this.heightAfterClick = heightAfterClick;
        }

        @Override
        public Matcher<View> getConstraints() {
            return isAssignableFrom(RecyclerView.class);
        }

        @Override
        public String getDescription() {
            return "Get height after click";
        }

        @Override
        public void perform(UiController uiController, View view) {
            RecyclerView recyclerView = (RecyclerView) view;
            View firstItem = recyclerView.getChildAt(0);
            if (firstItem != null) {
                heightAfterClick[0] = firstItem.getHeight();
            }
        }
    }
    public static int getViewHeight(int recyclerViewId, int position) {
        final int[] height = {0};
        onView(withId(recyclerViewId)).perform(RecyclerViewActions.scrollToPosition(position));
        onView(withId(recyclerViewId)).perform(RecyclerViewActions.actionOnItemAtPosition(position, new ViewAction() {
            @Override
            public Matcher<View> getConstraints() {
                return isAssignableFrom(View.class);
            }

            @Override
            public String getDescription() {
                return "Get height of item";
            }

            @Override
            public void perform(UiController uiController, View view) {
                height[0] = view.getHeight();
            }
        }));
        return height[0];
    }
    public static ViewAction waitForView(final long millis) {
        return new ViewAction() {
            @Override
            public Matcher<View> getConstraints() {
                return isRoot();
            }

            @Override
            public String getDescription() {
                return "Ожидание определенного представления";
            }

            @Override
            public void perform(UiController uiController, View view) {
                uiController.loopMainThreadUntilIdle();
                final long startTime = System.currentTimeMillis();
                final long endTime = startTime + millis;
                while (System.currentTimeMillis() < endTime) {
                    // Ничего не делать и ждать
                }
            }
        };
    }

}
