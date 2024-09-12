package by.clevertec.entity;


import by.clevertec.common.IceCreamType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.OffsetDateTime;
import java.util.UUID;
@Data
@Builder
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class IceCreamEntity {
    private UUID id;
    private String title;
    private IceCreamType type;
    private OffsetDateTime expiredPeriod;

}
