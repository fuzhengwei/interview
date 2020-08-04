package org.itstack.interview;

public class RateInfo {

    private int maxHash;            // 最大Hash
    private int minHash;            // 最小Hash
    private int multiplier;         // hash计算乘机因子
    private int collisionCount;     // 碰撞数
    private double collisionRate;   // 碰撞比率

    public RateInfo(int maxHash, int minHash, int multiplier, int collisionCount, double collisionRate) {
        this.maxHash = maxHash;
        this.minHash = minHash;
        this.multiplier = multiplier;
        this.collisionCount = collisionCount;
        this.collisionRate = collisionRate;
    }

    public int getMaxHash() {
        return maxHash;
    }

    public void setMaxHash(int maxHash) {
        this.maxHash = maxHash;
    }

    public int getMinHash() {
        return minHash;
    }

    public void setMinHash(int minHash) {
        this.minHash = minHash;
    }

    public int getMultiplier() {
        return multiplier;
    }

    public void setMultiplier(int multiplier) {
        this.multiplier = multiplier;
    }

    public int getCollisionCount() {
        return collisionCount;
    }

    public void setCollisionCount(int collisionCount) {
        this.collisionCount = collisionCount;
    }

    public double getCollisionRate() {
        return collisionRate;
    }

    public void setCollisionRate(double collisionRate) {
        this.collisionRate = collisionRate;
    }
}
