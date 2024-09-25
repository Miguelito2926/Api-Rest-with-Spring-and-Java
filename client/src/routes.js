import React from "react";
import { Routes, Route } from 'react-router-dom';
import Login from './pages/Login/index'; // Aponte para o index.js dentro de Login
import Book from './pages/Book/index';     // Aponte para o index.js dentro de Book
import Home from './pages/Home/index';     // Aponte para o index.js dentro de Home

export default function AppRoutes() {
    return (    
        <Routes>
            <Route path="/" element={<Home />} />
            <Route path="/login" element={<Login />} />
            <Route path="/book" element={<Book />} />
        </Routes>       
    );
}
