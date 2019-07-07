CREATE OR REPLACE FUNCTION insert100towns() returns void as
$$
declare
 i integer;
begin
	for i in 1..100 loop
	INSERT INTO towns(name) VALUES (random_string(6));
	end loop;
	
	for i in 1..80 loop
	INSERT INTO roads(left_town_id, right_town_id, distance) VALUES
		(trunc(random()*99+1), trunc(random()*99+1), trunc(random()*450+20));
	end loop;
end;
$$ language plpgsql;