import './styles.css';
import React from 'react';

export default function Login() {
    return (
        <div className="Login-container" style={{
            backgroundImage: 'url(https://images.unsplash.com/photo-1517245386807-bb43f82c33c4?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=MnwxMTc3M3wwfDF8c2VhcmNofDR8fGxhcHRvcHxlbnwwfHx8fDE2MzI4MTg3NjA&ixlib=rb-1.2.1&q=80&w=1080)',
            backgroundSize: 'cover',
            backgroundPosition: 'center',
            backgroundRepeat: 'no-repeat',
            height: '100vh',
            display: 'flex',
            justifyContent: 'center',
            alignItems: 'center'
        }}>
            <section className="form">
                <h1>Login</h1>
                <form>
                    <div className="input-wrapper">
                        <input type="text" placeholder="Email" />
                        <input type="password" placeholder="Senha" />
                        <button className="button" type="submit">Entrar</button>
                    </div>
                </form>
            </section>
        </div>
    );
}
