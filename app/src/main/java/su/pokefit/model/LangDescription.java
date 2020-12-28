package su.pokefit.model;


import com.google.gson.annotations.SerializedName;

import java.util.List;

public class LangDescription {

    @SerializedName("flavor_text_entries")
    private List<Description> descriptions; //consists of descriptions in all languages

    public List<Description> getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(List<Description> descriptions) {
        this.descriptions = descriptions;
    }
}
