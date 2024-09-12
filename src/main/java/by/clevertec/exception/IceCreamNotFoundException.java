package by.clevertec.exception;

import java.util.UUID;

public class IceCreamNotFoundException extends RuntimeException {

    private IceCreamNotFoundException(String message){
        super(message);
    }
    public static IceCreamNotFoundException byIceCreamID(UUID iceCreamID){
        return new IceCreamNotFoundException(
                String.format("IceCream not found by id %s",iceCreamID)
        );
    }
}
