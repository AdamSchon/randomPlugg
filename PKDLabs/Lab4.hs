 -- stringOfInteger :: Integer -> String
-- This function takes an integer x and returns the same value as a String.
-- Precondition:
-- Example:
-- stringOfInteger 64 = "64"
-- stringOfInteger (-234) = "-234"
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

-- This function recursively checks if the inputstring m contains a series of characters equal to s. The function returns a boolean corresponding to the previous question.
-- Precondition: n <= length m
 -- Example:
 -- recursiveSearch "hej" "ej" 0 = True (After one recursive step)
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
-- 5. Function name: binaryToDecimal. Variable name: Binary


 --This function takes the inputs n (Integer) and x (any value) and creates an array containing n copies of x.
 -- Precondition: n >= 0
 -- Example:
 -- 
 myReplicate n x | n == 0  = []
                 | otherwise = x:(myReplicate (n-1) x)

 fromTo low high =
   if (low == high)
     then [high]
     else if (low > high)
             then []
             else low:(fromTo (low+1) high)

 severian vodalus =
   if vodalus == ""
       then 0
       else severian (take (length vodalus - 1) vodalus) * 2 +
           if drop (length vodalus - 1) vodalus == "0"
               then 0
               else 1


    
 searchString2 mainstring substring =
   let recursiveSearch m s n =
          if ((length s) > (length (drop n m)))
            then (-1)
            else if (s == (take (length s) (drop n m)))
                   then n
                   else recursiveSearch m s (n+1)
   in recursiveSearch mainstring substring 0
