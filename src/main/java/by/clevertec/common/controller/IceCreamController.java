package by.clevertec.common.controller;


import by.clevertec.domain.IceCream;
import by.clevertec.service.IceCreamService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class IceCreamController {

    private final IceCreamService iceCreamService;


    @GetMapping("/api/v1/IceCreams")
    public ResponseEntity<List<IceCream>> findAll(){
        List<IceCream> iceCreams = iceCreamService.getIceCreams();
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(iceCreams);

    }

}
