package group.meetmix.service.place;

import group.meetmix.data.dto.PlaceDto;
import group.meetmix.data.dto.PlaceLocationInputDto;

import java.util.List;

public interface PlaceService {
    List<PlaceDto> getPlacesByCoordinates(PlaceLocationInputDto placeLocationInputDto);

}