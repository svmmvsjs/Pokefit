package su.pokefit.di;

import javax.inject.Singleton;

import dagger.Component;
import su.pokefit.DetailActivity;
import su.pokefit.MainActivity;

@Singleton
@Component(modules = {AppModule.class, NetworkModule.class, ApiModule.class})
public interface AppComponent {

    void inject(MainActivity mainActivity);

    void inject(DetailActivity detailActivity);
}
