import React, { useState, useEffect } from 'react';
import './style.css';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { FiArrowLeft } from 'react-icons/fi';
import Navbar from '../Navbar';
import api from '../../services/api';

export default function NewBook() {
  const [id, setId] = useState();
  const [autor, setAuthor] = useState('');
  const [data_lancamento, setLaunchDate] = useState('');
  const [preco, setPrice] = useState('');
  const [titulo, setTitle] = useState('');
  const [image, setImage] = useState(''); // Novo estado para a URL da imagem
  const { bookId } = useParams(); // Captura o ID do livro a ser editado

  const accessToken = localStorage.getItem('accessToken');
  const navigate = useNavigate();

  useEffect(() => {
    if (bookId && bookId !== '0') { // Verifica se o ID é válido para edição
      const headers = {
        Authorization: `Bearer ${accessToken}`,
      };

      // Carregar informações do livro para edição
      api.get(`v1/books/${bookId}`, { headers })
        .then(response => {
          const book = response.data;
          setId(book.id);
          setTitle(book.titulo);
          setAuthor(book.autor);
          // Formatar a data para 'yyyy-MM-dd' antes de definir o estado
          const formattedDate = book.data_lancamento.split('T')[0];
          setLaunchDate(formattedDate);
          setPrice(book.preco);
          setImage(book.image); // Define a imagem atual
        })
        .catch(error => {
          console.error("Erro ao buscar o livro:", error);
          alert('Erro ao buscar o livro. Tente novamente!');
        });
    }
  }, [bookId, accessToken]);

  async function createOrUpdateBook(event) {
    event.preventDefault();
  
    const data = {
      titulo,
      autor,
      data_lancamento,
      preco,
      image, // Inclui a URL da imagem no objeto de dados
    };
  
    const headers = {
      Authorization: `Bearer ${accessToken}`,
    };
  
    try {     
      if (id) {
        // Editar livro existente
        await api.put(`v1/books/${id}`, data, { headers });
        alert('Livro atualizado com sucesso!');
      } else {
        // Criar novo livro
        await api.post('v1/books', data, { headers });
        alert('Livro criado com sucesso!');
      }
      navigate('/books'); // Redireciona para a lista de livros após a ação
    } catch (error) {
      alert('Erro ao gravar livro. Tente novamente!');
    }
  }

  return (
    <div className="new-book-container" style={{ marginTop: '64px' }}> {/* Adiciona margem para não ficar atrás da navbar */}
      <Navbar />
      <div className="content">
        <div className="form-container">
          <section className="form">
            <h1>{id ? 'Editar Livro' : 'Adicionar Novo Livro'}</h1>
            <p>Insira as informações do livro e clique em <strong>{id ? 'Atualizar' : 'Adicionar'}</strong>!</p>
            <Link className='back-link' to='/books'>
              <FiArrowLeft size={30} color='#251fc5' />
              Voltar
            </Link>
          </section>

          <form onSubmit={createOrUpdateBook}>
            <input placeholder="Título" value={titulo} onChange={event => setTitle(event.target.value)} />
            <input placeholder="Autor" value={autor} onChange={event => setAuthor(event.target.value)} />
            <input type="date" value={data_lancamento} onChange={event => setLaunchDate(event.target.value)} />
            <input placeholder="Preço" type="number" value={preco} onChange={event => setPrice(event.target.value)} />
            <input placeholder="URL Capa do livro" value={image} onChange={event => setImage(event.target.value)} /> {/* Novo campo para a imagem */}

            <button className="button" type='submit'>{id ? 'Atualizar' : 'Adicionar'}</button>
          </form>
        </div>
      </div>
    </div>
  );
}
