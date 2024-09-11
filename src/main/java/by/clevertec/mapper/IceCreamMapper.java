package by.clevertec.mapper;


import by.clevertec.domain.IceCream;
import by.clevertec.entity.IceCreamEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface IceCreamMapper {

    List<IceCream> toDomains(List<IceCreamEntity> iceCreamEntities);

    IceCream toDomain(IceCreamEntity iceCreamEntity);

    IceCreamEntity toEntity(IceCream iceCreamEntity);
}
