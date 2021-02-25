import React from 'react';
import ReactDOM from 'react-dom';
import { BrowserRouter as Router, Route } from 'react-router-dom';
import '../node_modules/bootstrap/dist/css/bootstrap.min.css';
import App from './App';
import './App.css';
import Edit from './component/Edit';
import Create from './component/Create';
import Show from './component/Show';

import CreateProduct from './component/CreateProduct';
import ShowProduct from './component/ShowProduct';
import EditProduct from './component/EditProduct';

ReactDOM.render(
  <Router>
    <div>
    <Route exact path='/' component={App} />
    <Route path='/newProduct/:id/products' component={CreateProduct} />
        <Route path='/create' component={Create} />
        <Route path='/edit/:id' component={Edit} />
        <Route path='/show/:id' component={Show} />
        <Route path='/editProduct/:id/products/:iid' component={EditProduct} />
        <Route path='/showProduct/:id/products/:iid' component={ShowProduct} /> </div>
  </Router>,
  document.getElementById('root')
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals

