DROP FUNCTION InsertByLatLng (int ,varchar(100) ,float ,float ,float ,float, varchar(20));
DROP FUNCTION InsertOperationalData (int ,int , float ,int , int , int, varchar(20), int);
DROP FUNCTION PortlandData1();
DROP FUNCTION PortlandData2();
DROP FUNCTION PortlandData3();
DROP FUNCTION PortlandData4(integer,integer);
DROP FUNCTION PortlandData5();
DROP FUNCTION InsertPropertyValue (int , varchar(20), float, float);
DROP FUNCTION InsertLightPropertyCityValue();
DROP FUNCTION InsertLightTimeZoneValue();
DROP FUNCTION UpdateSmartpointRniId (int, int, int);
--Remove smartpoint with Tenant = 2 that were associated to group with Tenant = 1
delete from smartpoint_grouping where smartpoint_id in (3011,3012,3013,3014);
delete from smartpoint_tag where smartpoint_id in (3011,3012,3013,3014);