-- 1 if [] or [a]
-- n/2 else

-- n^2

-- N(0) = t0 -> t0
-- N(1) = N(0) + T -> t0 + T
-- N(2) = N(1) + T -> 2 * T + t0
-- N(3) = N(2) + T -> 3 * T + t0

-- N(n) = N(n-1) + n
--  = (N(n-2) + n) + n
--  = (N(n-3) + n) + 2n
--  = (N(n-4) + n) + 3n

-- Problem 4
-- 0 for n <= 1234
-- 2^pi for n > 1234
