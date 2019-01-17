-- Problem 1: drJeep checks if length x > length y and then makes x and y the same length.
-- Then the function returns x == y (true if the ending of x == y).
-- A better name of the function is "endsWith"(?)
-- The arguments could be: Word and Ending

updatePersonName (a, b, c, d, e) 2 new = (new,b,c,d,e)
updatePersonName (a, b, c, d, e) 1 new = (a,new,c,d,e)
updatePersonName (a, b, c, d, e) _ _ = (a,b,c,d,e)
