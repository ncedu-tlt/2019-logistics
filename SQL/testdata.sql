CREATE OR REPLACE FUNCTION public.initTestData() RETURNS void
	LANGUAGE plpgsql
	AS $$
DECLARE 
	i INTEGER;
	row_town towns%rowtype;
	left_town_row towns%rowtype;
	right_town_row towns%rowtype;
	row_product products%rowtype;
	row_office offices%rowtype;
BEGIN
	INSERT INTO products(name) VALUES
		('Cheese'), ('Milk'), ('Feijoa'); 
	INSERT INTO towns (name) VALUES
		('Togliatti'), ('Samara'), ('Saratov');
		
	FOR row_town IN SELECT * FROM towns LOOP
		FOR i IN 1..3 LOOP
			INSERT INTO offices(phone, town_id) VALUES
			(trunc(random()*899999+100000), row_town.id);
		END LOOP;
	END LOOP;
	
	FOR row_product IN SELECT * FROM products LOOP
		FOR row_office IN SELECT * FROM offices LOOP
			INSERT INTO offerings (office_id, product_id, price) VALUES
				(row_office.id, row_product.id, trunc(random()*80+70));
		END LOOP;
	END LOOP;


END; $$;