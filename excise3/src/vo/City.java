package vo;

/**
 * @author 13281
 */
public class City {
    private int id;
    private String province;
    private String city;

    public City(){
        super();
    }

    public City(int id, String province, String city) {
        this.id = id;
        this.province = province;
        this.city = city;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "City{" +
                "id=" + id +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}
