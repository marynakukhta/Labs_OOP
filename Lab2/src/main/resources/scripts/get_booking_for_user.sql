SELECT b.*, r.cost
FROM bookings b
         JOIN rides r on b.id = r.booking_id
WHERE r.car_id=?;