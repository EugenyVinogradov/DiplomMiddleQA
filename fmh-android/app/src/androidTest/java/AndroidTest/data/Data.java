package AndroidTest.data;


import static AndroidTest.data.DataHelper.getStringFromResource;

import net.datafaker.Faker;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import ru.iteco.fmhandroid.R;

public class Data {
  private static Faker faker = new Faker();
  private static LocalDateTime date = LocalDateTime.now();
  private static DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("dd.MM.yyyy");
  private static DateTimeFormatter formatterTime = DateTimeFormatter.ofPattern("hh:mm");


  /* Данные для авторизации */
  public static final String correctLogin = "login2";
  public static final String correctPassword = "password2";
  public static final String wrongLogin = "login";
  public static final String wrongPassword = "password";

  /* Данные для создания заявок    */

  public static final String tittleClaim = "MyDiplom_" + faker.number().numberBetween(1, 9999999);
  public static final String tittleClaimOneCharacter = faker.letterify("?");
  public static final String tittleClaimFortyNineCharacter = "MyDiplom_" + faker.text().text(40);
  public static final String tittleClaimFiftyCharacter = "MyDiplom_" + faker.text().text(41);
  public static final String tittleClaimFiftyOneCharacter = "MyDiplom_" + faker.text().text(42);
  public static final String newTittleClaim = "MyDiplom_" + faker.number().numberBetween(1, 9999999) + "new";
  public static final String dateClaim = formatterDate.format(date.plusDays(1));
  public static final String newDateClaim = formatterDate.format(date.plusDays(2));
  public static final String timeClaim = formatterTime.format(date.plusMinutes(2));
  public static final String newTimeClaim = formatterTime.format(date.plusMinutes(4));
  public static final String descriptionClaim = "Description" + faker.beer().toString();
  public static final String newDescriptionClaim = "Description" + faker.rickAndMorty().toString();
  public static final String commentClaim = "Comment_" + faker.number().numberBetween(1, 9999);
  public static final String commentClaimEditind = "Comment_" + faker.number().numberBetween(1, 9999);


  /*    Страница о приложении   */

  public static final String urlPrivacyPolicy = "https://vhospice.org/#/privacy-policy/";
  public static final String urlTermsOfUse = "https://vhospice.org/#/terms-of-use";

  /* Данные для создания новостей    */

  public static final String categoryFirst = "Объявление";
  public static final String categorySecond = "День рождения";
  public static final String categoryThird = "Зарплата";
  public static final String categoryForth = "Профсоюз";
  public static final String categoryFifth = "Праздник";
  public static final String categorySixth = "Массаж";
  public static final String categorySeventh = "Благодарность";
  public static final String categoryEighth = "Нужна помощь";
  public static final String tittleNews = "Tittle_" + faker.number().numberBetween(1, 9999999);
  public static final String newTittleNews = "NewTittle_" + faker.number().numberBetween(1, 9999999);
  public static final String dateNews = formatterDate.format(date);
  public static final String dateNewsNextDay = formatterDate.format(date.plusDays(1));
  public static final String dateNewsPreviousDay = formatterDate.format(date.minusDays(1));
  public static final String timeNews = formatterTime.format(date);
  public static final String newTimeNews = formatterTime.format(date.plusHours(1));
  public static final String descriptionNews = "Description" + faker.simpsons().toString();
  public static final String newDescriptionNews = "NewDescription" + faker.simpsons().toString();
  public static final String statusActive = getStringFromResource(R.string.news_control_panel_active);
  public static final String statusNotActive = getStringFromResource(R.string.news_control_panel_not_active);
}
