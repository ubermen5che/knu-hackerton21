package KM.KNUguard.controller;

import KM.KNUguard.domain.CrawlData;
import KM.KNUguard.service.CheckingService;
import org.json.HTTP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

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

    @PostMapping("/getXY")
    public HashMap<String, List<CrawlData>> getCPData(@RequestBody HashMap<String, String> lld) {
        System.out.println("lld.getLat() " + lld.get("lat"));
        System.out.println("lld.getLng() " + lld.get("lng"));

        Optional<List> ol = checkingService.getCPInfo(Float.parseFloat(lld.get("lat")), Float.parseFloat(lld.get("lng")));

        HashMap<String, List<CrawlData>> body = new HashMap<>();

        if (ol.isPresent()) {
            body.put("dataList", ol.get());
            System.out.println("ol.get().size() = " + ol.get().size());
        }
        else{
            body.put("empty", null);
        }
        return body;
    }
}
