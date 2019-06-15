CREATE TABLE public.towns (
    id integer NOT NULL,
    name character varying NOT NULL
);

CREATE TABLE public.products (
    id integer NOT NULL,
    name character varying
);

CREATE TABLE public.offices (
    id integer NOT NULL,
    phone integer NOT NULL,
    town_id integer NOT NULL
);

CREATE TABLE public.offerings (
    office_id integer NOT NULL,
    product_id integer NOT NULL,
    price double precision NOT NULL
);

CREATE TABLE public.roads (
    left_town_id integer NOT NULL,
    right_town_id integer NOT NULL,
    distance double precision NOT NULL
);

ALTER TABLE ONLY public.offerings
    ADD CONSTRAINT offerings_pkey PRIMARY KEY (product_id, office_id);

ALTER TABLE ONLY public.offices
    ADD CONSTRAINT offices_pkey PRIMARY KEY (id);

ALTER TABLE ONLY public.products
    ADD CONSTRAINT product_name UNIQUE (name);

ALTER TABLE ONLY public.products
    ADD CONSTRAINT products_pkey PRIMARY KEY (id);

ALTER TABLE ONLY public.roads
    ADD CONSTRAINT roads_pkey PRIMARY KEY (left_town_id, right_town_id);

ALTER TABLE ONLY public.towns
    ADD CONSTRAINT towns_name_key UNIQUE (name);

ALTER TABLE ONLY public.towns
    ADD CONSTRAINT towns_pkey PRIMARY KEY (id);

CREATE SEQUENCE public.towns_ai_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE SEQUENCE public.products_ai_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
    
CREATE SEQUENCE public.offices_ai_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE FUNCTION public.towns_ai_trigger() RETURNS trigger
    LANGUAGE plpgsql
    AS $$DECLARE
	possible_id towns.id%TYPE;
BEGIN
	IF NEW.id IS NULL THEN
		LOOP
			possible_id := nextval('towns_ai_sequence');
			EXIT WHEN NOT EXISTS (SELECT 1 FROM towns WHERE towns.id = possible_id);
		END LOOP;
		NEW.id := possible_id;
	END IF;
	RETURN NEW;
END;
$$;

CREATE FUNCTION public.products_ai_trigger() RETURNS trigger
    LANGUAGE plpgsql
    AS $$
DECLARE
	possible_id towns.id%TYPE;
BEGIN
	IF NEW.id IS NULL THEN
		LOOP
			possible_id := nextval('products_ai_sequence');
			EXIT WHEN NOT EXISTS (SELECT 1 FROM products WHERE products.id = possible_id);
		END LOOP;
		NEW.id := possible_id;
	END IF;
	RETURN NEW;
END;
$$;

CREATE FUNCTION public.offices_ai_trigger() RETURNS trigger
    LANGUAGE plpgsql
    AS $$
DECLARE
	possible_id towns.id%TYPE;
BEGIN
	IF NEW.id IS NULL THEN
		LOOP
			possible_id := nextval('offices_ai_sequence');
			EXIT WHEN NOT EXISTS (SELECT 1 FROM offices WHERE offices.id = possible_id);
		END LOOP;
		NEW.id := possible_id;
	END IF;
	RETURN NEW;
END;
$$;

CREATE FUNCTION public.roads_ai_trigger() RETURNS trigger
    LANGUAGE plpgsql
    AS $$
DECLARE
    buf roads.left_town_id%TYPE;
BEGIN
    IF NEW.left_town_id > NEW.right_town_id THEN
        buf := NEW.left_town_id;
        NEW.left_town_id := NEW.right_town_id;
        NEW.right_town_id := buf;
    END IF;
    IF NEW.left_town_id = NEW.right_town_id THEN
        RAISE EXCEPTION 'Can''t insert two same towns!';
    END IF;
    RETURN NEW;
END; 
$$;

CREATE TRIGGER towns_ai_trigger_on_insert BEFORE INSERT ON public.towns FOR EACH ROW EXECUTE PROCEDURE public.towns_ai_trigger();
CREATE TRIGGER products_ai_trigger_on_insert BEFORE INSERT ON public.products FOR EACH ROW EXECUTE PROCEDURE public.products_ai_trigger();
CREATE TRIGGER offices_ai_trigger_on_insert BEFORE INSERT ON public.offices FOR EACH ROW EXECUTE PROCEDURE public.offices_ai_trigger();
CREATE TRIGGER roads_ai_trigger_on_insert BEFORE INSERT ON public.roads FOR EACH ROW EXECUTE PROCEDURE public.roads_ai_trigger();

ALTER TABLE ONLY public.offerings
    ADD CONSTRAINT office_id_in_offerings FOREIGN KEY (office_id) REFERENCES public.offices(id);

ALTER TABLE ONLY public.offerings
    ADD CONSTRAINT product_id_in_offerings FOREIGN KEY (product_id) REFERENCES public.products(id);

ALTER TABLE ONLY public.roads
    ADD CONSTRAINT right_town_id_to_town_id FOREIGN KEY (right_town_id) REFERENCES public.towns(id);

ALTER TABLE ONLY public.roads
    ADD CONSTRAINT left_town_id_to_town_id FOREIGN KEY (left_town_id) REFERENCES public.towns(id);

ALTER TABLE ONLY public.offices
    ADD CONSTRAINT town_id_in_offices FOREIGN KEY (town_id) REFERENCES public.towns(id);
