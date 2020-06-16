SELECT COUNT(b.id) AS counted
FROM bookings b
         JOIN rides r on b.id = r.booking_id
WHERE r.car_id=?
  AND b.status=?::ride_status;