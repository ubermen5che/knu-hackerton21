package KM.KNUguard.domain;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "crawl_data")
public class CrawlData {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @Column(name = "univ_name")
    private String u_name;
    private String college;
    private String type;
    private String address;
    private Timestamp exposure_sdate;
    private Timestamp exposure_edate;
    private String disinfection;
    @Column(name = "latitude")
    private Float lat;
    @Column(name = "longitude")
    private Float lng;
    private Timestamp crawled_date;
    private String content;
    private String region;

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

    public Float getLat() {
        return lat;
    }

    public void setLat(Float latitude) {
        this.lat = latitude;
    }

    public Float getLng() {
        return lng;
    }

    public void setLng(Float longitude) {
        this.lng = longitude;
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

    public String getU_name() {
        return u_name;
    }

    public void setU_name(String u_name) {
        this.u_name = u_name;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public Timestamp getExposure_sdate() {
        return exposure_sdate;
    }

    public void setExposure_sdate(Timestamp exposure_sdate) {
        this.exposure_sdate = exposure_sdate;
    }

    public Timestamp getExposure_edate() {
        return exposure_edate;
    }

    public void setExposure_edate(Timestamp exposure_edate) {
        this.exposure_edate = exposure_edate;
    }

    public Timestamp getCrawled_date() {
        return crawled_date;
    }

    public void setCrawled_date(Timestamp crawled_date) {
        this.crawled_date = crawled_date;
    }
}
