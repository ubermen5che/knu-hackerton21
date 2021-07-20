package KM.KNUguard.repository;

import KM.KNUguard.domain.CrawlData;

import java.util.List;

public interface CrawlDataRepository {
    List<CrawlData> getCrawlData(String univ_name, Double latitude, Double longitude);
}
