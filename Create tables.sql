CREATE TABLE cities
(
  id serial NOT NULL,
  countryname character varying(80),
  cityname character varying(80),
  CONSTRAINT cities_pkey PRIMARY KEY (id)
);

CREATE TABLE countries
(
  id serial NOT NULL,
  countryname character varying(80) NOT NULL,
  countryisocode character varying(3),
  CONSTRAINT countries_pkey PRIMARY KEY (id)
);