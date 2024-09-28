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
import { FiEdit, FiTrash2 } from "react-icons/fi";
import { Link } from "react-router-dom";
import api from "../../services/api";
import Navbar from "../Navbar";

export default function Book() {
  const [books, setBooks] = useState([]); // Estado para armazenar a lista de livros
  const [currentPage, setCurrentPage] = useState(0); // Estado para controlar a página atual
  const itemsPerPage = 12; // Número de itens por página
  const accessToken = localStorage.getItem("accessToken");

  const headers = {
    Authorization: `Bearer ${accessToken}`,
  };

  // Efeito para buscar os livros da API ao montar o componente
  useEffect(() => {
    api
      .get("v1/books", { headers })
      .then((response) => {
        setBooks(response.data); // Acesse diretamente a resposta como um array
      })
      .catch((error) => {
        console.error("Erro ao buscar os livros:", error);
      });
  }, []);

  // Função para avançar para a próxima página
  const handleNextPage = () => {
    if ((currentPage + 1) * itemsPerPage < books.length) {
      setCurrentPage(currentPage + 1); // Incrementa a página atual
    }
  };

  // Função para voltar para a página anterior
  const handlePrevPage = () => {
    if (currentPage > 0) {
      setCurrentPage(currentPage - 1); // Decrementa a página atual
    }
  };

  // Seleciona os livros a serem exibidos com base na página atual
  const displayedBooks = books.slice(currentPage * itemsPerPage, (currentPage + 1) * itemsPerPage);

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
          to="/new"
        >
          Adicionar Livro
        </Button>
        <Button
          variant="contained"
          color="secondary"
          style={{ marginLeft: "10px" }}
          component={Link}
          to="book/remove"
        >
          Remover Livro
        </Button>
      </div>

      <Grid container spacing={3}>
        {displayedBooks.map((book, index) => (
          <Grid item xs={12} sm={6} md={4} key={index}>
            <Card className="book-card" style={{ height: "100%" }}>
              <CardContent className="book-card-content" style={{ height: "100%" }}>
                <Typography variant="h6" color="primary">
                  Título: {book.titulo}
                </Typography>
                <Typography variant="body2" color="textSecondary" gutterBottom>
                  Autor: {book.autor}
                </Typography>
                <Typography variant="body2" color="textSecondary" gutterBottom>
                  Preço: R$ {book.preco.toFixed(2).replace(".", ",")}
                </Typography>
                <Typography variant="body2" color="textSecondary" style={{ flexGrow: 1 }}>
                  Data de Lançamento: {new Date(book.data_lancamento).toLocaleDateString()}
                </Typography>

                <div style={{ display: "flex", justifyContent: "flex-end", marginTop: "10px" }}>
                  <IconButton aria-label="edit">
                    <FiEdit size={30} color="#6366f1" />
                  </IconButton>
                  <IconButton aria-label="delete">
                    <FiTrash2 size={30} color="#ff4d4d" />
                  </IconButton>
                </div>
              </CardContent>
            </Card>
          </Grid>
        ))}
      </Grid>

      <div style={{ display: 'flex', justifyContent: 'space-between', marginTop: '20px'}}>
        {/* Botão para voltar à página anterior */}
        <Button variant="outlined" onClick={handlePrevPage} disabled={currentPage === 0}>
          Anterior
        </Button>
        {/* Botão para avançar para a próxima página */}
        <Button variant="outlined" onClick={handleNextPage} disabled={(currentPage + 1) * itemsPerPage >= books.length}>
          Próxima
        </Button>
      </div>
    </div>
  );
}
