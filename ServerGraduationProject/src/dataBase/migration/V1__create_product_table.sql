CREATE TABLE products (
                               prod_id IDENTITY PRIMARY KEY,
                               price numeric(6,2),
                               name varchar(50)

) as SELECT * FROM CSVREAD('/Users/19208093/IdeaProjects/ServerServletEdu/ServerGraduationProject/src/dataBase/ddl/products.cvc');

CREATE INDEX products_name ON products(name);

