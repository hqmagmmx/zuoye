package com.hqm.utils;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.InputStream;

public class XmlUtil {
    public static Document getDocument(String xmlPath){
        try( InputStream inputStream = XmlUtil.class.getClassLoader().getResourceAsStream(xmlPath)){
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            return documentBuilder.parse(inputStream);
        } catch (Exception e){
            System.out.println("获取xml文档失败");
            return null;
        }
    }

    public static Object evaluate(Document document, String xpath, QName qName) throws XPathExpressionException {
        XPathFactory xPathFactory = XPathFactory.newInstance();
        XPath xPath = xPathFactory.newXPath();
        return xPath.evaluate(xpath,document,qName);
    }

    // 默认根据xpath获取第一个节点
    public static Node getNode(Document document, String xpath) {
        try {
            return (Node)evaluate(document,xpath,XPathConstants.NODE);
        } catch (XPathExpressionException e) {
            System.out.println("获取节点失败");
            return null;
        }
    }

    public static NodeList getNodeList(Document document,String xpath) {
        try {
            return (NodeList) evaluate(document,xpath,XPathConstants.NODESET);
        } catch (XPathExpressionException e) {
            System.out.println("获取节点失败");
            return null;
        }
    }
}
