package com.roll.casserole.buffer;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author roll
 * created on 2019-08-28 19:41
 */
public class ByteBufTest {
    public static void main(String[] args) {
        ByteBuf byteBuf = Unpooled.directBuffer(100);
        List<Object> idList = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            idList.add(new Random().nextLong());
        }
        Map<Object, Long> map = new HashMap<>();
        for (Object id : idList) {
            if ((long) id % 2 == 0) {
                map.put(id, 1L);
            } else {
                map.put(id, 2L);
            }
        }
        int cap = (int) Math.round(idList.size() / 0.75 + 1);
        Map<Long, String> codeMap = new HashMap<>(cap);
        long startTime = System.currentTimeMillis();
        for (Object id : idList) {
            Long codeId = Long.parseLong(String.valueOf(id));
            long code = map.get(codeId);
            if (codeMap.containsKey(code)) {
                codeMap.put(code, codeMap.get(code) + "," + codeId);
            } else {
                codeMap.put(code, "" + codeId);
            }

        }
        System.out.println(System.currentTimeMillis() - startTime + "ms");


        List<Object> idList2 = new ArrayList<>();

        /*long startTime1 = System.currentTimeMillis();
        Map<Long, String> codeMap1 =  idList2.parallelStream().map(id -> {
            Long codeId = Long.parseLong(String.valueOf(id));
            long code = map.get(codeId);
            if (codeMap1.containsKey(code)) {
                codeMap1.put(code, codeMap1.get(code) + "," + codeId);
            } else {
                codeMap1.put(code, "" + codeId);
            }
        }).collect(Collectors.toMap(codeMap1.);
        System.out.println(System.currentTimeMillis() - startTime1 + "ms");*/
    }

}
