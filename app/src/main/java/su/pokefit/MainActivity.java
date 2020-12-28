package su.pokefit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import su.pokefit.model.Pokedex;

public class MainActivity extends AppCompatActivity {

    @Inject
    PokeApi pokeAPI;

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        PokeApp.appComponent().inject(this);

        recyclerView = findViewById(R.id.xml_recycler_pokedex);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        pokeAPI.getPokedex().enqueue(pokeCallback);
    }

    Callback<Pokedex> pokeCallback = new Callback<Pokedex>() {
        @Override
        public void onResponse(Call<Pokedex> call, Response<Pokedex> response) {
            if (response.isSuccessful() && response.body() != null) {
                Pokedex data = new Pokedex();
                data.setEntries(response.body().getEntries());
                //data is a list of entries with number and name
                recyclerView.setAdapter(new PokedexViewAdapter(data));
            } else {
                Log.v("Callback", "Code " + response.code() + " Message " + response.message());
            }
        }

        @Override
        public void onFailure(Call<Pokedex> call, Throwable t) {
            t.printStackTrace();
        }
    };
}
