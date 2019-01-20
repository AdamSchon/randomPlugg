myInit list =
  if length list == 1
    then []
    else (head list):myInit(drop 1 list)

fromDecimals list =
  if list == []
    then 0
    else
      if length list == 1
        then head list
        else (head list) * (10 ^ (length list - 1)) + (fromDecimals (drop 1 list))

squareOfEven list =
  if list == []
    then []
    else
      if ((head list) % 2 == 0)
        then ((head list) ^ 2):squareOfEven (drop 1 list)
        else squareOfEven (drop 1 list)
