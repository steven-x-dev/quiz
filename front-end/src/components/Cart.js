import React from "react";
import { Table } from 'antd';
import _ from 'lodash';
import './cart.css';

class Cart extends React.Component {

  constructor(props) {
    super(props);
    this.state = {

    }
  }

  columns = [
    {
      title: '商品',
      dataIndex: 'product',
      key: 'name',
      render: product => <div>{product.name}</div>
    },
    {
      title: '数量',
      dataIndex: 'quantity',
      key: 'quantity',
      render: quantity => (
        <div>
          <button>-</button>{quantity}<button>+</button>
        </div>
      )
    },
    {
      title: '',
      key: 'remove',
      render: () => <button>删除</button>
    },
  ];

  render() {
    const { cart } = this.props;
    return (
      <div className='cart'>
        <Table
          columns={this.columns}
          dataSource={_.map(cart, cartItem => _.extend({key: cartItem.id}, cartItem))}
          pagination={false}
        />
        <div className='buttons-area'>
          <button>立即下单</button>
          <button>清空</button>
        </div>
      </div>
    );
  }

}

export default Cart;
