// App.js
import React from 'react';
import { BrowserRouter as Router } from 'react-router-dom';
import { ThemeProvider } from '@mui/material/styles'; // Importando o ThemeProvider
import theme from './theme'; // Importando o tema que você criou
import './global.css';
import AppRoutes from './routes';

export default function App() {
    return (  
        <ThemeProvider theme={theme}> {/* Envolvendo sua aplicação com o ThemeProvider */}
            <Router>
                <div className="App">
                    <AppRoutes />
                </div>
            </Router>
        </ThemeProvider>
    );
}
