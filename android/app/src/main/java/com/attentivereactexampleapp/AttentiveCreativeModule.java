package com.attentivereactexampleapp;

import android.app.Activity;
import android.util.Log;
import android.view.ViewGroup;
import com.attentive.androidsdk.AttentiveConfig;
import com.attentive.androidsdk.UserIdentifiers;
import com.attentive.androidsdk.creatives.Creative;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.UiThreadUtil;
import java.util.HashMap;
import java.util.Map;

public class AttentiveCreativeModule extends ReactContextBaseJavaModule {
    private static final String TAG = "AttentiveCreativeModule";

    private final AttentiveConfig config;

    AttentiveCreativeModule(ReactApplicationContext context, AttentiveConfig config) {
        super(context);
        this.config = config;
    }

    @Override
    public String getName() {
        return "AttentiveCreativeModule";
    }

    @ReactMethod
    public void triggerCreative() {
        Log.i(TAG, "Native Attentive module was called to trigger the creative.");
        try {
            Activity currentActivity = getReactApplicationContext().getCurrentActivity();
            if (currentActivity != null) {
                ViewGroup rootView =
                    (ViewGroup) currentActivity.getWindow().getDecorView().getRootView();
                // The following calls edit the view hierarchy so they must run on the UI thread
                UiThreadUtil.runOnUiThread(() -> {
                    Creative creative = new Creative(config, rootView);
                    creative.trigger();
                });
            } else {
                Log.w(TAG, "Could not trigger the Attentive Creative because the current Activity was null");
            }
        } catch (Exception e) {
            Log.e(TAG, "Exception when triggering the creative: " + e);
        }
    }

    @ReactMethod
    public void clearUser() {
        config.clearUser();
    }

    @ReactMethod
    public void identify(ReadableMap identifiers) {
        UserIdentifiers.Builder idsBuilder = new UserIdentifiers.Builder();
        if (identifiers.hasKey("phone")) {
            idsBuilder.withPhone(identifiers.getString("phone"));
        }
        if (identifiers.hasKey("email")) {
            idsBuilder.withEmail(identifiers.getString("email"));
        }
        if (identifiers.hasKey("klaviyoId")) {
            idsBuilder.withKlaviyoId(identifiers.getString("klaviyoId"));
        }
        if (identifiers.hasKey("shopifyId")) {
            idsBuilder.withShopifyId(identifiers.getString("shopifyId"));
        }
        if (identifiers.hasKey("clientUserId")) {
            idsBuilder.withClientUserId(identifiers.getString("clientUserId"));
        }
        if (identifiers.hasKey("customIdentifiers")) {
            Map<String, String> customIds = new HashMap<>();
            Map<String, Object> rawCustomIds = identifiers.getMap("customIdentifiers").toHashMap();
            for (Map.Entry<String, Object> entry : rawCustomIds.entrySet()) {
                if (entry.getValue() instanceof String) {
                    customIds.put(entry.getKey(), (String) entry.getValue());
                }
            }
            idsBuilder.withCustomIdentifiers(customIds);
        }

        config.identify(idsBuilder.build());
    }
}