SELECT Users.id, COUNT(completed_rides.car_id) as count, SUM(completed_rides.cost) as sum
FROM Users
         INNER JOIN (SELECT car_id, status, cost
                     FROM Rides
                              INNER JOIN Bookings ON Rides.booking_id = Bookings.id
                     WHERE status = 'COMPLETED') as completed_rides ON Users.car_id = completed_rides.car_id
GROUP BY Users.id
ORDER BY count DESC, sum DESC
LIMIT 1;