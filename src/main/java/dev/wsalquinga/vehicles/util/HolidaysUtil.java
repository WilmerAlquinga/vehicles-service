package dev.wsalquinga.vehicles.util;

import java.time.LocalDate;
import java.util.List;

/**
 * @author wsalquinga on 31/10/2023
 */
public class HolidaysUtil {
    public HolidaysUtil() {
    }

    public static final List<LocalDate> HOLIDAYS_LIST = List.of(
            LocalDate.of(2023, 11, 1),
            LocalDate.of(2023, 10, 31),
            LocalDate.of(2023, 12, 25),
            LocalDate.of(2023, 12, 31)
    );
}
