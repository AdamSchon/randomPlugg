public class Driver {
    public static void main(String[] args) {
        String[] strings = new String[128];
        for (int i = 0; i < strings.length; ++i) strings[i] = "String " + i;

        Set<Integer> set1 = new Set<Integer>(4);
        try {
            for (int i = 0; i < 5; ++i) set1.add(i);
            assert false : "Seems able to put 5 elements in a set of size 4";
        } catch (SetFullException e) {
            // do nothing
        }

        Set<String> set2 = new Set<String>(40);
        for (int i = 10; i < 50; ++i) set2.add(strings[i]);
        for (int i = 10; i < 50; ++i) {
            boolean result = set2.contains("String " + i);
            assert result : "Either contains or add is broken";
        }
        boolean result = set2.contains("NoSuchString");
        assert result == false: "Contains seems broken";

        result = set1.size() == 4;
        assert result : "Size seems broken";
        result = set2.size() == 40;
        assert result : "Size seems broken";

        Object o1 = set2;
        result = o1.equals(o1);
        assert result : "Equals broken -- an object is not equal to itself!"; 
        
        
        Set<String> set3 = new Set<String>(40);
        Object o2 = set3;
        result = o1.equals(o2);
        assert result== false: "Equals broken -- non-empty set equal to empty set!"; 

        for (int i = 49; i >= 10; --i) set3.add(strings[i]);
        result = o1.equals(o2);
        assert result : "Equals broken -- two sets with same elements are not equal";

        System.err.println("If you can read this, and there are no error messages above, then all tests pass!");
    }
}
