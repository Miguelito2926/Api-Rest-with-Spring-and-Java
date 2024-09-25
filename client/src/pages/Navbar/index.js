import React from 'react';
import { AppBar, Toolbar, Typography, Button, Box } from '@mui/material';
import { Link } from 'react-router-dom';

export default function Navbar() {
    return (
        <AppBar position="static" sx={{ backgroundColor: '#6366f1' }}> {/* Definindo a cor personalizada */}
            <Toolbar>
                {/* Título à esquerda */}
                <Typography variant="h6" component={Link} to="/" sx={{ color: 'white', textDecoration: 'none', flexGrow: 1 }}>
                    Meu Livro
                </Typography>

                {/* Botões à direita */}
                <Box>
                    <Button color="inherit" component={Link} to="/" sx={{ marginLeft: 2 }}>
                        Home
                    </Button>
                    <Button color="inherit" component={Link} to="/login" sx={{ marginLeft: 2 }}>
                        Login
                    </Button>
                    <Button color="inherit" component={Link} to="/books" sx={{ marginLeft: 2 }}>
                        Livros
                    </Button>
                    <Button color="inherit" component={Link} to="/contact" sx={{ marginLeft: 2 }}>
                        Contato
                    </Button>
                </Box>
            </Toolbar>
        </AppBar>
    );
}
