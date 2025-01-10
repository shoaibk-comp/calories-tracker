package calories.tracker.app.model;

import javax.persistence.*;
import java.sql.Time;
import java.util.Date;

/**
 * The Meal JPA entity
 */
@Entity
@Table(name = "MEALS")
public class Meal extends AbstractEntity {

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "date")
    private Date date;

    @Column(name = "time")
    private Time time;

    @Column(name = "description")
    private String description;

    @Column(name = "calories")
    private Long calories;

    public Meal() {

    }

    public Meal(User user, Date date, Time time, String description, Long calories) {
        this.user = user;
        this.date = date;
        this.time = time;
        this.description = description;
        this.calories = calories;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getCalories() {
        return calories;
    }

    public void setCalories(Long calories) {
        this.calories = calories;
    }
}
