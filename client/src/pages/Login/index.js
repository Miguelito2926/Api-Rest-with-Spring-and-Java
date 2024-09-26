import './styles.css'; // Certifique-se de que o CSS global está sendo importado aqui.
import React, { useEffect } from 'react';
import Navbar from '../Navbar/index';

export default function Login() {
    useEffect(() => {
        // Adicionar a classe de fundo ao body quando o componente é montado
        document.body.classList.add('login-background');

        // Remover a classe de fundo ao desmontar o componente
        return () => {
            document.body.classList.remove('login-background');
        };
    }, []);

    return (
        <div>
            <Navbar />           
            <div className="login-container" style={{ padding: '20px', marginTop: '64px' }}> {/* Adiciona margem para não ficar atrás da navbar */}
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
        </div>
    );
}
