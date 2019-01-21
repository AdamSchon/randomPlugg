sizeList list = map length list

multiples n list = filter ((0==) . (\x -> mod x n)) list

myConcat = foldr (++)
