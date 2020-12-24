package su.pokefit;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DetailActivity extends AppCompatActivity {

    private PokeAPI pokeAPI;
    @BindView(R.id.xml_details_name)
    TextView title;
    @BindView(R.id.xml_details_image)
    ImageView image;
    @BindView(R.id.xml_detail_desc)
    TextView desc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        int pokeId = intent.getIntExtra("pokeInt", 1);
        String pokeName = intent.getStringExtra("pokeString");
        title.setText(pokeName);
        configureImage(pokeId);

        createAPI();
        pokeAPI.getAllDescriptions(pokeId).enqueue(descCallback);
    }

    private void createAPI() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(PokeAPI.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        pokeAPI = retrofit.create(PokeAPI.class);
    }

    Callback<LangDescription> descCallback = new Callback<LangDescription>() {
        @Override
        public void onResponse(Call<LangDescription> call, Response<LangDescription> response) {
            if (response.isSuccessful() && response.body() != null) {
                LangDescription data = new LangDescription();
                data.setDescriptions(response.body().getDescriptions());
                //index 1 represents English description
                desc.setText(data.getDescriptions().get(1).getDescription());
            } else {
                Log.v("Callback", "Code " + response.code() + " Message " + response.message());
            }
        }

        @Override
        public void onFailure(Call<LangDescription> call, Throwable t) {
            t.printStackTrace();
        }
    };

    private void configureImage(int pokeId) {
        String url = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/" + pokeId + ".png";
        Glide.with(this)
                .load(url)
                .override(480, 480)
                .placeholder(R.drawable.loading)
                .into(image);
    }

}
