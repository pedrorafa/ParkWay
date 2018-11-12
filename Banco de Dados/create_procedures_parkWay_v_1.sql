use parkway
go
create procedure sp_tbCliente_tbEndereco_I 
	(@cpf varchar(14), @nome varchar(50), @contato varchar(50),
	@numero varchar(6), @logradouro varchar(50), @estado varchar(2), @cidade varchar(50),  @cep varchar(14)) as

insert into tbEndereco (cpf,cep, estado, cidade, logradouro, numero)
		values (@cpf, @cep, @estado, @cidade, @logradouro, @numero)
		
insert into tbCliente (cpf, nome, contato)	
		values (@cpf, @nome, @contato)
go

create procedure sp_tbCliente_tbEndereco_U 
	(@cpf varchar(14), @nome varchar(50), @contato varchar(50),
	 @numero varchar(6), @logradouro varchar(50), @estado varchar(2), @cidade varchar(50),  @cep varchar(14)) as

update tbCliente set nome = @nome, contato = @contato
	where cpf = @cpf

update tbEndereco set  estado = @estado, cidade = @cidade, logradouro = @logradouro, numero = @numero
	where cpf = @cpf
go

create procedure sp_tbCliente_tbEndereco_D
	(@cpf varchar(14)) as
	
delete from tbCliente where cpf = @cpf
delete from tbCliente where cpf = @cpf
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
	(@numero int, @tamanho varchar(10),  @posx int, @posy int) as

insert into tbVaga (numero, tamanho, posx, posy)	
		values (@numero, @tamanho, @posx, @posy)
go

create procedure sp_tbVaga_U
	(@numero int, @tamanho varchar(10),  @posx int, @posy int) as

update tbVaga set tamanho = @tamanho,  posx = @posx, posy = @posy
		where numero = @numero
go

create procedure sp_tbVaga_D
	(@numero int) as

delete from tbVaga where numero = @numero
go


create procedure sp_tbHistVaga_I
	(@placa varchar(7), @numero int, @dataPgto Date, @ativo bit) as

insert into tbHistVaga (placa, numero, dataPgto, ativo)	
		values (@placa, @numero, @dataPgto, @ativo)
go

create procedure sp_tbHistVaga_U
	(@placa varchar(7), @numero int, @dataPgto Date, @ativo bit) as
--Incoerencia no momento de setar valores, desenvolver outras procs (placa, numero de vaga e/ou data)
update tbHistVaga set placa = @placa, numero = @numero, dataPgto = @dataPgto, ativo = @ativo	
		where placa = @placa and numero = @numero and dataPgto = @dataPgto
go

create procedure sp_tbHistVaga_D
	(@placa varchar(7), @numero int, @dataPgto Date) as

delete from tbHistVaga where placa = @placa and numero = @numero and dataPgto = @dataPgto		
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

