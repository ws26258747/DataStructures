package tree;

public class BinaryTree {
	TreeNode rootTreeNode;
	public void setRootTreeNode(TreeNode rooTreeNode) {
		this.rootTreeNode = rooTreeNode;
	}
	public TreeNode getRootTreeNode() {
		return rootTreeNode;
	}
	public void frontShow() {
		rootTreeNode.frontShow();
	}
	public void middleShow() {
		rootTreeNode.middleShow();
	}
	public void postShow() {
		rootTreeNode.postShow();
	}
	@Override
	public String toString() {
		return "BinaryTree [rooTreeNode=" + rootTreeNode + "]";
	}
	
}
