package KM.KNUguard.controller;

import KM.KNUguard.domain.CrawlData;
import KM.KNUguard.domain.UnivData;
import KM.KNUguard.service.GeocodingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class GeoCodingController {

    private GeocodingService geocodingService;

    @Autowired
    public GeoCodingController(GeocodingService geocodingService){
        this.geocodingService = geocodingService;
    }

    @PostMapping("/data")
    public String storeCrawledData(@RequestBody List<CrawlData> cd){
        Map<String, String> xy;
        List<CrawlData> cList = new ArrayList<>();

        for (CrawlData c : cd) {
            if(geocodingService.getGeoDataByAddress(c.getAddress()) == null)
                continue;

            CrawlData new_cd = new CrawlData();
            new_cd.setCrawled_date(Timestamp.valueOf(LocalDateTime.now()));
            new_cd.setCollege(c.getCollege());
            new_cd.setExposure_edate(c.getExposure_edate());
            new_cd.setExposure_sdate(c.getExposure_sdate());
            new_cd.setU_name(c.getU_name());
            new_cd.setAddress(c.getAddress());
            new_cd.setContent(c.getContent());
            new_cd.setDisinfection(c.getDisinfection());
            System.out.println("c.getAddress() = " + c.getAddress());
            new_cd.setLat(Float.parseFloat(geocodingService.getGeoDataByAddress(c.getAddress()).get("lat")));
            new_cd.setLng(Float.parseFloat(geocodingService.getGeoDataByAddress(c.getAddress()).get("lng")));
            new_cd.setRegion(c.getRegion());
            new_cd.setType(c.getType());
            cList.add(new_cd);
        }


        for (CrawlData crawlData : cList) {

            Boolean valid = geocodingService.validateDuplicateCData(crawlData);

            if(valid == true)
                geocodingService.saveOneCData(crawlData);
            else
                continue;
        }

        return "success";
    }
    @PostMapping("/univ/data")
    public String storeUnivCrawledData(@RequestBody List<UnivData> cd){
        List<UnivData> uList = new ArrayList<>();

        for (UnivData c : cd) {
            if(geocodingService.getGeoDataByAddress(c.getAddress()) == null
            || geocodingService.getGeoDataByAddress(c.getAddress()).get("lat") == null
            || geocodingService.getGeoDataByAddress(c.getAddress()).get("lng") == null)
                continue;

            UnivData new_ud = new UnivData();

            new_ud.setPost_id(c.getPost_id());
            new_ud.setCont(c.getCont());
            new_ud.setCrawled_date(Timestamp.valueOf(LocalDateTime.now()));
            new_ud.setAddress(c.getAddress());
            new_ud.setCollege(c.getCollege());
            new_ud.setLat(Float.parseFloat(geocodingService.getGeoDataByAddress(c.getAddress()).get("lat")));
            new_ud.setLng(Float.parseFloat(geocodingService.getGeoDataByAddress(c.getAddress()).get("lng")));
            new_ud.setDisinfection(c.getDisinfection());
            new_ud.setUniv_name(c.getUniv_name());
            new_ud.setConfirmedDate(c.getConfirmedDate());
            new_ud.setSubject(c.getSubject());
            uList.add(new_ud);
        }

        for (UnivData uData : uList) {

            Boolean valid = geocodingService.validateDuplicateUnivData(uData);

            if(valid == true) {
                geocodingService.saveOneUData(uData);
                CrawlData crawlData = new CrawlData();

                crawlData.setCrawled_date(uData.getCrawled_date());
                crawlData.setAddress(uData.getAddress());
                crawlData.setCollege(uData.getCollege());
                crawlData.setLat(uData.getLat());
                crawlData.setLng(uData.getLng());
                crawlData.setContent(uData.getCont());
                crawlData.setuName(uData.getUniv_name());
                crawlData.setType(uData.getUniv_name());
                geocodingService.saveOneCData(crawlData);
            }
            else
                continue;
        }

        return "success";
    }
}
