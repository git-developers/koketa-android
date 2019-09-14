package com.tianos.koketa.util;

import android.content.Context;
import android.content.SharedPreferences;


import com.tianos.koketa.KoketaApplication;
import com.tianos.koketa.database.BreadcrumbDb;
import com.tianos.koketa.database.ProfileDb;
import com.tianos.koketa.database.UserDb;
import com.tianos.koketa.entity.User;
import com.tianos.koketa.ui.activity.LoginActivity;

import io.realm.Realm;

public class PreferencesManager {

    private static final String PREFERENCES_NAME = "KOKETA";
    private static final String PREFERENCE_LOGGED = "LOGGED";
    private static final String PREFERENCE_USERNAME = "USERNAME";

    private static PreferencesManager self;
    private final SharedPreferences mPreferences;
    private final Context context;

    private PreferencesManager(Context context) {
        this.context = context;
        mPreferences = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
    }

    public static PreferencesManager getInstance(Context context) {
        if (self == null) {
            self = new PreferencesManager(context);
        }

        return self;
    }

    public void logOut() {
        deleteLogged();
        deleteDatabase();
        realmDeleteUser();
    }


    /**
     * User logged
     */
    public boolean isLogged() {
        return mPreferences.getBoolean(PREFERENCE_LOGGED, false);
    }

    public void setLogged() {
        SharedPreferences.Editor editor = mPreferences.edit();
        editor.putBoolean(PREFERENCE_LOGGED, true);
        editor.commit();
    }

    public void deleteLogged() {
        SharedPreferences.Editor editor = mPreferences.edit();
        editor.putString(PREFERENCE_LOGGED, null);
        editor.commit();
    }


    /**
     * USERNAME
     */
    public void setUsername(User user) {

        if (user == null) {
            return;
        }

        SharedPreferences.Editor editor = mPreferences.edit();
        editor.putString(PREFERENCE_USERNAME, user.getUsername());
        editor.apply();
    }

    public String getUsername() {
        return mPreferences.getString(PREFERENCE_USERNAME, "");
    }

    public void deleteUsername() {
        SharedPreferences.Editor editor = mPreferences.edit();
        editor.putString(PREFERENCE_USERNAME, null);
        editor.apply();
    }

    public void deleteDatabase() {
        BreadcrumbDb breadcrumbDb = new BreadcrumbDb(this.context);
        breadcrumbDb.delete();

        UserDb userDb = new UserDb(this.context);
        userDb.delete();

        ProfileDb profileDb = new ProfileDb(this.context);
        profileDb.delete();
    }






    /**
     * Preference User - INICIO
     *
     * @param object
     */
    public void realmSetUser(final User object) {
        Realm realm = KoketaApplication.getInstance().getRealm();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealm(object);
            }
        });
    }

    public User realmGetUser() {
        Realm realm = KoketaApplication.getInstance().getRealm();
        return realm.where(User.class).findFirst();
    }

    public void realmDeleteUser() {

        Realm realm = KoketaApplication.getInstance().getRealm();
        final User response = realm.where(User.class).findFirst();

        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {

                if (response == null) {
                    return;
                }

                response.deleteFromRealm();
            }
        });
    }

}
