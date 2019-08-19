package com.roll.casserole.buffer;

/**
 * @author roll
 * created on 2019-08-15 20:20
 */
public class BufferTest1 {


    public void change(int i) {
        i = 123;
    }

    public void change1(Integer integer) {
        integer = 1234;
    }

    public static void main(String[] args) {

        BufferTest1 bufferTest1 = new BufferTest1();
        int a = 1;
        System.out.println(a);
        bufferTest1.change(a);
        System.out.println(a);

        Integer b = new Integer(9);
        System.out.println(b);
        bufferTest1.change1(b);
        System.out.println(b);

        bufferTest1.test();
        System.out.println("WF2019062517040623819175".hashCode());
    }

    public void test() {
        Data data = new Data(100);
        System.out.println("data: " + data.getData());
        BufferTest1 bufferTest1 = new BufferTest1();
        bufferTest1.change2(data);
        System.out.println("change data:" + data.getData());
    }

    public void change2(Data data) {
        //data.setData(20);
        data = new Data(20);
    }

    class Data {

        public Data() {
        }

        public Data(int data) {
            this.data = data;
        }

        int data;

        public int getData() {
            return data;
        }

        public void setData(int data) {
            this.data = data;
        }
    }
}
