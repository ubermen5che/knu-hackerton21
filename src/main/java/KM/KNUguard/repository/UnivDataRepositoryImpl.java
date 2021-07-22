package KM.KNUguard.repository;

import KM.KNUguard.domain.UnivData;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class UnivDataRepositoryImpl implements UnivDataRepository{

    private final EntityManager em;

    public UnivDataRepositoryImpl(EntityManager em){
        this.em = em;
    }

    public Long save(UnivData ud){
        em.persist(ud);
        return ud.getId();
    }
}
