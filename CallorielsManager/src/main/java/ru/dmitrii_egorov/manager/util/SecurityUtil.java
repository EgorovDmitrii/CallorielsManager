package ru.dmitrii_egorov.manager.util;

public class SecurityUtil {
    private static int id = 0;

    private SecurityUtil() {
    }

    public static int authUserID() {
        return id;
    }

    public static void setAuthUserID(int id) {
        SecurityUtil.id = id;
    }

    public static int authUserCaloriesPerDay() {
        return MealsUtil.DEFAULT_CALORIES_DAY;
    }


}
