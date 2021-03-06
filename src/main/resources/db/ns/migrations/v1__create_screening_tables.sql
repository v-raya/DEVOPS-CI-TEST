/* ---------------------------------------------------- */
/*  Generated by Enterprise Architect Version 12.1 		*/
/*  Created On : 18-Oct-2016 1:24:40 PM 				*/
/*  DBMS       : PostgreSQL 						*/
/* ---------------------------------------------------- */

/* Drop Sequences for Autonumber Columns */

DROP SEQUENCE IF EXISTS public.seq_address_id
;

DROP SEQUENCE IF EXISTS public.seq_hotline_contact_id
;

DROP SEQUENCE IF EXISTS public.seq_person_id
;

/* Drop Tables */

DROP TABLE IF EXISTS public.address CASCADE
;

DROP TABLE IF EXISTS public.hotline_contact CASCADE
;

DROP TABLE IF EXISTS public.person CASCADE
;

/* Create Tables */

CREATE TABLE public.address
(
	address_id integer NOT NULL   DEFAULT NEXTVAL(('seq_address_id'::text)::regclass),    -- Unique primary key of the address table.
	street_address varchar(50) NULL,    -- Street address, including street number and street name. 
	city varchar(50) NULL,    -- Full name of the city.
	state varchar(50) NULL,    -- Full state name or  code corresponding to state.
	zip varchar(9) NULL,    -- The zip code for the address.  5 digit or 9 digit (i.e., zip+4)
	create_user_id integer NULL,    -- The ID of the user that created the record.
	create_datetime timestamp NULL,    -- The date and time that the user created the record.
	update_user_id integer NULL,    -- The ID of the user which made the last update to the record.
	update_datetime timestamp NULL    -- The date and time that the user updated the record.
)
;

CREATE TABLE public.hotline_contact
(
	hotline_contact_id integer NOT NULL   DEFAULT NEXTVAL(('seq_hotline_contact_id'::text)::regclass),    -- Unique, sequential primary key of the table.
	hotline_contact_reference varchar(50) NULL,    -- Internally generated and distinct reference to this hotline contact event.
	hotline_contact_name varchar(50) NULL,    -- User supplied name for the hotline contact event. 
	screening_start_datetime timestamp NULL,    -- Date and time that active screening processing was initiated.
	screening_end_datetime timestamp NULL,    -- Date and time that active screening processing was completed (e.g., may be point-in-time that a proposed screening response is determined).
	incident_datetime timestamp NULL,    -- The date and time that either the incident or allegation allegedly began to occur or occurred or the date and time that the communication (e.g., question, request for services) was initiated.   
	hotline_communication_method varchar(50) NULL,    -- Communication method that conveyed the communication (e.g., telephone, fax, etc.).
	screening_report_narrative varchar(1500) NULL,    -- Freeform text of the details of the communication reported (e.g., immediately answerable question, effort that requires routing, request for service, apparent allegation of abuse or neglect)�
	location_type varchar(50) NULL,    -- List of values with potential locations where the incident occurred, if applicable.  CC has a great list of locations.
	screening_result varchar(50) NULL,    -- Drop down of proposed screening result/decision.   See Screening Result values used in legacy SYSTEM_CODE_TABLE.
	hotline_contact_county varchar(50) NULL,    -- County within which the inquiry or incident originated.
	hotline_contact_participant_array varchar(100) NULL,    -- A string of person ids, each separated by a space, of participants to the hotline contact / screening event.  Each person id parsed from the string and converted to an integer is a foreign key to the person table.  
	contact_address_id integer NULL,    -- The address id associated with either the inquiry (e.g., contact) or the or incident (e.g., referral).  Foreign key to address table. 
	create_user_id integer NULL,    -- The ID of the user that created the record.
	create_datetime timestamp NULL,    -- The date and time that the user created the record.
	update_user_id integer NULL,    -- The ID of the user which made the last update to the record.
	update_datetime timestamp NULL    -- The date and time that the user updated the record.
)
;

