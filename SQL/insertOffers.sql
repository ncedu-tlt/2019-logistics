CREATE OR REPLACE FUNCTION public.insertoffers(
	)
    RETURNS void
    LANGUAGE 'plpgsql'
    VOLATILE 
AS $$
BEGIN
DECLARE
	i integer;
BEGIN
	FOR i in 18..114 LOOP
		INSERT INTO offerings(office_id, product_id, price) VALUES
		(i, 10, trunc(random()*100+100));
	END LOOP;
END;
END;
$$;