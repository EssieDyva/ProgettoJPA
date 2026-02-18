
    alter table abbonamento_socio 
       drop 
       foreign key FK22uaw7830fw71duo88vg10i3m;

    alter table certificato_medico 
       drop 
       foreign key FK4qvmdsjo3bhbkpvla58ue89un;

    drop table if exists abbonamento_socio;

    drop table if exists certificato_medico;

    drop table if exists socio_palestra;
