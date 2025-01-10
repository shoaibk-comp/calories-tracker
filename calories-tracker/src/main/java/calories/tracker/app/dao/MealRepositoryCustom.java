package calories.tracker.app.dao;

import calories.tracker.app.model.Meal;

import java.util.List;
import java.sql.Time;
import java.util.Date;

public interface MealRepositoryCustom {
    List<Meal> findMealsByDateTime(String username, Date fromDate, Date toDate, Time fromTime, Time toTime, int pageNumber);
    Long countMealsByDateTime(String username, Date fromDate, Date toDate, Time fromTime, Time toTime);
}

