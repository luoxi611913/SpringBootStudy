package com.lx.HashMapTest;

public class Test {
    public static void main(String[] args) {

        RBTree rbTree = new RBTree();

        //定义根节点
        rbTree.mroot = new RBTreeNode("0",null,null,null,RBTree.RED);

        //定义一个结点
        RBTreeNode node1 = new RBTreeNode("1",null,null,null,RBTree.RED);
        RBTreeNode node2 = new RBTreeNode("2",null,null,null,RBTree.RED);
        RBTreeNode node3 = new RBTreeNode("3",null,null,null,RBTree.RED);
        RBTreeNode node4 = new RBTreeNode("4",null,null,null,RBTree.RED);
        RBTreeNode node5 = new RBTreeNode("5",null,null,null,RBTree.RED);

        rbTree.mroot.rightSon = node1;
        node1.parent = rbTree.mroot;

        node1.leftSon = node2;
        node2.parent = node1;

        node1.rightSon = node3;
        node3.parent = node1;

        node3.leftSon = node4;
        node3.rightSon = node5;
        node4.parent = node3;
        node5.parent = node3;

        System.out.println("根节点: "+rbTree.mroot.key);
        System.out.println("根节点右子节点: "+rbTree.mroot.rightSon.key);
        System.out.println("根节点右子节点的右子节点: "+rbTree.mroot.rightSon.rightSon.key);

        rbTree.leftRotate(node1);

        System.out.println("根节点: "+rbTree.mroot.key);
        System.out.println("根节点右子节点: "+rbTree.mroot.rightSon.key);
        System.out.println("根节点右子节点的左子节点: "+rbTree.mroot.rightSon.leftSon.key);

        rbTree.rightRotate(node3);

        System.out.println("根节点: "+rbTree.mroot.key);
        System.out.println("根节点右子节点: "+rbTree.mroot.rightSon.key);
        System.out.println("根节点右子节点的右子节点: "+rbTree.mroot.rightSon.rightSon.key);
    }
}
