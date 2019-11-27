package com.yanlaoge.greedy;

import javax.sound.midi.Soundbank;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * @ClassName: GreedyAlogrithm
 * @Description:
 * @Author: niunan
 * @Create: 2019/11/26 7:51
 **/
public class GreedyAlogrithm {
    public static void main(String[] args) {
        //创建电台,放入到map
        Map<String, HashSet<String>> broadcasts = new HashMap<>();
        //将电台放入map
        HashSet<String> hashSet1 = new HashSet<>();
        hashSet1.add("北京");
        hashSet1.add("上海");
        hashSet1.add("天津");

        HashSet<String> hashSet2 = new HashSet<>();
        hashSet2.add("广州");
        hashSet2.add("北京");
        hashSet2.add("深圳");

        HashSet<String> hashSet3 = new HashSet<>();
        hashSet3.add("成都");
        hashSet3.add("上海");
        hashSet3.add("深圳");

        HashSet<String> hashSet4 = new HashSet<>();
        hashSet4.add("上海");
        hashSet4.add("天津");

        HashSet<String> hashSet5 = new HashSet<>();
        hashSet5.add("杭州");
        hashSet5.add("大连");

        //放入map
        broadcasts.put("k1",hashSet1);
        broadcasts.put("k2",hashSet2);
        broadcasts.put("k3",hashSet3);
        broadcasts.put("k4",hashSet4);
        broadcasts.put("k5",hashSet5);

        //准备一个存放所有地区的set
        HashSet<String> allAreas = new HashSet<>();
        allAreas.add("北京");
        allAreas.add("天津");
        allAreas.add("上海");
        allAreas.add("广州");
        allAreas.add("深圳");
        allAreas.add("成都");
        allAreas.add("杭州");
        allAreas.add("大连");

        //创建一个list集合, 存放选择的电台
        ArrayList<String> selects = new ArrayList<>();

        //定义一个临时集合, 在遍历过程中,存放遍历过程中的电台覆盖的地区和当前还没有覆盖的地区的交集
        HashSet<String> tempSet = new HashSet<>();
        //定义一个maxKey,保存在一次遍历过程中,能够覆盖最大未覆盖地区对应的key
        //如果maxKey不为null,就假如到selects里面
        String maxKey = null;

        //开始处理
        while (allAreas.size() > 0){
            //每次处理时都要清空
            maxKey = null;
            //取出电台
            for (String key : broadcasts.keySet()){
                //每次都需要情况temp
                tempSet.clear();
                HashSet<String> areas = broadcasts.get(key);
                tempSet.addAll(areas);
                //球 tempset 和 allAreas的交集
                tempSet.retainAll(allAreas);
                //如果当前这个集合包含的为覆盖地区的数量,比maxKey指向的结合的地区还多,就重新设置maxKey
                //核心 tempSet.size() > broadcasts.get(maxKey).size()
                //每次选择最优的
                if(tempSet.size() > 0 && (maxKey == null || tempSet.size() > broadcasts.get(maxKey).size()  )){
                    maxKey = key;
                }
            }

            if(maxKey != null){
                //将maxKey添加到选中的电台
                selects.add(maxKey);
                //删除已经选中的
                allAreas.removeAll(broadcasts.get(maxKey));
            }
        }
        System.out.println("结果: "+selects);
    }
}
