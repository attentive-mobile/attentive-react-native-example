# attentive-react-native-example

The Attentive React Native example app is an example for how to use the Attentive Android and iOS SDKs with React Native. The example app is made up of two parts:
1. The React Native app itself. This app provides a UI to trigger the Attentive SDK's functionality, such as showing a Creative and triggering user events.
2. The "[bridging](https://reactnative.dev/docs/native-modules-intro)" code for iOS and Android. This code allows the React Native code to call the native Attentive SDKs.

## Getting Started

1. Update both instances of "YOUR_ATTENTIVE_DOMAIN" ([here](https://github.com/attentive-mobile/attentive-react-native-example/blob/main/ios/AttentiveSDKSingleton.m) and [here](https://github.com/attentive-mobile/attentive-react-native-example/blob/main/android/app/src/main/java/com/attentivereactexampleapp/MyAppPackage.java)) to your Attentive domain.
2. Run the app on either iOS or Android

## FAQ

### Does Attentive provide an installable React Native Library?

No. Host apps will need to use this example's bridging code in their app to use the Attentive SDKs.

### Which version of the Attentive SDKs is this example compatible with?

iOS: 0.3.2+

Android: 0.3.3+
