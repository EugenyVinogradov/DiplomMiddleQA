package AndroidTest.data;

import net.datafaker.Faker;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

public class Data {
    private static Faker faker = new Faker();
    private static LocalDateTime date = LocalDateTime.now();
    private static DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private  static DateTimeFormatter formatterTime = DateTimeFormatter.ofPattern("hh:mm");



    /* Данные для авторизации */
    public static final String correctLogin = "login2";
    public static final String correctPassword = "password2";
    public static final String wrongLogin = "login";
    public static final String wrongPassword = "password";

    /* Данные для создания заявок    */

    public static final String tittleClaim = "MyDiplom_"+ faker.number().numberBetween(1, 9999999);
    public static final String dateClaim = formatterDate.format(date.plusDays(1));
    public static final String timeClaim = formatterTime.format(date);
    public static final String descriptionClaim = faker.beer().toString();
    public static final String executor = "Ivanov Ivan Ivanovich";


}
