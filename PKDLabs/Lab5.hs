myInit list =
  if length list == 1
    then []
    else (list !! 1):myInit(drop 1 list)
