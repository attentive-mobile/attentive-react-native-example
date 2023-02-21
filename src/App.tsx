/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 *
 * @format
 */

import React, { useEffect } from 'react';
import { NavigationContainer, useFocusEffect } from '@react-navigation/native';
import { createNativeStackNavigator } from '@react-navigation/native-stack';
import { NativeModules } from 'react-native';
import { RootStackParamList } from './navTypes';
import HomeScreen from './HomeScreen'
import ProductScreen from './ProductScreen'

const Stack = createNativeStackNavigator<RootStackParamList>();

const { AttentiveCreativeModule } = NativeModules;

function App(): JSX.Element {
  useEffect(() => {
    // When the app starts up, send the user's identifiers to Attentive ASAP to improve the
    // functionality of the Attentive Creative
    const identifiers = {
      'phone': '+15556667777',
      'email': 'some_email@gmailfake.com',
      'klaviyoId': 'userKlaviyoId',
      'shopifyId': 'userShopifyId',
      'clientUserId': 'userClientUserId',
      'customIdentifiers': { 'customIdKey': 'customIdValue' }
    };
    AttentiveCreativeModule.identify(identifiers);
  }, [])

  return (
    <NavigationContainer>
      <Stack.Navigator>
        <Stack.Screen
          name="Home"
          component={HomeScreen}
          options={{ title: 'Home' }}
        />
        <Stack.Screen
          name="Product"
          component={ProductScreen}
          options={{ title: 'Product Screen' }}
        />
      </Stack.Navigator>
    </NavigationContainer>);
}

export default App;
