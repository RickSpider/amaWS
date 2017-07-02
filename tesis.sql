-- DOMAIN: public.d_authority

-- DROP DOMAIN public.d_authority;

CREATE DOMAIN d_authority
    AS character varying(50);

ALTER DOMAIN d_authority OWNER TO postgres;

-- DOMAIN: public.d_coordenada

-- DROP DOMAIN public.d_coordenada;

CREATE DOMAIN d_coordenada
    AS character varying(20);

ALTER DOMAIN d_coordenada OWNER TO postgres;

-- DOMAIN: public.d_enabled

-- DROP DOMAIN public.d_enabled;

CREATE DOMAIN d_enabled
    AS boolean;

ALTER DOMAIN d_enabled OWNER TO postgres;


-- DOMAIN: public.d_password

-- DROP DOMAIN public.d_password;

CREATE DOMAIN d_password
    AS character varying(50);

ALTER DOMAIN d_password OWNER TO postgres;

-- DOMAIN: public.d_radio

-- DROP DOMAIN public.d_radio;

CREATE DOMAIN d_radio
    AS integer;

ALTER DOMAIN d_radio OWNER TO postgres;


-- DOMAIN: public.d_username

-- DROP DOMAIN public.d_username;

CREATE DOMAIN d_username
    AS character varying(80);

ALTER DOMAIN d_username OWNER TO postgres;


-- Table: public.datos

-- DROP TABLE public.datos;

CREATE TABLE public.datos
(
    fecha timestamp without time zone NOT NULL,
    data text COLLATE pg_catalog."default" NOT NULL,
    notificar boolean NOT NULL,
    centroide text COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT datos_pkey PRIMARY KEY (fecha)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.datos
    OWNER to postgres;

-- Table: public.users

-- DROP TABLE public.users;

CREATE TABLE public.users
(
    username d_username COLLATE pg_catalog."default" NOT NULL,
    password d_password COLLATE pg_catalog."default" NOT NULL,
    enabled d_enabled NOT NULL,
    alertar boolean NOT NULL,
    CONSTRAINT pk_users PRIMARY KEY (username)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.users
    OWNER to postgres;

-- Index: users_pk

-- DROP INDEX public.users_pk;

CREATE UNIQUE INDEX users_pk
    ON public.users USING btree
    (username COLLATE pg_catalog."default")
    TABLESPACE pg_default;

-- Table: public.authorities

-- DROP TABLE public.authorities;

CREATE TABLE public.authorities
(
    username d_username COLLATE pg_catalog."default" NOT NULL,
    authority d_authority COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT fk_authorit_relations_users FOREIGN KEY (username)
        REFERENCES public.users (username) MATCH SIMPLE
        ON UPDATE RESTRICT
        ON DELETE RESTRICT
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.authorities
    OWNER to postgres;

-- Index: relationship_2_fk

-- DROP INDEX public.relationship_2_fk;

CREATE INDEX relationship_2_fk
    ON public.authorities USING btree
    (username COLLATE pg_catalog."default")
    TABLESPACE pg_default;