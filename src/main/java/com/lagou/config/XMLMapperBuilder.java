package com.lagou.config;

import com.lagou.pojo.Configuration;
import com.lagou.pojo.MappedStatement;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.util.List;

public class XMLMapperBuilder {

	private Configuration configuration;

	public XMLMapperBuilder(Configuration configuration) {
		this.configuration = configuration;
	}

	public void parse(InputStream inputStream) throws DocumentException {
		Document document = new SAXReader().read(inputStream);
		Element rootElement = document.getRootElement();
		String namespace = rootElement.attributeValue("namespace");
		List<Element> selectList = rootElement.selectNodes("//select");
		setAttributeValue(namespace, selectList);

		List<Element> updateList = rootElement.selectNodes("//update");
		setAttributeValue(namespace, updateList);

		List<Element> insertList = rootElement.selectNodes("//insert");
		setAttributeValue(namespace, insertList);

		List<Element> deleteList = rootElement.selectNodes("//delete");
		setAttributeValue(namespace, deleteList);
	}

	private void setAttributeValue(String namespace, List<Element> elements) {
		if (elements != null && elements.size() > 0) {
			for (Element element : elements) {
				String id = element.attributeValue("id");
				String resultType = element.attributeValue("resultType");
				String parameterType = element.attributeValue("parameterType");
				String sqlText = element.getTextTrim();
				MappedStatement mappedStatement = new MappedStatement();
				mappedStatement.setId(id);
				mappedStatement.setParamterType(parameterType);
				mappedStatement.setResultType(resultType);
				mappedStatement.setSql(sqlText);
				String key = namespace + "." + id;
				configuration.getMappedStatementMap().put(key, mappedStatement);
			}
		}

	}


}
