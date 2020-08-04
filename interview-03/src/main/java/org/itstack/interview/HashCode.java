package org.itstack.interview;

import java.util.*;

public class HashCode {

    /**
     * public int hashCode() {
     * int h = hash;
     * if (h == 0 && value.length > 0) {
     * char val[] = value;
     * for (int i = 0; i < value.length; i++) {
     * h = 31 * h + val[i];
     * }
     * hash = h;
     * }
     * return h;
     * }
     */
    public static Integer hashCode(String str, Integer multiplier) {
        int hash = 0;
        for (int i = 0; i < str.length(); i++) {
            hash = multiplier * hash + str.charAt(i);
        }
        return hash;
    }

    /**
     * 计算Hash碰撞概率
     */
    private static RateInfo hashCollisionRate(Integer multiplier, List<Integer> hashCodeList) {
        int maxHash = hashCodeList.stream().max(Integer::compareTo).get();
        int minHash = hashCodeList.stream().min(Integer::compareTo).get();

        int collisionCount = (int) (hashCodeList.size() - hashCodeList.stream().distinct().count());
        double collisionRate = (collisionCount * 1.0) / hashCodeList.size();

        return new RateInfo(maxHash, minHash, multiplier, collisionCount, collisionRate);
    }

    public static List<RateInfo> collisionRateList(Set<String> strList, Integer... multipliers) {
        List<RateInfo> rateInfoList = new ArrayList<>();
        for (Integer multiplier : multipliers) {
            List<Integer> hashCodeList = new ArrayList<>();
            for (String str : strList) {
                Integer hashCode = hashCode(str, multiplier);
                hashCodeList.add(hashCode);
            }
            rateInfoList.add(hashCollisionRate(multiplier, hashCodeList));
        }
        return rateInfoList;
    }

    public static Map<Integer, Integer> hashArea(List<Integer> hashCodeList) {
        Map<Integer, Integer> statistics = new LinkedHashMap<>();
        int start = 0;
        for (long i = 0x80000000; i <= 0x7fffffff; i += 67108864) {
            long min = i;
            long max = min + 67108864;
            // 筛选出每个格子里的哈希值数量，java8流统计；https://bugstack.cn/itstack-demo-any/2019/12/10/%E6%9C%89%E7%82%B9%E5%B9%B2%E8%B4%A7-Jdk1.8%E6%96%B0%E7%89%B9%E6%80%A7%E5%AE%9E%E6%88%98%E7%AF%87(41%E4%B8%AA%E6%A1%88%E4%BE%8B).html
            int num = (int) hashCodeList.parallelStream().filter(x -> x >= min && x < max).count();
            statistics.put(start++, num);
        }
        return statistics;
    }

    public static Map<Integer, Integer> hashArea(Set<String> strList, Integer multiplier){
        List<Integer> hashCodeList = new ArrayList<>();
        for (String str : strList) {
            Integer hashCode = hashCode(str, multiplier);
            hashCodeList.add(hashCode);
        }
        return hashArea(hashCodeList);
    }

}
