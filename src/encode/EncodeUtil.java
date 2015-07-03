package encode;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EncodeUtil {
	private static Pattern HANGLE_PATTERN = Pattern
			.compile("[\\x{ac00}-\\x{d7af}]");

	public static String encodeIfNeed(String input) {
		Matcher matcher = HANGLE_PATTERN.matcher(input);
		while (matcher.find()) {
			String group = matcher.group();

			try {
				input = input.replace(group, URLEncoder.encode(group, "UTF-8"));
			} catch (UnsupportedEncodingException ignore) {
			}
		}

		return input;
	}
}
