import React, { useEffect } from 'react';
import {
  Alert,
  Button,
  Text,
  View,
  NativeModules,
  Image,
} from 'react-native';
import { ProductScreenProps } from './navTypes';

const { AttentiveEventTrackingModule } = NativeModules;

const ProductScreen = ({ navigation }: ProductScreenProps) => {
  const getItems = () => {
    return {
      'items': [{
        'productId': '555',
        'productVariantId': '777',
        'price': {
          'price': '14.99',
          'currency': 'USD'
        }
      }]
    }
  }

  useEffect(() => {
    const productViewAttrs = {
      ...getItems()
    }
    AttentiveEventTrackingModule.productViewed(productViewAttrs);

    Alert.alert('Product View event recorded');
  }, [])

  const addToCart = () => {
    const addToCartAttrs = {
      ...getItems()
    }
    AttentiveEventTrackingModule.addedToCart(addToCartAttrs);

    Alert.alert('Add to Cart event recorded');
  }

  const purchase = () => {
    const purchaseAttrs = {
      ...getItems(),
      'order': {
        'id': '8989'
      }
    }
    AttentiveEventTrackingModule.purchased(purchaseAttrs);

    Alert.alert('Purchase event recorded');
  }

  return (
    <View style={{ flex: 1 }}>
      <Image source={require('../assets/images/tshirt.png')} />
      <Text>T-Shirt</Text>
      <Button
        title="Add to Cart"
        color="#841584"
        onPress={addToCart}
      />
      <Button
        title="Purchase"
        onPress={purchase}
      />
    </View>
  )
}

export default ProductScreen;