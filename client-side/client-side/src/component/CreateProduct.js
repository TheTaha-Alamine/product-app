import React, { Component } from 'react';
import ReactDOM from 'react-dom';
import axios from 'axios';
import { Link } from 'react-router-dom';

class CreateProduct extends Component {

  constructor() {
    super();
    this.state = {
      name: '',
      price: 0,
      currency:""
    };
  }
  componentDidMount() {
    axios.get('http://localhost:8080/api/categories/'+this.props.match.params.id)
      .then(res => {
        this.setState({ categories: res.data });
        console.log(this.state);

      });
  }
  onChange = (e) => {
    const state = this.state
    state[e.target.name] = e.target.value;
    state[e.target.price] = e.target.value;
    state[e.target.currency] = e.target.value;
    this.setState(state);
  }

  onSubmit = (e) => {
    e.preventDefault();

    const { name,price,currency } = this.state;

    axios.post('http://localhost:8080/api/categories/'+this.props.match.params.id+'/products', { name,price,currency })
      .then((result) => {
        this.props.history.push("/show/"+this.state.categories.id)
      });
  }

  render() {
    const { name,price,currency} = this.state;
    return (
      <div class="container">
        <div class="panel panel-default">
          <div class="panel-heading">
            <h3 class="panel-title">
              ADD PRODUCT
            </h3>
          </div>
          <div class="panel-body">
            <h4><Link to={`/show/${this.props.match.params.id}`}><span class="glyphicon glyphicon-th-list" aria-hidden="true"></span> Product List</Link></h4>
            <form onSubmit={this.onSubmit}>
              <div class="form-group">
                <label htmlFor="isbn">Name:</label>
                <input type="text" class="form-control" name="name" value={this.state.name} onChange={this.onChange} placeholder="Name" />
              </div>
              <div>
                <label htmlFor="isbn">Price:</label>
                <input type="text" class="form-control" name="price" value={price} onChange={this.onChange} placeholder="Price" />
              
              </div>
              <div>
                <label htmlFor="isbn">currency:</label>
                <input type="text" class="form-control" name="currency" value={currency} onChange={this.onChange} placeholder="currency" />
              
              </div>
              
              <button type="submit" class="btn btn-default" >Submit</button>
            </form>
          </div>
        </div>
      </div>
    );
  }
}

export default CreateProduct;