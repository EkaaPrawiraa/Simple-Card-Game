public class Animal implements Storable {
    private String name;
    private int weight;
    final private int weightlimit;
    private String type; // Added type parameter

    public Animal(String name, int weight, int weightlimit, String type) {
        this.name = name;
        this.weight = weight;
        this.weightlimit = weightlimit;
        setType(type); // Call setType to validate and set the type
    }

    public String getName() {
        return name;
    }

    public int getWeight() {
        return weight;
    }

    public int getweightlimit() {
        return weightlimit;
    }

    public String getType() {
        return type;
    }

    private void setType(String type) {
        if (type.equalsIgnoreCase("herbivore") || type.equalsIgnoreCase("omnivore") || type.equalsIgnoreCase("carnivore")) {
            this.type = type.toLowerCase();
        } else {
            throw new IllegalArgumentException("Invalid animal type");
        }
    }

    public void addWeight(int weight) {
        this.weight += weight;
    }
}
