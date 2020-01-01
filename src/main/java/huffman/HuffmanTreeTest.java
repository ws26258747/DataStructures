package huffman;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

import tree.TreeNode;

public class HuffmanTreeTest {
	public static void main(String[] args) {
		int[] array = new int[]{3,7,8,29,5,11,23,14};
		TreeNode node = createHuffmanTree(array);
		System.out.println(node);
	}

	private static TreeNode createHuffmanTree(int[] array) {
		List<TreeNode> nodes = new ArrayList<TreeNode>();
		for (int i = 0; i < array.length; i++) {
			nodes.add(new TreeNode(array[i]));
		}
		while (nodes.size()>1) {
			Collections.sort(nodes);
			TreeNode leftTreeNode = nodes.get(nodes.size()-1);
			TreeNode rightTreeNode = nodes.get(nodes.size()-2);
			TreeNode parentNode = new TreeNode(leftTreeNode.getValue()+rightTreeNode.getValue());
			parentNode.setLeftNode(leftTreeNode);
			parentNode.setRightNode(rightTreeNode);
			nodes.remove(leftTreeNode);
			nodes.remove(rightTreeNode);
			nodes.add(parentNode);
		}
		return nodes.get(0);
	}
}
