import java.util.ArrayList;

public class Product implements Storable{
    private String type;
    private String origin;
    private Integer addedWeight;

    public Product(){
        this.type = "";
        this.origin = "";
        this.addedWeight = 0;
    }

    public Product(String type, String origin, Integer addedWeight){
        this.type = type;
        this.origin = origin;
        this.addedWeight = addedWeight;
    }

    public Product(final Product other){
        this.type = other.type;
        this.origin = other.origin;
        this.addedWeight = other.addedWeight;
    }

    public String getType(){
        return this.type;
    }

    public String getOrigin(){
        return this.origin;
    }

    public Integer getAddedWeight(){
        return this.addedWeight;
    }


    public String getClassName(){
        return "Product";
    }

}
