myInit list =
  if length list == 1
    then []
    else (head list):myInit(drop 1 list)
