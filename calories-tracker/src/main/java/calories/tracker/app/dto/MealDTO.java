package calories.tracker.app.dto;

import calories.tracker.app.dto.serialization.CustomTimeDeserializer;
import calories.tracker.app.dto.serialization.CustomTimeSerializer;
import calories.tracker.app.model.Meal;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.sql.Time;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * JSON serializable DTO containing Meal data.
 */
public class MealDTO {

    private Long id;

    @JsonFormat(pattern = "yyyy/MM/dd", timezone = "CET")
    private Date date;

    @JsonSerialize(using = CustomTimeSerializer.class)
    @JsonDeserialize(using = CustomTimeDeserializer.class)
    private Time time;

    private String description;
    private Long calories;

    // Default constructor for JSON deserialization
    public MealDTO() {
    }

    // Parameterized constructor
    public MealDTO(Long id, Date date, Time time, String description, Long calories) {
        this.id = id;
        this.date = date;
        this.time = time;
        this.description = description;
        this.calories = calories;
    }

    // Static method to convert Meal entity to MealDTO
    public static MealDTO fromMeal(Meal meal) {
        return new MealDTO(
                meal.getId(),
                meal.getDate(),
                meal.getTime(),
                meal.getDescription(),
                meal.getCalories()
        );
    }

    // Static method to convert a list of Meal entities to a list of MealDTOs
    public static List<MealDTO> fromMeals(List<Meal> meals) {
        return meals.stream()
                .map(MealDTO::fromMeal)
                .collect(Collectors.toList());
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "MealDTO{" +
                "id=" + id +
                ", date=" + date +
                ", time=" + time +
                ", description='" + description + '\'' +
                ", calories=" + calories +
                '}';
    }
}
