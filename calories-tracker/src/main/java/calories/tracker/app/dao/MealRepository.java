package calories.tracker.app.dao;

import calories.tracker.app.model.Meal;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Time;
import java.util.Date;

@Repository
public interface MealRepository extends JpaRepository<Meal, Long>, MealRepositoryCustom {
    Page<Meal> findByUser_UsernameAndDateBetweenAndTimeBetween(String username, Date fromDate, Date toDate, Time fromTime, Time toTime, Pageable pageable);
    Long countByUser_UsernameAndDateBetweenAndTimeBetween(String username, Date fromDate, Date toDate, Time fromTime, Time toTime);
}


