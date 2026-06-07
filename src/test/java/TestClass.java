import java.util.*;

public class TestClass {
    //0,0,0,0,1,1,2,2,2,,3,3,3,3,3,4,4


    public static void main(String[] args) {

    List<Integer> li = Arrays.asList(0,0,0,0,1,1,2,2,2,3,3,3,3,3,4,4);
        TestClass.topKFrequent(li,2);

    }
    public static void topKFrequent(List<Integer>nums, int k){
        Map<Integer,Integer> feqMap = new HashMap<>();

        for (Integer num : nums) {
            feqMap.put(num, feqMap.getOrDefault(num,0)+1);
        }
        System.out.println(feqMap);

        List<Map.Entry<Integer,Integer>> entries = new ArrayList<>(feqMap.entrySet());

        Collections.sort(entries, new Comparator<Map.Entry<Integer, Integer>>() {
            @Override
            public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2) {
                return 0;
            }
        });


        System.out.println(entries);
    }
}
