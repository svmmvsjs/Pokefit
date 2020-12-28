package su.pokefit.di;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class PreferencesModule {

    private final Context context;

    public PreferencesModule(Context context) {
        this.context = context;
    }

    // Dagger will only look for methods annotated with @Provides
    @Provides
    @Singleton
    SharedPreferences provideSharedPreferences() {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }
}
