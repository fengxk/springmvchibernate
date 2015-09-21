package com.surfo.common.utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

public class ReadXML {

	/**
	 * @param args
	 * @return 
	 */
	public static List<String> parseXML(File file) {
		SAXBuilder sax = new SAXBuilder(false);
		List<String> list = new ArrayList<String>();
		try {
			Document doc = sax.build(file);
			Element rootEle = doc.getRootElement();
			List<Element> domainList = rootEle.getChildren();
			for (Element element : domainList) {
				list.add(element.getChildText("name"));
			}
		} catch (JDOMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return list;
	}

}
