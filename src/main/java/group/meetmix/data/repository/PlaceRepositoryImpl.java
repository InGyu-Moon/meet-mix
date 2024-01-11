package group.meetmix.data.repository;

import group.meetmix.data.entity.PlaceEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
@Slf4j
public abstract class PlaceRepositoryImpl implements PlaceRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<PlaceEntity> getAllPlacesByXBetweenAndYBetween(double minX, double maxX, double minY, double maxY) {
        TypedQuery<PlaceEntity> query = entityManager.createQuery(
                "SELECT p FROM PlaceEntity p WHERE p.x BETWEEN :minX AND :maxX AND p.y BETWEEN :minY AND :maxY",
                PlaceEntity.class
        );

        query.setParameter("minX", minX);
        query.setParameter("maxX", maxX);
        query.setParameter("minY", minY);
        query.setParameter("maxY", maxY);

        List<PlaceEntity> places = query.getResultList();

        return places;
    }
    @Override
    public List<PlaceEntity> getPlacesByXBetweenAndYBetweenAndType(double minX, double maxX, double minY, double maxY, int type){
        TypedQuery<PlaceEntity> query;

        query = entityManager.createQuery(
                "SELECT p FROM PlaceEntity p WHERE p.x BETWEEN :minX AND :maxX AND p.y BETWEEN :minY AND :maxY AND p.type = :type",
                PlaceEntity.class
        );
        query.setParameter("type", type);

        query.setParameter("minX", minX);
        query.setParameter("maxX", maxX);
        query.setParameter("minY", minY);
        query.setParameter("maxY", maxY);

        List<PlaceEntity> places = query.getResultList();

        return places;
    }
}
