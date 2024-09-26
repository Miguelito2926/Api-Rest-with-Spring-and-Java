import { AppBar, Box, Button, Toolbar, Typography } from '@mui/material';
import React from 'react';
import { Link } from 'react-router-dom';

export default function Navbar() {
    return (
        <AppBar position="fixed" sx={{ backgroundColor: '#6366f1', width: '100%', height:'8%' }}> {/* Ajuste na largura */}
            <Toolbar>
                {/* Título à esquerda */}
                <Typography variant="h6" component={Link} to="/" sx={{ color: 'white', textDecoration: 'none', flexGrow: 2, marginBottom:4 }}>
                    Meu Livro
                </Typography>

                {/* Botões à direita */}
                <Box>
                    <Button color="inherit" component={Link} to="/" sx={{ marginLeft: 4, marginBottom:4 }}>
                        Home
                    </Button>
                    <Button color="inherit" component={Link} to="/login" sx={{ marginLeft: 4, marginBottom:4 }}>
                        Login
                    </Button>
                    <Button color="inherit" component={Link} to="/books" sx={{ marginLeft: 4, marginBottom:4 }}>
                        Livros
                    </Button>
                    <Button color="inherit" component={Link} to="/contact" sx={{ marginLeft: 4, marginBottom:4 }}>
                        Contato
                    </Button>
                </Box>
            </Toolbar>
        </AppBar>
    );
}
