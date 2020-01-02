package huffman;

import java.io.*;
import java.util.List;
import java.util.Map;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

public class HuffmanCodeTest {
	public static void main(String[] args){
		String msg = "can you can a can as a can canner can a can";
		byte[] bytes = msg.getBytes();
		System.out.println(Arrays.toString(bytes));
		byte[] b = huffmanZip(bytes);
		System.out.println(Arrays.toString(b));
		byte[] newbyte = decode(huffCodes,b);
		System.out.println(Arrays.toString(newbyte));
		System.out.println(new String(newbyte));

		//压缩文件
		String src = "1.bmp";
		String dst = "2.zip";
		try{
			zipFile(src,dst);
		}catch (IOException e){
			e.printStackTrace();
		}
	}

	private static void zipFile(String src, String dst) throws IOException{
		InputStream is =new FileInputStream(src);
		byte[] b = new byte[is.available()];
		is.read(b);
		is.close();
		byte[] byteZip=huffmanZip(b);
		OutputStream os = new FileOutputStream(dst);
		ObjectOutputStream oos = new ObjectOutputStream(os);
		oos.writeObject(byteZip);
		oos.writeObject(huffCodes);
		oos.close();
		os.close();
		System.out.println(b);
		System.out.println(byteZip);
	}

	private static byte[] decode(Map<Byte, String> huffCodes, byte[] bytes) {
		StringBuilder sb = new StringBuilder();
		//把byte数组转化为一个二进制的字符串
		for (int i =0;i<bytes.length;i++){
			byte b = bytes[i];
			//是否为最后一个字节
			boolean flag = (i==bytes.length-1);
			sb.append(byteToBitStr(!flag,b));
		}
//		System.out.println(sb);
		//把编码的键值进行调换
		Map<String,Byte> map = new HashMap<String, Byte>();
		for(Map.Entry<Byte,String> entry:huffCodes.entrySet() ){
			map.put(entry.getValue(),entry.getKey());
		}
		List<Byte> list =new ArrayList<Byte>();
		for(int i =0;i<sb.length();){
			int count=1;
			boolean flag = true;
			Byte b = null;
			while (flag){
				String s = sb.substring(i,i+count);
				b = map.get(s);
				if(b==null){
					count++;
				}else {
					flag=false;
				}
			}
			list.add(b);
			i+=count;
		}
		byte[] b = new byte[list.size()];
		for (int i=0;i<b.length;i++){
			b[i] = list.get(i);
		}
		return b;
	}

	private static String byteToBitStr(boolean flag,byte b) {
		int tmp = b;
		if (flag){
			tmp |=256;
		}
		String str =  Integer.toBinaryString(tmp);
		if (flag){
			return str.substring(str.length()-8);
		}else {
			return str;
		}
//		System.out.println(str);
	}

	private static byte[] huffmanZip(byte[] bytes) {
		List<HuffmanNode> nodes= getNodes(bytes);
		HuffmanNode tree = createHuffmanTress(nodes);
		//创建一个赫夫曼编码表
		Map<Byte, String> huffmanCodes = getCodes(tree);
//		System.out.println(huffmanCodes);
		return zip(bytes,huffmanCodes);
	}

	/**Using code map to compress the byte[]
	 * @param bytes
	 * @param huffmanCodes
	 * @return
	 */
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
//			System.out.println(strByte);
			byte byt = (byte)Integer.parseInt(strByte, 2);
			by[index] = byt;
			index++;
		}
		return by;
	}
	
	//Local variable to store code entry
	static StringBuilder sb =new StringBuilder();
	//Local variable to shore code map
	static Map<Byte, String> huffCodes = new HashMap<Byte, String>();
	
	/**Generate codes map
	 * @param tree
	 * @return
	 */
	private static Map<Byte, String> getCodes(HuffmanNode tree) {
		if (tree==null) {
			return null;
		}
		getCodes((HuffmanNode)tree.getLeftNode(),"0",sb);
		getCodes((HuffmanNode)tree.getRightNode(),"1",sb);
		return huffCodes;
	}

	/** Generate codes map
	 * @param node
	 * @param code
	 * @param sb
	 */
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
