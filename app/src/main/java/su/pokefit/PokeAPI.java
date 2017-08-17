package su.pokefit;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface PokeAPI {

    String BASE_URL = "https://pokeapi.co/api/v2/";

    @GET("pokedex/2/")
    Call<Pokedex> getPokedex();

    @GET("pokemon-species/{id}/")
    Call<LangDescription> getAllDescriptions(@Path("id") int pokeId);
}
