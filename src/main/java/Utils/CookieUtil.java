package Utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Optional;

public class CookieUtil {
    public static Optional<Cookie> findCookieByName(HttpServletRequest request, String cookieName){
        Cookie[] cookies = request.getCookies();
        Optional<Cookie> userCookie = cookies!=null
                ? Arrays.stream(cookies).filter(cookie -> cookie.getName().equals("userId")).findFirst()
                : Optional.empty();
        return userCookie;
    }
}
