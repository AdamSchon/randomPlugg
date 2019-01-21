sizeList list = map length list

multiplies n list = filter (0== . (\x mod x n)) list
