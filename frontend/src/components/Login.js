import React from 'react';
import Button from 'react-bootstrap/Button';
import Form from 'react-bootstrap/Form';
import Container from 'react-bootstrap/Container';
import {
    Link,
    useNavigate
  } from 'react-router-dom';
import 'bootstrap/dist/css/bootstrap.min.css';


function Login() {
    const [email, setEmail] = React.useState('');
    const [password, setPassword] = React.useState('');
    async function submit () {
        console.log(email);
        console.log(password);
    }

    return (
        <Container className="d-flex justify-content-center align-items-center" style={{ minHeight: '100vh' }}>
            <Form style={{ width: '60%' }}>
                <h1 className='text-center mb-5'>Sharing Smile</h1>
                <Form.Group className="mb-3">
                    <Form.Label>Email</Form.Label>
                    <Form.Control type="email" placeholder="Enter email"  onChange={(e) => setPassword(e.target.value)}/>
                </Form.Group>

                <Form.Group className="mb-3">
                    <Form.Label>Password</Form.Label>
                    <Form.Control type="password" placeholder="Password" />
                </Form.Group>
                <Link to="/register">Dont have an account? Register now!</Link>
                <br/>
                <Button variant="primary" type="submit" className="mt-3" onClick={submit}>
                    Login
                </Button>
            </Form>
        </Container>
    );
}

export default Login;