package KM.KNUguard.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;

@Entity

public class CrawlData {
    @Id
    private Long id;
    private String u_name;
    private String college;
    private String type;
    private String address;
    private Timestamp exposure_sdate;
    private Timestamp exposure_edate;
    private String disinfection;
    private Float latitude;
    private Float longitude;
    private Timestamp crawled_date;
    private String content;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRegion() {
        return college;
    }

    public void setRegion(String region) {
        this.college = region;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Timestamp getExposureSDate() {
        return exposure_sdate;
    }

    public void setExposureSDate(Timestamp exposureSDate) {
        this.exposure_sdate = exposureSDate;
    }

    public Timestamp getExposureEDate() {
        return exposure_edate;
    }

    public void setExposureEDate(Timestamp exposureEDate) {
        this.exposure_edate = exposureEDate;
    }

    public String getDisinfection() {
        return disinfection;
    }

    public void setDisinfection(String disinfection) {
        this.disinfection = disinfection;
    }

    public Float getLatitude() {
        return latitude;
    }

    public void setLatitude(Float latitude) {
        this.latitude = latitude;
    }

    public Float getLongitude() {
        return longitude;
    }

    public void setLongitude(Float longitude) {
        this.longitude = longitude;
    }

    public Timestamp getCrawledDate() {
        return crawled_date;
    }

    public void setCrawledDate(Timestamp crawledDate) {
        this.crawled_date = crawledDate;
    }

    public String getuName() {
        return u_name;
    }

    public void setuName(String uName) {
        this.u_name = uName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
