CREATE DATABASE "openWeather"
    WITH
    OWNER = postgres
    ENCODING = 'UTF8'
    LC_COLLATE = 'Italian_Italy.1252'
    LC_CTYPE = 'Italian_Italy.1252'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1;

CREATE SCHEMA public
    AUTHORIZATION postgres;

COMMENT ON SCHEMA public
    IS 'Public Schema';

GRANT ALL ON SCHEMA public TO PUBLIC;

GRANT ALL ON SCHEMA public TO postgres;

CREATE TABLE public.cities
(
    id   bigint                                              NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 9223372036854775807 CACHE 1 ),
    name character varying(250) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT cities_pkey PRIMARY KEY (id)
)
    TABLESPACE pg_default;

ALTER TABLE public.cities
    OWNER to postgres;

COMMENT ON TABLE public.cities
    IS 'Holds cities information';

CREATE TABLE public.weather_information
(
    id    bigint                                              NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 9223372036854775807 CACHE 1 ),
    value double precision                                    NOT NULL,
    type  character varying(250) COLLATE pg_catalog."default" NOT NULL,
    city  bigint                                              NOT NULL,
    CONSTRAINT "weatherInformation_pkey" PRIMARY KEY (id),
    CONSTRAINT "FK_information2city" FOREIGN KEY (city)
        REFERENCES public.cities (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
    TABLESPACE pg_default;

ALTER TABLE public.weather_information
    OWNER to postgres;

COMMENT ON TABLE public.weather_information
    IS 'Holds weather information related to cities';
