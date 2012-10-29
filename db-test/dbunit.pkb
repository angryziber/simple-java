create or replace package body dbunit
is
    procedure fail(message varchar2) is
    begin
        raise_application_error(-20000, 'Assertion failed' || chr(10) || message);
    end;

    procedure fail(expected varchar2, actual varchar2) is
    begin
        fail('Expected: ' || expected || chr(10) || 'Actual:   ' || actual);
    end;

    procedure expect_exception is
    begin
      fail('Exception expected');
    end;

    procedure assert_equals(expected number, actual number) is
    begin
        if expected is null or actual is null or expected != actual then
            fail(expected, actual);
        end if;
    end;

    procedure assert_equals(expected varchar2, actual varchar2) is
    begin
        if expected is null or actual is null or expected != actual then
            fail(expected, actual);
        end if;
    end;

    procedure assert_null(actual varchar2) is
    begin
        if actual is not null then
            fail(actual || ' is not null');
        end if;
    end;

    procedure assert_not_null(actual varchar2) is
    begin
        if actual is null then
            fail('argument is null');
        end if;
    end;
end;
