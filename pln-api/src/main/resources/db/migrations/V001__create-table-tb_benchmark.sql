CREATE TABLE tb_benchmark (
  id SERIAL PRIMARY KEY,
  benchmark_name VARCHAR NOT NULL,
  first_country_name VARCHAR NOT NULL,
  last_country_name VARCHAR NOT NULL,
  first_date TIMESTAMP NOT NULL,
  last_date TIMESTAMP NOT NULL,
  results JSONB NOT NULL
);
