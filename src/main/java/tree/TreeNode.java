package tree;

public class TreeNode  implements Comparable<TreeNode>{
	int value;
	TreeNode leftNode;
	TreeNode rightNode;
	public TreeNode(int value) {
		this.value = value;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public TreeNode getLeftNode() {
		return leftNode;
	}
	public void setLeftNode(TreeNode leftNode) {
		this.leftNode = leftNode;
	}
	public TreeNode getRightNode() {
		return rightNode;
	}
	public void setRightNode(TreeNode rightNode) {
		this.rightNode = rightNode;
	}
	public void frontShow() {
		System.out.println(value);
		if(leftNode != null) {
			leftNode.frontShow();
		}
		if(rightNode != null) {
			rightNode.frontShow();
		}
	}
	public void middleShow() {
		if(leftNode != null) {
			leftNode.frontShow();
		}
		System.out.println(value);
		if(rightNode != null) {
			rightNode.frontShow();
		}

	}
	public void postShow() {
		if(leftNode != null) {
			leftNode.frontShow();
		}
		if(rightNode != null) {
			rightNode.frontShow();
		}
		System.out.println(value);

	}
	@Override
	public String toString() {
		return "TreeNode [value=" + value + ", leftNode=" + leftNode + ", rightNode=" + rightNode + "]";
	}
	public int compareTo(TreeNode o) {	
		return -(this.value - o.value);
	}
}
