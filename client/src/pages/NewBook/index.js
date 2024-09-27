import React, {useState} from 'react';
import './style.css';
import { Link, useNavigate } from 'react-router-dom';
import { FiArrowLeft } from 'react-icons/fi';
import Navbar from '../Navbar';
import api from '../../services/api';

export default function NewBook() {

  const [id, setId] = useState(null);
  const [autor, setAuthor] = useState('');
  const [data_lancamento, setLaucnhDate] = useState('');
  const [preco, setPrice] = useState('');
  const [titulo, setTitle] = useState(''); 

  const accessToken = localStorage.getItem('accessToken');

  const navigate = useNavigate();

  async function createNewBook(event) {
    event.preventDefault();
  
    const data = {
      titulo,
      autor,
      data_lancamento,
      preco,
    };
  
    const headers = {
      Authorization: `Bearer ${accessToken}`,
    };
  
    try {     
      await api.post('v1/books', data, { headers });
      alert('Livro criado com sucesso!');
      navigate('/books');
    } catch (error) {
      alert('Erro ao gravar livro. Tente novamente!');
    }
  }
  

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

          <form onSubmit={createNewBook}>
            <input placeholder="Título" value={titulo} onChange={event => setTitle(event.target.value)} />
            <input placeholder="Autor" value={autor} onChange={event => setAuthor(event.target.value)} />
            <input type="date"value={data_lancamento} onChange={event => setLaucnhDate (event.target.value)} />
            <input placeholder="Preço" value={preco} onChange={event =>  setPrice(event.target.value)} />

            <button className="button" type='submit'>Adicionar</button>
          </form>
        </div>
      </div>
    </div>
  );
}
