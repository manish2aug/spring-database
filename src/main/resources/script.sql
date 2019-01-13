-- Table: myschema.student

-- DROP TABLE myschema.student;

CREATE TABLE myschema.student
(
  id serial NOT NULL,
  name character varying(50) NOT NULL,
  age smallint NOT NULL,
  CONSTRAINT id PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE myschema.student
  OWNER TO postgres;

  
-- Table: myschema.course

-- DROP TABLE myschema.course;

CREATE TABLE myschema.course
(
  name character varying(20) NOT NULL,
  duration smallint NOT NULL,
  id serial NOT NULL,
  CONSTRAINT idd PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE myschema.course
  OWNER TO postgres;
  