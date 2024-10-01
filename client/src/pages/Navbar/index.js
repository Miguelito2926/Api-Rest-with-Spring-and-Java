import { AppBar, Box, Button, Toolbar, Typography } from '@mui/material';
import React from 'react';
import { Link, useNavigate } from 'react-router-dom';

export default function Navbar() {
  const navigate = useNavigate();
  const accessToken = localStorage.getItem('accessToken');

  const handleLogout = () => {
    localStorage.removeItem('accessToken');
    navigate('/');
  };

  return (
    <AppBar position="fixed" sx={{ backgroundColor: '#6366f1', width: '100%', height: '8%' }}>
      <Toolbar>
        {/* Título à esquerda */}
        <Typography
          variant="h6"
          component={Link}
          to="/"
          sx={{ color: 'white', textDecoration: 'none', flexGrow: 2, marginBottom: 4 }}
        >
          Meu Livro
        </Typography>

        {/* Botões à direita */}
        <Box>
          {!accessToken ? (
            <Button color="inherit" component={Link} to="/" sx={{ marginLeft: 4, marginBottom: 4 }}>
              Login
            </Button>
          ) : (
            <>
              <Button color="inherit" component={Link} to="/home" sx={{ marginLeft: 4, marginBottom: 4 }}>
                Home
              </Button>
              <Button color="inherit" component={Link} to="/books" sx={{ marginLeft: 4, marginBottom: 4 }}>
                Livros
              </Button>
              <Button color="inherit" component={Link} to="/contact" sx={{ marginLeft: 4, marginBottom: 4 }}>
                Contato
              </Button>
              <Button color="inherit" onClick={handleLogout} sx={{ marginLeft: 4, marginBottom: 4 }}>
                Logout
              </Button>
            </>
          )}
        </Box>
      </Toolbar>
    </AppBar>
  );
}
