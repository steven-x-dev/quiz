import React from 'react';
import Product from './Product';
import { baseURL } from '../server';
import {notification} from "antd";
import Cart from "./Cart";

class Products extends React.Component {

  constructor(props) {
    super(props);
    this.state = {
      products: [],
      cart: [],
      cartVisible: false,
    }
  }

  componentDidMount() {
    fetch(`${baseURL}/products`, {
      method: 'GET'
    })
      .then(response => {
        if (response.status === 200) {
          return response.json();
        } else {
          notification.open({
            message: `系统错误：${response.status} ${response.statusText}`,
            className: 'notification error',
          });
        }
      })
      .then(fetchedProducts => {
        console.log(fetchedProducts);
        this.setState({ products: fetchedProducts });
      })
      .catch(err => err);
  }

  fetchCart = () => {
    fetch(`${baseURL}/cartItems`, {
      method: 'GET'
    })
      .then(response => {
        if (response.status === 200) {
          return response.json();
        } else {
          notification.open({
            message: `系统错误：${response.status} ${response.statusText}`,
            className: 'notification error',
          });
        }
      })
      .then(fetchedCart => {
        console.log(fetchedCart);
        this.setState({ cart: fetchedCart });
      })
      .catch(err => err);
    this.setState({
      cartVisible: true
    });
  }

  render() {
    const { products, cart, cartVisible } = this.state;
    return (
      <div className='products-container'>
        {products.map(product => <Product key={product.id} product={product} />)}
        <button className='cart-button' onClick={this.fetchCart}>购物车</button>
        <div style={{display: cartVisible ? 'block' : 'none'}}>
          <Cart cart={cart} />
        </div>
      </div>
    )
  }
}

export default Products;
