SELECT SUM(cost) AS earnings
FROM Rides r
         INNER JOIN Bookings b ON r.booking_id = b.id
WHERE r.car_id=?
  AND b.status = 'COMPLETED';