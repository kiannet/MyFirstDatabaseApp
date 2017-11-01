package sample;

/**
 * Created by kiannet on 01.11.2017.
 */
public class Manager {

    private Integer kod;
    private String name;
    private String contactName;
    private String contactPhone;

    public Manager(){

    }

    public Integer getKod(){
        return kod;
    }

    public String getName(){
        return name;
    }

    public String getContactName(){
        return contactName;
    }

    public String getContactPhone(){
        return contactPhone;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setContactName(String contactName){
        this.contactName = contactName;
    }

    public void setKod(int kod){
        this.kod = kod;
    }

    public void setContactPhone(String contactPhone){
        this.contactName = contactPhone;
    }
}
