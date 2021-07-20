package KM.KNUguard.service;

import KM.KNUguard.domain.CrawlData;
import KM.KNUguard.repository.CrawlDataRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public class CheckingService {

    private final CrawlDataRepository crawlDataRepository;

    public CheckingService(CrawlDataRepository crawlDataRepository){
        this.crawlDataRepository = crawlDataRepository;
    }

    public List<CrawlData> getCPInfo(String univ_name, Double latitude, Double longitude){
        return crawlDataRepository.getCrawlData(univ_name, latitude, longitude);
    }
}
