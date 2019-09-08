package com.tianos.koketa.util;

import android.content.Context;
import android.content.SharedPreferences;


import com.tianos.koketa.entity.User;

import io.realm.Realm;

public class PreferencesManager {

    private static final String PREFERENCES_NAME = "KOKETA";
    private static final String PREFERENCE_LOGGED = "LOGGED";

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
     * Preference User - START
     *
     * @param object
     */
    public void realmSetUser(final User object) {
//        Realm realm = IntralotApplication.getInstance().getRealm();
//        realm.executeTransaction(new Realm.Transaction() {
//            @Override
//            public void execute(Realm realm) {
//                realm.copyToRealm(object);
//            }
//        });
    }

    public User realmGetUser() {
//        Realm realm = IntralotApplication.getInstance().getRealm();
//        return realm.where(User.class).findFirst();

        return new User();
    }

    public void realmDeleteUser() {
//        Realm realm = IntralotApplication.getInstance().getRealm();
//        final User response = realm.where(User.class).findFirst();
//        realm.executeTransaction(new Realm.Transaction() {
//            @Override
//            public void execute(Realm realm) {
//
//                if (response == null) {
//                    return;
//                }
//
//                response.deleteFromRealm();
//            }
//        });
    }
    /**
     * Preference User - END
     *
     */

}
