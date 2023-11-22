package group.meetmix.data.repository;

import group.meetmix.data.entity.MeetingEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MeetingRepository {
    @PersistenceContext
    private EntityManager em;

    public void save(MeetingEntity meeting){
        em.persist(meeting);
    }
    public List<MeetingEntity> findAll(){
        return em.createQuery("select m from MeetingEntity m",MeetingEntity.class)
                .getResultList();
    }

}
