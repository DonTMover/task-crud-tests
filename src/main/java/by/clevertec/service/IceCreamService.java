package by.clevertec.service;

import by.clevertec.common.helper.DateSupplier;
import by.clevertec.domain.IceCream;
import by.clevertec.entity.IceCreamEntity;
import by.clevertec.exception.IceCreamNotFoundException;
import by.clevertec.mapper.IceCreamMapper;
import by.clevertec.mapper.IceCreamMapperImpl;
import by.clevertec.repository.IceCreamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class IceCreamService {
    private final DateSupplier dateSupplier;

    private final static IceCreamRepository iceCreamRepository = new IceCreamRepository();

    private final static IceCreamMapper iceCreamMapper = new IceCreamMapperImpl();

    public List<IceCream> getIceCreams() {
        List<IceCreamEntity> iceCreamEntities = iceCreamRepository.getIceCreams();
        return iceCreamMapper.toDomains(iceCreamEntities);
    }

    public IceCream getIceCreamById(UUID id) {
        IceCreamEntity iceCreamEntity = iceCreamRepository.getIceCreamById(id)
                .orElseThrow(() -> IceCreamNotFoundException.byIceCreamID(id));
        return iceCreamMapper.toDomain(iceCreamEntity);
    }

    public IceCream create(IceCream iceCream) {
        try {
            IceCreamEntity iceCreamEntity = iceCreamMapper.toEntity(iceCream);
            iceCreamRepository.create(iceCreamEntity);
            return iceCream;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public IceCream update(UUID iceCreamID, IceCream newIceCream) {
        IceCreamEntity existingEntity = iceCreamRepository.getIceCreamById(iceCreamID)
                .orElseThrow(() -> IceCreamNotFoundException.byIceCreamID(iceCreamID));

        IceCreamEntity updatedEntity = iceCreamMapper.toEntity(newIceCream);
        updatedEntity.setId(existingEntity.getId());

        IceCreamEntity resultEntity = iceCreamRepository.update(iceCreamID, updatedEntity);
        return iceCreamMapper.toDomain(resultEntity);
    }

    public void delete(UUID iceCreamID) {
        iceCreamRepository.delete(iceCreamID);
    }

}
