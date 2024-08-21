package ru.dmitrii_egorov.manager.repository.jdbc;

import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import ru.dmitrii_egorov.manager.model.Meal;
import ru.dmitrii_egorov.manager.repository.MealRepository;

import java.time.LocalDateTime;
import java.util.List;

public class JDBCMealRepository implements MealRepository {

    private static final BeanPropertyRowMapper<Meal> ROW_MAPPER = new BeanPropertyRowMapper<>(Meal.class);

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
                    WHERE id=:id AND meal.user_id=:userId
                    """, params) == 0) {
                return null;
            }
        }
        return meal;
    }

    @Override
    public Meal get(int id, int userId) {
        List<Meal> meals = jdbcTemplate.query("SELECT * FROM meal WHERE id = ? AND user_id = ?", ROW_MAPPER, id, userId);
        return DataAccessUtils.singleResult(meals);
    }

    @Override
    public boolean delete(int id, int userId) {
        return jdbcTemplate.update("DELETE FROM meal WHERE id = ? AND user_id = ?", id, userId) != 0;
    }

    @Override
    public List<Meal> getAll(int userId) {
        return jdbcTemplate.query("SELECT * FROM meal WHERE user_id = ?", ROW_MAPPER, userId);
    }

    @Override
    public List<Meal> getBetweenHalfOpen(LocalDateTime startDate, LocalDateTime endDate, int userId) {
        return jdbcTemplate.query("SELECT * FROM meal WHERE user_id = ? AND date_time >= ? AND date_time < ?", ROW_MAPPER, userId, startDate, endDate);
    }
}
