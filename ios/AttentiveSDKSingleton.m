//
//  AttentiveSDKSingleton.m
//  AttentiveReactExampleApp
//
//  Created by Wyatt Davis on 2/20/23.
//

#import <Foundation/Foundation.h>
#import "AttentiveSDKSingleton.h"
#import "attentive-sdk-umbrella.h"

@implementation AttentiveSDKSingleton

static ATTNSDK* _sdk;

+ (id)sharedSDK {
    static dispatch_once_t onceToken;
    dispatch_once(&onceToken, ^{
        _sdk = [[ATTNSDK alloc] initWithDomain:@"YOUR_ATTENTIVE_DOMAIN" mode:@"production"];
    });
    return _sdk;
}

@end

