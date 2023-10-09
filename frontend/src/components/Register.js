import React, { useState } from 'react';
import Button from 'react-bootstrap/Button';
import Form from 'react-bootstrap/Form';
import Container from 'react-bootstrap/Container';
import Mod from './Mod';
import {
    Link,
    useNavigate
  } from 'react-router-dom';

import 'bootstrap/dist/css/bootstrap.min.css';

function Register() {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [password1, setPassword1] = useState('');
  const [showModal, setShowModal] = useState(false);
  const [modalContent, setModalContent] = useState('');

  function submit(event) {
    if (password !== password1) {
      setShowModal(true);
      setModalContent('Password have to match');
      return;
    }
  }

  return (
    <>
      <Container className="d-flex justify-content-center align-items-center" style={{ minHeight: '100vh' }}>
        <Form style={{ width: '60%' }} onSubmit={submit}>
          <h1 className='text-center mb-5'>Sharing Smile</h1>
          <Form.Group className="mb-3">
            <Form.Label>Email</Form.Label>
            <Form.Control type="email" placeholder="Enter email" onChange={(e) => setEmail(e.target.value)} />
          </Form.Group>

          <Form.Group className="mb-3">
            <Form.Label>Password</Form.Label>
            <Form.Control type="password" placeholder="Password" onChange={(e) => setPassword(e.target.value)} />
          </Form.Group>

          <Form.Group className="mb-3">
            <Form.Label>Repeat password</Form.Label>
            <Form.Control type="password" placeholder="Repeated password" onChange={(e) => setPassword1(e.target.value)} />
          </Form.Group>
          <Link to="/login">Already have an account? Login!</Link>
          <br />
          <Button variant="primary" type="submit" className="mt-3" disabled={!email || !password || !password1} onClick={submit}>
            Register
          </Button>
        </Form>
      </Container>
      <Mod open={showModal} close={() => setShowModal(false)} title='Error'>
        {modalContent}
      </Mod>
    </>
  );
}

export default Register;
