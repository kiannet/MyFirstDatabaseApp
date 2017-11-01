package sample;

/**
 * Created by kiannet on 01.11.2017.
 */
public class Director {

    private Integer kod;
    private String name;
    private String speciality;

    public Director(){

    }

    public Integer getKod(){
        return kod;
    }

    public String getName(){
        return name;
    }

    public String getSpeciality(){
        return speciality;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setSpeciality(String speciality){
        this.speciality = speciality;
    }

    public void setKod(int kod){
        this.kod = kod;
    }


}

