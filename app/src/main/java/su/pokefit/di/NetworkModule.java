package su.pokefit.di;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static java.util.concurrent.TimeUnit.SECONDS;

@Module
public class NetworkModule {

    public static final String RETROFIT_BACKEND = "retrofitBackend";
    public static final String RETROFIT_POKE_API = "retrofitPokeApi";

    private static final String POKE_API_URL = "https://pokeapi.co/api/v2/";
    private String backendUrl;

    public NetworkModule(String backend) {
        backendUrl = backend;
    }

    public NetworkModule() {
    }

    @Provides
    @Singleton
    Gson provideGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        return gsonBuilder.create();
    }

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient() {
        OkHttpClient.Builder client = new OkHttpClient.Builder()
                .connectTimeout(10, SECONDS);
        return client.build();
    }

    @Provides
    Retrofit.Builder provideRetrofitBuilder(Gson gson, OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson));
    }

    @Provides
    @Singleton
    @Named(RETROFIT_BACKEND)
    Retrofit provideRetrofitBackend(Retrofit.Builder builder) {
        return builder.baseUrl(backendUrl).build();
    }

    @Provides
    @Singleton
    @Named(RETROFIT_POKE_API)
    Retrofit provideRetrofitPokeApi(Retrofit.Builder builder) {
        return builder.baseUrl(POKE_API_URL).build();
    }
}
