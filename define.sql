
    create table abbonamento_attivita (
        abbonamento_id integer not null,
        attivita_id integer not null
    ) engine=InnoDB;

    create table abbonamento_socio (
        data_iscrizione date not null,
        id integer not null auto_increment,
        id_socio integer,
        primary key (id)
    ) engine=InnoDB;

    create table attivita (
        id integer not null auto_increment,
        descrizione varchar(100) not null,
        primary key (id)
    ) engine=InnoDB;

    create table certificato_medico (
        data_certificato date,
        id integer not null auto_increment,
        socio_id integer,
        tipo_certificato bit,
        primary key (id)
    ) engine=InnoDB;

    create table socio_palestra (
        id integer not null auto_increment,
        codice_fiscale varchar(16) not null,
        cognome varchar(100) not null,
        nome varchar(100) not null,
        email varchar(255),
        primary key (id)
    ) engine=InnoDB;

    alter table attivita 
       add constraint UKk0chtstwv1iubirficpejhr4d unique (descrizione);

    alter table certificato_medico 
       add constraint UKfh7x211m11apxus0ygp7io1of unique (socio_id);

    alter table socio_palestra 
       add constraint UKkomp5csel946vm6jyxq537rae unique (codice_fiscale);

    alter table abbonamento_attivita 
       add constraint FK853iwjge5sco7nac3v8pvs72v 
       foreign key (attivita_id) 
       references attivita (id);

    alter table abbonamento_attivita 
       add constraint FK7slwjgyb7wchv5gnm07g9m6v1 
       foreign key (abbonamento_id) 
       references abbonamento_socio (id);

    alter table abbonamento_socio 
       add constraint FK22uaw7830fw71duo88vg10i3m 
       foreign key (id_socio) 
       references socio_palestra (id);

    alter table certificato_medico 
       add constraint FK4qvmdsjo3bhbkpvla58ue89un 
       foreign key (socio_id) 
       references socio_palestra (id);
