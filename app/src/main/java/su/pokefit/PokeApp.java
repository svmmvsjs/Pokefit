package su.pokefit;

import android.app.Application;

import su.pokefit.di.ApiModule;
import su.pokefit.di.AppComponent;
import su.pokefit.di.AppModule;
import su.pokefit.di.DaggerAppComponent;
import su.pokefit.di.DaggerPreferencesComponent;
import su.pokefit.di.NetworkModule;
import su.pokefit.di.PreferencesComponent;
import su.pokefit.di.PreferencesModule;

public class PokeApp extends Application {

    private static AppComponent appComponent;
    private static PreferencesComponent preferencesComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        setupDI();
    }

    private void setupDI() {
        preferencesComponent = DaggerPreferencesComponent.builder().preferencesModule(new PreferencesModule(this)).build();

        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .networkModule(new NetworkModule())
                .apiModule(new ApiModule())
                .build();
    }

    public static PreferencesComponent preferencesComponent() {
        return preferencesComponent;
    }

    public static AppComponent appComponent() {
        return appComponent;
    }
}
