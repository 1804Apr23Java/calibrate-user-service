DELETE FROM ACCOUNT;

ALTER SEQUENCE SQ_ACCOUNT_PK RESTART WITH 1;

INSERT INTO ACCOUNT(ACCOUNT_ID,ISADMIN,EMAIL,PASSWORD, FIRSTNAME, LASTNAME, ISACTIVE) VALUES (SQ_ACCOUNT_PK.NEXTVAL,true,'collinmeaney375@gmail.com','password', 'collin', 'M', true);
INSERT INTO ACCOUNT(ACCOUNT_ID,ISADMIN,EMAIL,PASSWORD, FIRSTNAME, LASTNAME, ISACTIVE) VALUES (SQ_ACCOUNT_PK.NEXTVAL,true,'shivam.aashir@gmail.com','password', 'shivam', 'A', true);

