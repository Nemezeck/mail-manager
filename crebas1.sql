/*==============================================================*/
/* DBMS name:      ORACLE Version 11g                           */
/* Created on:     1/19/2025 8:05:26 PM                         */
/*==============================================================*/


alter table ARCHIVOADJUNTO
   drop constraint FK_ARCHIVOA_RELATIONS_TIPOARCH;

alter table ARCHIVOADJUNTO
   drop constraint FK_ARCHIVOA_RELATIONS_MENSAJE;

alter table CONTACTO
   drop constraint FK_CONTACTO_RELATIONS_USUARIO;

alter table DESTINATARIO
   drop constraint FK_DESTINAT_RELATIONS_PAIS;

alter table DESTINATARIO
   drop constraint FK_DESTINAT_RELATIONS_CONTACTO;

alter table DESTINATARIO
   drop constraint FK_DESTINAT_RELATIONS_TIPOCOPI;

alter table DESTINATARIO
   drop constraint FK_DESTINAT_RELATIONS_MENSAJE;

alter table MENSAJE
   drop constraint FK_MENSAJE_RELATIONS_PAIS;

alter table MENSAJE
   drop constraint FK_MENSAJE_RELATIONS_MENSAJE;

alter table MENSAJE
   drop constraint FK_MENSAJE_RELATIONS_USUARIO;

alter table MENSAJE
   drop constraint FK_MENSAJE_RELATIONS_TIPOCARP;

alter table USUARIO
   drop constraint FK_USUARIO_RELATIONS_ESTADO;

alter table USUARIO
   drop constraint FK_USUARIO_RELATIONS_PAIS;

drop index RELATIONSHIP_6_FK;

drop index RELATIONSHIP_2_FK;

drop table ARCHIVOADJUNTO cascade constraints;

drop index RELATIONSHIP_12_FK;

drop table CONTACTO cascade constraints;

drop index RELATIONSHIP_14_FK;

drop index RELATIONSHIP_10_FK;

drop index RELATIONSHIP_9_FK;

drop index RELATIONSHIP_8_FK;

drop table DESTINATARIO cascade constraints;

drop table ESTADO cascade constraints;

drop index RELATIONSHIP_15_FK;

drop index RELATIONSHIP_7_FK;

drop index RELATIONSHIP_5_FK;

drop index RELATIONSHIP_4_FK;

drop table MENSAJE cascade constraints;

drop table PAIS cascade constraints;

drop table TIPOARCHIVO cascade constraints;

drop table TIPOCARPETA cascade constraints;

drop table TIPOCOPIA cascade constraints;

drop index RELATIONSHIP_3_FK;

drop index RELATIONSHIP_1_FK;

drop table USUARIO cascade constraints;

/*==============================================================*/
/* Table: ARCHIVOADJUNTO                                        */
/*==============================================================*/
create table ARCHIVOADJUNTO 
(
   CONSECARCHIVO        INTEGER              not null,
   IDTIPOARCHIVO        VARCHAR2(5)          not null,
   USUARIO              VARCHAR2(5)          not null,
   IDMENSAJE            VARCHAR2(5)          not null,
   NOMARCHIVO           VARCHAR2(30)         not null,
   constraint PK_ARCHIVOADJUNTO primary key (CONSECARCHIVO)
);

/*==============================================================*/
/* Index: RELATIONSHIP_2_FK                                     */
/*==============================================================*/
create index RELATIONSHIP_2_FK on ARCHIVOADJUNTO (
   IDTIPOARCHIVO ASC
);

/*==============================================================*/
/* Index: RELATIONSHIP_6_FK                                     */
/*==============================================================*/
create index RELATIONSHIP_6_FK on ARCHIVOADJUNTO (
   USUARIO ASC,
   IDMENSAJE ASC
);

/*==============================================================*/
/* Table: CONTACTO                                              */
/*==============================================================*/
create table CONTACTO 
(
   CONSECCONTACTO       INTEGER              not null,
   USUARIO              VARCHAR2(5)          not null,
   NOMBRECONTACTO       VARCHAR2(30),
   CORREOCONTACTO       VARCHAR2(30)         not null,
   constraint PK_CONTACTO primary key (CONSECCONTACTO)
);

/*==============================================================*/
/* Index: RELATIONSHIP_12_FK                                    */
/*==============================================================*/
create index RELATIONSHIP_12_FK on CONTACTO (
   USUARIO ASC
);

