import React, { Component } from 'react';
import axios from 'axios';
import { Link } from 'react-router-dom';
import '../App.css';
class Show extends Component {

  constructor(props) {
    super(props);
    this.state = {
      categories: {},
      products: []
      };
  }

  componentDidMount() {
    axios.get('http://localhost:8080/api/categories/'+this.props.match.params.id)
      .then(res => {
        this.setState({ categories: res.data });
        console.log(this.state.categories);
      });
      axios.get('http://localhost:8080/api/categories/'+this.props.match.params.id+'/products')
      .then(res => {
        this.setState({ products: res.data.content });
        console.log(this.state.products);
        console.log(this.props)
      });
  }

  delete(id){
    console.log(id);
    axios.delete('http://localhost:8080/api/categories/'+id)
      .then((result) => {
        this.props.history.push("/")
      });
  }

  render() {
    return (
      <div class="container">
        <div class="panel panel-default">
          <div class="panel-heading">
            <h2 class="panel-title">
            {this.state.categories.name}
            
            </h2>
            <br>
            </br>
            <Link to={`/edit/${this.state.categories.id}`} class="btn btn-success">Edit Categorie name</Link>&nbsp;
            <button onClick={this.delete.bind(this, this.state.categories.id)} class="btn btn-danger">Delete Categorie</button>&nbsp;
            <Link to={`/`} class="btn btn-success">Return to Categories List</Link>
          </div>
          <div class="panel-body">
            <dl>
            <div class="center">
              <dt><h3>Products</h3></dt>
              </div>
              <table className="table table-stripe" align="center">
              <thead>
                <tr>
                  <th>Name</th>
                  <th>Price</th>
                  <th>Currency</th>
                </tr>
              </thead> 
              <tbody>
                {Array.from(this.state.products).map(catr =>
                  <tr>
                    <td><Link to={`/showProduct/${this.state.categories.id}/products/${catr.id}`}>{catr.name}</Link></td>
                    <td>{catr.price}</td>
                    <td>EUR</td>
                  </tr>
                )}
              </tbody>
              </table>
              <div class = "center">
              <Link to={`/newProduct/${this.state.categories.id}/products`} class="btn btn-info"><span className="glyphicon glyphicon-plus-sign" aria-hidden="true"></span> Add Product</Link>
              </div>
            </dl>
            
          </div>
        </div>
      </div>
    );
  }
}

export default Show;