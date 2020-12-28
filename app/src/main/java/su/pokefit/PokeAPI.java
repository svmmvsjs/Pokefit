package su.pokefit;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import su.pokefit.model.LangDescription;
import su.pokefit.model.Pokedex;

public interface PokeApi {

    @GET("pokedex/2/")
    Call<Pokedex> getPokedex();

    @GET("pokemon-species/{id}/")
    Call<LangDescription> getAllDescriptions(@Path("id") int pokeId);
}
