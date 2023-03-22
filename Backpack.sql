use BackpackDB;

create table backpack(
mochila_id int primary key identity (1,1),
nombre varchar(245),
capacidad int,
color varchar(100))

create table personage(
personaje_id int primary key identity (1,1),
nombre varchar(245),
salud float,
velocidad float,
id_mochila int,
constraint id_mochila foreign key (id_mochila) references backpack(mochila_id))

create table objectt(
objeto_id int primary key identity (1,1),
nombre varchar(245),
tipo varchar(100),
costo float)

create table backpack_object(
mochila_objeto_id int primary key identity (1,1),
id_mochilita int,
id_objeto int,
constraint id_mochilita foreign key (id_mochilita) references backpack(mochila_id),
constraint id_objeto foreign key (id_objeto) references objectt(objeto_id))

select * from backpack;
select * from objectt;
select * from personage;
select * from backpack_object;