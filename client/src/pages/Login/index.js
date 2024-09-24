import './styles.css';
import React from 'react';

export default function Login() {
    return (      
        <div class="Login-container">
        <section class="form">
            <h1>Login</h1>
            <form>
                <div class="input-wrapper">
                    <input type="text" placeholder="Email" />
                    <input type="password" placeholder="Senha" />
                    <button class="button" type="submit">Entrar</button>
                </div>
            </form>
        </section>
    </div>         
    );
}