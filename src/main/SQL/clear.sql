CREATE OR REPLACE FUNCTION clear() RETURNS void
	LANGUAGE plpgsql
	AS $$
BEGIN
	DELETE FROM roads;
	DELETE FROM offerings;
	DELETE FROM offices;
	DELETE FROM products;
	DELETE FROM towns;
	ALTER SEQUENCE towns_ai_sequence RESTART WITH 1;
	ALTER SEQUENCE products_ai_sequence RESTART WITH 1;
	ALTER SEQUENCE offices_ai_sequence RESTART WITH 1;
END; $$