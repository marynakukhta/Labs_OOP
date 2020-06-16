SELECT *
FROM Bookings
WHERE status = 'WAITING'
  AND Bookings.id NOT IN (
    SELECT booking_id
    FROM Rides
);