/*==============================================================*/
/* Table: DESTINATARIO                                          */
/*==============================================================*/
create table DESTINATARIO 
(
   CONSECDESTINATARIO   INTEGER              not null,
   IDTIPOCOPIA          VARCHAR2(4)          not null,
   USUARIO              VARCHAR2(5)          not null,
   IDMENSAJE            VARCHAR2(5)          not null,
   IDPAIS               VARCHAR2(5)          not null,
   CONSECCONTACTO       INTEGER              not null,
   constraint PK_DESTINATARIO primary key (CONSECDESTINATARIO)
);

/*==============================================================*/
/* Index: RELATIONSHIP_8_FK                                     */
/*==============================================================*/
create index RELATIONSHIP_8_FK on DESTINATARIO (
   IDTIPOCOPIA ASC
);

/*==============================================================*/
/* Index: RELATIONSHIP_9_FK                                     */
/*==============================================================*/
create index RELATIONSHIP_9_FK on DESTINATARIO (
   USUARIO ASC,
   IDMENSAJE ASC
);

/*==============================================================*/
/* Index: RELATIONSHIP_10_FK                                    */
/*==============================================================*/
create index RELATIONSHIP_10_FK on DESTINATARIO (
   IDPAIS ASC
);

/*==============================================================*/
/* Index: RELATIONSHIP_14_FK                                    */
/*==============================================================*/
create index RELATIONSHIP_14_FK on DESTINATARIO (
   CONSECCONTACTO ASC
);

/*==============================================================*/
/* Table: ESTADO                                                */
/*==============================================================*/
create table ESTADO 
(
   IDESTADO             VARCHAR2(5)          not null,
   NOMBREESTADO         VARCHAR2(30)         not null,
   constraint PK_ESTADO primary key (IDESTADO)
);

/*==============================================================*/
/* Table: MENSAJE                                               */
/*==============================================================*/
create table MENSAJE 
(
   USUARIO              VARCHAR2(5)          not null,
   IDMENSAJE            VARCHAR2(5)          not null,
   MEN_USUARIO          VARCHAR2(5),
   MEN_IDMENSAJE        VARCHAR2(5),
   IDTIPOCARPETA        VARCHAR2(3)          not null,
   IDPAIS               VARCHAR2(5)          not null,
   ASUNTO               VARCHAR2(255)        not null,
   CUERPOMENSAJE        VARCHAR2(255)        not null,
   FECHAACCION          DATE                 not null,
   HORAACCION           DATE                 not null,
   constraint PK_MENSAJE primary key (USUARIO, IDMENSAJE)
);

/*==============================================================*/
/* Index: RELATIONSHIP_4_FK                                     */
/*==============================================================*/
create index RELATIONSHIP_4_FK on MENSAJE (
   MEN_USUARIO ASC,
   MEN_IDMENSAJE ASC
);

/*==============================================================*/
/* Index: RELATIONSHIP_5_FK                                     */
/*==============================================================*/
create index RELATIONSHIP_5_FK on MENSAJE (
   USUARIO ASC
);

/*==============================================================*/
/* Index: RELATIONSHIP_7_FK                                     */
/*==============================================================*/
create index RELATIONSHIP_7_FK on MENSAJE (
   IDTIPOCARPETA ASC
);

/*==============================================================*/
/* Index: RELATIONSHIP_15_FK                                    */
/*==============================================================*/
create index RELATIONSHIP_15_FK on MENSAJE (
   IDPAIS ASC
);

/*==============================================================*/
/* Table: PAIS                                                  */
/*==============================================================*/
create table PAIS 
(
   IDPAIS               VARCHAR2(5)          not null,
   NOMBREPAIS           VARCHAR2(30)         not null,
   DOMINIO              VARCHAR2(3)          not null,
   constraint PK_PAIS primary key (IDPAIS)
);

/*==============================================================*/
/* Table: TIPOARCHIVO                                           */
/*==============================================================*/
create table TIPOARCHIVO 
(
   IDTIPOARCHIVO        VARCHAR2(5)          not null,
   DESCTIPOARCHIVO      VARCHAR2(30)         not null,
   constraint PK_TIPOARCHIVO primary key (IDTIPOARCHIVO)
);

