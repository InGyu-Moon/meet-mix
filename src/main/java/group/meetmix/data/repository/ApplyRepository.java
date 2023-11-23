package group.meetmix.data.repository;

import group.meetmix.data.entity.ApplyEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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
}
