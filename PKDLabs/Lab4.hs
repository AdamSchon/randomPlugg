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
      else if (s == (take (length s) (drop n m)))
          then n
          else recursiveSearch m s (n+1)

  searchString mainstring substring = recursiveSearch mainstring substring 0

-- 1. vodalus is a String
-- 2. "10" is not "". (1 * 2 + 0) 2 will be the output.
-- 3. For each character in the string vodalus that is not "0" the function adds 2 to the output.
-- 4. The output of severian is the amount of characters in vodalus multiplied by two.
-- 5. Function name: DoubleNotZero. Variable name: string

  myReplicate n x =
    if (n == 0)
      then []
      else x:(myReplicate (n-1) x)

  fromTo low high =
    if (low == high)
      then [high]
      else low:(fromTo (low+1) high)

  searchString2 mainstring substring =
    takeWhile (\x -> (length substring) > (length(drop x mainstring))) [1..99999]
      if (substring == (take (length s) (drop n mainstring)))
        then return n
      if ((length substring) == (length(drop n mainstring)))
        then return (-1)


-- Problem 6, not sure what declared locally means. Ask about it?
