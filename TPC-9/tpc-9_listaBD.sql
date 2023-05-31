create database tpc_9_POO;
use tpc_9_POO;

select * from UserAcounts;

-- inserindo valores na base de dados
insert into UserAcounts(Firstname, Lastname, Username, Password) values ("Fredy","Brito","Froyd","Fredboy17$");
insert into UserAcounts(Firstname, Lastname, Username, Password) values ("Paulo","Silva","Isecmar-Paulo","cisco2023");
insert into UserAcounts(Firstname, Lastname, Username, Password) values ("Nelson","Sousa","Rei_da_Passada","enejotta");
insert into UserAcounts(Firstname, Lastname, Username, Password) values ("Ericles","Fonseca","emossOK","efonseca2023");

select count(1) from UserAcounts where username = "Froyd" and password = "Fredboy17$"; 