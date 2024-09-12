package by.clevertec.util;

import by.clevertec.common.helper.DateSupplier;

import java.time.OffsetDateTime;

public class DateSupplierTest implements DateSupplier {

    @Override
    public OffsetDateTime getCurrentDateTime() {
        return OffsetDateTime.parse("2022-10-10T13:00:00.1337Z");
    }
}
