
import React, { Component } from 'react';

import { Link } from 'react-router-dom';
import axios from 'axios';


class App extends Component {
  constructor(props) {
    super(props);
    this.state = {
      categories: []
    };
  }

  componentDidMount() {
    axios.get('http://localhost:8080/api/categories')
      .then(res => {
        this.setState({ categories: res.data.content });
        console.log(Object.values(this.state.categories));
        
      });
  }
  
  render(){
  return (
    <div className="container">
    <div className="panel panel-default">
      <div className="panel-heading">
        <h3 className="panel-title">
          CATEGORIE LIST
        </h3>
      </div>
      <div className="panel-body">
      <h4><Link to="/create"><span className="glyphicon glyphicon-plus-sign" aria-hidden="true"></span> Add Categorie</Link></h4>
      <table className="table table-stripe">
              <thead>
                <tr>
                  
                  <th>Name</th>
                </tr>
              </thead> 
              <tbody>
                {Object.values(this.state.categories).map(catr =>
                  <tr>
                    <td><Link to={`/show/${catr.id}`}>{catr.name}</Link></td>
                  </tr>
                )}
              </tbody>
              </table>
          </div>
        </div>
      </div>
  
  );
}
}
export default App;
