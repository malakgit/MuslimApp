package hamood.malak.muslimapp.MyUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MyValidations {
    /**
     * מחלקה בודקת תקינות שדות כמו אימיל וסיסמה ועוד
     */
    private Pattern pattern;
    private Matcher matcher;
    private static final String PASSWORD_PATTERN = "((?=.[a-z])(?=.\\d)(?=.[A-Z])(?=.[@#$%!]).{8,40})";

    public MyValidations() {
        pattern = Pattern.compile(PASSWORD_PATTERN);
    }

    public boolean validatePassword(final String password) {

        matcher = pattern.matcher(password);
        return matcher.matches();
        //

    }


}
