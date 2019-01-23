myInit [] = []
myInit [x] = []
myInit (x:xs) = x:myInit xs

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

squareOfEven2 list =
  [ x ^ 2 | x <- list, mod (x) 2 == 0]

-- 1000 - 0.32 seconds
-- 2000 - 1.27 seconds
-- 3000 - 2.83 seconds
-- 4000 - 5.06 seconds
-- 5000 - 7.96 seconds
-- 6000 - 11.58 seconds
-- 7000 - 14.79 seconds
-- 8000 - 19.44 seconds
-- 9000 - 29.58 seconds
-- 10000 - 37.18 secs

-- length (mergeSort [1..10000]) - 0.09 seconds
-- length (mergeSort [1..100000]) - 0.99 seconds
-- length (mergeSort [1..1000000]) - 11.45 seconds


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
