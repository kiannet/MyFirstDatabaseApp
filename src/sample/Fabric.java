package sample;

/**
 * Created by kiannet on 24.10.2017.
 */
public class Fabric {
    private Integer kod;
    private String name;
    private String place;
    private String telephone;

    public Fabric(){

    }

    public Integer getKod(){
        return kod;
    }

    public String getName(){
        return name;
    }

    public String getPlace(){
        return place;
    }

    public String getTelephone(){
        return telephone;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setPlace(String place){
        this.place = place;
    }

    public void setKod(int kod){
        this.kod = kod;
    }

    public void setTelephone(String telephone){
        this.telephone = telephone;
    }
}
