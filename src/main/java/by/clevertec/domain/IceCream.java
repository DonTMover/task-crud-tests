package by.clevertec.domain;


import by.clevertec.common.IceCreamType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IceCream {
    private UUID id;
    private String title;
    private IceCreamType type;
    private OffsetDateTime expiredPeriod;

}
