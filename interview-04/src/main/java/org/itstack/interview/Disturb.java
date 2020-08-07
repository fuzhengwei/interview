package org.itstack.interview;

public class Disturb {

    public static int disturbHashIdx(String key, int size) {
        return (size - 1) & (key.hashCode() ^ (key.hashCode() >>> 16));
    }

    public static int hashIdx(String key, int size) {
        return (size - 1) & key.hashCode();
    }

}