CREATE TABLE public.person
(
	person_id integer NOT NULL   DEFAULT NEXTVAL(('seq_person_id'::text)::regclass),    -- Unique, sequential primary key of the table.
	first_name varchar(50) NULL,    -- Persons first name.
	last_name varchar(50) NULL,    -- Persons last name.
	gender varchar(10) NULL,    -- Gender of Person.
	date_of_birth date NULL,    -- Persons date of birth.
	ssn varchar(9) NULL,    -- Social Security Number of the person.
	person_address_id integer NULL,    -- A link to the single address associated with a person.   Added 10/14/16
	create_user_id integer NULL,    -- The ID of the user that created the record.
	create_datetime timestamp NULL,    -- The date and time that the user created the record.
	update_user_id integer NULL,    -- The ID of the user which made the last update to the record.
	update_datetime timestamp NULL    -- The date and time that the user updated the record.
)
;

/* Create Primary Keys, Indexes, Uniques, Checks */

ALTER TABLE public.address ADD CONSTRAINT PK_Person_Address
	PRIMARY KEY (address_id)
;

ALTER TABLE public.hotline_contact ADD CONSTRAINT PK_Hotline_Contact
	PRIMARY KEY (hotline_contact_id)
;

CREATE INDEX IXFK_hotline_contact_address ON public.hotline_contact (contact_address_id ASC)
;

ALTER TABLE public.person ADD CONSTRAINT PK_Person
	PRIMARY KEY (person_id)
;

CREATE INDEX IXFK_person_address ON public.person (person_address_id ASC)
;

/* Create Foreign Key Constraints */

ALTER TABLE public.hotline_contact ADD CONSTRAINT FK_hotline_contact_address
	FOREIGN KEY (contact_address_id) REFERENCES public.address (address_id) ON DELETE No Action ON UPDATE No Action
;

ALTER TABLE public.person ADD CONSTRAINT FK_person_address
	FOREIGN KEY (person_address_id) REFERENCES public.address (address_id) ON DELETE No Action ON UPDATE No Action
;

/* Create Table Comments, Sequences for Autonumber Columns */

COMMENT ON TABLE public.address
	IS 'A physical location (e.g., where a incident occurred, a person resides a service can be obtained, etc).'
;

COMMENT ON COLUMN public.address.address_id
	IS 'Unique primary key of the address table.'
;

COMMENT ON COLUMN public.address.street_address
	IS 'Street address, including street number and street name. '
;

COMMENT ON COLUMN public.address.city
	IS 'Full name of the city.'
;

COMMENT ON COLUMN public.address.state
	IS 'Full state name or  code corresponding to state.'
;

COMMENT ON COLUMN public.address.zip
	IS 'The zip code for the address.  5 digit or 9 digit (i.e., zip+4)'
;

COMMENT ON COLUMN public.address.create_user_id
	IS 'The ID of the user that created the record.'
;

COMMENT ON COLUMN public.address.create_datetime
	IS 'The date and time that the user created the record.'
;

COMMENT ON COLUMN public.address.update_user_id
	IS 'The ID of the user which made the last update to the record.'
;

COMMENT ON COLUMN public.address.update_datetime
	IS 'The date and time that the user updated the record.'
;

CREATE SEQUENCE public.seq_address_id INCREMENT 1 START 1
;

COMMENT ON TABLE public.hotline_contact
	IS 'Information associated with the screening of either a static (e.g., email, fax, SCAR, voice mail) or interactive (e.g., telephone call, in person) communication regarding a referral for service.  The communication reported directly to a CWS county agency�s hotline or central receiving unit may consist of concerns regarding abuse (e.g., physical, emotional, sexual), neglect (e.g., general or severe), or exploitation as well as inquiry calls for resource information.  Staff screen and assess the hotline contact to determine whether county staff can address the inquiry (e.g., office hours), route the inquiry to county services (e.g., food insecure) or whether a reported incident requires a response, and what type.  Hotline contacts determined to be possible instances of Child Abuse Neglect or Exploitation (CANE) are processed as a Referral.  '
;

COMMENT ON COLUMN public.hotline_contact.hotline_contact_id
	IS 'Unique, sequential primary key of the table.'
