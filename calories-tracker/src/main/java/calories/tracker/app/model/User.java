package calories.tracker.app.model;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * The User JPA entity.
 */
@Entity
@Table(name = "USERS")
public class User extends AbstractEntity {

    @NotBlank
    @Column(nullable = false, unique = true)
    private String username;

    @NotBlank
    @Column(nullable = false)
    private String passwordDigest;

    @Email
    @NotBlank
    @Column(nullable = false, unique = true)
    private String email;

    @NotNull
    @Column(nullable = false)
    private Long maxCaloriesPerDay;

    // User entity
    /*@OneToMany(mappedBy = "user")
    private List<Meal> meals;*/

    public User() {
        // Default constructor for JPA
    }

    public User(@NotBlank String username,
                @NotBlank String passwordDigest,
                @Email @NotBlank String email,
                @NotNull Long maxCaloriesPerDay) {
        this.username = username;
        this.passwordDigest = passwordDigest;
        this.email = email;
        this.maxCaloriesPerDay = maxCaloriesPerDay;
    }

    // Getters and setters

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswordDigest() {
        return passwordDigest;
    }

    public void setPasswordDigest(String passwordDigest) {
        this.passwordDigest = passwordDigest;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getMaxCaloriesPerDay() {
        return maxCaloriesPerDay;
    }

    public void setMaxCaloriesPerDay(Long maxCaloriesPerDay) {
        this.maxCaloriesPerDay = maxCaloriesPerDay;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", maxCaloriesPerDay=" + maxCaloriesPerDay +
                '}';
    }
}

