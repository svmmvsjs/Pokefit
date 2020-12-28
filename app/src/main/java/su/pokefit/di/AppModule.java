package su.pokefit.di;

import dagger.Module;
import su.pokefit.PokeApp;

@Module
public class AppModule {

    final PokeApp pokeApp;

    public AppModule(PokeApp application) {
        pokeApp = application;
    }
}
