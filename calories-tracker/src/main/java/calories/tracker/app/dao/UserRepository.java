package calories.tracker.app.dao;

import calories.tracker.app.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("select u from User u where u.username = :username")
    User findByUsername(@Param("username") String username);

    boolean existsByUsername(String username);

    @Query("select sum(m.calories) from Meal m where m.user.username = :username and m.date = CURRENT_DATE")
    Long findTodaysCaloriesForUser(@Param("username") String username);

    @Query("select count(u) = 0 from User u where u.username = :username")
    boolean isUsernameAvailable(@Param("username") String username);
}


