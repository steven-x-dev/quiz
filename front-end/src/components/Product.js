import React from 'react';

class Product extends React.Component {

  render() {
    const { product } = this.props;
    return (
      <div className='product-wrapper'>
        <div className='img-wrapper'>
          <img
            src={product.url}
            alt='product'
            className='product-img'
          />
        </div>
        <h3>{product.name}</h3>
        <p>{`单价：${product.price}元/${product.unit}`}</p>
        <button>+</button>
      </div>
    );
  }

}

export default Product;
