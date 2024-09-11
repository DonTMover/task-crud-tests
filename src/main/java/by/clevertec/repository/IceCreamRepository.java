package by.clevertec.repository;

import by.clevertec.common.IceCreamType;
import by.clevertec.exception.IceCreamNotFoundException;
import by.clevertec.entity.IceCreamEntity;


import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class IceCreamRepository {

    private static final List<IceCreamEntity> db = List.of(
            IceCreamEntity.builder()
                    .id(UUID.randomUUID())
                    .type(IceCreamType.Plombir)
                    .title("IceCream1")
                    .expiredPeriod(OffsetDateTime.now())
                    .build(),
            IceCreamEntity.builder()
                    .id(UUID.randomUUID())
                    .type(IceCreamType.Plombir)
                    .title("IceCream2")
                    .expiredPeriod(OffsetDateTime.now())
                    .build(),
            IceCreamEntity.builder()
                    .id(UUID.randomUUID())
                    .type(IceCreamType.Horn)
                    .title("IceCream3")
                    .expiredPeriod(OffsetDateTime.now())
                    .build()
    );

    public List<IceCreamEntity> getIceCreams() {
        return db;
    }

    public Optional<IceCreamEntity> getIceCreamById(UUID id) {
        List<IceCreamEntity> list = db.stream()
                .filter(iceCreamEntity -> iceCreamEntity.getId().equals(id))
                .toList();
        return Optional.of(list.getFirst());
    }

    public boolean create(IceCreamEntity iceCreamEntity) {
        try {
            db.add(iceCreamEntity);
            return true;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public IceCreamEntity update(UUID iceCreamID, IceCreamEntity newIceCreamEntity) {
        IceCreamEntity iceCreamById = getIceCreamById(iceCreamID)
                .orElseThrow(() -> IceCreamNotFoundException.byIceCreamID(iceCreamID));
        if (db.contains(iceCreamById)) {
            db.stream()
                    .filter(iceCreamEntity -> iceCreamEntity.getId().equals(iceCreamID))
                    .peek(iceCreamEntity -> {
                        iceCreamEntity.setId(newIceCreamEntity.getId());
                        iceCreamEntity.setType(newIceCreamEntity.getType());
                        iceCreamEntity.setTitle(newIceCreamEntity.getTitle());
                        iceCreamEntity.setExpiredPeriod(newIceCreamEntity.getExpiredPeriod());
                    })
                    .close();
        }
        return iceCreamById;
    }
    public void delete(UUID iceCreamID){
        IceCreamEntity iceCreamById = getIceCreamById(iceCreamID)
                .orElseThrow(() -> IceCreamNotFoundException.byIceCreamID(iceCreamID) );
        if(db.contains(iceCreamById)){
            db.remove(iceCreamById);
        }
    }
}
