package myutilities;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidationChecks {

    private final Pattern EMAIL_PATTERN = Pattern.compile("^[_A-Za-z0-9-.]+([A-Za-z0-9-_.]+)*@[A-Za-z0-9-]+(.[A-Za-z0-9-]+)*(.[A-Za-z]{2,})$");
    private final Pattern NAME_PATTERN = Pattern.compile("^[a-zA-Z]{3,20}$");
    private final Pattern USERNAME_PATTERN = Pattern.compile("^[a-zA-Z0-9_.]{3,20}$");
    private final Pattern IP_PATTERN = Pattern.compile("^([1-9]|[1-9][0-9]|1[0-9][0-9]|2[0-4][0-9]|25[0-5]).([0-9]|[1-9][0-9]|1[0-9][0-9]|2[0-4][0-9]|25[0-5]).([0-9]|[1-9][0-9]|1[0-9][0-9]|2[0-4][0-9]|25[0-5]).([0-9]|[1-9][0-9]|1[0-9][0-9]|2[0-4][0-9]|25[0-5])$");
    private Matcher patternMatcher;

    public boolean isEmptyString(String string) {
        boolean emptyString = false;
        if (string.trim().length() == 0 || string.equals(null) || "".equals(string.trim())) {
            emptyString = true;
        }
        return emptyString;
    }

    public boolean isEmail(String email) {
        patternMatcher = EMAIL_PATTERN.matcher(email);
        return !isEmptyString(email) && patternMatcher.matches();
    }

    public boolean isName(String name) {
        patternMatcher = NAME_PATTERN.matcher(name);
        return !isEmptyString(name) && patternMatcher.matches();
    }

    public boolean isUserName(String username) {
        patternMatcher = USERNAME_PATTERN.matcher(username);
        return !isEmptyString(username) && patternMatcher.matches();
    }

    public boolean isIP(String ip) {
        patternMatcher = IP_PATTERN.matcher(ip);
        return patternMatcher.matches();
    }

    /*public boolean isLegalAged(Date dateOfBirth){
        boolean legalAge = false;
        if(dateOfBirth.getYear() >= 15){
            legalAge = true;
        }
    
        return legalAge;
    }*/
    public boolean isValidPassword(String pass) {
        boolean validPass = false;
        if (pass.length() >= 8 && pass.length() <= 30) {
            validPass = true;
        }
        return validPass;
    }

    public boolean isMatchPassword(String pass, String confirmPass) {
        boolean matchPass = false;
        if (pass.equals(confirmPass)) {
            matchPass = true;
        }
        return matchPass;
    }

}
