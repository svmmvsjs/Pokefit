package su.pokefit;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import su.pokefit.model.LangDescription;

public class DetailActivity extends AppCompatActivity {

    @Inject
    PokeApi pokeAPI;

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
        PokeApp.appComponent().inject(this);

        ButterKnife.bind(this);
        Intent intent = getIntent();
        int pokeId = intent.getIntExtra("pokeInt", 1);
        String pokeName = intent.getStringExtra("pokeString");
        title.setText(pokeName);
        configureImage(pokeId);

        pokeAPI.getAllDescriptions(pokeId).enqueue(descCallback);
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
                .placeholder(R.drawable.loading_128)
                .into(image);
    }
}
