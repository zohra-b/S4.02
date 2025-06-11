H2 

SELECT * FROM  FRUITS
INSERT INTO FRUITS (name, quantity_kg) VALUES('APPLE', 4);
UPDATE fruits SET quantity_kg = 15 WHERE name = 'CHERRY';
DELETE FROM FRUITS WHERE name = 'APPLE'


CURL (terminal bash)
curl.exe -X GET http://localhost:8080/fruits/getAll
curl.exe -X GET http://localhost:8080/fruits/getOne/2
curl.exe -X DELETE http://localhost:8080/fruits/delete/2

curl.exe -X POST http://localhost:8080/fruits/add -H "Content-Type: application/json" -d  '{"name":"apple","quantityKg":4}'
curl.exe -X POST http://localhost:8080/fruits/add -H "Content-Type: application/json" -d  '{"name":"banana","quantityKg":2}'
curl.exe -X POST http://localhost:8080/fruits/add -H "Content-Type: application/json" -d  '{"name":"cherry","quantityKg":25}'
curl.exe -X PUT http://localhost:8080/fruits/update/1 -H "Content-Type: application/json" -d  '{"name":"peach","quantityKg":3}'
curl.exe -X PUT http://localhost:8080/fruits/update/3 -H "Content-Type: application/json" -d  '{"quantityKg":18}'

curl.exe -X POST http://localhost:8080/fruits/add -H "Content-Type: application/json" -d '{"name":"orange","quantityKg":5}'
curl --location 'http://localhost:8080/fruits/add' \
--header 'Content-Type: application/json' \
--data '{"name":"poire", "quantityKg": 3}'
