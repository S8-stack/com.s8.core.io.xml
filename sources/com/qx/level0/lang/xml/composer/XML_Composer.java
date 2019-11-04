package com.qx.level0.lang.xml.composer;

import java.util.List;
import java.util.Stack;

import com.qx.level0.lang.xml.XML_Context;
import com.qx.level0.lang.xml.XML_Syntax;
import com.qx.level0.lang.xml.handler.TypeHandler;

public class XML_Composer {

	private XML_Context context;

	private Stack<ComposableElement> stack = new Stack<>(); 
	
	private XML_StreamWriter writer;

	public XML_Composer(XML_Context context, XML_StreamWriter writer) {
		super();
		this.context = context;
		this.writer = writer;
	}
	
	public void compose(Object object) throws Exception{
		
		writer.append(XML_Syntax.HEADER+"\n");
		add(new ObjectComposableElement(this, "root", object));
		
		while(!stack.isEmpty()){
			stack.pop().compose(writer);
		}
	}
	
	
	/**
	 * 
	 * @param element
	 */
	public void add(ComposableElement element){
		stack.push(element);
	}
	
	
	public void add(List<ComposableElement> elements){
		int n = elements.size();
		for(int i=n-1; i>=0; i--){
			stack.push(elements.get(i));
		}
	}

	
	/**
	 * 
	 * @param typeName
	 * @return
	 */
	public TypeHandler getTypeHandler(String typeName){
		return context.getByDeserialName(typeName);
	}
	
}