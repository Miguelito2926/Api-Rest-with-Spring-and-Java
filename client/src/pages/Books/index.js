import { Button, Card, CardContent, Grid, IconButton, Typography } from '@mui/material';
import React from 'react';
import { FiEdit, FiTrash2 } from 'react-icons/fi';
import { Link } from 'react-router-dom';
import Navbar from '../Navbar/index';
import './styles.css';

export default function Book() {
    return (
        <div className="book-container">
            <Navbar />          

            <h1>Registros de Livros</h1>

            <div className="crud-buttons">
                <Button variant="contained" color="success" style={{ marginLeft: '10px' }} component={Link} to="/new">
                    Adicionar Livro
                </Button>
                <Button variant="contained" color="secondary" style={{ marginLeft: '10px' }} component={Link} to="book/remove">
                    Remover Livro
                </Button>                
            </div>

            <Grid container spacing={3}>
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
                                        <FiEdit size={30} color="#6366f1" />
                                    </IconButton>
                                    <IconButton aria-label="delete">
                                        <FiTrash2 size={30} color="#ff4d4d" />
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
