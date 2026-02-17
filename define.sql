
    create table socio_palestra (
        id integer not null auto_increment,
        codice_fiscale varchar(16) not null,
        cognome varchar(100) not null,
        nome varchar(100) not null,
        email varchar(255),
        primary key (id)
    ) engine=InnoDB;

    alter table socio_palestra 
       add constraint UKkomp5csel946vm6jyxq537rae unique (codice_fiscale);
