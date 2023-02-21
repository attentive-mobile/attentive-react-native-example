package com.attentivereactexampleapp;

import android.util.Log;
import androidx.annotation.NonNull;
import com.attentive.androidsdk.AttentiveConfig;
import com.attentive.androidsdk.AttentiveEventTracker;
import com.attentive.androidsdk.events.AddToCartEvent;
import com.attentive.androidsdk.events.Item;
import com.attentive.androidsdk.events.Order;
import com.attentive.androidsdk.events.Price;
import com.attentive.androidsdk.events.ProductViewEvent;
import com.attentive.androidsdk.events.PurchaseEvent;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;

public class AttentiveEventTrackingModule extends ReactContextBaseJavaModule {
    private static final String TAG = "AttentiveEventModule";

    public AttentiveEventTrackingModule(AttentiveConfig attentiveConfig) {
        // This should only ever be initialized once
        AttentiveEventTracker.getInstance().initialize(attentiveConfig);
    }

    @NonNull
    @Override
    public String getName() {
        return "AttentiveEventTrackingModule";
    }

    @ReactMethod
    public void productViewed(ReadableMap productViewAttrs) {
        Log.i(TAG, "Sending product viewed event");

        List<Item> items = buildItems(productViewAttrs.getArray("items"));
        ProductViewEvent productViewEvent = new ProductViewEvent.Builder(items).build();

        AttentiveEventTracker.getInstance().recordEvent(productViewEvent);
    }

    @ReactMethod
    public void purchased(ReadableMap purchaseAttrs) {
        Log.i(TAG, "Sending purchase event");
        Order order = new Order.Builder(purchaseAttrs.getMap("order").getString("id")).build();

        List<Item> items = buildItems(purchaseAttrs.getArray("items"));
        PurchaseEvent purchaseEvent = new PurchaseEvent.Builder(items, order).build();

        AttentiveEventTracker.getInstance().recordEvent(purchaseEvent);
    }

    @ReactMethod
    public void addedToCart(ReadableMap addToCartAttrs) {
        Log.i(TAG, "Sending add to cart event");

        List<Item> items = buildItems(addToCartAttrs.getArray("items"));
        AddToCartEvent addToCartEvent = new AddToCartEvent.Builder(items).build();

        AttentiveEventTracker.getInstance().recordEvent(addToCartEvent);
    }

    private List<Item> buildItems(ReadableArray rawItems) {
        List<Item> items = new ArrayList<>();
        for (int i = 0; i < rawItems.size(); i++) {
            ReadableMap rawItem = rawItems.getMap(i);

            ReadableMap priceMap = rawItem.getMap("price");
            Price price = new Price.Builder(new BigDecimal(priceMap.getString("price")), Currency.getInstance(priceMap.getString("currency"))).build();

            Item item = new Item.Builder(rawItem.getString("productId"), rawItem.getString("productVariantId"), price).build();
            items.add(item);
        }

        return items;
    }
}
