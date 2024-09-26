// Home.js
import React from 'react';
import { Link } from 'react-router-dom';
import { Typography, Button } from '@mui/material';
import Navbar from '../Navbar'; // Ajuste o caminho conforme necessário
import './style.css'; // Importando o CSS da Home
import {FiPower, FiUser } from 'react-icons/fi';

export default function Home() {
    return (
        <div>
            <Navbar />
            <div className="home-container">
            <header className="header">
                <div className="profile-icon">
                    <FiUser size={100} color="#251FC5" />
                </div>
                <span>Bem-vindo, <strong>Ednaldo</strong>!</span>
                <button type="button" className="logout-button">
                    <FiPower size={40} color="#251FC5" /> <strong>Logout</strong>
                </button>
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
