CREATE TABLE IF NOT EXISTS webshop
(
  ws_id SERIAL PRIMARY KEY,
  webshop_key VARCHAR UNIQUE
);


CREATE TABLE IF NOT EXISTS webshopAnalytics
(
  an_id SERIAL PRIMARY KEY,
  webshop_id INT REFERENCES webshop(ws_id),
  session_id VARCHAR,
  visit_start TIMESTAMP,
  visit_end TIMESTAMP NULL,
  location VARCHAR,
  amount FLOAT NULL,
  currency VARCHAR NULL
);