CREATE TABLE IF NOT EXISTS webshop
(
id VARCHAR PRIMARY KEY
);


CREATE TABLE IF NOT EXISTS webshopAnalytics
(
  id SERIAL PRIMARY KEY,
  webshop_id VARCHAR  REFERENCES webshop(id),
  session_id VARCHAR ,
  time_spent INTERVAL,
  location VARCHAR,
  amount FLOAT
);