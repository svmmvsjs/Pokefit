package su.pokefit.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Pokedex {

    @SerializedName("pokemon_entries")
    private List<Entry> entries; //consists of number and name

    public List<Entry> getEntries() {
        return entries;
    }

    public void setEntries(List<Entry> entries) {
        this.entries = entries;
    }
}
