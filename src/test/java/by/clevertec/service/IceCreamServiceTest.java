package by.clevertec.service;

import by.clevertec.exception.IceCreamNotFoundException;
import by.clevertec.domain.IceCream;
import by.clevertec.entity.IceCreamEntity;
import by.clevertec.mapper.IceCreamMapper;
import by.clevertec.repository.IceCreamRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class IceCreamServiceTest {


    @Mock
    private IceCreamRepository iceCreamRepository;

    @Mock
    private IceCreamMapper iceCreamMapper;

    @InjectMocks
    private IceCreamService iceCreamService;

    @Test
    void shouldGetIceCreams() {
        //given

        IceCreamEntity iceCreamEntity = new IceCreamEntity();
        List<IceCreamEntity> iceCreamEntities = List.of(iceCreamEntity);

        List<IceCream> iceCreams = List.of(new IceCream());

        when(iceCreamRepository.getIceCreams())
                .thenReturn(iceCreamEntities);

        when(iceCreamMapper.toDomains(iceCreamEntities))
                .thenReturn(iceCreams);

        //when
        List<IceCream> testIceCreams = iceCreamService.getIceCreams();


        //then
        assertFalse(testIceCreams.isEmpty());

    }

    @Test
    void shouldGetIceCreamById() {
        //given
        UUID iceCreamUUID = UUID.randomUUID();

        IceCreamEntity iceCreamEntity = new IceCreamEntity();
        IceCream iceCream = new IceCream();

        iceCream.setId(UUID.randomUUID());

        when(iceCreamRepository.getIceCreamById(iceCreamUUID))
                .thenReturn(Optional.of(iceCreamEntity));

        when(iceCreamMapper.toDomain(iceCreamEntity))
                .thenReturn(iceCream);
        //when
        IceCream actualIceCream = iceCreamService.getIceCreamById(iceCreamUUID)
                .orElseThrow(() -> IceCreamNotFoundException.byIceCreamID(iceCreamUUID));

        //then
        assertEquals(iceCream.getId(),actualIceCream.getId());
    }
    @Test
    void shouldNotGetIceCreamById_whenIceCreamNotFound() {
        //given
        UUID iceCreamUUID = UUID.randomUUID();

        IceCreamEntity iceCreamEntity = new IceCreamEntity();
        IceCream iceCream = new IceCream();

        iceCreamEntity.setId(iceCreamUUID);
        iceCream.setId(iceCreamUUID);

        when(iceCreamRepository.getIceCreamById(iceCreamUUID))
                .thenReturn(Optional.empty());

        when(iceCreamMapper.toDomain(iceCreamEntity))
                .thenReturn(iceCream);
        //when,then
        assertThrows(
                IceCreamNotFoundException.class, () -> iceCreamRepository.getIceCreamById(iceCreamUUID)
        )
    }

    @Test
    void create() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }
}