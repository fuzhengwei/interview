package org.itstack.interview.聚合关系;

/**
 * 博客：https://bugstack.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 * 公众号：bugstack虫洞栈
 * Create by 小傅哥(fustack) @2020
 */
public class 山庄 {

    private 宋晓峰 晓峰;
    private 李宝库 宝库;

    public void 药膳房(李宝库 宝库) {
        this.宝库 = 宝库;
    }

    public void 保安部(宋晓峰 晓峰) {
        this.晓峰 = 晓峰;
    }

}
