# Adiciona uma nova coluna chamada "ativo" do tipo tinyint à tabela "medicos"
alter table medicos add ativo tinyint;

# Atualiza todos os registros existentes na tabela "medicos", definindo o valor da coluna "ativo" como 1 (true).
update medicos set ativo = 1;

# Adiciona a restrição de NOT NULL na coluna "ativo" e define o valor padrão como 1 (true).
alter table medicos modify ativo tinyint not null default 1;
