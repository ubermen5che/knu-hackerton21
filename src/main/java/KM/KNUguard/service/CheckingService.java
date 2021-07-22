package KM.KNUguard.service;

import KM.KNUguard.domain.CrawlData;
import KM.KNUguard.repository.CrawlDataRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
public class CheckingService {

    private final CrawlDataRepository crawlDataRepository;

    public CheckingService(CrawlDataRepository crawlDataRepository){
        this.crawlDataRepository = crawlDataRepository;
    }

    public List<CrawlData> getCPInfoUniv(String univ_name, Float latitude, Float longitude){

        Optional<List> cList = crawlDataRepository.getCrawlDataUniv(univ_name, latitude, longitude);

        if(cList.isPresent())
            return cList.get();
        else
            return null;
    }

    public Optional<List> getCPInfo(Float latitude, Float longitude){
        Optional<List> cList = crawlDataRepository.getCrawlData(latitude, longitude);

        return cList;
    }
}
