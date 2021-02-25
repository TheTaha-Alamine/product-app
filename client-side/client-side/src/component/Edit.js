import React, { Component } from 'react';
import axios from 'axios';
import { Link } from 'react-router-dom';

class Edit extends Component {

  constructor(props) {
    super(props);
    this.state = {
      categories: {}
    };
  }

  componentDidMount() {
    axios.get('http://localhost:8080/api/categories/'+this.props.match.params.id)
      .then(res => {
        this.setState({ categories: res.data });
        console.log(this.state.categories);
      });
  }

  onChange = (e) => {
    const state = this.state.categories
    state[e.target.name] = e.target.value;
    this.setState({categories:state});
  }

  onSubmit = (e) => {
    e.preventDefault();

    const { name} = this.state.categories;

    axios.put('http://localhost:8080/api/categories/'+this.props.match.params.id, { name})
      .then((result) => {
        this.props.history.push("/show/"+this.props.match.params.id)
      });
  }

  render() {
    return (
      <div class="container">
        <div class="panel panel-default">
          <div class="panel-heading">
            <h3 class="panel-title">
              EDIT Categories
            </h3>
          </div>
          <div class="panel-body">
            <h4><Link to={`/show/${this.state.categories.id}`}><span class="glyphicon glyphicon-eye-open" aria-hidden="true"></span> categories List</Link></h4>
            <form onSubmit={this.onSubmit}>
              <div class="form-group">
                <label for="name">Name:</label>
                <input type="text" class="form-control" name="name" value={this.state.categories.name} onChange={this.onChange} placeholder="Name" />
              </div>
              <button type="submit" class="btn btn-default">Update</button>
            </form>
          </div>
        </div>
      </div>
    );
  }
}

export default Edit;