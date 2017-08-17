package su.pokefit;

import com.google.gson.annotations.SerializedName;

public class Species {

    @SerializedName("name")
    private String name;

    public String getName() {
        name = name.substring(0,1).toUpperCase() + name.substring(1);
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
