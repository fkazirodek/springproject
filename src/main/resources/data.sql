INSERT INTO category(category_name)
VALUES ("Telefony i Akcesoria");
 
INSERT INTO category(category_name)
VALUES ("Telewizory");

INSERT INTO category(category_name)
VALUES ("Laptopy");

INSERT INTO category(category_name)
VALUES ("Samochody osobowe");

INSERT INTO category(category_name)
VALUES ("Motocykle");

INSERT INTO category(category_name)
VALUES ("Akcesoria");

INSERT INTO payments(method, delivery_costs)
VALUES ("Płatność kartą kredytową", 10);

INSERT INTO payments(method, delivery_costs)
VALUES ("Płatność przy odbiorze", 15);

INSERT INTO payments(method, delivery_costs)
VALUES ("Odbiór osobisty", 0);

INSERT INTO user_role(role) 
VALUES ("ROLE_USER");


INSERT INTO users(id_user, firstname, lastname, username, email, password, enabled) VALUES(1, "Jan", "Kowalski", "jankow", "jankowalski@email.com", "1234567", 1);