/*==============================================================*/
/* Table: TIPOCARPETA                                           */
/*==============================================================*/
create table TIPOCARPETA 
(
   IDTIPOCARPETA        VARCHAR2(3)          not null,
   DESCTIPOCARPETA      VARCHAR2(30)         not null,
   constraint PK_TIPOCARPETA primary key (IDTIPOCARPETA)
);

/*==============================================================*/
/* Table: TIPOCOPIA                                             */
/*==============================================================*/
create table TIPOCOPIA 
(
   IDTIPOCOPIA          VARCHAR2(4)          not null,
   DESCTIPOCOPIA        VARCHAR2(30)         not null,
   constraint PK_TIPOCOPIA primary key (IDTIPOCOPIA)
);

/*==============================================================*/
/* Table: USUARIO                                               */
/*==============================================================*/
create table USUARIO 
(
   USUARIO              VARCHAR2(5)          not null,
   IDESTADO             VARCHAR2(5)          not null,
   IDPAIS               VARCHAR2(5)          not null,
   NOMBRE               VARCHAR2(30)         not null,
   APELLIDO             VARCHAR2(30)         not null,
   FECHANACIMIENTO      DATE                 not null,
   FECHACREACION        DATE                 not null,
   CORREOALTERNO        VARCHAR2(30)         not null,
   CELULAR              VARCHAR2(12)         not null,
   constraint PK_USUARIO primary key (USUARIO)
);

/*==============================================================*/
/* Index: RELATIONSHIP_1_FK                                     */
/*==============================================================*/
create index RELATIONSHIP_1_FK on USUARIO (
   IDESTADO ASC
);

/*==============================================================*/
/* Index: RELATIONSHIP_3_FK                                     */
/*==============================================================*/
create index RELATIONSHIP_3_FK on USUARIO (
   IDPAIS ASC
);

alter table ARCHIVOADJUNTO
   add constraint FK_ARCHIVOA_RELATIONS_TIPOARCH foreign key (IDTIPOARCHIVO)
      references TIPOARCHIVO (IDTIPOARCHIVO);

alter table ARCHIVOADJUNTO
   add constraint FK_ARCHIVOA_RELATIONS_MENSAJE foreign key (USUARIO, IDMENSAJE)
      references MENSAJE (USUARIO, IDMENSAJE);

alter table CONTACTO
   add constraint FK_CONTACTO_RELATIONS_USUARIO foreign key (USUARIO)
      references USUARIO (USUARIO);

alter table DESTINATARIO
   add constraint FK_DESTINAT_RELATIONS_PAIS foreign key (IDPAIS)
      references PAIS (IDPAIS);

alter table DESTINATARIO
   add constraint FK_DESTINAT_RELATIONS_CONTACTO foreign key (CONSECCONTACTO)
      references CONTACTO (CONSECCONTACTO);

alter table DESTINATARIO
   add constraint FK_DESTINAT_RELATIONS_TIPOCOPI foreign key (IDTIPOCOPIA)
      references TIPOCOPIA (IDTIPOCOPIA);

alter table DESTINATARIO
   add constraint FK_DESTINAT_RELATIONS_MENSAJE foreign key (USUARIO, IDMENSAJE)
      references MENSAJE (USUARIO, IDMENSAJE);

alter table MENSAJE
   add constraint FK_MENSAJE_RELATIONS_PAIS foreign key (IDPAIS)
      references PAIS (IDPAIS);

alter table MENSAJE
   add constraint FK_MENSAJE_RELATIONS_MENSAJE foreign key (MEN_USUARIO, MEN_IDMENSAJE)
      references MENSAJE (USUARIO, IDMENSAJE);

alter table MENSAJE
   add constraint FK_MENSAJE_RELATIONS_USUARIO foreign key (USUARIO)
      references USUARIO (USUARIO);

alter table MENSAJE
   add constraint FK_MENSAJE_RELATIONS_TIPOCARP foreign key (IDTIPOCARPETA)
      references TIPOCARPETA (IDTIPOCARPETA);

alter table USUARIO
   add constraint FK_USUARIO_RELATIONS_ESTADO foreign key (IDESTADO)
      references ESTADO (IDESTADO);

alter table USUARIO
   add constraint FK_USUARIO_RELATIONS_PAIS foreign key (IDPAIS)
      references PAIS (IDPAIS);

