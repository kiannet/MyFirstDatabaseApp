package sample;

/**
 * Created by kiannet on 24.10.2017.
 */
public class Item {
    private Integer id;
    private String name;
    private String type;
    private Integer price;

    public Item(){

    }

    public void setName(String name){
        this.name = name;
    }

    public void setType(String type){
        this.type = type;
    }

    public void setPrice(Integer price){
        this.price = price;
    }

    public String getName(){
        return name;
    }

    public String getType(){
        return type;
    }

    public Integer getPrice(){
        return price;
    }

    public Integer getId(){
        return id;
    }
}
