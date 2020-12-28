package su.pokefit.di;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import su.pokefit.PokeApi;

import static su.pokefit.di.NetworkModule.RETROFIT_POKE_API;

@Module(includes = NetworkModule.class)
public class ApiModule {

    public ApiModule() {
    }

    @Provides
    @Singleton
    PokeApi providePokeApi(@Named(RETROFIT_POKE_API) Retrofit retrofit) {
        return retrofit.create(PokeApi.class);
    }
}
