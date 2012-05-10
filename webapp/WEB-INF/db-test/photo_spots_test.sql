declare
  spot_id number;
  spot photospot%rowtype;
begin
  -- Can add photo spots
  spot_id := photo_spots.add('Teletorn', 'Tallinn TV Tower', 59.47111, 24.8875);
  dbunit.assert_not_null(spot_id);

  dbunit.assert_equals('Teletorn', spot.name);
  dbunit.assert_equals('Tallinn TV Tower', spot.description);
  dbunit.assert_equals(59.47111, spot.latitude);
  dbunit.assert_equals(24.8875, spot.longitude);
  rollback;
end;