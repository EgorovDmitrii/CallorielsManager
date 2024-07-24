package ru.dmitrii_egorov.manager.util;

import org.springframework.lang.Nullable;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class TimeUtil {

  private static final LocalDateTime MIN_DATE = LocalDateTime.of(1, 1, 1, 0, 0);
  private static final LocalDateTime MAX_DATE = LocalDateTime.of(3000, 1, 1, 0, 0);

    public static <T extends Comparable<T>> boolean isBetweenHalfOpen(T value, @Nullable T start, @Nullable T end) {
        return (start == null || value.compareTo(start) >= 0) && (end == null || value.compareTo(end) < 0);
    }

  // вход: LocalDate.of(2023, 7, 7)
  // выход: 2023-07-07T00:00:00
    public static LocalDateTime atStartOfDayOrMin(LocalDate localDate) {
      return localDate != null ? localDate.atStartOfDay() : MIN_DATE;
    }

    public static LocalDateTime atStartOfDayOrMax(LocalDate localDate) {
      return localDate != null ? localDate.plusDays(1).atStartOfDay() : MAX_DATE;
    }

}
