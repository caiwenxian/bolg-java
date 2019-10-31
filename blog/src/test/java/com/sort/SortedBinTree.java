package com.sort;

import org.junit.Test;

/**
 * 二叉树排序
 * @author caiwx
 * @date 2019年03月19日 10:04
 *
 */
public class SortedBinTree {

    /**
     * 节点定义
     * @author caiwx
     * @date 2019年03月19日 10:25
     * 
     */
    public class Node {

        int data;   //节点值

        Node left;   //左节点

        Node right;   //右节点

        public Node(int data) {
            this.data = data;
            left = null;
            right = null;
        }

    }

    /**
     * 搜索二叉树
     * @author caiwx
     * @date 2019年03月19日 10:26
     * 
     */
    public class BinSearchTree {

        private Node root;  //根节点

        public BinSearchTree() {
            this.root = null;
        }


        /**
         * 搜索函数
         * 先从根节点匹配，不匹配则判断要比较的值是否大于根节点的值，大于则在左节点继续查询，小于则反之
         * @author caiwx
         * @date 2019年03月19日 10:30
         *
         */
        public boolean find(int id) {
            Node current = root;
            while (current != null) {
                if(current.data == id) {
                    return true;
                } else if (current.data > id) {
                    current = current.left;
                } else {
                    current = current.right;
                }
            }
            return false;
        }

        /**
         * 插入函数
         * @author caiwx
         * @date 2019年03月19日 10:36
         *
         */
        public void insert(int id) {
            Node newNode = new Node(id);    //新节点
            if(root == null) {
                root = newNode;
                return;
            }
            Node current = root;
            Node parent = null;
            while (true) {
                parent = current;
                if (id < current.data) {
                    current = current.left;
                    if (current == null) {
                        parent.left = newNode;
                        break;
                    }
                } else {
                    current = current.right;
                    if (current == null) {
                        parent.right = newNode;
                        break;
                    }
                }
            }

        }

        public void display() {
            display(root);
        }

        private void display(Node node) {
            if (node != null) {
                display(node.left);
                System.out.print(" " + node.data);
                display(node.right);
            }
        }
    }

    @Test
    public void test1() {
        BinSearchTree binSearchTree = new BinSearchTree();
        binSearchTree.insert(1);
        binSearchTree.insert(7);
        binSearchTree.insert(2);
        binSearchTree.insert(6);
        binSearchTree.insert(9);
        binSearchTree.insert(3);
        System.out.println("树的结构：");
        binSearchTree.display();
        System.out.println("搜索：" + binSearchTree.find(6));
        System.out.println("搜索：" + binSearchTree.find(5));
    }
}

