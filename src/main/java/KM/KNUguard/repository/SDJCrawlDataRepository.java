package KM.KNUguard.repository;

import KM.KNUguard.domain.CrawlData;
import KM.KNUguard.domain.UnivData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SDJCrawlDataRepository extends JpaRepository<CrawlData, Long> {

    Optional<CrawlData> findByLatAndLng(Float lat, Float lng);
    Optional<CrawlData> findById(Long id);
}
