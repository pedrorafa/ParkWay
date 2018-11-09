use parkway
go
create procedure sp_tbCliente_tbEndereco_I 
	(@cpf varchar(14), @nome varchar(50), @contato varchar(50),
	@numero varchar(6), @logradouro varchar(50), @estado varchar(2), @cidade varchar(50),  @cep varchar(14)) as

insert into tbEndereco (cep, estado, cidade, logradouro, numero)
		values (@cep, @estado, @cidade, @logradouro, @numero)

declare @idEndereco int
set @idEndereco = (select @@IDENTITY)

insert into tbCliente (cpf, nome, idEndereco, contato)	
		values (@cpf, @nome, @idEndereco, @contato)
go

create procedure sp_tbCliente_tbEndereco_U 
	(@cpf varchar(14), @nome varchar(50), @contato varchar(50),
	@idEndereco int, @numero varchar(6), @logradouro varchar(50), @estado varchar(2), @cidade varchar(50),  @cep varchar(14)) as

update tbCliente set nome = @nome, contato = @contato, idEndereco = @idEndereco
	where cpf = @cpf

update tbEndereco set cep = @cep, estado = @estado, cidade = @cidade, logradouro = @logradouro, numero = @numero
	where idEndereco = @idEndereco
go

create procedure sp_tbCliente_tbEndereco_D
	(@cpf varchar(14), @idEndereco int) as
	
delete from tbCliente where cpf = @cpf
delete from tbCliente where idEndereco = @idEndereco
go 

create procedure sp_tbCliente_I 
	(@cpf varchar, @nome varchar(50), @contato varchar(50)) as

insert into tbCliente (cpf, nome, idEndereco, contato)	
		values (@cpf, @nome, null, @contato)
go

create procedure sp_tbCliente_U 
	(@cpf varchar, @nome varchar(50), @contato varchar(50)) as

update tbCliente set nome = @nome, contato = @contato
	where cpf = @cpf
go

create procedure sp_tbCliente_D
	(@cpf varchar) as

declare @idEndereco int
set @idEndereco = (select isnull((idEndereco), 0) from tbCliente where cpf = @cpf)

if @idEndereco <> 0
begin
	delete from tbEndereco where idEndereco = @idEndereco
end

delete from tbCliente where cpf = @cpf
go

create procedure sp_tbEndereco_I
	(@numero varchar(6), @logradouro varchar(50), @estado varchar(2), @cidade varchar(50),  @cep varchar(14)) as

insert into tbEndereco (cep, estado, cidade, logradouro, numero)
		values (@cep, @estado, @cidade, @logradouro, @numero)
go

create procedure sp_tbEndereco_U
	(@numero varchar(6), @logradouro varchar(50), @estado varchar(2), @cidade varchar(50),  @cep varchar(14), @idEndereco int) as

update tbEndereco set numero = @numero, logradouro = @logradouro, estado = @estado, cidade = @cidade, cep = @cep
	where idEndereco = @idEndereco
go

create procedure sp_tbEndereco_D
	(@idEndereco int) as

delete from tbEndereco where idEndereco = @idEndereco
go

create procedure sp_tbVeiculo_I
	(@placa varchar(7), @cpfCliente varchar(14), @idCor numeric(2,0), @modelo varchar(30)) as

insert into tbVeiculo (placa, cpfCliente, idCor, modelo)	
		values (@placa, @cpfCliente, @idCor, @modelo)
go

create procedure sp_tbVeiculo_U
	(@placa varchar(7), @cpfCliente varchar(14), @idCor numeric(2,0), @modelo varchar(30)) as

update tbVeiculo set cpfCliente = @cpfCliente, idCor = @idCor, modelo = @modelo
		where placa = @placa
go

create procedure sp_tbVeiculo_D
	(@placa varchar(7)) as

delete from tbVeiculo where placa = @placa
go

create procedure sp_tbVaga_I
	(@numero int, @tamanho varchar(10), @dataPagamento Date, @posx int, @posy int) as

insert into tbVaga (numero, tamanho, dataPagamento, posx, posy)	
		values (@numero, @tamanho, @dataPagamento, @posx, @posy)
go

create procedure sp_tbVaga_U
	(@numero int, @tamanho varchar(10), @dataPagamento Date, @posx int, @posy int) as

update tbVaga set tamanho = @tamanho, dataPagamento = @dataPagamento, posx = @posx, posy = @posy
		where numero = @numero
go

create procedure sp_tbVaga_D
	(@numero int) as

delete from tbVaga where numero = @numero
go


create procedure sp_tbHistoricoVaga_I
	(@placa varchar(7), @numero int, @dataPgto Date, @ativo bit) as

insert into tbHistoricoVaga (placa, numero, dataPgto, ativo)	
		values (@placa, @numero, @dataPgto, @ativo)
go

create procedure sp_tbHistoricoVaga_U
	(@placa varchar(7), @numero int, @dataPgto Date, @ativo bit) as
--Incoerencia no momento de setar valores, desenvolver outras procs (placa, numero de vaga e/ou data)
update tbHistoricoVaga set placa = @placa, numero = @numero, dataPgto = @dataPgto, ativo = @ativo	
		where placa = @placa and numero = @numero and dataPgto = @dataPgto
go

create procedure sp_tbHistoricoVaga_D
	(@placa varchar(7), @numero int, @dataPgto Date) as

delete from tbHistoricoVaga where placa = @placa and numero = @numero and dataPgto = @dataPgto		
go

create procedure sp_tbPagamento_I
	(@placa varchar(7), @numero int, @data Date, @idFormaPgto int, @valor numeric (5,2)) as

insert into tbPagamento (placa, numero, data, idFormaPgto, valor)	
		values (@placa, @numero, @data, @idFormaPgto, @valor)
go

create procedure sp_tbPagamento_U
	(@placa varchar(7), @numero int, @data Date, @idFormaPgto int, @valor numeric (5,2)) as
--Incoerencia no momento de setar valores, desenvolver outras procs (placa, numero de vaga e/ou data) 
update tbPagamento set placa = @placa, numero = @numero, data = @data, idFormaPgto = @idFormaPgto, valor = @valor
		where placa = @placa and numero = @numero and data = @data
go

create procedure sp_tbPagamento_D
	(@placa varchar(7), @numero int, @data Date, @idFormaPgto int, @valor numeric (5,2)) as

insert into tbPagamento (placa, numero, data, idFormaPgto, valor)	
		values (@placa, @numero, @data, @idFormaPgto, @valor)
go

