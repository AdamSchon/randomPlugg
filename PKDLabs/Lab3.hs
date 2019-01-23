-- Problem 1: drJeep checks if length x > length y and then makes x and y the same length.
-- Then the function returns x == y (true if the ending of x == y).
-- A better name of the function is "endsWith"(?)
-- The arguments could be: Word and Ending

-- The function takes an input of the form: (String String String Int String) val new and returns the first tuple, with a few changes depending on the Integer 'val'. If val = 2, the return tuple has exchanged the first String in the tuple with new. If val = 2, the return tuple has changed the second value with the String new. If val = any other number, nothing is changed between the input tuple and the output tuple.
-- Preconditions:
--Example:
-- updatePersonName ("ett", "tva", "tre", 4, "fem") 1 "new" = ("ett", "new", "tre", 4, "fem")
-- updatePersonName ("ett", "tva", "tre", 4, "fem") 2 "new" = ("new", "tva", "tre", 4, "fem")
-- updatePersonName ("ett", "tva", "tre", 4, "fem") 3 "new" = ("ett", "tva", "tre", 4, "fem")
updatePersonName (_, b, c, d, e) 2 new = (new,b,c,d,e)
updatePersonName (a, _, c, d, e) 1 new = (a,new,c,d,e)
updatePersonName (a, b, c, d, e) _ _ = (a,b,c,d,e)


-- Algorithm
-- if input == 0 return nothing
-- return recursive(input/10) ++ "input % 10"
