SELECT SUM(cost) AS earnings
FROM Rides r
         INNER JOIN bookings b on r.booking_id = b.id
WHERE b.status = 'COMPLETED';