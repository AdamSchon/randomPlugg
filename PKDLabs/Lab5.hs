myInit list =
  if length list == 1
    then []
    else (take 1 list):myInit(drop 1 list)
