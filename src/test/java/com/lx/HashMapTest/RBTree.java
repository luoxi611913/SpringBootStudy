package com.lx.HashMapTest;

public class RBTree {

    //根节点
    public RBTreeNode mroot;

    //红色
    public static final boolean RED = false;

    //黑色
    public static final boolean BLACK = true;

    /*
    对X结点进行左旋
     */
    public void leftRotate(RBTreeNode x){
        System.out.println("以节点 "+x.key +"  进行左旋 ...");
        //找到右孩儿，记做y
        RBTreeNode y = x.rightSon;

        //y的左孩做x的右孩
        x.rightSon = y.leftSon;

        //如果y的父节点已经空，则为根节点
        if(null == y.parent){
            mroot = y;
        }else{
            y.parent = x.parent;
            //如果x原来是父节点的右孩子,y也就变成右孩子
            if(x.parent.leftSon == x){
                x.parent.leftSon = y;
            }else{
                x.parent.rightSon = y;
            }
        }

        //x变为y的左孩子
        y.leftSon = x;
        x.parent = y;
    }

    /*
    红黑树右旋
     */
    public void rightRotate(RBTreeNode target){

        //将目标点的左子节点取出
        RBTreeNode lef = target.leftSon;

        //lef右子节点成为目标的左子节点
        target.leftSon = lef.rightSon;
        lef.rightSon.parent = target;

        //目标的父节点成为lef的父节点
        if(null == target.parent){
            mroot = lef;
        }else{
            lef.parent = target.parent;

            //如果目标是左子节点
            if(target.parent.leftSon == target){
                target.parent.leftSon = lef;
            }else{
                target.parent.rightSon = lef;
            }

            //让lef成为目标点的父亲
            target.parent = lef;
            lef.rightSon = target;

            System.out.println("以节点 "+target.key +"  进行右旋 ...");
        }
    }
}
