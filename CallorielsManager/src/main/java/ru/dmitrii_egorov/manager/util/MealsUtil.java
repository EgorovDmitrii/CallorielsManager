package ru.dmitrii_egorov.manager.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import ru.dmitrii_egorov.manager.model.Meal;
import ru.dmitrii_egorov.manager.model.MealTo;

public class MealsUtil {
  public static final int DEFAULT_CALORIES_DAY = 2000;

  public static final List<Meal> meals = Arrays.asList(
          new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500),
          new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000),
          new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500),
          new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "Еда на грачное значение", 100),
          new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 1000),
          new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Обед", 500),
          new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Ужин", 410)
  );

  public static List<MealTo> getTos(Collection<Meal> meals, int caloriesPerDay) {
    return filterByPredicate(meals, caloriesPerDay, meal -> true);
  }

  public static List<MealTo> getFilteredTos(final Collection<Meal> meals, final int caloriesPerDay,
                                            final LocalTime startTime, final LocalTime endTime) {
    return filterByPredicate(meals, caloriesPerDay,
            meal -> TimeUtil.isBetweenHalfOpen(meal.getTime(), startTime, endTime));

  }

  private static List<MealTo> filterByPredicate(Collection<Meal> meals,int caloriesPerDay, Predicate<Meal> predicate) {
    Map<LocalDate, Integer> caloriesSumByDate = meals.stream()
            .collect(Collectors.groupingBy(Meal::getDate, Collectors.summingInt(Meal::getCalories)));

    return meals.stream()
            .filter(predicate)
            .map(meal -> createTo(meal, caloriesSumByDate.get(meal.getDate()) > caloriesPerDay))
            .collect(Collectors.toList());
  }

  private static MealTo createTo(final Meal meal, final boolean excess) {
    return new MealTo(meal.getId(), meal.getDateTime(), meal.getDescription(), meal.getCalories(), excess);
  }
}
