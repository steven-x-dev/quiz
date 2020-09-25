import React from 'react';
import { BrowserRouter, Switch, Route, NavLink } from 'react-router-dom';
import Products from "./Products";

class Home extends React.Component {

  render() {
    return (
      <div className='store-container'>
        <BrowserRouter>
          <nav className='nav-bar'>
            <div className='navlink-wrapper'>
              <NavLink to='/'>商城</NavLink>
              <NavLink to='/orders'>订单</NavLink>
              <NavLink to='/add-product'>添加商品</NavLink>
            </div>
          </nav>
          <Switch>
            <Route exact path='/' component={Products} />
            {/*<Route path='/my-profile' component={MyProfile} />*/}
            {/*<Route path='/about-us' component={AboutUs} />*/}
          </Switch>
        </BrowserRouter>
      </div>
    );
  }
}

export default Home;
