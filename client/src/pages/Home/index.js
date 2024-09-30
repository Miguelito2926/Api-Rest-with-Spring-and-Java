// Home.js
import React from 'react';
import { Link } from 'react-router-dom';
import { Typography, Button } from '@mui/material';
import Navbar from '../Navbar'; // Ajuste o caminho conforme necessário
import './style.css'; // Importando o CSS da Home
import {FiPower, FiUser } from 'react-icons/fi';
import useLogout from '../../utils/useLogout';

export default function Home() {   

    const logout = useLogout(); // Usa o hook personalizado para o logout
    const username = localStorage.getItem('username');  

    return (
        <div>
            <Navbar />
            <div className="home-container">
            <header className="header">
                <div className="profile-icon">
                    <FiUser size={100} color="#251FC5" />
                </div>
                <span>Bem-vindo, <strong>{username.toUpperCase()}</strong>!</span>                
            </header>
                <Typography variant="h2" component="h1" gutterBottom>
                    Bem-vindo ao Meu Livro
                </Typography>
                <Typography variant="h6" paragraph paddingTop={5}>
                    Aqui você pode gerenciar seus livros de forma simples e rápida.
                </Typography>
                <Button variant="contained" className="button" component={Link} to="/books">
                    Ver Livros
                </Button>
            </div>
        </div>
    );
}