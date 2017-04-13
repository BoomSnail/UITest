package com.xiaoshihua.thinkpad.uitest;


import java.lang.reflect.Constructor;
import java.util.Collection;

/**
 *
 * Created by ThinkPad on 2016/9/3.
 */
public class Test{

    public static void main(String[] args){
        BinaryTree b = new BinaryTree();
        b.addNode(8);
        b.addNode(3);
        b.addNode(10);
        b.addNode(1);
        b.addNode(6);
        b.addNode(14);
        b.addNode(4);
        b.addNode(7);
        b.addNode(13);
        b.printNode();


        NodeManager n = new NodeManager();
        n.add("1");
        n.add("3");
        n.add("2");
        n.add("5");
        n.add("7");
        n.print();
        n.del("3");
        n.print();

      //获取class泪
//        Cat cat = new Cat("mimi",2);
//        Class catClass = cat.getClass();
        Class catClass1 = Cat.class;

        try {
            Class catClass2 = Class.forName("com.xiaoshihua.thinkpad.uitest.Cat");
            Cat cat = (Cat) catClass2.newInstance();
            Constructor[] constructors = catClass2.getConstructors();
            for (int i = 0; i < constructors.length; i++) {
                System.out.println(constructors[i]);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        //通过类信息创建对象
        try {
            Cat cat = (Cat) catClass1.newInstance();

        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }
//
//   public static void main(String[] args){
//
//       File dir = new File("D:\\ps\\Adobe Bridge CS6");
//       List<File> list = fileToList(dir);
//       File file = new File(dir,"javaList.txt");
//       writeToFile(list,file.toString());
//   }
//
//
//    public static List<File> fileToList(File dir){
//        List<File> list = new ArrayList<>();
//        File[] files = dir.listFiles();
//        for (File file : files) {
//            if (file.isDirectory()) {
//                //递归遍历
//                fileToList(file);
//            } else {
//                if (file.getName().endsWith(".dll")) {
//                    list.add(file);
//                }
//            }
//        }
//        return list;
//    }
//
//    public static void writeToFile(List<File> list,String javaListFile){
//        File file = new File(javaListFile);
//        if (file.exists()) {
//            System.out.println("文件已存在");
//            return;
//        }
//        BufferedWriter bw = null;
//        try {
//            bw = new BufferedWriter(new FileWriter(javaListFile));
//            for (File f : list){
//                String path = f.getAbsolutePath();
//                bw.write(path);
//                bw.newLine();
//                bw.flush();
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }finally {
//            if (bw!= null) {
//                try {
//                    bw.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//    }
}


class NodeManager{

    private Node root;

    public void add(String name){
        if (root == null) {
            root = new Node(name);
        }else {
            root.addNode(name);
        }
    }

    public void del(String name){
        if (root.getName().equals(name)) {
            root = root.next;
        }else {
            root.delNode(name);
        }
    }

    public void print(){
        if (root != null) {
            System.out.print(root.getName() + " ");
            root.printNode();
        }
    }

    class Node{
        private String name;
        private Node next;

        public Node(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void addNode(String name){
            if (this.next == null) {
                this.next = new Node(name);
            } else  {
               this.next.addNode(name);
            }
        }

        public void delNode(String name){
            if (this.next != null) {
                if (this.next.getName().equals(name)) {
                    this.next = this.next.next;
                }else{
                    this.next.delNode(name);
                }
            }
        }

        public void printNode(){
            if (this.next != null) {
                System.out.print(this.next.getName());
                this.next.printNode();
            }
        }
    }
}


class  BinaryTree{
    private Node root;
    public void addNode(int data){
        if (root == null) {
            root = new Node(data);//根节点为空，就将其作为根节点
        } else {//如果有根节点，则需要添加数据
            root.addData(data);
        }
    }

    public void printNode(){
        if (root != null) {
            root.print();
        }
    }
    private class Node{
        private int data;
        private Node left;
        private Node right;

        public Node(int data) {
            this.data = data;
        }

        public void addData(int data){
            if (this.data > data) {
                if (this.left == null) {//左边为空，则新建
                    this.left = new Node(data);
                }else {//递归
                    this.left.addData(data);
                }
            } else if (this.data < data) {
                if (this.right == null) {
                    this.right = new Node(data);
                }else {
                    this.right.addData(data);
                }
            }
        }

        public void print(){
            if (this.left != null) {
                this.left.print();
            }

            System.out.print(this.data + "->");
            if (this.right != null) {
                this.right.print();
            }
        }
    }
}


