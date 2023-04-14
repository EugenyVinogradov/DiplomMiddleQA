package AndroidTest.data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

public class Data {
    static LocalDateTime date = LocalDateTime.now();
    public static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd");


    /* Данные для авторизации */
    public static final String correctLogin = "login2";
    public static final String correctPassword = "password2";
    public static final String wrongLogin = "login";
    public static final String wrongPassword = "password";

    /* Данные для создания заявок    */

    public static final String tittleClaim = "Нужна проверка";
    public static final String dateClaim = formatter.format(date.plusDays(1));

}
