package ru.dmitrii_egorov.manager.model;

import java.time.LocalDateTime;

public class MealTo {
    private final Integer id;
    private final LocalDateTime dateTime;
    private final String descrition;
    private final int calories;
    private final boolean excess;

    public MealTo(Integer id, LocalDateTime dateTime, String descrition, int calories, boolean excess) {
        this.id = id;
        this.dateTime = dateTime;
        this.descrition = descrition;
        this.calories = calories;
        this.excess = excess;
    }

    @Override
    public String toString() {
        return "MealTo{" +
                "dateTime=" + dateTime +
                ", descrition='" + descrition + '\'' +
                ", calories=" + calories +
                ", excess=" + excess +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getDescrition() {
        return descrition;
    }

    public int getCalories() {
        return calories;
    }

    public boolean isExcess() {
        return excess;
    }
}
