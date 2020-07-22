package com.roll.casserole.set.hash;

import java.util.*;

/**
 * 分布式一致性算法
 * <p>@author roll
 * <p>created on 2020/7/21 11:35 上午
 */
public class HashFunction {
    private String virtualLinkTag = "-";
    /**
     * 每个服务虚拟节点个数，默认10
     */
    private int virtualNumber = 10;

    /**
     * 圆环上所有节点个数
     */
    private int allNodeNumber = 2 << 15;

    /**
     * 虚拟节点位置
     */
    List<Integer> virtualNode = new ArrayList<>();

    /**
     * 真实节点对应的圆环上的节点位置
     */
    Map<Service, List<Integer>> serviceListMap = new HashMap<>();

    /**
     * 所有节点位置
     */
    Map<Integer, Service> serviceMap = new HashMap<>();

    /**
     * 排过序的节点位置
     */
    List<Integer> sortedNodeIndex = new ArrayList<>();

    /**
     * 初始化节点 -> 虚拟节点
     */
    public void initService() {
        serviceListMap.keySet().forEach(this::initVirtualNode);
    }

    /**
     * 生成虚拟节点
     *
     * @return 生成的虚拟节点位置
     */
    private List<Integer> initVirtualNode(Service service) {
        String url = service.getUrl();
        List<Integer> virtualNodeList = new ArrayList<>();
        for (int i = 0; i < virtualNumber; i++) {
            String virtualUrl = url + virtualLinkTag + i;
            int virtualIndex = hash(virtualUrl);
            // 如果包含，hash重复了，过滤掉这个key
            if (serviceMap.containsKey(virtualIndex)) {
                // do nothing
            } else {
                serviceMap.put(virtualIndex, service);
                virtualNodeList.add(virtualIndex);
            }
        }
        // 给每个 service 对应到它的 虚拟节点列表
        serviceListMap.put(service, virtualNodeList);
        virtualNode.addAll(virtualNodeList);
        return virtualNodeList;
    }

    /**
     * 对象获取节点信息
     *
     * @param object 被放置的对象
     */
    private Service getNode(Object object) {
        if (sortedNodeIndex == null) {
            sortNodeIndex();
        }
        int objectIndex = hash(object);
        // 利用二分查找找到最近的节点地址
        int virtualIndex = sortUtil.getNextIndex((Integer[]) serviceMap.keySet().toArray(), 0, sortedNodeIndex.size(), objectIndex);
        return serviceMap.get(virtualIndex);
    }

    /**
     * hash 算法
     * 简单取余
     *
     * @param o 被hash的对象
     * @return hash 结果
     */
    public Integer hash(Object o) {
        return o.hashCode() % allNodeNumber;
    }

    /**
     * 新增节点
     * 节点变动
     *
     * @param service 节点信息
     */
    public void addService(Service service) {
        List<Integer> virtualNodeList = initVirtualNode(service);
        // 对每个节点进行处理：如果新的节点有前置节点，需要做replicate log
        virtualNodeList.forEach(index -> {
            int lastIndex = sortUtil.getLastIndex((Integer[]) sortedNodeIndex.toArray(), 0, sortedNodeIndex.size(), index);
            if (!virtualNodeList.contains(lastIndex)) {
                // lastIndex 到 index 之间的节点数据复制到新节点上去
            } else {
                // 不需要操作
            }
        });

    }

    /**
     * 删除节点
     *
     * @param service 节点信息
     */
    public void deleteService(Service service) {
        // 删除节点要把被删除的节点位置和上一个节点位置的数据 移动到下一个节点位置上
    }

    /**
     * 对已经存在的节点进行位置排序
     * 利用归并排序
     */
    public void sortNodeIndex() {
        Integer[] indexArray = (Integer[]) serviceMap.keySet().toArray();
        sortUtil.mergeSort(indexArray);
    }
}

class sortUtil {

    /**
     * 归并排序
     */
    public static void mergeSort(Integer[] arr) {
        mergeSort(arr, 0, arr.length - 1);
    }

    public static void mergeSort(Integer[] arrays, int L, int R) {
        //如果只有一个元素，那就不用排序了
        if (L == R) {
            return;
        } else {
            //取中间的数，进行拆分
            int M = (L + R) / 2;
            //左边的数不断进行拆分
            mergeSort(arrays, L, M);
            //右边的数不断进行拆分
            mergeSort(arrays, M + 1, R);
            //合并
            merge(arrays, L, M + 1, R);
        }
    }

    public static void merge(Integer[] arrays, int L, int M, int R) {
        //左边的数组的大小
        int[] leftArray = new int[M - L];
        //右边的数组大小
        int[] rightArray = new int[R - M + 1];
        //往这两个数组填充数据
        if (M - L >= 0) System.arraycopy(arrays, L, leftArray, 0, M - L);
        if (R + 1 - M >= 0) System.arraycopy(arrays, M, rightArray, 0, R + 1 - M);
        int i = 0, j = 0;
        // arrays数组的第一个元素
        int k = L;

        //比较这两个数组的值，哪个小，就往数组上放
        while (i < leftArray.length && j < rightArray.length) {
            //谁比较小，谁将元素放入大数组中,移动指针，继续比较下一个
            if (leftArray[i] < rightArray[j]) {
                arrays[k] = leftArray[i];
                i++;
            } else {
                arrays[k] = rightArray[j];
                j++;
            }
            k++;
        }

        //如果左边的数组还没比较完，右边的数都已经完了，那么将左边的数抄到大数组中(剩下的都是大数字)
        while (i < leftArray.length) {
            arrays[k] = leftArray[i];
            i++;
            k++;
        }
        //如果右边的数组还没比较完，左边的数都已经完了，那么将右边的数抄到大数组中(剩下的都是大数字)
        while (j < rightArray.length) {
            arrays[k] = rightArray[j];
            k++;
            j++;
        }
    }

    /**
     * 获取下一个节点
     *
     * @param arr    数组
     * @param start  开始位置
     * @param end    结束为止
     * @param target 目标数据
     */
    public static int getNextIndex(Integer[] arr, int start, int end, int target) {
        if (start == end) {
            if (end == arr.length) {
                return 0;
            }
            return start + 1;
        }
        int mid = (start + end) / 2;
        if (arr[mid] < target) {
            return getNextIndex(arr, mid + 1, end, target);
        } else if (arr[mid] > target) {
            return getNextIndex(arr, start, mid, target);
        } else {
            return mid;
        }
    }

    /**
     * 获取上一个节点
     *
     * @param arr    数组
     * @param start  开始位置
     * @param end    结束为止
     * @param target 目标数据
     */
    public static int getLastIndex(Integer[] arr, int start, int end, int target) {
        if (start == end) {
            if (end == arr.length) {
                return end - 1;
            }
            return start;
        }
        int mid = (start + end) / 2;
        if (arr[mid] < target) {
            return getNextIndex(arr, mid + 1, end, target);
        } else if (arr[mid] > target) {
            return getNextIndex(arr, start, mid, target);
        } else {
            return mid;
        }
    }

}
