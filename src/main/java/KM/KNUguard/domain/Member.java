package KM.KNUguard.domain;

import javax.persistence.*;

@Entity
@Table(name = "Member")
public class Member {

    //db가 알아서 PK를 생성해주는것을 Identity라고 한다.
    @Id
    private String m_id;
    private String pw;
    private String univ_name;
    private String name;
    private Double m_latitude;
    private Double m_longitude;

    public String getId() {
        return m_id;
    }

    public void setId(String id) {
        this.m_id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public String getUniv_name() {
        return univ_name;
    }

    public void setUniv_name(String univ_name) {
        this.univ_name = univ_name;
    }

    public Double getM_latitude() {
        return m_latitude;
    }

    public void setM_latitude(Double m_latitude) {
        this.m_latitude = m_latitude;
    }

    public Double getM_longitude() {
        return m_longitude;
    }

    public void setM_longitude(Double m_longitude) {
        this.m_longitude = m_longitude;
    }
}