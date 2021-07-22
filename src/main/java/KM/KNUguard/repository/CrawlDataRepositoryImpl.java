package KM.KNUguard.repository;

import KM.KNUguard.domain.CrawlData;
import KM.KNUguard.domain.Member;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

//아래의 어노테이션을 달아주니까 빈 순환 오류 해결이 되었음
@Repository
public class CrawlDataRepositoryImpl implements CrawlDataRepository{

    private final EntityManager em;

    public CrawlDataRepositoryImpl(EntityManager em){
        this.em = em;
    }

    @Override
    public Optional<List> getCrawlDataUniv(String univ_name, Float latitude, Float longitude) {

        Query query = em.createQuery("select c from CrawlData c where c.u_name = :univ_name", CrawlData.class);
        query.setParameter("univ_name", univ_name);

        return Optional.ofNullable(query.getResultList());
    }

    @Override
    public Optional<List> getCrawlData(Float u_latitude, Float u_longitude){

        //이용자 현재 위,경도 기준으로 반경계산 후 반경안에 해당하는 데이터 DB에서 불러옴.
        String sql = "SELECT *," +
                "(6371*acos(cos(radians(?))*cos(radians(latitude))" +
                "*cos(radians(longitude) -radians(?))+sin(radians(?))" +
                "*sin(radians(latitude)))) AS distance FROM crawl_data HAVING distance <= 100 ORDER BY distance";

        Query nativeQuery = em.createNativeQuery(sql, CrawlData.class)
                .setParameter(1, u_latitude)
                .setParameter(2, u_longitude)
                .setParameter(3, u_latitude);

        Optional<List> resultList = Optional.ofNullable(nativeQuery.getResultList());
        System.out.println("resultList.get().size() = " + resultList.get().size());
        return resultList;
    }

    @Override
    public Long save(CrawlData cd) {
        em.persist(cd);
        return cd.getId();
    }
}
