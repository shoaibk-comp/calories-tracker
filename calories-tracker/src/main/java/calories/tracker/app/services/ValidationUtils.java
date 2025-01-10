package calories.tracker.app.services;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;

import java.util.regex.Pattern;

/**
 * Validation utility methods
 */
public final class ValidationUtils {

    private ValidationUtils() {
        throw new UnsupportedOperationException("Utility classes cannot be instantiated");
    }

    /**
     * Asserts that the given string is not blank.
     *
     * @param input the input string to validate
     * @param message the error message to throw if the input is blank
     */
    public static void assertNotBlank(String input, String message) {
        Validate.notBlank(input, message);
    }

    /**
     * Asserts that the given string has a minimum length.
     *
     * @param input the input string to validate
     * @param minLength the minimum length of the input string
     * @param message the error message to throw if the input is too short
     */
    public static void assertMinimumLength(String input, int minLength, String message) {
        if (StringUtils.length(input) < minLength) {
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * Asserts that the given string matches the specified regular expression.
     *
     * @param input the input string to validate
     * @param regex the regular expression to match
     * @param message the error message to throw if the input does not match
     */
    public static void assertMatches(String input, Pattern regex, String message) {
        if (!regex.matcher(input).matches()) {
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * Asserts that the given string is a valid email address.
     *
     * @param email the input email address to validate
     * @param message the error message to throw if the input is not a valid email address
     */
    public static void assertValidEmail(String email, String message) {
        String emailRegex = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";
        Pattern pattern = Pattern.compile(emailRegex);
        assertMatches(email, pattern, message);
    }

    /**
     * Asserts that the given string is a valid phone number.
     *
     * @param phoneNumber the input phone number to validate
     * @param message the error message to throw if the input is not a valid phone number
     */
    public static void assertValidPhoneNumber(String phoneNumber, String message) {
        String phoneRegex = "^\\(?([0-9]{3})\\)?[-.\\s]?([0-9]{3})[-.\\s]?([0-9]{4})$";
        Pattern pattern = Pattern.compile(phoneRegex);
        assertMatches(phoneNumber, pattern, message);
    }
}
