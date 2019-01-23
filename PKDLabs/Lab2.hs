
-- Function squareArea takes an numerical length x and calculates the area of a square with that radius
-- Precondition: x != null, negative values are considered.
-- Example:
-- squareArea 2.0 = 4.0
squareArea x = (x^2)

-- Function circleArea takes an numerical radius x and calculates the area of a circle with that radius
-- Precondition: x != null, negative values are not considered.
-- Example:
-- circleArea 2.0 = 12.566370614359172
circleArea x = (x^2)* pi

-- recieves numberical input x corresponding to length x. The functions calculates the area of a circle with radius x and the area of a square with side length 2*x. The return value is the difference of the squareArea - the circleArea.
-- precon: The circle calculates is in the square.
-- Example:
-- squareCircleArea 2.0 = 3.4336293856408275
squareCircleArea x = squareArea (2*x) - circleArea x

-- Recieves a numberical value x corresponding to a hypothenusis. Calculates the cathetus of the triangle. The triangles cathetuses are of equal length
-- Precondition: The input x should be non-negative
-- Example:
-- cathetus 2.0 = 1.414213562373095
cathetus x = x/sqrt 2

-- Recieves a numerical value x, corresponding to the radius x. The difference of the area of a circle with radius x and the square with a diagonal of length 2*x is calculated.
-- Precon:x != null
-- Example:
-- circleSquareArea 2.0 = 4.566370614359174
circleSquareArea x = circleArea x - squareArea(cathetus (2*x))

-- Function that recieves a string word and returns the last three letters of the String.
-- Precondition: The length of word is at least 3 characters long.
-- Example:
-- takeLast "hejsan" = "san"
takeLast word = drop (length word - 3)

-- Function that takes two Strings, first and second and checks if the final three letter of both strings are the same.-- If any or both of the Strings are shorter than three characters the function checks if first == second. The function returns a boolean.
-- Precondition:
-- Example:
-- rhymes "hejsan" "tjosan" = true
-- rhymes "durå" "ehjsan" = false
-- rhymes "ej" "ej" = true
rhymes first second =
  if (((length first) > 3) && ((length second) > 3))
  then
    if ((drop ((length first) - 3) first) == (drop ((length second) - 3) second))
      then True
      else False
  else
    if first == second
      then True
      else False

-- Problem 3: drJeep checks if length x > length y and then makes x and y the same length.
-- Then the function returns x == y (true if the ending of x == y).
