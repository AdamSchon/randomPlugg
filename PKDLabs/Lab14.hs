import Text.Printf -- for printing stuff out
import Test.HUnit
import Debug.Trace

-- Return whether a string contains balanced brackets. Nothing indicates a
-- balanced string, while (Just i) means an imbalance was found at, or just
-- after, the i'th bracket. We assume the string contains only brackets.
isBalanced :: String -> Maybe Int
isBalanced = bal (-1) 0
  --where bal :: Int -> In t -> String -> Maybe Int
bal _ 0 [] = Nothing
bal i _ [] = Just i
-- Changed this from: bal i (-1) _ = Nothing
bal i (-1) _ = Just i
bal i n ('[':bs) = bal (i+1) (n+1) bs
--bal i n ('[':bs) = trace ("n: " ++ show n) $ bal (i+1) (n+1) bs
-- Changed this from = bal (i+1) (n+1) bs
bal i n (']':bs) = bal (i+1) (n-1) bs

-- Print a string, indicating whether it contains balanced brackets. If not,
-- indicate the bracket at which the imbalance was found.
--check :: String -> IO ()
check s = maybe (good s) (bad s) (isBalanced s)
  where good s = printf "Good \"%s\"\n" s
bad s n = printf "Bad \"%s\"\n%*s^\n " s (n+6) " "

test1 = TestCase(assertEqual "test1: []" Nothing (isBalanced "[]"))
test2 = TestCase(assertEqual "test2: [[[]]]" Nothing (isBalanced "[[[]]]"))
test3 = TestCase(assertEqual "test3: []]" (Just 3) (isBalanced "[]]"))
test4 = TestCase(assertEqual "test 4: EmptyTest" Nothing (isBalanced ""))
test5 = TestCase(assertEqual "test 5: [[[]]" (Just 5) (isBalanced "[[[]]"))

tests = TestList [TestLabel "test1" test1, TestLabel "test2" test2, TestLabel "test3" test3, TestLabel "test4" test4, TestLabel "test5" test5]
