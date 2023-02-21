import React, { useEffect } from 'react';
import {
    Alert,
    Button,
    Text,
    View,
    NativeModules,
} from 'react-native';

import { HomeScreenProps } from './navTypes';

const { AttentiveCreativeModule } = NativeModules;

const HomeScreen = ({ navigation }: HomeScreenProps) => {

    const showCreative = () => {
        AttentiveCreativeModule.triggerCreative();
    };

    const showProductPage = () => {
        navigation.navigate('Product');
    }

    const clearUser = () => {
        AttentiveCreativeModule.clearUser();
    }

    return (
        <View style={{ flex: 1 }}>
            <Text>This is React Native!</Text>
            <Button
                title="Show creative!"
                color="#841584"
                onPress={showCreative}
            />
            <Button
                title="View Product Page"
                onPress={showProductPage}
            />
            <Button
                title="Clear User"
                onPress={clearUser}
            />
        </View>
    )
}

export default HomeScreen;
