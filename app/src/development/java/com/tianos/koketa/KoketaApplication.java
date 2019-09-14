package com.tianos.koketa;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;
import android.util.Log;

import com.facebook.stetho.Stetho;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class KoketaApplication extends Application {

    private Realm realm;
    private static KoketaApplication instance;

    public static KoketaApplication getInstance() {
        return instance;
    }

    public void setInstance() {
        instance = this;
    }

    // Called when the application is starting, before any other application objects have been created.
    // Overriding this method is totally optional!
    @Override
    public void onCreate() {
        super.onCreate();

        setInstance();
        initializeStetho();
        initializeRealm();
    }

    // Called by the system when the device configuration changes while your component is running.
    // Overriding this method is totally optional!
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    // This is called when the overall system is running low on memory,
    // and would like actively running processes to tighten their belts.
    // Overriding this method is totally optional!
    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        Realm.getDefaultInstance().close();
    }

    private void initializeRealm() {
        // Initialize Realm (just once per application)
        Realm.init(this);

        // Get a Realm instance for this thread
//        Realm realm = Realm.getDefaultInstance();


        RealmConfiguration configuration = new RealmConfiguration.Builder()
            .name("koketa.realm")
            .schemaVersion(8)
            .deleteRealmIfMigrationNeeded()
            .build()
        ;

        Realm.setDefaultConfiguration(configuration);
        realm = Realm.getInstance(configuration);
    }

    public Realm getRealm() {
        return realm;
    }

    private void initializeStetho() {
        Stetho.initialize(
            Stetho.newInitializerBuilder(this)
                .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
                .build()
        );
    }
}
