package com.company;


import java.util.ArrayList;
import java.util.List;

public class TreeNode<T> {

    private T value = null;

    private List<TreeNode<T>> children = new ArrayList<>();

    private TreeNode<T> parent = null;

    public TreeNode(T value) {
        this.value = value;
    }

    public void addChild(TreeNode<T> child){
        child.setParent(this);
        this.children.add(child);
    }

    private void setParent(TreeNode<T> parent){
        this.parent = parent;
    }

    private TreeNode<T> getParent(){
        return parent;
    }

    private TreeNode<T> getHighestParent(){
        TreeNode<T> highestParent = this;
        while(highestParent.parent != null){
            highestParent = getParent();
        }
        return highestParent;
    }

}
