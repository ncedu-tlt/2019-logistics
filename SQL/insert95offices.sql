CREATE OR REPLACE FUNCTION public.insert95offices()
    RETURNS void
    LANGUAGE 'plpgsql'
	VOLATILE
AS $$
DECLARE
	i integer;
	j integer;
BEGIN
	j := 19;
	FOR i IN 5..100 LOOP
	    INSERT INTO offices(id, town_id, phone) VALUES
		(j, i, trunc(random()*899999)+100000);
		j := j+1;
	END LOOP;
END;
$$;
