import "./styles.css";
import {
  Button,
  Card,
  CardContent,
  Grid,
  IconButton,
  Typography,
} from "@mui/material";
import React, { useEffect, useState } from "react";
import { FiEdit, FiTrash2, FiPower } from "react-icons/fi";
import { Link } from "react-router-dom";
import api from "../../services/api";
import Navbar from "../Navbar";
import useLogout from '../../utils/useLogout';

export default function Book() {
  
  const [books, setBooks] = useState([]);
  const [loading, setLoading] = useState(true);
  const [currentPage, setCurrentPage] = useState(0);
  const itemsPerPage = 9;
  const logout = useLogout();  
  
  const accessToken = localStorage.getItem("accessToken");
  const headers = {
    Authorization: `Bearer ${accessToken}`,
  }; 

  async function deleteBook(id) {
    try {
      await api.delete(`v1/books/${id}`, { headers });
      setBooks(books.filter(book => book.id !== id));
    } catch (error) {
      alert("Não foi possível deletar esse livro da base de dados!");
    }
  }

  useEffect(() => {
    const fetchBooks = async () => {
      setLoading(true);
      try {
        const response = await api.get("v1/books", { headers });
        console.log("Dados dos Livros:", response.data);
        setBooks(response.data);
      } catch (error) {
        console.error("Erro ao buscar os livros:", error);
      } finally {
        setLoading(false);
      }
    };

    fetchBooks();
  }, []);

  const handleNextPage = () => {
    if ((currentPage + 1) * itemsPerPage < books.length) {
      setCurrentPage(currentPage + 1);
    }
  };

  const handlePrevPage = () => {
    if (currentPage > 0) {
      setCurrentPage(currentPage - 1);
    }
  };

  const displayedBooks = books.slice(
    currentPage * itemsPerPage,
    (currentPage + 1) * itemsPerPage
  );

  return (
    <div className="book-container">
      <Navbar />
      <h1>Registros de Livros</h1>

      <div className="crud-buttons">
        <Button
          variant="contained"
          color="success"
          style={{ marginLeft: "10px" }}
          component={Link}
          to="/new/0"
        >
          Adicionar Livro
        </Button>
        <button onClick={logout} type="button" className="logout-button">
          <FiPower size={40} color="#251FC5" /> <strong>Logout</strong>
        </button>
      </div>

      {loading ? (
        <Typography variant="h6" color="textSecondary">
          Carregando livros...
        </Typography>
      ) : (
        <Grid container spacing={1}>
          {displayedBooks.length === 0 ? (
            <Typography variant="h6" color="textSecondary">
              Nenhum livro encontrado.
            </Typography>
          ) : (
            displayedBooks.map((book, index) => (
              <Grid item xs={12} sm={6} md={4} key={book.id}>
                <Card
                  className="book-card"
                  style={{ height: "100%", position: "relative" }}
                >
                  <CardContent className="book-card-content">
                    <div className="book-info">
                      <img
                        src={book.image}
                        alt={book.titulo}
                        className="book-image"
                        onError={(e) => {
                          e.target.src = "/images/default-book-cover.jpg";
                        }}
                      />
                      <div className="book-details">
                        <Typography variant="h6" style={{ color: "black" }}>
                          Título: {book.titulo}
                        </Typography>
                        <Typography
                          variant="body2"
                          style={{ color: "gray" }}
                          gutterBottom
                        >
                          Autor: {book.autor}
                        </Typography>
                        <Typography
                          variant="body2"
                          style={{ color: "gray" }}
                          gutterBottom
                        >
                          Preço: {book.preco !== undefined ? Intl.NumberFormat('pt-BR', { style: 'currency', currency: 'BRL' }).format(book.preco) : "Preço não disponível"}
                        </Typography>
                        <Typography variant="body2" style={{ color: "gray" }}>
                          Data de Lançamento:{" "}
                          {book.data_lancamento ? new Date(book.data_lancamento).toLocaleDateString('pt-BR') : "Data não disponível"}
                        </Typography>
                      </div>
                    </div>

                    <div className="book-actions">
                      <IconButton aria-label="edit" component={Link} to={`/new/${book.id}`}>
                        <FiEdit size={30} color="#000000" />
                      </IconButton>
                      <IconButton onClick={() => deleteBook(book.id)} aria-label="delete">
                        <FiTrash2 size={30} color="#ff4d4d" />
                      </IconButton>
                    </div>
                  </CardContent>
                </Card>
              </Grid>
            ))
          )}
        </Grid>
      )}

      <div
        style={{
          display: "flex",
          justifyContent: "space-between",
          marginTop: "20px",
        }}
      >
        <Button
          variant="outlined"
          onClick={handlePrevPage}
          disabled={currentPage === 0}
        >
          Anterior
        </Button>
        <Button
          variant="outlined"
          onClick={handleNextPage}
          disabled={(currentPage + 1) * itemsPerPage >= books.length}
        >
          Próxima
        </Button>
      </div>
    </div>
  );
}
