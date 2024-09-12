package by.clevertec.service;

import by.clevertec.common.helper.DateSupplier;
import by.clevertec.domain.IceCream;
import by.clevertec.entity.IceCreamEntity;
import by.clevertec.exception.IceCreamNotFoundException;
import by.clevertec.mapper.IceCreamMapper;
import by.clevertec.repository.IceCreamRepository;
import by.clevertec.util.DateSupplierTest;
import by.clevertec.util.TestData;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class IceCreamServiceTest {


    @Mock
    private IceCreamRepository iceCreamRepository;

    @Mock
    private IceCreamMapper iceCreamMapper;

    @Spy
    private DateSupplier dateSupplier = new DateSupplierTest();

    @InjectMocks
    private IceCreamService iceCreamService;

    @Test
    void shouldGetIceCreams() {
        // given

        IceCreamEntity iceCreamEntity = TestData.generateIceCreamEntity();
        List<IceCreamEntity> iceCreamEntities = List.of(iceCreamEntity);

        List<IceCream> iceCreams = List.of(TestData.generateIceCream().setId(iceCreamEntity.getId()));

        when(iceCreamRepository.getIceCreams())
                .thenReturn(iceCreamEntities);
        when(iceCreamMapper.toDomains(iceCreamEntities))
                .thenReturn(iceCreams);

        // when

        List<IceCream> testIceCreams = iceCreamService.getIceCreams();

        // then
        assertFalse(testIceCreams.isEmpty());
    }

    @Test
    void shouldNotGetIceCreamById() {
        // given
        UUID iceCreamUUID = UUID.randomUUID();

        when(iceCreamRepository.getIceCreamById(iceCreamUUID))
                .thenReturn(Optional.empty());

        // when, then
        assertThrows(
                IceCreamNotFoundException.byIceCreamID(UUID.randomUUID()).getClass(),
                () -> iceCreamService.getIceCreamById(iceCreamUUID)
        );

        // Завершается с ошибкой на которую я проверяю :clown:
    }


    @Test
    void shouldAddIceCreamInDataBase() {
        // given
        UUID iceCreamID = UUID.randomUUID();
        IceCream iceCream = new IceCream();
        iceCream.setId(iceCreamID);

        // when
        iceCreamService.create(iceCream);

        // then

        assertDoesNotThrow(() -> iceCreamService.getIceCreamById(iceCream.getId()));
    }

    @Test
    void shouldUpdateIceCreamInDataBase() {
        // given
        UUID iceCreamID = UUID.randomUUID();
        IceCream iceCream = TestData.generateIceCream().setId(iceCreamID);
        IceCreamEntity iceCreamEntity = TestData.generateIceCreamEntity().setId(iceCreamID);

        IceCream testIceCream = TestData.generateIceCream();

        List<IceCreamEntity> iceCreamEntities = List.of(iceCreamEntity);

        // when

        when(iceCreamRepository.getIceCreamById(iceCreamID))
                .thenReturn(Optional.of(iceCreamEntity));
        when(iceCreamMapper.toEntity(iceCream))
                .thenReturn(iceCreamEntity);
        when(iceCreamRepository.getIceCreams())
                .thenReturn(iceCreamEntities);
        iceCreamService.update(iceCreamID, testIceCream);


        // then

        assertEquals(iceCreamID, iceCreamService.getIceCreamById(iceCreamID).getId());

    }

    @Test
    void shouldDeleteIceCreamFromDataBase() {
        // given
        UUID iceCreamID = UUID.randomUUID();

        //when,then

        assertThrows(
                IceCreamNotFoundException.class,
                () -> iceCreamService.delete(iceCreamID)
        );

    }
}