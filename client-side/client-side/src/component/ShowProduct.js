import React, { Component } from 'react';
import axios from 'axios';
import { Link } from 'react-router-dom';

class ShowProduct extends Component {

  constructor(props) {
    super(props);
    this.state = {
      categories: {},
      products: {}
      };
  }

  componentDidMount() {
    axios.get('http://localhost:8080/api/categories/'+this.props.match.params.id)
      .then(res => {
        this.setState({ categories: res.data });
        console.log(this.state.categories);
        console.log(this.props.match)
      });
      axios.get('http://localhost:8080/api/categories/'+this.props.match.params.id+'/products/'+this.props.match.params.iid)
      .then(res => {
        this.setState({ products: res.data });
        console.log(this.state.products);
      });
  }

  delete(id,iid){
    console.log(id,iid);
    axios.delete('http://localhost:8080/api/categories/'+this.props.match.params.id+'/products/'+this.props.match.params.iid)
      .then((result) => {
        this.props.history.push("/show/"+this.props.match.params.id+"/products/")
      });
  }

  render() {
    return (
      <div class="container">
        <div class="panel panel-default">
          <div class="panel-heading">
            <h3 class="panel-title">
            Product Details
            </h3>
          </div>
          <div class="panel-body">
            <h4><Link to={`/show/${this.state.categories.id}`}><span class="glyphicon glyphicon-th-list" aria-hidden="true"></span> Product List</Link></h4>
            <dl>
              <dt>Name:</dt>
              <dd>{this.state.products.name}</dd>
              <dt>Price:</dt>
             <dd>{this.state.products.price}</dd>
             <dt>Currency:</dt>
             <dd>EUR</dd>
            </dl>
            <Link to={`/editProduct/${this.state.categories.id}/products/${this.state.products.id}`} class="btn btn-success">Edit</Link>&nbsp;
            <button onClick={this.delete.bind(this, this.state.products.id)} class="btn btn-danger">Delete</button>
          </div>
        </div>
      </div>
    );
  }
}

export default ShowProduct;