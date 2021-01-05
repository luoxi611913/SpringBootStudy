package com.lx.HashMapTest;

/**
 * 红黑树的节点
 */
public class RBTreeNode {

    public RBTreeNode(String key, RBTreeNode leftSon, RBTreeNode rightSon, RBTreeNode parent, Boolean color) {
        this.key = key;
        this.leftSon = leftSon;
        this.rightSon = rightSon;
        this.parent = parent;
        this.color = color;
    }

    /*
        KEY值
         */
    public String key;

    /*
    左孩儿
     */
    public RBTreeNode leftSon;

    /*
    右孩儿
     */
    public RBTreeNode rightSon;

    /*
    父亲
     */
    public RBTreeNode parent;

    /*
    颜色
     */
    public Boolean color;
}
