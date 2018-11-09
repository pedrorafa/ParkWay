create database parkWay
go
use parkWay
go
create table tbCliente 
(
	cpf varchar(14) primary key,
	nome varchar(50),
	idEndereco int,
	contato varchar(50)	
)
go
create table tbEndereco
(
	idEndereco int primary key identity(1,1),
	cep varchar(14),
	estado varchar(2),
	cidade varchar(50),
	logradouro varchar(50),
	numero varchar(6)
)
go
create table tbVeiculo
(
	placa varchar(7) primary key,
	cpfCliente varchar(14),
	idCor numeric(2,0),
	modelo varchar(30)
)
go
create table tbVaga
(	
	numero int primary key,
	tamanho varchar(10),
	dataPagamento Date,
	posx int,
	posy int
)
go
create table tbHistoricoVaga
(
	placa varchar(7),
	numero int,
	dataPgto Date,
	ativo bit
)
go
create table tbPagamento
(
	placa varchar(7),
	numero int,
	data Date,
	idFormaPgto int,
	valor numeric (5,2)
)
go