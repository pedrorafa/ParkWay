USE [master]
GO
/****** Object:  Database [parkWay]    Script Date: 20/11/2018 22:46:19 ******/
CREATE DATABASE [parkWay]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'parkWay', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL12.MSSQLSERVER\MSSQL\DATA\parkWay.mdf' , SIZE = 4288KB , MAXSIZE = UNLIMITED, FILEGROWTH = 1024KB )
 LOG ON 
( NAME = N'parkWay_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL12.MSSQLSERVER\MSSQL\DATA\parkWay_log.ldf' , SIZE = 1072KB , MAXSIZE = 2048GB , FILEGROWTH = 10%)
GO
ALTER DATABASE [parkWay] SET COMPATIBILITY_LEVEL = 120
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [parkWay].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [parkWay] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [parkWay] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [parkWay] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [parkWay] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [parkWay] SET ARITHABORT OFF 
GO
ALTER DATABASE [parkWay] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [parkWay] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [parkWay] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [parkWay] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [parkWay] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [parkWay] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [parkWay] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [parkWay] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [parkWay] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [parkWay] SET  ENABLE_BROKER 
GO
ALTER DATABASE [parkWay] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [parkWay] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [parkWay] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [parkWay] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [parkWay] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [parkWay] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [parkWay] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [parkWay] SET RECOVERY FULL 
GO
ALTER DATABASE [parkWay] SET  MULTI_USER 
GO
ALTER DATABASE [parkWay] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [parkWay] SET DB_CHAINING OFF 
GO
ALTER DATABASE [parkWay] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [parkWay] SET TARGET_RECOVERY_TIME = 0 SECONDS 
GO
ALTER DATABASE [parkWay] SET DELAYED_DURABILITY = DISABLED 
GO
EXEC sys.sp_db_vardecimal_storage_format N'parkWay', N'ON'
GO
USE [parkWay]
GO
/****** Object:  Table [dbo].[tbCliente]    Script Date: 20/11/2018 22:46:19 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[tbCliente](
	[cpf] [varchar](14) NOT NULL,
	[nome] [varchar](50) NULL,
	[idEndereco] [int] NULL,
	[contato] [varchar](50) NULL,
PRIMARY KEY CLUSTERED 
(
	[cpf] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[tbEndereco]    Script Date: 20/11/2018 22:46:19 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[tbEndereco](
	[cpf] [varchar](14) NOT NULL,
	[cep] [varchar](14) NULL,
	[estado] [varchar](2) NULL,
	[cidade] [varchar](50) NULL,
	[logradouro] [varchar](50) NULL,
	[numero] [varchar](6) NULL,
PRIMARY KEY CLUSTERED 
(
	[cpf] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[tbHistVaga]    Script Date: 20/11/2018 22:46:19 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[tbHistVaga](
	[placa] [varchar](7) NULL,
	[numero] [int] NULL,
	[dataPgto] [date] NULL,
	[ativo] [bit] NULL
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[tbPagamento]    Script Date: 20/11/2018 22:46:19 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[tbPagamento](
	[placa] [varchar](7) NULL,
	[numero] [int] NULL,
	[data] [date] NULL,
	[idFormaPgto] [int] NULL,
	[valor] [numeric](5, 2) NULL
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[tbVaga]    Script Date: 20/11/2018 22:46:19 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[tbVaga](
	[numero] [int] NOT NULL,
	[tamanho] [varchar](10) NULL,
	[dataPagamento] [date] NULL,
	[posx] [int] NULL,
	[posy] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[numero] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[tbVeiculo]    Script Date: 20/11/2018 22:46:19 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[tbVeiculo](
	[placa] [varchar](7) NOT NULL,
	[cpfCliente] [varchar](14) NULL,
	[idCor] [numeric](2, 0) NULL,
	[modelo] [varchar](30) NULL,
PRIMARY KEY CLUSTERED 
(
	[placa] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
ALTER TABLE [dbo].[tbEndereco]  WITH CHECK ADD  CONSTRAINT [FK_tbEndereco_tbCliente] FOREIGN KEY([cpf])
REFERENCES [dbo].[tbCliente] ([cpf])
GO
ALTER TABLE [dbo].[tbEndereco] CHECK CONSTRAINT [FK_tbEndereco_tbCliente]
GO
ALTER TABLE [dbo].[tbVeiculo]  WITH CHECK ADD  CONSTRAINT [FK_tbVeiculo_tbCliente] FOREIGN KEY([cpfCliente])
REFERENCES [dbo].[tbCliente] ([cpf])
GO
ALTER TABLE [dbo].[tbVeiculo] CHECK CONSTRAINT [FK_tbVeiculo_tbCliente]
GO
/****** Object:  StoredProcedure [dbo].[sp_tbCliente_tbEndereco_D]    Script Date: 20/11/2018 22:46:19 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

create procedure [dbo].[sp_tbCliente_tbEndereco_D]
	(@cpf varchar(14)) as
	
delete from tbCliente where cpf = @cpf
delete from tbCliente where cpf = @cpf

GO
/****** Object:  StoredProcedure [dbo].[sp_tbCliente_tbEndereco_I]    Script Date: 20/11/2018 22:46:19 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
create procedure [dbo].[sp_tbCliente_tbEndereco_I] 
	(@cpf varchar(14), @nome varchar(50), @contato varchar(50),
	@numero varchar(6), @logradouro varchar(50), @estado varchar(2), @cidade varchar(50),  @cep varchar(14)) as

insert into tbEndereco (cpf,cep, estado, cidade, logradouro, numero)
		values (@cpf, @cep, @estado, @cidade, @logradouro, @numero)
		
insert into tbCliente (cpf, nome, contato)	
		values (@cpf, @nome, @contato)

GO
/****** Object:  StoredProcedure [dbo].[sp_tbCliente_tbEndereco_U]    Script Date: 20/11/2018 22:46:19 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

create procedure [dbo].[sp_tbCliente_tbEndereco_U] 
	(@cpf varchar(14), @nome varchar(50), @contato varchar(50),
	 @numero varchar(6), @logradouro varchar(50), @estado varchar(2), @cidade varchar(50),  @cep varchar(14)) as

update tbCliente set nome = @nome, contato = @contato
	where cpf = @cpf

update tbEndereco set  estado = @estado, cidade = @cidade, logradouro = @logradouro, numero = @numero
	where cpf = @cpf

GO
/****** Object:  StoredProcedure [dbo].[sp_tbHistVaga_D]    Script Date: 20/11/2018 22:46:19 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

create procedure [dbo].[sp_tbHistVaga_D]
	(@placa varchar(7), @numero int, @dataPgto Date) as

delete from tbHistVaga where placa = @placa and numero = @numero and dataPgto = @dataPgto		

GO
/****** Object:  StoredProcedure [dbo].[sp_tbHistVaga_I]    Script Date: 20/11/2018 22:46:19 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO


create procedure [dbo].[sp_tbHistVaga_I]
	(@placa varchar(7), @numero int, @dataPgto Date, @ativo bit) as

insert into tbHistVaga (placa, numero, dataPgto, ativo)	
		values (@placa, @numero, @dataPgto, @ativo)

GO
/****** Object:  StoredProcedure [dbo].[sp_tbHistVaga_U]    Script Date: 20/11/2018 22:46:19 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

create procedure [dbo].[sp_tbHistVaga_U]
	(@placa varchar(7), @numero int, @dataPgto Date, @ativo bit) as
--Incoerencia no momento de setar valores, desenvolver outras procs (placa, numero de vaga e/ou data)
update tbHistVaga set placa = @placa, numero = @numero, dataPgto = @dataPgto, ativo = @ativo	
		where placa = @placa and numero = @numero and dataPgto = @dataPgto

GO
/****** Object:  StoredProcedure [dbo].[sp_tbPagamento_D]    Script Date: 20/11/2018 22:46:19 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE procedure [dbo].[sp_tbPagamento_D]
	(@placa varchar(7), @numero int, @data Date) as

delete from tbPagamento where placa = @placa AND numero = @numero AND data = @data

GO
/****** Object:  StoredProcedure [dbo].[sp_tbPagamento_I]    Script Date: 20/11/2018 22:46:19 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

create procedure [dbo].[sp_tbPagamento_I]
	(@placa varchar(7), @numero int, @data Date, @idFormaPgto int, @valor numeric (5,2)) as

insert into tbPagamento (placa, numero, data, idFormaPgto, valor)	
		values (@placa, @numero, @data, @idFormaPgto, @valor)

GO
/****** Object:  StoredProcedure [dbo].[sp_tbPagamento_U]    Script Date: 20/11/2018 22:46:19 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

create procedure [dbo].[sp_tbPagamento_U]
	(@placa varchar(7), @numero int, @data Date, @idFormaPgto int, @valor numeric (5,2)) as
--Incoerencia no momento de setar valores, desenvolver outras procs (placa, numero de vaga e/ou data) 
update tbPagamento set placa = @placa, numero = @numero, data = @data, idFormaPgto = @idFormaPgto, valor = @valor
		where placa = @placa and numero = @numero and data = @data

GO
/****** Object:  StoredProcedure [dbo].[sp_tbVaga_D]    Script Date: 20/11/2018 22:46:19 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

create procedure [dbo].[sp_tbVaga_D]
	(@numero int) as

delete from tbVaga where numero = @numero

GO
/****** Object:  StoredProcedure [dbo].[sp_tbVaga_I]    Script Date: 20/11/2018 22:46:19 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

create procedure [dbo].[sp_tbVaga_I]
	(@numero int, @tamanho varchar(10),  @posx int, @posy int) as

insert into tbVaga (numero, tamanho, posx, posy)	
		values (@numero, @tamanho, @posx, @posy)

GO
/****** Object:  StoredProcedure [dbo].[sp_tbVaga_U]    Script Date: 20/11/2018 22:46:19 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

create procedure [dbo].[sp_tbVaga_U]
	(@numero int, @tamanho varchar(10),  @posx int, @posy int) as

update tbVaga set tamanho = @tamanho,  posx = @posx, posy = @posy
		where numero = @numero

GO
/****** Object:  StoredProcedure [dbo].[sp_tbVeiculo_D]    Script Date: 20/11/2018 22:46:19 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

create procedure [dbo].[sp_tbVeiculo_D]
	(@placa varchar(7)) as

delete from tbVeiculo where placa = @placa

GO
/****** Object:  StoredProcedure [dbo].[sp_tbVeiculo_I]    Script Date: 20/11/2018 22:46:19 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
create procedure [dbo].[sp_tbVeiculo_I]
	(@placa varchar(7), @cpfCliente varchar(14), @idCor numeric(2,0), @modelo varchar(30)) as

insert into tbVeiculo (placa, cpfCliente, idCor, modelo)	
		values (@placa, @cpfCliente, @idCor, @modelo)

GO
/****** Object:  StoredProcedure [dbo].[sp_tbVeiculo_U]    Script Date: 20/11/2018 22:46:19 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

create procedure [dbo].[sp_tbVeiculo_U]
	(@placa varchar(7), @cpfCliente varchar(14), @idCor numeric(2,0), @modelo varchar(30)) as

update tbVeiculo set cpfCliente = @cpfCliente, idCor = @idCor, modelo = @modelo
		where placa = @placa

GO
USE [master]
GO
ALTER DATABASE [parkWay] SET  READ_WRITE 
GO
