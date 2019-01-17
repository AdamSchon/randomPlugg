  -- stringOfInteger :: Integer -> String

  stringOfInteger x =
  if (abs x) > 9
    Then (stringOfInteger (div x 10)) ++ show(mod x 10)
    else
      if (x > 0)
        then show(x)
        else
          if (x != 0)
            then "-" ++ show(x)
            else ""