;

COMMENT ON COLUMN public.hotline_contact.hotline_contact_reference
	IS 'Internally generated and distinct reference to this hotline contact event.'
;

COMMENT ON COLUMN public.hotline_contact.hotline_contact_name
	IS 'User supplied name for the hotline contact event. '
;

COMMENT ON COLUMN public.hotline_contact.screening_start_datetime
	IS 'Date and time that active screening processing was initiated.'
;

COMMENT ON COLUMN public.hotline_contact.screening_end_datetime
	IS 'Date and time that active screening processing was completed (e.g., may be point-in-time that a proposed screening response is determined).'
;

COMMENT ON COLUMN public.hotline_contact.incident_datetime
	IS 'The date and time that either the incident or allegation allegedly began to occur or occurred or the date and time that the communication (e.g., question, request for services) was initiated.   '
;

COMMENT ON COLUMN public.hotline_contact.hotline_communication_method
	IS 'Communication method that conveyed the communication (e.g., telephone, fax, etc.).'
;

COMMENT ON COLUMN public.hotline_contact.screening_report_narrative
	IS 'Freeform text of the details of the communication reported (e.g., immediately answerable question, effort that requires routing, request for service, apparent allegation of abuse or neglect)�'
;

COMMENT ON COLUMN public.hotline_contact.location_type
	IS 'List of values with potential locations where the incident occurred, if applicable.  CC has a great list of locations.'
;

COMMENT ON COLUMN public.hotline_contact.screening_result
	IS 'Drop down of proposed screening result/decision.   See Screening Result values used in legacy SYSTEM_CODE_TABLE.'
;

COMMENT ON COLUMN public.hotline_contact.hotline_contact_county
	IS 'County within which the inquiry or incident originated.'
;

COMMENT ON COLUMN public.hotline_contact.hotline_contact_participant_array
	IS 'A string of person ids, each separated by a space, of participants to the hotline contact / screening event.  Each person id parsed from the string and converted to an integer is a foreign key to the person table.  '
;

COMMENT ON COLUMN public.hotline_contact.contact_address_id
	IS 'The address id associated with either the inquiry (e.g., contact) or the or incident (e.g., referral).  Foreign key to address table. '
;

COMMENT ON COLUMN public.hotline_contact.create_user_id
	IS 'The ID of the user that created the record.'
;

COMMENT ON COLUMN public.hotline_contact.create_datetime
	IS 'The date and time that the user created the record.'
;

COMMENT ON COLUMN public.hotline_contact.update_user_id
	IS 'The ID of the user which made the last update to the record.'
;

COMMENT ON COLUMN public.hotline_contact.update_datetime
	IS 'The date and time that the user updated the record.'
;

CREATE SEQUENCE public.seq_hotline_contact_id INCREMENT 1 START 1
;

COMMENT ON COLUMN public.person.person_id
	IS 'Unique, sequential primary key of the table.'
;

COMMENT ON COLUMN public.person.first_name
	IS 'Persons first name.'
;

COMMENT ON COLUMN public.person.last_name
	IS 'Persons last name.'
;

COMMENT ON COLUMN public.person.gender
	IS 'Gender of Person.'
;

COMMENT ON COLUMN public.person.date_of_birth
	IS 'Persons date of birth.'
;

COMMENT ON COLUMN public.person.ssn
	IS 'Social Security Number of the person.'
;

COMMENT ON COLUMN public.person.person_address_id
	IS 'A link to the single address associated with a person.   Added 10/14/16'
;

COMMENT ON COLUMN public.person.create_user_id
	IS 'The ID of the user that created the record.'
;

COMMENT ON COLUMN public.person.create_datetime
	IS 'The date and time that the user created the record.'
;

COMMENT ON COLUMN public.person.update_user_id
	IS 'The ID of the user which made the last update to the record.'
;

COMMENT ON COLUMN public.person.update_datetime
	IS 'The date and time that the user updated the record.'
;

CREATE SEQUENCE public.seq_person_id INCREMENT 1 START 1
;
