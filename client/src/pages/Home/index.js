// Home.js
import React from 'react';
import { Link } from 'react-router-dom';
import { Typography, Button } from '@mui/material';
import Navbar from '../Navbar'; // Ajuste o caminho conforme necessário
import './style.css'; // Importando o CSS da Home

export default function Home() {
    return (
        <div>
            <Navbar />
            <div className="home-container">
                <Typography variant="h2" component="h1" gutterBottom>
                    Bem-vindo ao Meu Livro
                </Typography>
                <Typography variant="h6" paragraph>
                    Aqui você pode gerenciar seus livros de forma simples e rápida.
                </Typography>
                <Button variant="contained" className="button" component={Link} to="/book">
                    Ver Livros
                </Button>
            </div>
        </div>
    );
}
