import './styles.css'; // Certifique-se de que o CSS global está sendo importado aqui.
import React, { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom'; 
import Navbar from '../Navbar/index';
import api from '../../services/api';

export default function Login() {

    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const navigate = useNavigate();

    async function login(event) {
        event.preventDefault();

        const data = {
            username,
            password,
        };

        try {
            const response = await api.post('auth/signin', data);
            
            // Corrigindo o nome da chave do token
            localStorage.setItem('username', username);
            localStorage.setItem('accessToken', response.data.token);

            // Navegar para a rota 'books'
            navigate('/home');
            
        } catch (error) {
            alert('Login ou Senha inválida!');
        }
    };

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
            <div className="login-container" style={{ padding: '20px', marginTop: '64px' }}>
                <section className="form">
                    <h1>Login</h1>
                    <form onSubmit={login}>
                        <div className="input-wrapper">
                            <input 
                                type="text" 
                                placeholder="Email" 
                                value={username} 
                                onChange={event => setUsername(event.target.value)}
                            />
                            <input 
                                type="password" 
                                placeholder="Senha" 
                                value={password} 
                                onChange={event => setPassword(event.target.value)} 
                            />
                            <button className="button" type="submit">Entrar</button>
                        </div>
                    </form>
                </section>
            </div>
        </div>
    );
}
