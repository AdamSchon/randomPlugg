  -- stringOfInteger :: Integer -> String

  stringOfInteger x =
    if ((abs x) > 9)
      then if (x > 0)
            then (stringOfInteger (div x 10)) ++ show(mod x 10)
            else "-" ++ (stringOfInteger (div (abs x) 10)) ++ show(10 - (mod x 10))
      else if (x > 0)
            then show(x)
            else if (not (x == 0))
                  then "-" ++ show(x)
                  else ""

  recursiveSearch m s n =
    if ((length s) > (length (drop n m)))
      then (-1)
      else if (s == (drop n m))
          then return n
          else recursiveSearch(m s n+1)

  searchString mainstring substring = recursiveSearch mainstring substring 0
