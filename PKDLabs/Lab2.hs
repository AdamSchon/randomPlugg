squareArea x = (x^2)
circleArea x = (x^2)* pi
squareCircleArea x = squareArea (2*x) - circleArea x
cathetus x = x/sqrt 2
circleSquareArea x = circleArea x - squareArea(cathetus (2*x))

takeLast word = drop (length word - 3)

rhymes first second =
  if (((length first) > 3) && ((length second) > 3))
  then
    if (drop ((length first) - 3) == drop ((length second) - 3))
      then True
      else False
  else
    if first == second
      then True
      else False
