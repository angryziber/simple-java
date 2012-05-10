create or replace package body photo_spots is
  function add(name varchar2, description varchar2, lat float, lon float) return number is
    id number;
  begin
    if lat < -90 or lat > 90 or lon < -180 or lon > 180 then
      raise illegal_coordinates;
    end if;

    select hibernate_sequence.nextval into id from dual;
    insert into photospot (id, name, description, latitude, longitude)
      values (id, name, description, lat, lon);
    return id;
  end;
end;