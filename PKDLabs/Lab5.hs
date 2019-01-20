myInit list =
  if length list == 1
    then []
    else (head list):myInit(drop 1 list)

fromDecimals list =
  if length list == 1
    then head list
    else (head list) * (10 ^ (length list)) + (fromDecimals (drop 1 list))
