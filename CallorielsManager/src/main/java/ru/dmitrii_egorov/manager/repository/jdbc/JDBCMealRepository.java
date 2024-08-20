package ru.dmitrii_egorov.manager.repository.jdbc;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import ru.dmitrii_egorov.manager.model.Meal;
import ru.dmitrii_egorov.manager.repository.MealRepository;

import java.time.LocalDateTime;
import java.util.List;

public class JDBCMealRepository implements MealRepository {

    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final SimpleJdbcInsert insertMeal;

    public JDBCMealRepository(JdbcTemplate jdbcTemplate,
                              NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.insertMeal = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("meal")
                .usingGeneratedKeyColumns("id");
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public Meal save(Meal meal, int userId) {
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("id", meal.getId())
                .addValue("description", meal.getDescription())
                .addValue("calories", meal.getCalories())
                .addValue("dateTime", meal.getDateTime())
                .addValue("userId", userId);

        if (meal.isNew()) {
            Number newId = insertMeal.executeAndReturnKey(params);
            meal.setId(newId.intValue());
        } else {
            if (namedParameterJdbcTemplate.update("""
                    UPDATE meal
                    SET description=:description, calories =: calories, date_time=: dateTime
                    WHERE id=:id AND userId=:userId
                    """, params) == 0) {
                return null;
            }
        }
        return meal;
    }

    @Override
    public Meal get(int id, int userId) {
        return null;
    }

    @Override
    public boolean delete(int id, int userId) {
        return false;
    }

    @Override
    public List<Meal> getAll(int userId) {
        return List.of();
    }

    @Override
    public List<Meal> getBetweenHalfOpen(LocalDateTime startDate, LocalDateTime endDate, int userId) {
        return List.of();
    }
}
