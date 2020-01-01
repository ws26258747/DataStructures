package tree;

public class BinaryTreeTest {
	public static void main(String[] args) {
		BinaryTree binTree =new BinaryTree();
		TreeNode root = new TreeNode(1);
		
		root.setLeftNode(new TreeNode(2));
		root.setRightNode(new TreeNode(3));
		
		binTree.setRootTreeNode(root);
		//System.out.println(binTree);
		
		root.getLeftNode().setLeftNode(new TreeNode(4));
		root.getLeftNode().setRightNode(new TreeNode(5));
		
		root.getRightNode().setLeftNode(new TreeNode(6));
		root.getRightNode().setRightNode(new TreeNode(7));
		
		//前序遍历
		binTree.frontShow();
		//中序遍历
		binTree.middleShow();
		//后序遍历
		binTree.postShow();
	}
}
