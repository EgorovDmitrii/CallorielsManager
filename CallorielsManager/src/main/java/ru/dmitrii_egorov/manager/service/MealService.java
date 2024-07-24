package ru.dmitrii_egorov.manager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.dmitrii_egorov.manager.model.Meal;
import ru.dmitrii_egorov.manager.repository.MealRepository;
import ru.dmitrii_egorov.manager.util.TimeUtil;
import ru.dmitrii_egorov.manager.util.ValidationUtil;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class MealService {


    private final MealRepository mealRepository;

    @Autowired
    public MealService(MealRepository mealRepository) {
        this.mealRepository = mealRepository;
    }

    public Meal create(Meal meal, int userid) {
        Assert.notNull(meal, "Meal must not be null");
        return mealRepository.save(meal, userid);
    }

    public Meal get(int id, int userid) {
        return ValidationUtil.checkNotFoundWithId(mealRepository.get(id, userid), userid);
    }

    public List<Meal> getAll(int userid) {
        return mealRepository.getAll(userid);
    }

    public List<Meal> getBetweenInclusive(LocalDate startDate, LocalDate endDate, int userid) {
        return mealRepository.getBetweenHalfOpen(TimeUtil.atStartOfDayOrMin(startDate), TimeUtil.atStartOfDayOrMax(endDate), userid);
    }

    public void update(Meal meal, int userid) {
        Assert.notNull(meal, "Meal must not be null");
        ValidationUtil.checkNotFoundWithId(mealRepository.save(meal, userid), userid);
    }

    public void delete(int id, int userid) {
        ValidationUtil.checkNotFoundWithId(mealRepository.delete(id, userid), id);
    }
}
