package by.clevertec.service;

import by.clevertec.exception.IceCreamNotFoundException;
import by.clevertec.domain.IceCream;
import by.clevertec.entity.IceCreamEntity;
import by.clevertec.mapper.IceCreamMapper;
import by.clevertec.mapper.IceCreamMapperImpl;
import by.clevertec.repository.IceCreamRepository;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
public class IceCreamService {
    private IceCreamRepository iceCreamRepository = new IceCreamRepository();

    private IceCreamMapper iceCreamMapper = new IceCreamMapperImpl();

    public List<IceCream> getIceCreams() {
        List<IceCreamEntity> iceCreams = iceCreamRepository.getIceCreams();

        return iceCreamMapper.toDomains(iceCreams);
    }

        public Optional<IceCream> getIceCreamById(UUID id) {
            List<IceCream> iceCreams = getIceCreams().stream()
                    .filter(iceCreamEntity -> iceCreamEntity.getId().equals(id))
                    .toList();

            return Optional.empty();


        }

    public boolean create(IceCreamEntity iceCreamEntity) {
        try {

            iceCreamRepository.getIceCreams().add(iceCreamEntity);
            return true;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public IceCream update(UUID iceCreamID, IceCream newIceCream) {
        IceCream iceCreamById = getIceCreamById(iceCreamID)
                .orElseThrow(() -> IceCreamNotFoundException.byIceCreamID(iceCreamID));
        if (getIceCreams().contains(iceCreamById)) {
            getIceCreams().stream()
                    .filter(iceCreamEntity -> iceCreamEntity.getId().equals(iceCreamID))
                    .peek(iceCreamEntity -> {
                        iceCreamEntity.setId(newIceCream.getId());
                        iceCreamEntity.setType(newIceCream.getType());
                        iceCreamEntity.setTitle(newIceCream.getTitle());
                        iceCreamEntity.setExpiredPeriod(newIceCream.getExpiredPeriod());
                    })
                    .close();
        }
        return iceCreamById;
    }

    public void delete(UUID iceCreamID) {
        IceCream iceCreamById = getIceCreamById(iceCreamID)
                .orElseThrow(() -> IceCreamNotFoundException.byIceCreamID(iceCreamID));
        if (getIceCreams().contains(iceCreamById)) {
//            iceCreamRepository.getIceCreams().remove(iceCreamMapper.toEntity(iceCreamById));
        }
    }

}
