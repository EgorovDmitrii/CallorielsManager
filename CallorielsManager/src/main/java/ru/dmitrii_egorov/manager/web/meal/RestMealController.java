package ru.dmitrii_egorov.manager.web.meal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import ru.dmitrii_egorov.manager.model.Meal;
import ru.dmitrii_egorov.manager.repository.MealRepository;
import ru.dmitrii_egorov.manager.service.MealService;
import ru.dmitrii_egorov.manager.util.SecurityUtil;
import ru.dmitrii_egorov.manager.util.ValidationUtil;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Controller
public class RestMealController {

    private final MealService mealService;

    @Autowired
    public RestMealController(MealService mealService) {
        this.mealService = mealService;
    }

    public Meal create(final Meal meal) {
        int userId = SecurityUtil.authUserID();
        ValidationUtil.checkNew(meal);
        return mealService.create(meal, userId);
    }

    public Meal get(final int id) {
        int userId = SecurityUtil.authUserID();
        return mealService.get(id, userId);
    }
    //Fronted -> Servlet -> Controler -> Service -> Repository -> DB
    public List<Meal> getAll() {
        int userId = SecurityUtil.authUserID();
        return mealService.getAll(userId);
    }

    public Meal getBetweenHalfOpen(@Nullable final LocalDate startDate,@Nullable final LocalTime startTime,
                                   @Nullable final LocalDate endDate,@Nullable final LocalTime endTime) {
    }

    public void update(final Meal meal) {}

    public void delete(final int id) {}
}
