package org.itstack.interview.test;

import com.sun.deploy.util.StringUtils;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.*;

public class ApiTest {

    @Test
    public void test_Collections() {

        List<String> list = Arrays.asList("32", "1", "4");
//        Collections.sort(list);

        Collections.shuffle(list);

        System.out.println(list);

        String set = list.set(1, "6");
        System.out.println(set);

    }

    @Test
    public void test_sort() {
        List<String> list = new ArrayList<String>();
        list.add("7");
        list.add("4");
        list.add("8");
        list.add("3");
        list.add("9");

//        Collections.sort(list);
//
//        System.out.println(list);

        Collections.sort(list, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o2.compareTo(o1);
            }
        });

        Collections.sort(list, Collections.<String>reverseOrder());

        System.out.println(list);
    }

    @Test
    public void test_binarySearch() {
        List<String> list = new LinkedList<String>();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");
        list.add("6");
        list.add("7");
        list.add("8");

        ListIterator<String> stringListIterator = list.listIterator();

        System.out.println(stringListIterator.getClass());

        int idx = Collections.binarySearch(list, "5");
        System.out.println("二分查找：" + idx);
    }

    @Test
    public void test_reverse() {
        List<String> list = new ArrayList<String>();
        list.add("3");
        list.add("5");
        list.add("6");
        list.add("8");
        list.add("10");

        Collections.reverse(list);

        System.out.println(list);
    }

    @Test
    public void test_fill() {
        List<String> list = new ArrayList<String>();
        list.add("7");
        list.add("4");
        list.add("8");
        list.add("3");
        list.add("9");

        Collections.fill(list, "0");
        System.out.println(list);
    }

    @Test
    public void test_copy() {
        List<String> list = new ArrayList<String>();
        list.add("7");
        list.add("4");
        list.add("8");
        list.add("3");
        list.add("9");

        List<String> newList = new ArrayList<String>(Arrays.asList(new String[list
                .size()]));
        Collections.copy(newList, list);
        System.out.println(newList);
    }

    /**
     * 洗牌算法
     */
    @Test
    public void test_shuffle() {
        List<String> list = new ArrayList<String>();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");
        list.add("6");
        list.add("7");
        list.add("8");

        Random random = new Random();
        for (int i = list.size(); i > 1; i--) {
            int ri = random.nextInt(i);  // 随机位置
            int ji = i - 1;              // 顺延
            System.out.println("ri：" + ri + " ji：" + ji);
            list.set(ji, list.set(ri, list.get(ji)));        // 元素置换
        }

        System.out.println(list);
    }

    @Test
    public void test_min_max() {
        String min = Collections.min(Arrays.asList("1", "2", "3"));
        String max = Collections.max(Arrays.asList("1", "2", "3"));
    }

    @Test
    public void test_rotate() {
        List<String> list = new LinkedList<String>();
        list.add("7");
        list.add("4");
        list.add("8");
        list.add("3");
        list.add("9");

        Collections.rotate(list, 2);

        System.out.println(list);
    }

    @Test
    public void test_replaceAll() {
        List<String> list = new ArrayList<String>();
        list.add("7");
        list.add("4");
        list.add("8");
        list.add("8");
        Collections.replaceAll(list, "8", "9");
        System.out.println(list);
    }

    @Test
    public void test_indexOfSubList() {
        List<String> list = new ArrayList<String>();
        list.add("7");
        list.add("4");
        list.add("8");
        list.add("3");
        list.add("9");

        int idx = Collections.indexOfSubList(list, Arrays.asList("8", "3"));
        System.out.println(idx);


    }

    @Test
    public void test_lastIndexOfSubList() {
        List<String> list = new ArrayList<String>();
        list.add("7");
        list.add("4");
        list.add("8");
        list.add("3");
        list.add("9");

        Collection<String> strings = Collections.unmodifiableCollection(list);
    }

    @Test
    public void test_synchronizedList() {
        List<String> list = new ArrayList<String>();
        list.add("7");
        list.add("4");
        list.add("8");
        list.add("3");
        list.add("9");

        List<String> list2 = Collections.synchronizedList(new ArrayList<String>());
        Map<String, String> map = Collections.synchronizedMap(new HashMap<String, String>());
    }

    @Test
    public void test_checkedList() {
        List<String> list = new ArrayList<String>();
        list.add("7");
        list.add("4");
        list.add("8");
        list.add("3");
        list.add("9");

        List<String> list1 = Collections.checkedList(list, String.class);
    }

}
