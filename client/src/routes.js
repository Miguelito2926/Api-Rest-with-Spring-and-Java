import React from "react";
import { Route, Routes } from 'react-router-dom';
import Books from './pages/Books/index'; // Aponte para o index.js dentro de Book
import Home from './pages/Home/index'; // Aponte para o index.js dentro de Home
import Login from './pages/Login/index'; // Aponte para o index.js dentro de Login
import NewBook from './pages/NewBook/index';
export default function AppRoutes() {
    return (    
        <Routes>
            <Route path="/" element={<Home />} />
            <Route path="/login" element={<Login />} />
            <Route path="/books" element={<Books />} />
            <Route path="/new" element={<NewBook />} />            
        </Routes>       
    );
}
