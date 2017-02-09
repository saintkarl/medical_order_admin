set search_path to MoPlatform;

CREATE SEQUENCE invoice_no_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;


-- SELECT setval('invoice_no_seq', cast((select max(substr(code,4, 10)) from giftexchangerequest) as integer));



