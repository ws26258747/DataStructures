package huffman;

import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;

import tree.TreeNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;

public class HuffmanCodeTest {
	public static void main(String[] args) {
		String msg = "can you can a can as a can canner can a can";
		byte[] bytes = msg.getBytes();
		//System.out.println(Arrays.toString(bytes));
		byte[] b = huffmanZip(bytes);
		System.out.println(Arrays.toString(b));
	}

	private static byte[] huffmanZip(byte[] bytes) {
		List<HuffmanNode> nodes= getNodes(bytes);
		HuffmanNode tree = createHuffmanTress(nodes);
		//创建一个赫夫曼编码表
		Map<Byte, String> huffmanCodes = getCodes(tree);
		System.out.println(huffmanCodes);
		byte[] b = zip(bytes,huffmanCodes);
		return b;
	}

	private static byte[] zip(byte[] bytes, Map<Byte, String> huffmanCodes) {
		StringBuilder sBuilder = new StringBuilder();
		for(byte b:bytes) {
			sBuilder.append(huffmanCodes.get(b));
		}
		int len;
		if(sBuilder.length()%8 == 0) {
			len = sBuilder.length()/8;
		}else {
			len = sBuilder.length()/8+1;
		}
		byte[] by = new byte[len];
		int index=0;
		for (int i = 0; i < sBuilder.length(); i+=8) {
			String strByte;
			if(i+8>sBuilder.length()) {
				strByte = sBuilder.substring(i);				
			}else {
				strByte = sBuilder.substring(i, i+8);								
			}
			//System.out.println(strByte);
			byte byt = (byte)Integer.parseInt(strByte, 2);
			by[index] = byt;
			index++;
		}
		return by;
	}
	
	static StringBuilder sb =new StringBuilder();
	static Map<Byte, String> huffCodes = new HashMap<Byte, String>();
	
	private static Map<Byte, String> getCodes(HuffmanNode tree) {
		if (tree==null) {
			return null;
		}
		getCodes((HuffmanNode)tree.getLeftNode(),"0",sb);
		getCodes((HuffmanNode)tree.getRightNode(),"1",sb);
		return huffCodes;
	}

	private static void getCodes(HuffmanNode node, String code, StringBuilder sb) {
		StringBuilder sb2 = new StringBuilder(sb);
		sb2.append(code);
		if(node.getData()==null) {
			getCodes((HuffmanNode)node.getLeftNode(), "0", sb2);
			getCodes((HuffmanNode)node.getRightNode(), "1", sb2);
		}else {
			huffCodes.put(node.getData(), sb2.toString());
		}
	}

	private static HuffmanNode createHuffmanTress(List<HuffmanNode> nodes) {
		while(nodes.size()>1) {
			
			Collections.sort(nodes);
			HuffmanNode leftHuffmanNode = nodes.get(nodes.size()-1);
			HuffmanNode rightHuffmanNode = nodes.get(nodes.size()-2);
			HuffmanNode parentHuffmanNode = new HuffmanNode(null, leftHuffmanNode.getValue()+rightHuffmanNode.getValue());
			parentHuffmanNode.setLeftNode(leftHuffmanNode);
			parentHuffmanNode.setRightNode(rightHuffmanNode);
			nodes.remove(leftHuffmanNode);
			nodes.remove(rightHuffmanNode);
			nodes.add(parentHuffmanNode);
		}
		return nodes.get(0);
	}

	/**
	 * @param bytes
	 * @return
	 */
	private static List<HuffmanNode> getNodes(byte[] bytes) {
		List<HuffmanNode> nodes = new ArrayList<HuffmanNode>();
		Map<Byte, Integer> counts = new HashMap<Byte, Integer>();
		for (byte b:bytes) {
			Integer count = counts.get(b);
			if(count == null) {
				counts.put(b, 1);
			}else {
				counts.put(b, count+1);
			}
		}
		//System.out.println(counts);
		for(Map.Entry<Byte, Integer> entry:counts.entrySet()) {
			nodes.add(new HuffmanNode(entry.getKey(), entry.getValue()));
		}
		return nodes;
	}
}
