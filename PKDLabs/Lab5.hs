myInit list =
  if length list == 1
    then []
    else (head list):myInit(drop 1 list)

fromDecimals list =
  if list == []
    then 0
    else (head list) * (length list) + (fromDecimals (drop 1 list))
