package KM.KNUguard.service;

import KM.KNUguard.domain.CrawlData;
import KM.KNUguard.domain.Member;
import KM.KNUguard.domain.UnivData;
import KM.KNUguard.repository.*;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Transactional
public class GeocodingService {

    private CrawlDataRepository crawlDataRepository;

    @Autowired
    private UnivDataRepository univDataRepository;

    @Autowired
    SDJCrawlDataRepository sRepo;

    @Autowired
    SDJUnivCrawlDataRepository uRepo;

    public GeocodingService(CrawlDataRepository crawlDataRepository){
        this.crawlDataRepository = crawlDataRepository;
    }

    public Boolean validateDuplicateCData(CrawlData cd) {
        Optional<CrawlData> ocd = sRepo.findByLatAndLng(cd.getLat(), cd.getLng());  //Return optional

        if(ocd.isPresent())
            return false;

        return true;
    }

    public Boolean validateDuplicateUnivData(UnivData ud) {
        Optional<UnivData> oud = uRepo.findByAddress(ud.getAddress());  //Return optional

        if(oud.isPresent())
            return false;

        return true;
    }

    public void saveOneUData(UnivData ud){
        univDataRepository.save(ud);
    }

    public void saveOneCData(CrawlData cd){
        crawlDataRepository.save(cd);
    }

    public Optional<CrawlData> findXY(Float lat, Float lng){
        return sRepo.findByLatAndLng(lat, lng);
    }

    //public storeCrawledData
    public Map<String, String> getGeoDataByAddress(String completeAddress) {
        try {
            System.out.println("completeAddress = " + completeAddress);
            String API_KEY = "";
            String surl = "https://maps.googleapis.com/maps/api/geocode/json?address="+ URLEncoder.encode(completeAddress, "UTF-8")+"&key="+API_KEY;
            URL url = new URL(surl);
            InputStream is = url.openConnection().getInputStream();

            BufferedReader streamReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));

            StringBuilder responseStrBuilder = new StringBuilder();
            String inputStr;
            System.out.println(">>>>>>>>>> >>>>>>>>>> InputStream Start <<<<<<<<<< <<<<<<<<<<");
            while ((inputStr = streamReader.readLine()) != null) {
                //System.out.println(">>>>>>>>>>     "+inputStr);
                responseStrBuilder.append(inputStr);
            }
            System.out.println(">>>>>>>>>> >>>>>>>>>> InputStream End <<<<<<<<<< <<<<<<<<<<");

            JSONObject jo = new JSONObject(responseStrBuilder.toString());
            JSONArray results = jo.getJSONArray("results");
            String region = null;
            String province = null;
            String zip = null;
            Map<String, String> ret = new HashMap<String, String>();
            if(results.length() > 0) {
                JSONObject jsonObject;
                jsonObject = results.getJSONObject(0);
                Double lat = jsonObject.getJSONObject("geometry").getJSONObject("location").getDouble("lat");
                Double lng = jsonObject.getJSONObject("geometry").getJSONObject("location").getDouble("lng");
                ret.put("lat", lat.toString());
                ret.put("lng", lng.toString());
                System.out.println("LAT:\t\t"+lat);
                System.out.println("LNG:\t\t"+lng);
                JSONArray ja = jsonObject.getJSONArray("address_components");
                for(int l=0; l<ja.length(); l++) {
                    JSONObject curjo = ja.getJSONObject(l);
                    String type = curjo.getJSONArray("types").getString(0);
                    String short_name = curjo.getString("short_name");
                    if(type.equals("postal_code")) {
                        System.out.println("POSTAL_CODE: "+short_name);
                        ret.put("zip", short_name);
                    }
                    else if(type.equals("administrative_area_level_3")) {
                        System.out.println("CITY: "+short_name);
                        ret.put("city", short_name);
                    }
                    else if(type.equals("administrative_area_level_2")) {
                        System.out.println("PROVINCE: "+short_name);
                        ret.put("province", short_name);
                    }
                    else if(type.equals("administrative_area_level_1")) {
                        System.out.println("REGION: "+short_name);
                        ret.put("region", short_name);
                    }
                }
                return ret;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}
