import React from 'react';
import { BrowserRouter as Router } from 'react-router-dom';
import './global.css';
import AppRoutes from './routes';



export default function App() {

  return (  

<Router>
      <div className="App">
        <AppRoutes />
      </div>
    </Router>

  );
}
