import React from 'react';
import { BrowserRouter, Switch, Route, NavLink } from 'react-router-dom';
import Products from "./Products";
import Empty from "./Empty";
import AddProduct from "./AddProduct";

class Home extends React.Component {

  render() {
    return (
      <div className='store-container'>
        <BrowserRouter>
          <nav className='nav-bar'>
            <NavLink exact to='/'>商城</NavLink>
            <NavLink to='/orders'>订单</NavLink>
            <NavLink to='/add-product'>添加商品</NavLink>
          </nav>
          <Switch>
            <Route exact path='/' component={Products} />
            <Route path='/orders' component={Empty} />
            <Route path='/add-product' component={AddProduct} />
          </Switch>
        </BrowserRouter>
      </div>
    );
  }
}

export default Home;
