SELECT a.*
FROM automobiles a
         LEFT JOIN users u on a.id = u.car_id
WHERE a.id NOT IN (SELECT car_id FROM users);