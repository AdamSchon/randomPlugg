-- function - n = 10 - n = 30 - n = 60
-- 1 - 1 - 1 - 1
-- log_2 n - 3.32 - 4.9 - 5.9
-- n - 10 - 30 - 60
-- n log_2 n - 33.2 - 147 - 354
-- n^2 - 100 - 900 - 3,600 microseconds
-- n^3 - 1 microsecond - 27 microseconds - 216 microseconds
-- 2^n - 1,024 microseconds - 1,073741824 s - 36,6 years
-- n! - 3,6288 ms - 2,6525285981219105863630848e+32 (84111130077432,47673652602739726 centuries) - 8,3209871127413901442763411832234e+81 (lots...)

-- function - 10 microseconds - 1 000 000 - 86400000000
-- log_2 n - 1024 - too large - too large
-- n - 10 - 1 000 000 - 86400000000
-- n*log_2 n - 4.56 - 62746 - 2.75515 * 10^9
-- n² - 3.1623 - 1000 - 293939
-- n³ - 2.15 - 100 - 4420
-- 2^n - 3.32 - 19.9316 - 36.3303
-- n! - 3.39 - 9.44561 - 13.9966

-- Problem 3
-- insertsort from last lab was n^2 (bad)
-- mergesort was n*log n (good)

-- Problem 4
-- a) c1 = 4, c2 = 4, n >= 0
-- b) c1 = 5, c2 = 16, n >= 1
