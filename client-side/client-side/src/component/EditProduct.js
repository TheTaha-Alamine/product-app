import React, { Component } from 'react';
import axios from 'axios';
import { Link } from 'react-router-dom';

class EditProduct extends Component {

  constructor(props) {
    super(props);
    this.state = {
        categories : {},
        products: { name: "",
                    price: 0.0,
                    currency:""
                  },
        rates:{}
    };
  }

  componentDidMount() {
    axios.get('http://localhost:8080/api/categories/'+this.props.match.params.id)
    .then(res => {
      this.setState({ categories: res.data });
      console.log(this.state.categories);
    
    });
    axios.get('http://localhost:8080/api/categories/'+this.props.match.params.id+'/products/'+this.props.match.params.iid)
      .then(res => {
        this.setState({ products: res.data});
        console.log(this.state.products);
      });

      axios.get('http://data.fixer.io/api/latest?access_key=d146d38e556164ac9893c2196907fe2c')
      .then(res => {
        this.setState({ rates: res.data.rates});
        console.log(this.state.rates);
      });

  }

  onChange = (e) => {
    const state = this.state.products
    state[e.target.name] = e.target.value;
    state[e.target.price] = e.target.value;
    state[e.target.currency] = e.target.value;
    this.setState(state);
  }

  onSubmit = (e) => {
    e.preventDefault();
    const { name,price,currency} = this.state.products;
    axios.put('http://localhost:8080/api/categories/'+this.props.match.params.id+'/products/'+this.props.match.params.iid, { name,price,currency})
      .then((result) => {
        this.props.history.push("/show/"+this.props.match.params.id)
      });
  }

  render() {
    const { name,price,currency} = this.state.products;
    return (
      <div class="container">
        <div class="panel panel-default">
          <div class="panel-heading">
            <h3 class="panel-title">
              EDIT Products
            </h3>
          </div>
          <div class="panel-body">
            <h4><Link to={`/show/${this.state.categories.id}`}><span class="glyphicon glyphicon-eye-open" aria-hidden="true"></span> Products List</Link></h4>
            <form onSubmit={this.onSubmit}>
              <div class="form-group">
                <label for="name">Name:</label>
                <input type="text" class="form-control" name="name" value={name} onChange={this.onChange} placeholder="Name" />
                <label for="price">Price:</label>
                <input type="text" class="form-control" name="price" value={price} onChange={this.onChange} placeholder="Price" />
                <label for="price">Currency:</label>
                <input type="text" class="form-control" name="currency" value={currency} onChange={this.onChange} placeholder="Currency" />
              </div>
              <button type="submit" class="btn btn-default">Update</button>
            </form>
          </div>
        </div>
      </div>
    );
  }
}

export default EditProduct;