package KM.KNUguard.controller;

import KM.KNUguard.domain.CrawlData;
import KM.KNUguard.service.CheckingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CheckingController {

    private final CheckingService checkingService;

    @Autowired
    public CheckingController(CheckingService checkingService) {
        this.checkingService = checkingService;
    }

    @GetMapping("/member/{univ_name}/{latitude},{longitude}")
    public List<CrawlData> getCPData(@PathVariable String univ_name,
                                     @PathVariable Double latitude,
                                     @PathVariable Double longitude){
        return checkingService.getCPInfo(univ_name, latitude, longitude);
    }
}
