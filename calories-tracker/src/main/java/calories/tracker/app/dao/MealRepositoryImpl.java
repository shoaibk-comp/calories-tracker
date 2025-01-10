package calories.tracker.app.dao;

import calories.tracker.app.model.Meal;
import calories.tracker.app.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class MealRepositoryImpl implements MealRepositoryCustom {

    @Autowired
    private EntityManager em;

    @Override
    public Long countMealsByDateTime(String username, Date fromDate, Date toDate, Time fromTime, Time toTime) {
        CriteriaBuilder cb = em.getCriteriaBuilder();

        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Meal> countRoot = cq.from(Meal.class);
        cq.select(cb.count(countRoot));
        cq.where(getCommonWhereCondition(cb, username, countRoot, fromDate, toDate, fromTime, toTime));
        Long resultsCount = em.createQuery(cq).getSingleResult();

        return resultsCount;
    }

    @Override
    public List<Meal> findMealsByDateTime(String username, Date fromDate, Date toDate, Time fromTime, Time toTime, int pageNumber) {
        CriteriaBuilder cb = em.getCriteriaBuilder();

        CriteriaQuery<Meal> searchQuery = cb.createQuery(Meal.class);
        Root<Meal> searchRoot = searchQuery.from(Meal.class);
        searchQuery.select(searchRoot);
        searchQuery.where(getCommonWhereCondition(cb, username, searchRoot, fromDate, toDate, fromTime, toTime));

        List<Order> orderList = new ArrayList<>();
        orderList.add(cb.desc(searchRoot.get("date")));
        orderList.add(cb.asc(searchRoot.get("time")));
        searchQuery.orderBy(orderList);

        TypedQuery<Meal> filterQuery = em.createQuery(searchQuery)
                .setFirstResult((pageNumber - 1) * 10)
                .setMaxResults(10);

        return filterQuery.getResultList();
    }

    private Predicate[] getCommonWhereCondition(CriteriaBuilder cb, String username, Root<Meal> searchRoot, Date fromDate, Date toDate, Time fromTime, Time toTime) {
        List<Predicate> predicates = new ArrayList<>();
        Join<Meal, User> user = searchRoot.join("user");

        predicates.add(cb.equal(user.get("username"), username));
        predicates.add(cb.greaterThanOrEqualTo(searchRoot.get("date"), fromDate));

        if (toDate != null) {
            predicates.add(cb.lessThanOrEqualTo(searchRoot.get("date"), toDate));
        }

        if (fromTime != null) {
            predicates.add(cb.greaterThanOrEqualTo(searchRoot.get("time"), fromTime));
        }

        if (toTime != null) {
            predicates.add(cb.lessThanOrEqualTo(searchRoot.get("time"), toTime));
        }

        return predicates.toArray(new Predicate[0]);
    }
}

