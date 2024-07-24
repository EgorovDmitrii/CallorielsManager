package ru.dmitrii_egorov.manager.repository;

import ru.dmitrii_egorov.manager.model.Meal;

import java.time.LocalDateTime;
import java.util.List;

public interface MealRepository {

    Meal save(final Meal meal, final int userId);

    Meal get(final int id, final int userId);

    boolean delete(final int id, final int userId);

    List<Meal> getAll(final int userId);

    List<Meal> getBetweenHalfOpen(LocalDateTime startDate, LocalDateTime endDate, int userId);
}
