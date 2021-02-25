import React, { Component } from 'react';

import AppNavbar from './AppNavbar';
import { Link } from 'react-router-dom';
import { Button, Container } from 'reactstrap';

class Home extends Component {
  render() {
    return (
      <div>
        <AppNavbar/>
        <Container >
          <Button ><Link to="/categorie">Manage Categorie List</Link></Button>
        </Container>
      </div>
    );
  }
}
export default Home;