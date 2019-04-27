package fa.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SearchMatcher {
  public static boolean matches(String pattern, String... searchables) {
    Pattern regex = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE);
    for (String searchable : searchables) {
      Matcher matcher = regex.matcher(searchable);
      if (matcher.find()) return true;
    }
    return false;
  }
}
