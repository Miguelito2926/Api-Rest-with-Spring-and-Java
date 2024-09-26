import React from 'react';
import './style.css';
import { Link } from 'react-router-dom';
import { FiArrowLeft } from 'react-icons/fi';
import Navbar from '../Navbar';

export default function NewBook() {
  return (
    <div className="new-book-container" style={{ marginTop: '64px' }}> {/* Adiciona margem para não ficar atrás da navbar */}
      <Navbar/>
      <div className="content">
        <div className="form-container">
          <section className="form">
            <h1>Adicionar Novo Livro</h1>
            <p>Insira as informações do livro e clique em <strong>Adicionar</strong>!</p>
            <Link className='back-link' to='/books'>
              <FiArrowLeft size={30} color='#251fc5' />
              Home
            </Link>
          </section>

          <form>
            <input placeholder="Título" />
            <input placeholder="Autor" />
            <input type="date" />
            <input placeholder="Preço" />

            <button className="button" type='submit'>Adicionar</button>
          </form>
        </div>
      </div>
    </div>
  );
}
