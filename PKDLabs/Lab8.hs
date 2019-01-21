-- head: [a] -> a (POLY)
-- tail: [a] -> a (POLY)
-- t -> t (POLY)
-- (,): a -> b ->(a,b) (POLY)
-- (:): a -> [a] -> [a] (POLY)
-- [[]]: [[t]] (POLY)
-- tail [[]]: [[t]] (POLY)
-- id:[] : [a -> a] (POLY)
-- id id: a -> a (POLY)
-- head [id] "foo": [char]

flipArguments f a b = f b a
