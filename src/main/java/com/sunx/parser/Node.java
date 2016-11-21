package com.sunx.parser;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.*;

public class Node implements List<Object>{
	/**
	 * 存储该标签所有的子标签
	 */
	private List<Object> list = new ArrayList<Object>();

	/**
	 * 获取数据
	 * @return
     */
	public static Node me(){
		return new Node();
	}
	/**
	 * 将抽取到的添加到集合中
	 * @param eles
	 */
	public Node $(Elements eles){
		for(Element ele : eles){
			list.add(ele);
		}
		return this;
	}
	/**
	 * 通过cssQuery查询语句来获取结果集合
	 * 对结果集合中重复的数据在进行解析
	 * @param index
	 * @param cssQuery
	 * @return
	 */
	public Object $(int index,String cssQuery){
		if(index >= list.size())return null;
		Elements eles = ((Element) list.get(index)).select(cssQuery);
		if(eles == null)return null;
		Node domNode = new Node();
		domNode.$(eles);
		return domNode;
	}
	/**
	 * 获取标签内容
	 * @param index 	对象list集合中的下标
	 * @param cssQuery	查询语句
	 * @return
	 */
	public String css(int index,String cssQuery){
		return css(index,cssQuery,"text","");
	}
	/**
	 * 获取标签内容
	 * @param index 	对象list集合中的下标
	 * @param cssQuery	查询语句
	 * @param subIndex	css查询结果集合中的下标
	 * @return
	 */
	public String css(int index,String cssQuery,int subIndex){
		return css(index,cssQuery,subIndex,"text","");
	}
	/**
	 * 获取属性值
	 * @param index 	对象list集合中的下标
	 * @param cssQuery	查询语句
	 * @param attr		属性名称
	 * @return
	 */
	public String css(int index,String cssQuery,String attr){
		return css(index,cssQuery,"attr",attr);
	}
	/**
	 * 根据给定的参数获取属性值
	 * @param index 	对象list集合中的下标
	 * @param cssQuery	查询语句
	 * @param attr		属性名称
	 * @param subIndex 	css查询结果集合中的下标
	 * @return
	 */
	public String css(int index,String cssQuery,int subIndex,String attr){
		return css(index,cssQuery,subIndex,"attr",attr);
	}
	/**
	 * 获取根据cssQuery查询出来的结果标签
	 * 通过正则抽取其中的特定值
	 * @param index
	 * @param cssQuery
	 * @param regex
	 * @return
	 */
	public String regex(int index,String cssQuery,String regex){
		return css(index,cssQuery,"regex",regex);
	}
	/**
	 * 根据给定的下标,cssQuery执行相应的方法和参数
	 * 获取执行的值
	 * @param index
	 * @param cssQuery
	 * @param method
	 * @param param
	 * @return
	 */
	public String css(int index,String cssQuery,String method,String param){
		Elements ele = null;
//		如果查询语句为空,则默认查询的为本标签
		if(index >= list.size())return null;
		Element node = (Element) list.get(index);
		if(cssQuery == null || "".equals(cssQuery)){
			ele = node.getAllElements();
		}else{
			ele = node.select(cssQuery);
		}
		if(ele == null || ele.toString().length() <= 0)return null;
		String result = null;
		if("regex".equals(method)){
			result = Ext.extractor(ele.html(),param);
		}else{
			result =  (String) Ext.extractor(ele,method,param);
		}
		return result;
	}
	/**
	 * 通过给定的下标以及cssQuery查询语句
	 * 执行相应的方法获取相应的值
	 * @param index
	 * @param cssQuery
	 * @param cindex
	 * @param method
	 * @param param
	 * @return
	 */
	public String css(int index,String cssQuery,int cindex,String method,String param){
		Element ele = null;
//		如果查询语句为空,则默认查询的为本标签
		if(index >= list.size())return null;
		Element node = (Element) list.get(index);
		Elements eles = node.select(cssQuery);
		if(eles == null || eles.size() <= 0)return null;
		ele = eles.get(cindex);
		if(ele == null || ele.toString().length() <= 0)return null;
		String result = null;
		if("regex".equals(method)){
			result = Ext.extractor(ele.html(),param);
		}else{
			result =  (String) Ext.extractor(ele.getAllElements(),method,param);
		}
		return result;
	}
	/**
	 * 判断满足cssQuery查询语句的标签是否存在
	 * 存在：返回1
	 * 不存在：返回0
	 * @param index
	 * @param cssQuery
	 * @return
	 */
	public int isExit(int index,String cssQuery){
		Elements ele = ((Element) list.get(index)).select(cssQuery);
		if(ele != null && ele.size() > 0)return 1;
		return 0;
	}
	
	
	public int size() {
		return list.size();
	}

	
	public boolean isEmpty() {
		return list.isEmpty();
	}

	public boolean contains(Object o) {
		return list.contains(o);
	}

	
	public Iterator<Object> iterator() {
		return list.iterator();
	}

	
	public Object[] toArray() {
		return list.toArray();
	}

	public <T> T[] toArray(T[] a) {
		return list.toArray(a);
	}

	public boolean add(Object e) {
		return list.add(e);
	}

	
	public boolean remove(Object o) {
		return list.remove(o);
	}
	
	public boolean containsAll(Collection<?> c) {
		return list.containsAll(c);
	}

	
	public boolean addAll(Collection<? extends Object> c) {
		return list.addAll(c);
	}

	
	public boolean addAll(int index, Collection<? extends Object> c) {
		return list.addAll(index, c);
	}

	
	public boolean removeAll(Collection<?> c) {
		return list.removeAll(c);
	}

	
	public boolean retainAll(Collection<?> c) {
		return list.retainAll(c);
	}

	
	public void clear() {
		list.clear();
	}

	
	public Object get(int index) {
		return list.get(index);
	}

	
	public Object set(int index, Object element) {
		return list.set(index, element);
	}

	
	public void add(int index, Object element) {
		list.add(index, element);
	}

	
	public Object remove(int index) {
		return list.remove(index);
	}

	
	public int indexOf(Object o) {
		return list.indexOf(o);
	}

	
	public int lastIndexOf(Object o) {
		return list.lastIndexOf(o);
	}

	
	public ListIterator<Object> listIterator() {
		return list.listIterator();
	}

	
	public ListIterator<Object> listIterator(int index) {
		return list.listIterator(index);
	}

	
	public List<Object> subList(int fromIndex, int toIndex) {
		return list.subList(fromIndex, toIndex);
	}
}