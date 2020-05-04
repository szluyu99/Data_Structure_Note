package com.mj;

/**
 * Created by ldh on 2019/7/26.
 */
public class PPTest {

    private Integer pAmount = 2;
    private Integer bottleToBeer = 2;
    private Integer lidToBeer = 4;

    private Integer bottleNum = 0;
    private Integer lidNum = 0;
    private Integer total = 0;

    public void calc(int amount) {
        int newBeers = amount/pAmount;
        newBeers(newBeers);
        calcReplace();
    }

    public void calcReplace() {
        while(bottleNum >= bottleToBeer) {  // 空瓶换酒
            int newBeers = bottleNum/bottleToBeer;
            bottleNum -= newBeers * bottleToBeer;
            newBeers(newBeers);

            if (lidNum >= lidToBeer) { // 瓶盖换酒
                int newBeers2 = lidNum/lidToBeer;
                lidNum -= newBeers2 * lidToBeer;
                newBeers(newBeers2);
            }
        }

        while(lidNum >= lidToBeer) {
            // 瓶盖换酒
            int newBeers2 = lidNum/lidToBeer;
            lidNum -= newBeers2 * lidToBeer;
            newBeers(newBeers2);

            if (bottleNum >= bottleToBeer) {
                int newBeers = bottleNum/bottleToBeer;
                bottleNum -= newBeers * bottleToBeer;
                newBeers(newBeers);
            }
        }
        if (lidNum >= lidToBeer || bottleNum >= bottleToBeer) {
            calcReplace();
        }
    }

    public void newBeers(int num) {
        total += num;
        bottleNum += num;
        lidNum += num;
    }

    public Integer getBottleNum() {
        return bottleNum;
    }

    public Integer getLidNum() {
        return lidNum;
    }

    public Integer getTotal() {
        return total;
    }

    public static void main(String[] args) {
        PPTest ppTest = new PPTest();
        ppTest.calc(100);
        String info = String.format("可以喝酒瓶数：%s, 剩余空瓶数量: %s, 剩余瓶盖数量： %s", ppTest.getTotal(), ppTest.getBottleNum(), ppTest.getLidNum());
        System.out.println("result: " + info);
    }
}
