sizeList list = map length list

multiplies n list = filter ((\x mod x n) == 0) list
