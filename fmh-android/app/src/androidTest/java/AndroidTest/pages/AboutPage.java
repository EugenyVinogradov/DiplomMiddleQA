package AndroidTest.pages;

import static androidx.fragment.app.FragmentManager.TAG;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withId;


import static org.hamcrest.CoreMatchers.allOf;
import static org.junit.Assert.assertEquals;

import android.util.Log;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.ViewInteraction;

import org.junit.Assert;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import ru.iteco.fmhandroid.R;

public class AboutPage {

  public static ViewInteraction versionText = onView(allOf(withId(R.id.about_version_title_text_view)));
  public static ViewInteraction versionInfo = onView(allOf(withId(R.id.about_company_info_label_text_view)));
  public static ViewInteraction aboutInfo = onView(allOf(withId(R.id.about_company_info_label_text_view)));
  public static ViewInteraction backButton = onView(allOf(withId(R.id.about_back_image_button)));

  public static void webPageExistence(String url) {
    try {
      OkHttpClient client = new OkHttpClient();
      Request request = new Request.Builder()
          .url(url)
          .build();
      Response response = client.newCall(request).execute();
      int statusCode = response.code();
      assertEquals(200, statusCode);
    } catch (Exception e) {
      Log.d(TAG, e.getMessage());
      Assert.assertTrue(false);
    } finally {
      Espresso.pressBack();
    }
  }
}
