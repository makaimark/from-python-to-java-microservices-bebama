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
  time_spent INTERVAL,
  location VARCHAR,
  amount FLOAT
);