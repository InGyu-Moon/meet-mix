package group.meetmix.controller;

import group.meetmix.data.dto.PlaceDto;
import group.meetmix.data.dto.PlaceLocationInputDto;
import group.meetmix.service.place.PlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping()
public class PlaceController {
    private final PlaceService placeService;
    @Autowired
    public PlaceController(PlaceService placeService) {
        this.placeService = placeService;
    }
    @PostMapping("/search-places")
    public ResponseEntity<List<PlaceDto>> searchPlacesByCoordinatesAndType(@RequestBody PlaceLocationInputDto placeLocationInputDto) {
        System.out.println("1");
        List<PlaceDto> places = placeService.getPlacesByCoordinates(placeLocationInputDto);
        System.out.println("2");
        return ResponseEntity.ok(places);
    }
}
