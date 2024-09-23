import './styles.css';
import padlock from '../../assets/padlock.png';
import React from 'react';

export default function Login() {
    return (      
        <div className="login-container">
<section className="form"></section>
<img src={padlock} alt="Login"/>
<form>
    <h1>Acesse sua conta!</h1>
    <input placeholder="Nome de Usuário" />
    <input type="password" placeholder='Password' />

    <button type="submit">Login</button>
</form>



        </div>       
    );
}