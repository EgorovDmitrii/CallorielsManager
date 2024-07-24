package ru.dmitrii_egorov.manager.repository.inmemory;

import org.springframework.stereotype.Repository;
import ru.dmitrii_egorov.manager.model.Meal;
import ru.dmitrii_egorov.manager.repository.MealRepository;
import ru.dmitrii_egorov.manager.util.MealsUtil;
import ru.dmitrii_egorov.manager.util.SecurityUtil;
import ru.dmitrii_egorov.manager.util.TimeUtil;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Repository
public class InMemoryMealRepository implements MealRepository {

    private final Map<Integer, Map<Integer, Meal>> repository = new ConcurrentHashMap<>();
    private final AtomicInteger idCounter = new AtomicInteger(0);


    {
        MealsUtil.meals.forEach(meal -> save(meal, SecurityUtil.authUserID()));
    }
    @Override
    public Meal save(Meal meal, final int userId) {
        Map<Integer, Meal> integerMealMap = repository.computeIfAbsent(userId, uId -> new ConcurrentHashMap<>());
        if (meal.isNew()) {
            meal.setId(idCounter.getAndIncrement());
            integerMealMap.put(meal.getId(), meal);
            return meal;
        }
        return integerMealMap.computeIfPresent(meal.getId(), (id, oldMeal) -> meal);
    }

    @Override
    public Meal get(int id, final int userId) {
        Map<Integer, Meal> integerMealMap = repository.get(userId);
        return integerMealMap.get(id);
    }

    @Override
    public boolean delete(int id, final int userId) {
        Map<Integer, Meal> integerMealMap = repository.get(userId);
        return integerMealMap.remove(id) != null;
    }

    @Override
    public List<Meal> getAll(final int userId) {
        Map<Integer, Meal> integerMealMap = repository.get(userId);
        return integerMealMap.values().stream()
                .sorted(Comparator.comparing(Meal::getDateTime).reversed())
                .collect(Collectors.toList());
    }

    @Override
    public List<Meal> getBetweenHalfOpen(LocalDateTime startDate, LocalDateTime endDate, int userId) {
        Map<Integer, Meal> integerMealMap = repository.get(userId);
        return integerMealMap.values().stream()
                .filter(meal -> TimeUtil.isBetweenHalfOpen(meal.getDateTime(), startDate, endDate))
                .sorted(Comparator.comparing(Meal::getDateTime).reversed())
                .collect(Collectors.toList());
    }
}
