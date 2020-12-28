package su.pokefit.model;


import com.google.gson.annotations.SerializedName;

public class Description {

    @SerializedName("flavor_text")
    private String description;

    public String getDescription() {
        description = description.replaceAll("\\n", " ");
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
