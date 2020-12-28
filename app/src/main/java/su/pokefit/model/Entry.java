package su.pokefit.model;


import com.google.gson.annotations.SerializedName;

public class Entry {

    @SerializedName("entry_number")
    private int number;

    @SerializedName("pokemon_species")
    private Species species;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Species getSpecies() {
        return species;
    }

    public void setSpecies(Species species) {
        this.species = species;
    }
}
