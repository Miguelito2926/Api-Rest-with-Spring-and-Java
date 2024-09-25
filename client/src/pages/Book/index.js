import React from 'react';
import './styles.css';
import { Link } from 'react-router-dom';
import {FiUser, FiMinus, FiPower, FiEdit, FiTrash2, FiPlus } from 'react-icons/fi';
import { Grid, Card, CardContent, Typography, IconButton, Button } from '@mui/material';
import Navbar from '../Navbar/index';


export default function Book() {
    return (
        <div className="book-container">
            <Navbar />
            <header className="header">
            <div className="profile-icon">
                    <FiUser size={100} color="#251FC5" /> {/* Ícone de perfil */}
                </div>
                <span>Bem-vindo, <strong>Ednaldo</strong>!</span>
                
                <button type="button" className="logout-button">
                    <FiPower size={18} color="#251FC5" /> <strong>Logout</strong>
                </button>
            </header>

            <h1>Registros de Livros</h1>

            <div className="crud-buttons">
                <Button variant="contained" color="success" component={Link} to="book/new" startIcon={<FiPlus />}>
                    Adicionar Livro
                </Button>
                <Button variant="contained" color="secondary" style={{ marginLeft: '10px' }} component={Link} to="book/remove"startIcon={<FiMinus />}>
                    Remover Livro
                </Button>                
            </div>

            <Grid container spacing={2}>
                {[...Array(4)].map((_, index) => (
                    <Grid item xs={12} sm={6} md={4} key={index}>
                        <Card className="book-card">
                            <CardContent className="book-card-content">
                                <Typography variant="h6" color="primary">
                                    Título: Docker
                                </Typography>
                                <Typography variant="body2" color="textSecondary" gutterBottom>
                                    Autor: Ed Teste
                                </Typography>
                                <Typography variant="body2" color="textSecondary" gutterBottom>
                                    Preço: R$ 50,00
                                </Typography>
                                <Typography variant="body2" color="textSecondary">
                                    Data de Lançamento: 12/07/2024
                                </Typography>

                                <div style={{ display: 'flex', justifyContent: 'flex-end', marginTop: '10px' }}>
                                    <IconButton aria-label="edit">
                                        <FiEdit size={20} color="#6366f1" />
                                    </IconButton>
                                    <IconButton aria-label="delete">
                                        <FiTrash2 size={20} color="#ff4d4d" />
                                    </IconButton>
                                </div>
                            </CardContent>
                        </Card>
                    </Grid>
                ))}
            </Grid>
        </div>
    );
}
