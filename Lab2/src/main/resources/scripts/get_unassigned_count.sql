SELECT COUNT(id) AS counted
FROM Bookings
WHERE status = 'WAITING'
  AND Bookings.id NOT IN (
    SELECT booking_id
    FROM Rides
);