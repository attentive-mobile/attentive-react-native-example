//
//  RCTAttentiveEventTrackingModule.h
//  AttentiveReactExampleApp
//
//  Created by Wyatt Davis on 2/20/23.
//

#import "RCTAttentiveEventTrackingModule.h"
#import <React/RCTLog.h>
#import "attentive-sdk-umbrella.h"
#import <UIKit/UIKit.h>
#import "AttentiveSDKSingleton.h"

@implementation RCTAttentiveEventTrackingModule

// To export a module named ATTNSDKModule
RCT_EXPORT_MODULE();

- (id)init {
  if (self = [super init]) {
    static dispatch_once_t onceToken;
    dispatch_once(&onceToken, ^{
      [ATTNEventTracker setupWithSdk:[AttentiveSDKSingleton sharedSDK]];
    });
  }
  
  return self;
}

// This is required since we implement init
+ (BOOL)requiresMainQueueSetup
{
  return NO;
}

RCT_EXPORT_METHOD(addedToCart:(NSDictionary*)attrs) {
  NSArray* items = [self parseItems:attrs[@"items"]];
  ATTNAddToCartEvent* event = [[ATTNAddToCartEvent alloc] initWithItems:items];
  [[ATTNEventTracker sharedInstance] recordEvent:event];
}

RCT_EXPORT_METHOD(productViewed:(NSDictionary*)attrs) {
  NSArray* items = [self parseItems:attrs[@"items"]];
  ATTNProductViewEvent* event = [[ATTNProductViewEvent alloc] initWithItems:items];
  [[ATTNEventTracker sharedInstance] recordEvent:event];
}

RCT_EXPORT_METHOD(purchased:(NSDictionary*)attrs) {
  NSArray* items = [self parseItems:attrs[@"items"]];
  ATTNOrder* order = [[ATTNOrder alloc] initWithOrderId:attrs[@"order"][@"id"]];
  ATTNPurchaseEvent* event = [[ATTNPurchaseEvent alloc] initWithItems:items order:order];
  [[ATTNEventTracker sharedInstance] recordEvent:event];
}

- (NSArray*)parseItems:(NSArray*)rawItems {
  NSMutableArray* itemsToReturn = [[NSMutableArray alloc] init];
  for (NSDictionary* rawItem in rawItems) {
    NSDictionary* rawPrice = rawItem[@"price"];
    ATTNPrice* price = [[ATTNPrice alloc] initWithPrice:[[NSDecimalNumber alloc] initWithString:rawPrice[@"price"]] currency:rawPrice[@"currency"]];
    
    ATTNItem* item = [[ATTNItem alloc] initWithProductId:rawItem[@"productId"] productVariantId:rawItem[@"productVariantId"] price:price];
    
    [itemsToReturn addObject:item];
  }
  return itemsToReturn;
}

@end

