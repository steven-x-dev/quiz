import React from 'react';
import Product from "./Product";

class Products extends React.Component {

  constructor(props) {
    super(props);
    this.state = {
      products: []
    }
  }

  componentDidMount() {
    fetch('http://localhost:8080/product/list', {
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
      .catch(err => {
        console.log(err);
      });
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