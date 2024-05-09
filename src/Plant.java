
public class Plant implements Storable{
    private int age;
    private String name;
    private int agelimit;
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
