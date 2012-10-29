declare
  spot_id number;
  spot photospot%rowtype;
begin
  -- Can add photo spots
  spot_id := photo_spots.add('Teletorn', 'Tallinn TV Tower', 59.47111, 24.8875);
  dbunit.assert_not_null(spot_id);

  select * into spot from photospot where id = spot_id;
  dbunit.assert_equals('Teletorn', spot.name);
  dbunit.assert_equals('Tallinn TV Tower', spot.description);
  dbunit.assert_equals(59.47111, spot.latitude);
  dbunit.assert_equals(24.8875, spot.longitude);

  -- Cannot insert illegal latitude
  begin
    spot_id := photo_spots.add('A', 'B', 91.0, 24.0);
    dbunit.expect_exception;
  exception
    when photo_spots.illegal_coordinates then null;
  end;

  -- Cannot insert illegal longitude
  begin
    spot_id := photo_spots.add('A', 'B', 59.0, -181);
    dbunit.expect_exception;
  exception
    when photo_spots.illegal_coordinates then null;
  end;

  rollback;
end;
