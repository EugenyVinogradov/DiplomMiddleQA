package AndroidTest.data;

import net.datafaker.Faker;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
    public static final String tittleClaimOneCharacter = faker.letterify("?");
    public static final String tittleClaimFortyNineCharacter = "MyDiplom_"+faker.text().text(40);
    public static final String tittleClaimFiftyCharacter = "MyDiplom_"+faker.text().text(41);
    public static final String tittleClaimFiftyOneCharacter = "MyDiplom_"+faker.text().text(42);
    public static final String newTittleClaim = "MyDiplom_"+ faker.number().numberBetween(1, 9999999) + "new";
    public static final String dateClaim = formatterDate.format(date.plusDays(1));
    public static final String newDateClaim = formatterDate.format(date.plusDays(2));
    public static final String timeClaim = formatterTime.format(date.plusMinutes(2));
    public static final String newTimeClaim = formatterTime.format(date.plusMinutes(4));
    public static final String descriptionClaim = faker.beer().toString();
    public static final String newDescriptionClaim = faker.rickAndMorty().toString();
    public static final String executor = "Ivanov Ivan Ivanovich";
    public static final String commentClaim = "comment_"+ faker.number().numberBetween(1, 9999);
    public static final String commentClaimEditind = "Comment_"+ faker.number().numberBetween(1, 9999);





}
