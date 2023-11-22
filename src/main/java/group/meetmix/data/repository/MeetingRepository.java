package group.meetmix.data.repository;

import group.meetmix.data.entity.MeetingEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class MeetingRepository {
    @PersistenceContext
    private EntityManager em;

    public void save(MeetingEntity meeting){
        if(meeting.getMeetingId()==null){
            em.persist(meeting);
        }else{
            em.merge(meeting);
        }
    }
    public List<MeetingEntity> findAll(){
        return em.createQuery("select m from MeetingEntity m",MeetingEntity.class)
                .getResultList();
    }
    public MeetingEntity findById(Long id){
        return em.find(MeetingEntity.class, id);
    }
    public void delete(MeetingEntity meeting){
        em.remove(meeting);
    }

}
