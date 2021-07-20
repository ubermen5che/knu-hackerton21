package KM.KNUguard.repository;

import KM.KNUguard.domain.CrawlData;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

//아래의 어노테이션을 달아주니까 빈 순환 오류 해결이 되었음
@Repository
public class CrawlDataRepositoryImpl implements CrawlDataRepository{

    private final EntityManager em;

    public CrawlDataRepositoryImpl(EntityManager em){
        this.em = em;
    }

    @Override
    public List<CrawlData> getCrawlData(String univ_name, Double latitude, Double longitude) {

        return em.createQuery("select c from CrawlData c", CrawlData.class)
                .getResultList();
    }
}
