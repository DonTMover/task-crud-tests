package by.clevertec.util;

import by.clevertec.common.IceCreamType;
import by.clevertec.domain.IceCream;
import by.clevertec.entity.IceCreamEntity;

import java.time.OffsetDateTime;
import java.util.UUID;

public class TestData {
    public static IceCreamEntity generateIceCreamEntity() {
        return new IceCreamEntity()
                .setId(UUID.randomUUID())
                .setType(IceCreamType.Plombir)
                .setTitle("IceCream")
                .setExpiredPeriod(OffsetDateTime.now());
    }
    public static IceCream generateIceCream() {
        return new IceCream()
                .setId(UUID.randomUUID())
                .setType(IceCreamType.Plombir)
                .setTitle("IceCream")
                .setExpiredPeriod(OffsetDateTime.now());
    }
}
