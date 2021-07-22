package KM.KNUguard.repository;

import KM.KNUguard.domain.CrawlData;

import java.util.List;
import java.util.Optional;

public interface CrawlDataRepository {
    Optional<List> getCrawlDataUniv(String univ_name, Float latitude, Float longitude);
    Optional<List> getCrawlData(Float latitude, Float longitude);
    Long save(CrawlData cd);
}
