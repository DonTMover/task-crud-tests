package by.clevertec.repository;

import by.clevertec.common.IceCreamType;
import by.clevertec.exception.IceCreamNotFoundException;
import by.clevertec.entity.IceCreamEntity;
import org.springframework.stereotype.Repository;


import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class IceCreamRepository {

    private static final List<IceCreamEntity> db = new ArrayList<>(List.of(
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
    ));


    public List<IceCreamEntity> getIceCreams() {
        return db;
    }

    public Optional<IceCreamEntity> getIceCreamById(UUID id) {
        return db.stream()
                .filter(iceCreamEntity -> iceCreamEntity.getId().equals(id))
                .findFirst();
    }


    public boolean create(IceCreamEntity iceCreamEntity) {
        try {
            return db.add(iceCreamEntity);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public IceCreamEntity update(UUID iceCreamID, IceCreamEntity newIceCreamEntity) {
        IceCreamEntity iceCreamById = getIceCreamById(iceCreamID)
                .orElseThrow(() -> IceCreamNotFoundException.byIceCreamID(iceCreamID));

        // Find and replace the ice cream entity in the list
        int index = db.indexOf(iceCreamById);
        if (index >= 0) {
            db.set(index, newIceCreamEntity);
            return newIceCreamEntity;
        } else {
            throw IceCreamNotFoundException.byIceCreamID(iceCreamID);
        }
    }
    public void delete(UUID iceCreamID) {
        IceCreamEntity iceCreamById = getIceCreamById(iceCreamID)
                .orElseThrow(() -> IceCreamNotFoundException.byIceCreamID(iceCreamID));

        db.remove(iceCreamById);
    }
}
