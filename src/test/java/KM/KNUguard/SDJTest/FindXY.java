package KM.KNUguard.SDJTest;

import KM.KNUguard.domain.CrawlData;
import KM.KNUguard.repository.CrawlDataRepository;
import KM.KNUguard.repository.MemberRepository;
import KM.KNUguard.repository.SDJCrawlDataRepository;
import KM.KNUguard.service.GeocodingService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
import java.util.Optional;

@SpringBootTest
@Transactional
public class FindXY {

    @Autowired
    SDJCrawlDataRepository sRepo;

    @Autowired
    CrawlDataRepository crawlDataRepository;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    GeocodingService geocodingService;



    /**
    @Test
    void getCD(){
        CrawlData cd1 = crawlDataRepository.getCrawlData(35.86967000000000F, 128.59310000000000F)
                .get(0);

        Optional<CrawlData> cd2 = sRepo.findByLatAndLng(35.86333000000000F, 128.59310000000000F);

        if(cd2.isPresent()){
            Assertions.assertThat(cd1.getId()).isEqualTo(cd2.get().getId());
        }else{
            System.out.println("null객체 return");
        }
    }
 **/
    @Test
    void getXY(){
        Map<String, String> xy = geocodingService.getGeoDataByAddress("경북대학교 사회과학대학 241호");

        System.out.println("xy.get(\"lat\") + xy.get(\"lng\") = " + xy.get("lat") + xy.get("lng"));
    }
}
