package KM.KNUguard.controller;

import KM.KNUguard.domain.CrawlData;
import KM.KNUguard.service.CheckingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CheckingController {

    private final CheckingService checkingService;

    @Autowired
    public CheckingController(CheckingService checkingService) {
        this.checkingService = checkingService;
    }

    @GetMapping("/member/{univ_name}/{latitude},{longitude}")
    public List<CrawlData> getCPDataUniv(@PathVariable String univ_name,
                                     @PathVariable Float latitude,
                                     @PathVariable Float longitude){
        return checkingService.getCPInfoUniv(univ_name, latitude, longitude);
    }

    @GetMapping("/member/{latitude},{longitude}")
    public List<CrawlData> getCPData(@PathVariable Float latitude,
                                     @PathVariable Float longitude){
        System.out.println("latitude = " + latitude);
        return checkingService.getCPInfo(latitude, longitude);
    }
}
