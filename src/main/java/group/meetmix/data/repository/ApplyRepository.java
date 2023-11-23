package group.meetmix.data.repository;

import group.meetmix.data.entity.ApplyEntity;
import group.meetmix.data.entity.MeetingEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class ApplyRepository {
    @PersistenceContext
    private EntityManager em;
    public void save(ApplyEntity applyEntity){
        if(applyEntity.getApplyId()==null){
            em.persist(applyEntity);
        }else{
            em.merge(applyEntity);
        }
    }
    public List<ApplyEntity> findAllAppliesByMemberId(Long id){
        return em.createQuery("select m from ApplyEntity m where m.member.id = :id", ApplyEntity.class)
                .setParameter("id", id)
                .getResultList();
    }
}
