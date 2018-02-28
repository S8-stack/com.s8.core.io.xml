package com.qx.lang.xml.composer;

import java.util.ArrayList;
import java.util.List;

import com.qx.lang.xml.handler.AttributeGetter;
import com.qx.lang.xml.handler.ElementGetter;
import com.qx.lang.xml.handler.TypeHandler;

public class ObjectComposableElement extends ComposableElement {


	private Object object;

	private TypeHandler typeHandler;

	private Mode mode = Mode.START;

	private enum Mode {
		START, END;
	}

	/**
	 * 
	 * @param context
	 * @param fieldValue
	 */
	public ObjectComposableElement(XML_Composer composer, String fieldName, Object fieldValue){
		super(composer, fieldName);
		this.object = fieldValue;
		this.typeHandler = composer.getTypeHandler(fieldValue.getClass().getName());
	}


	@Override
	public void compose(XML_StreamWriter writer) throws Exception {

		switch(mode){

		case START:

			// start tag
			if(fieldName!=null){
				writer.startTag(fieldName+':'+typeHandler.getSerialName());	
			}
			else{
				writer.startTag(typeHandler.getSerialName());	
			}

			// write attributes
			String attributeValue;
			for(AttributeGetter attributeGetter : typeHandler.getAttributeGetters()){
				attributeValue = attributeGetter.get(object);
				if(attributeValue!=null){
					writer.writeAttribute(attributeGetter.getName(), attributeValue);	
				}
			}

			// write elements
			List<ComposableElement> composables = new ArrayList<>();
			for(ElementGetter elementGetter : typeHandler.getElementGetters()){
				ComposableElement composable = elementGetter.createComposableElement(composer, object);

				Object value = elementGetter.getValue(object);
				if(value!=null){
					composables.add(composable);	
				}
			}

			if(!composables.isEmpty()){
				writer.append(">");
				mode = Mode.END;
				composables.add(this);
				composer.add(composables);
			}
			else{
				writer.append("/>");
			}
			break;

		case END:
			if(fieldName!=null){
				writer.appendClosingTag(fieldName+':'+typeHandler.getSerialName());
			}
			else{
				writer.appendClosingTag(typeHandler.getSerialName());
			}
			break;
		}

	}

}
