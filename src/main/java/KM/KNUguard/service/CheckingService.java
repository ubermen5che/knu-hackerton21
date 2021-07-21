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

    public List<CrawlData> getCPInfoUniv(String univ_name, Float latitude, Float longitude){
        return crawlDataRepository.getCrawlDataUniv(univ_name, latitude, longitude);
    }

    public List<CrawlData> getCPInfo(Float latitude, Float longitude){
        return crawlDataRepository.getCrawlData(latitude, longitude);
    }
}
