// ATTNSDKModule.m
#import "RCTAttentiveCreativeModule.h"
#import <React/RCTLog.h>
#import "attentive-sdk-umbrella.h"
#import "AttentiveSDKSingleton.h"
#import <UIKit/UIKit.h>

@implementation RCTAttentiveCreativeModule

// To export a module named ATTNSDKModule
RCT_EXPORT_MODULE();

RCT_EXPORT_METHOD(triggerCreative) {
  dispatch_async(dispatch_get_main_queue(), ^{
    UIWindow *window = [[UIApplication sharedApplication] keyWindow];
    UIView *topView = window.rootViewController.view;
    [[AttentiveSDKSingleton sharedSDK] trigger:topView];
  });
}

RCT_EXPORT_METHOD(identify:(NSDictionary*)identifiers) {
  // The dictionary already has the correct keys from the React code, so no translating necessary
  [[AttentiveSDKSingleton sharedSDK] identify:identifiers];
}

RCT_EXPORT_METHOD(clearUser) {
  [[AttentiveSDKSingleton sharedSDK] clearUser];
}

@end
