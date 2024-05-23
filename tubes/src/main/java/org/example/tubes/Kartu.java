package org.example.tubes;

public class Kartu {
    private String name;
    private String assets;
    private String propertites;
    private String path_image;

    public Kartu(String name, String assets, String propertites) {
        this.name = name;
        this.assets = assets;
        this.propertites = propertites;
    }
    public Kartu(){
        this.name = "";
        this.assets = null;
        this.propertites = null;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getAssets() {
        return assets;
    }
    public void setAssets(String assets) {
        this.assets = assets;
    }
    public String getPropertites() {
        return propertites;
    }
    public void setPropertites(String propertites) {
        this.propertites = propertites;
    }


}
