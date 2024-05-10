
public class Plant implements Storable{
    private int age;
    private String name;
    private int agelimit;
    // private Product product;
    public Plant() {
        this.age = 0;
        this.name = "Plant";
        this.agelimit = 1000000;
    }
    public Plant(int age, String name, int agelimit) {
        this.age = age;
        this.name = name;
        this.agelimit = agelimit;
    }
    public int getAge() {
        return age;
    }
    public void incrementAge() {
        this.age++;
    }
}
