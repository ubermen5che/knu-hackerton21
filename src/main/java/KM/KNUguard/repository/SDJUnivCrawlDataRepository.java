package KM.KNUguard.repository;

import KM.KNUguard.domain.UnivData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SDJUnivCrawlDataRepository extends JpaRepository<UnivData, Long> {
    Optional<UnivData> findByAddress(String address);
}
