package com.s8.lang.xml.parser;

import java.io.IOException;

import com.s8.lang.xml.handler.XML_Context;

/**
 * 
 * @author pc
 *
 */
public class XML_Parser {

	public boolean isVerbose;
	
	private XML_Context context;
	
	private XML_StreamReader reader;

	private RootParsedElement rootScope;
	public ParsedScope scope;

	public XML_Parser(XML_Context context, XML_StreamReader reader, boolean isVerbose) {
		super();
		this.context = context;
		this.reader = reader;
		this.isVerbose = isVerbose;
		rootScope = new RootParsedElement(context);
	}

	/**
	 * 
	 * @return
	 * @throws XML_ParsingException 
	 * @throws IOException 
	 * @throws Exception
	 */
	public Object parse() throws XML_ParsingException, IOException {
		scope = rootScope;
		while(scope!=null){
			scope.parse(context, this, reader);
		}
		return rootScope.getRootObject();
	}


}
