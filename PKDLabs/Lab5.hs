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
    else if (mod (head list) 2 == 0)
          then ((head list) ^ 2):squareOfEven (drop 1 list)
          else squareOfEven (drop 1 list)

-- Provided code --
split :: [a] -> ([a],[a])
split xs =
  let
    l = length xs `div` 2
  in
   (take l xs, drop l xs)

merge :: [Integer] -> [Integer] -> [Integer]
merge [] ys = ys
merge xs [] = xs
merge (x:xs) (y:ys)
  | y < x = y : merge (x:xs) ys
  | otherwise = x : merge xs (y:ys)

mergeSort :: [Integer] -> [Integer]
mergeSort [] = []
mergeSort [x] = [x]
mergeSort xs =
  let
    (xs1,xs2) = split xs
  in
   merge (mergeSort xs1) (mergeSort xs2)

insert k [] = [k]
insert k (x:xs) =
  if k < x then
    k : x : xs
  else
    x:(insert k xs)

insertionSortAux sorted [] = sorted
insertionSortAux sorted (x:xs) =
  insertionSortAux (insert x sorted) xs

insertionSort xs =
  insertionSortAux [] xs
