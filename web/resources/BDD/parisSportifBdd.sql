drop database parisSportifBdd;

create database parisSportifBdd;

use parisSportifBdd;

create table Utilisateur (
    nomCompte varchar(30) not null,
    mdp varchar(30) not null,
    nom varchar(30) not null,
    prenom varchar(30) not null,
    admin int(1) not null,
    solde float(11,2) not null
    ) engine = InnoDB, Charset = utf8 collate utf8_general_ci;
    
create table Sport (
    idS int(11) not null,
    nomSport varchar(30) not null
    ) engine = InnoDB, Charset = utf8 collate utf8_general_ci;

create table Equipe (
    idE int(11) not null,
    nomE varchar(30) not null,
    idS int(11) not null
    ) engine = InnoDB, Charset = utf8 collate utf8_general_ci;

create table Prediction (
    idR int(11) not null,
    idP int(11) not null,
    choix char(1)
    ) engine = InnoDB, Charset = utf8 collate utf8_general_ci;
    
create table Paris (
    idP int(11) not null,
    mise float(11, 2) not null,
    nomCompte varchar(30) not null
    ) engine = InnoDB Charset = utf8 collate utf8_general_ci;
    
create table Rencontre (
    idR int(11) not null,
    dateR datetime not null,
    termine int(1) not null,
    coteV float(11,2) not null,
    coteD float(11,2) not null,
    coteL float(11,2) not null,
    idVisiteur int(11) not null,
    idLocal int(11) not null
    ) engine = InnoDB, Charset = utf8 collate utf8_general_ci; 



alter table Utilisateur
    add constraint Pk_nomCompte primary key
    (nomCompte);    
    
alter table Sport
    add constraint Pk_idS primary key
    (idS);  

alter table Equipe
    add constraint Pk_idE primary key
    (idE);  

alter table Paris
    add constraint Pk_idP primary key
    (idP);  

alter table Prediction
    add constraint Pk_idU primary key
    (idR, idP);  

alter table Rencontre
    add constraint Pk_idR primary key
    (idR);  

 alter table Sport
   modify idS int(11) not null
   auto_increment;

alter table Equipe
   modify idE int(11) not null
   auto_increment;
    
alter table Paris
   modify idP int(11) not null
   auto_increment;

alter table Rencontre
   modify idR int(11) not null
   auto_increment;

   
alter table Equipe
    add constraint Fk_Equipe_idS_Sport foreign key
    (idS)
    references Sport (idS)
    on delete restrict
    on update cascade;

alter table Rencontre
    add constraint Fk_Rencontre_idVisiteur_Equipe foreign key
    (idVisiteur)
    references Equipe (idE)
    on delete restrict
    on update cascade;

alter table Rencontre
    add constraint Fk_Rencontre_idLocal_Equipe foreign key
    (idLocal)
    references Equipe (idE)
    on delete restrict
    on update cascade;
    
alter table Paris
    add constraint Fk_Paris_nomCompte_Utilisateur foreign key
    (nomCompte)
    references Utilisateur (nomCompte)
    on delete restrict
    on update cascade;

alter table Prediction
    add constraint Fk_Prediction_idR_Rencontre foreign key
    (idR)
    references Rencontre (idR)
    on delete restrict
    on update cascade;

 alter table Prediction
    add constraint Fk_Prediction_idP_Paris foreign key
    (idP)
    references Paris (idP)
    on delete restrict
    on update cascade;

show tables;

/*Peuplement*/

insert into Sport(nomSport) values 
    ('rugby'),
    ('football');

insert into Equipe (NomE, idS) values
    ('lille', 2),
    ('bordeaux', 2),
    ('fleure', 2),
    ('toulon', 1),
    ('castres', 1),
    ('montpellier', 1);

insert into Utilisateur values 
    ('loudel', 'loudel', 'Delaplace', 'Louis', 1, 10000.01),
    ('fenfut', 'fenfut', 'Fenneteau', 'Alexandre', 1, 10000.01),
    ('afousse', 'afousse', 'Fousse', 'Allan', 0, 100.58);

insert into Rencontre(dateR, termine, coteV, coteD, coteL, idVisiteur, idLocal) values
   ('2018-04-11 10:58:00', 1, 3.40, 1.2, 1.1, 1, 3),
   ('2018-12-11 10:58:00', 0, 3.40, 1.2, 1.1, 6, 4);

insert into Paris(mise, nomCompte) values
    (10, 'fenfut'),
    (10, 'afousse');


insert into Prediction values 
   (1, 1, 'v'),
   (2, 1, 'l'),
   (2, 2, 'd');





