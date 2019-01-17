-- Problem 1: drJeep checks if length x > length y and then makes x and y the same length.
-- Then the function returns x == y (true if the ending of x == y).
-- A better name of the function is "endsWith"(?)
-- The arguments could be: Word and Ending

updatePersonName (FamName, GivName, Street, PostNr, PostAddr) 1 new = (new, GivName, Street, PostNr, PostAddr)
updatePersonName (FamName, GivName, Street, PostNr, PostAddr) 2 new = (FamName, new, Street, PostNr, PostAddr)
updatePersonName (FamName, GivName, Street, PostNr, PostAddr) n new = (FamName, GivName, Street, PostNr, PostAddr)
