package com.roll.casserole.design.composite;

/**
 * leaf在组合中表示叶节点对象，叶节点没有子节点
 *
 * @author roll
 * created on 2019-07-28 22:42
 */
public class Leaf extends Component {
    public Leaf(String name) {
        super(name);
    }

    @Override
    void add(Component component) {
        System.out.println("con not add to a leaf");
    }

    @Override
    void remove(Component component) {
        System.out.println("can not remove form a leaf");
    }

    @Override
    void display(int depth) {
        System.out.println("-" + depth);
    }
}
