package group.meetmix.data.dao;

import group.meetmix.data.entity.PlaceEntity;
import group.meetmix.data.repository.PlaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PlaceDaoImpl implements PlaceDao{
    PlaceRepository placeRepository;
    @Autowired
    public PlaceDaoImpl(PlaceRepository placeRepository){
        this.placeRepository = placeRepository;
    }
    @Override
    public List<PlaceEntity> getPlacesByCoordinates(double minX, double maxX, double minY, double maxY, int type) {
        if(type==0)
            return placeRepository.getAllPlacesByXBetweenAndYBetween(minX, maxX, minY, maxY);
        else
            return placeRepository.getPlacesByXBetweenAndYBetweenAndType(minX, maxX, minY, maxY, type);
    }
}
