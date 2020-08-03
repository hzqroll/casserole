package com.roll.casserole.jvm.bytecode;

/**
 * Classfile /Users/haozongqiang/github/casserole/target/classes/com/roll/casserole/jvm/bytecode/MyTest7.class
 * Last modified 2020年8月2日; size 861 bytes
 * MD5 checksum 72d1a87db91903302c5985ad8caff2a9
 * Compiled from "MyTest7.java"
 * public class com.roll.casserole.jvm.bytecode.MyTest7
 * minor version: 0
 * major version: 52
 * flags: (0x0021) ACC_PUBLIC, ACC_SUPER
 * this_class: #5                          // com/roll/casserole/jvm/bytecode/MyTest7
 * super_class: #10                        // java/lang/Object
 * interfaces: 0, fields: 3, methods: 4, attributes: 1
 * Constant pool:
 * #1 = Methodref          #10.#34        // java/lang/Object."<init>":()V
 * #2 = String             #35            // Welcome
 * #3 = Fieldref           #5.#36         // com/roll/casserole/jvm/bytecode/MyTest7.str:Ljava/lang/String;
 * #4 = Fieldref           #5.#37         // com/roll/casserole/jvm/bytecode/MyTest7.x:I
 * #5 = Class              #38            // com/roll/casserole/jvm/bytecode/MyTest7
 * #6 = Methodref          #5.#34         // com/roll/casserole/jvm/bytecode/MyTest7."<init>":()V
 * #7 = Methodref          #5.#39         // com/roll/casserole/jvm/bytecode/MyTest7.setX:(I)V
 * #8 = Methodref          #40.#41        // java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
 * #9 = Fieldref           #5.#42         // com/roll/casserole/jvm/bytecode/MyTest7.in:Ljava/lang/Integer;
 * #10 = Class              #43            // java/lang/Object
 * #11 = Utf8               str
 * #12 = Utf8               Ljava/lang/String;
 * #13 = Utf8               x
 * #14 = Utf8               I
 * #15 = Utf8               in
 * #16 = Utf8               Ljava/lang/Integer;
 * #17 = Utf8               <init>
 * #18 = Utf8               ()V
 * #19 = Utf8               Code
 * #20 = Utf8               LineNumberTable
 * #21 = Utf8               LocalVariableTable
 * #22 = Utf8               this
 * #23 = Utf8               Lcom/roll/casserole/jvm/bytecode/MyTest7;
 * #24 = Utf8               main
 * #25 = Utf8               ([Ljava/lang/String;)V
 * #26 = Utf8               args
 * #27 = Utf8               [Ljava/lang/String;
 * #28 = Utf8               myTest7
 * #29 = Utf8               setX
 * #30 = Utf8               (I)V
 * #31 = Utf8               <clinit>
 * #32 = Utf8               SourceFile
 * #33 = Utf8               MyTest7.java
 * #34 = NameAndType        #17:#18        // "<init>":()V
 * #35 = Utf8               Welcome
 * #36 = NameAndType        #11:#12        // str:Ljava/lang/String;
 * #37 = NameAndType        #13:#14        // x:I
 * #38 = Utf8               com/roll/casserole/jvm/bytecode/MyTest7
 * #39 = NameAndType        #29:#30        // setX:(I)V
 * #40 = Class              #44            // java/lang/Integer
 * #41 = NameAndType        #45:#46        // valueOf:(I)Ljava/lang/Integer;
 * #42 = NameAndType        #15:#16        // in:Ljava/lang/Integer;
 * #43 = Utf8               java/lang/Object
 * #44 = Utf8               java/lang/Integer
 * #45 = Utf8               valueOf
 * #46 = Utf8               (I)Ljava/lang/Integer;
 * {
 * java.lang.String str;
 * descriptor: Ljava/lang/String;
 * flags: (0x0000)
 * <p>
 * public static java.lang.Integer in;
 * descriptor: Ljava/lang/Integer;
 * flags: (0x0009) ACC_PUBLIC, ACC_STATIC
 * <p>
 * public com.roll.casserole.jvm.bytecode.MyTest7();
 * descriptor: ()V
 * flags: (0x0001) ACC_PUBLIC
 * Code:
 * stack=2, locals=1, args_size=1
 * 0: aload_0
 * 1: invokespecial #1                  // Method java/lang/Object."<init>":()V
 * 4: aload_0
 * 5: ldc           #2                  // String Welcome
 * 7: putfield      #3                  // Field str:Ljava/lang/String;
 * 10: aload_0
 * 11: iconst_5
 * 12: putfield      #4                  // Field x:I
 * 15: return
 * LineNumberTable:
 * line 7: 0
 * line 8: 4
 * line 10: 10
 * LocalVariableTable:
 * Start  Length  Slot  Name   Signature
 * 0      16     0  this   Lcom/roll/casserole/jvm/bytecode/MyTest7;
 * <p>
 * public static void main(java.lang.String[]);
 * descriptor: ([Ljava/lang/String;)V
 * flags: (0x0009) ACC_PUBLIC, ACC_STATIC
 * Code:
 * stack=2, locals=2, args_size=1
 * 0: new           #5                  // class com/roll/casserole/jvm/bytecode/MyTest7
 * 3: dup
 * 4: invokespecial #6                  // Method "<init>":()V
 * 7: astore_1
 * 8: aload_1
 * 9: bipush        8
 * 11: invokevirtual #7                  // Method setX:(I)V
 * 14: bipush        20
 * 16: invokestatic  #8                  // Method java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
 * 19: putstatic     #9                  // Field in:Ljava/lang/Integer;
 * 22: return
 * LineNumberTable:
 * line 15: 0
 * line 17: 8
 * line 19: 14
 * line 20: 22
 * LocalVariableTable:
 * Start  Length  Slot  Name   Signature
 * 0      23     0  args   [Ljava/lang/String;
 * 8      15     1 myTest7   Lcom/roll/casserole/jvm/bytecode/MyTest7;
 * <p>
 * public void setX(int);
 * descriptor: (I)V
 * flags: (0x0001) ACC_PUBLIC
 * Code:
 * stack=2, locals=2, args_size=2
 * 0: aload_0
 * 1: iload_1
 * 2: putfield      #4                  // Field x:I
 * 5: return
 * LineNumberTable:
 * line 23: 0
 * line 24: 5
 * LocalVariableTable:
 * Start  Length  Slot  Name   Signature
 * 0       6     0  this   Lcom/roll/casserole/jvm/bytecode/MyTest7;
 * 0       6     1     x   I
 * <p>
 * static {};
 * descriptor: ()V
 * flags: (0x0008) ACC_STATIC
 * Code:
 * stack=1, locals=0, args_size=0
 * 0: bipush        10
 * 2: invokestatic  #8                  // Method java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
 * 5: putstatic     #9                  // Field in:Ljava/lang/Integer;
 * 8: return
 * LineNumberTable:
 * line 12: 0
 * }
 * SourceFile: "MyTest7.java"
 * <p>@author roll
 * <p>created on 2020/8/2 9:50 上午
 */
public class MyTest7 {
    String str = "Welcome";

    private int x = 5;

    public static Integer in = 10;

    public Object object;

    public static void main(String[] args) {
        MyTest7 myTest7 = new MyTest7();

        myTest7.setX(8);

        in = 20;
    }

    private synchronized void setX(int x) {
        this.x = x;
    }

    private void test(String srg) {
        synchronized (str) {
            System.out.println("hello world");
        }
    }

    private synchronized static void test2() {

    }
}
