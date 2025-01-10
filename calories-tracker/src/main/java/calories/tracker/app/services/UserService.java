package calories.tracker.app.services;

import calories.tracker.app.dao.UserRepository;
import calories.tracker.app.model.User;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.regex.Pattern;

import static calories.tracker.app.services.ValidationUtils.*;

/**
 * Business service for User entity related operations.
 */
@Service
public class UserService {

    private static final Logger LOGGER = Logger.getLogger(UserService.class);
    private static final Long DEFAULT_MAX_CAL_PER_DAY = 2000L;

    private static final Pattern PASSWORD_REGEX = Pattern.compile("(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{6,}");
    private static final Pattern EMAIL_REGEX = Pattern.compile(
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"
    );

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Updates the maximum calories of a given user.
     *
     * @param username       the currently logged in user
     * @param newMaxCalories the new max daily calories for the user
     */
    @Transactional
    public void updateUserMaxCaloriesPerDay(String username, Long newMaxCalories) {
        User user = userRepository.findByUsername(username);

        if (user != null) {
            user.setMaxCaloriesPerDay(newMaxCalories);
            userRepository.save(user);
            LOGGER.info("Updated max calories for user: " + username);
        } else {
            LOGGER.warn("User with username " + username + " not found. Max calories not updated.");
        }
    }

    /**
     * Creates a new user in the database.
     *
     * @param username the username of the new user
     * @param email    the user email
     * @param password the user plain text password
     */
    @Transactional
    public void createUser(String username, String email, String password) {
        assertNotBlank(username, "Username cannot be empty.");
        assertMinimumLength(username, 6, "Username must have at least 6 characters.");
        assertNotBlank(email, "Email cannot be empty.");
        assertMatches(email, EMAIL_REGEX, "Invalid email.");
        //assertValidEmail(email,"Invalid email.");
        assertNotBlank(password, "Password cannot be empty.");
        assertMatches(password, PASSWORD_REGEX, "Password must have at least 6 characters, with 1 numeric and 1 uppercase character.");

        if (!userRepository.isUsernameAvailable(username)) {
            throw new IllegalArgumentException("The username is not available.");
        }

        User user = new User(username, passwordEncoder.encode(password), email, DEFAULT_MAX_CAL_PER_DAY);
        userRepository.save(user);
        LOGGER.info("Created new user: " + username);
    }

    @Transactional(readOnly = true)
    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Transactional(readOnly = true)
    public Long findTodaysCaloriesForUser(String username) {
        return userRepository.findTodaysCaloriesForUser(username);
    }
}

