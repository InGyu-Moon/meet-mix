package group.meetmix.data.dao;


import group.meetmix.data.entity.PlaceEntity;

import java.util.List;

public interface PlaceDao {
    List<PlaceEntity> getPlacesByCoordinates(double minX, double maxX, double minY, double maxY, int type);
}
