package huffman;

import tree.TreeNode;

public class HuffmanNode extends TreeNode{
	private Byte data;
	
	public HuffmanNode(Byte data, int value) {
		super(value);
		this.data = data;
	}

	public Byte getData() {
		return data;
	}

	public void setData(Byte data) {
		this.data = data;
	}
		
}
