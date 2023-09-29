--
-- PostgreSQL database dump
--
CREATE USER koziolek with password 'koziolek';
GRANT ALL PRIVILEGES ON DATABASE postgres to koziolek;

-- Dumped from database version 9.2.0
-- Dumped by pg_dump version 9.2.0
-- Started on 2013-05-19 16:05:10 BST

SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- TOC entry 7 (class 2615 OID 32769)
-- Name: cd; Type: SCHEMA; Schema: -; Owner: -
--

SET default_tablespace = '';
SET default_with_oids = false;

CREATE TABLE book_id (
    id SERIAL NOT NULL PRIMARY KEY,
    name character varying(100) NOT NULL
);

CREATE TABLE book_uuid (
    id uuid NOT NULL PRIMARY KEY,
    name character varying(100) NOT NULL
);

create or replace FUNCTION tracked_insert(stmt text)
    RETURNS interval
AS
$$
DECLARE
    start timestamptz := clock_timestamp();
    finish interval;
BEGIN
    raise notice 'Start execution';
    start := clock_timestamp();
    execute stmt;
    finish := clock_timestamp() - start;
    raise notice 'time spent=%', finish;
    return finish;
END;
$$ LANGUAGE plpgsql;

create or replace FUNCTION prepare_insert(tbl text, id_gen text, c int)
    RETURNS TEXT
AS
$$
DECLARE
    sql text        := 'insert into ' || tbl || '(id, name) values';
    val text        := '(' || id_gen || ', ''name'')';
    t   timestamptz := clock_timestamp();
BEGIN
    for r in 1..(c - 1)
        loop
            sql := sql || val || ',';
        end loop;
    sql := sql || val || ';';
    raise notice 'SQL ready in: %', clock_timestamp() - t;
    return sql;
END;
$$ LANGUAGE plpgsql;

GRANT ALL PRIVILEGES ON ALL TABLES IN SCHEMA public TO koziolek;
GRANT ALL PRIVILEGES ON ALL SEQUENCES IN SCHEMA public TO koziolek;
GRANT ALL PRIVILEGES ON ALL FUNCTIONS IN SCHEMA public TO koziolek;
