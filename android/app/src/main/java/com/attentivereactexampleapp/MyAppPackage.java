
package com.attentivereactexampleapp;

import com.attentive.androidsdk.AttentiveConfig;
import com.facebook.react.ReactPackage;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.uimanager.ViewManager;

import java.util.ArrayList;
import java.util.List;

public class MyAppPackage implements ReactPackage {

    @Override
    public List<ViewManager> createViewManagers(ReactApplicationContext reactContext) {
        return new ArrayList<>();
    }

    @Override
    public List<NativeModule> createNativeModules(
        ReactApplicationContext reactContext) {
        List<NativeModule> modules = new ArrayList<>();

        createAttentiveNativeModules(reactContext, modules);

        return modules;
    }

    private void createAttentiveNativeModules(ReactApplicationContext reactContext, List<NativeModule> modules) {
        AttentiveConfig attentiveConfig = new AttentiveConfig("YOUR_ATTENTIVE_DOMAIN", AttentiveConfig.Mode.PRODUCTION, reactContext);
        modules.add(new AttentiveCreativeModule(reactContext, attentiveConfig));
        modules.add(new AttentiveEventTrackingModule(attentiveConfig));
    }
}

