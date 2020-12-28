package su.pokefit.di;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = PreferencesModule.class)
public interface PreferencesComponent {

//    void inject(MainActivity mainActivity);
}
