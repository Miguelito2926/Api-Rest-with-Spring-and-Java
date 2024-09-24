import React from "react";
import {Routes, Route} from 'react-router-dom';
import Login from './pages/Login';
import Book from './pages/Book';

export default function AppRoutes() {
    return (    
        <Routes>
            <Route path="/" element={<Login/>}/>
            <Route path="/book" element={<Book/>}/>
        </Routes>       
    );
}