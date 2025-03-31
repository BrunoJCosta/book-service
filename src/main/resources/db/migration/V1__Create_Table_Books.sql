create schema if not exists book_server;
CREATE TABLE book_server.book (
  id bigserial PRIMARY KEY,
  author text,
  date timestamp(6) NOT NULL,
  price decimal(65,2) NOT NULL,
  title text
);