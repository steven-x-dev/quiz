import React from 'react';
import Product from './Product';
import { baseURL } from '../server';

class Products extends React.Component {

  constructor(props) {
    super(props);
    this.state = {
      products: []
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
          return Promise.reject(`${response.status} ${response.statusText}`);
        }
      })
      .then(fetchedProducts => {
        console.log(fetchedProducts);
        this.setState({ products: fetchedProducts });
      })
      .catch(err => err);
  }

  render() {
    const { products } = this.state;
    return (
      <div className='products-container'>
        {products.map(product => <Product key={product.id} product={product} />)}
      </div>
    )
  }
}

export default Products;