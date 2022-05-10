package com.hqm.service.impl;

import com.hqm.bean.FlightInfo;
import com.hqm.service.FlightService;
import com.hqm.utils.XmlUtil;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FlightServiceImpl implements FlightService {
    private final static String xmlPath = "FlightINFO.xml";

    @Override
    public List<FlightInfo> searchFlightByArriveTime(String time){
        String xpathTemplate = "/flightInfos/flightInfo[translate(arriveTime,':','') < translate('%s',':','')]";
        String xpath = String.format(xpathTemplate,time);
        System.out.format("根据抵达的时间(%s)查找航班，xpath表达式：%s\n",time,xpath);
        return search(xpath);
    }

    @Override
    public List<FlightInfo> searchFlightByLeaveAirport(String airport) {
        String xpathTemplate = "/flightInfos/flightInfo[leaveAirport = '%s']";
        String xpath = String.format(xpathTemplate,airport);
        System.out.format("根据出发的机场(%s)查询航班,xpath表达式：%s\n",airport,xpath);
        return search(xpath);
    }

    private List<FlightInfo> search(String xpath){
        Document document = XmlUtil.getDocument(xmlPath);
        NodeList nodeList = XmlUtil.getNodeList(document,xpath);
        List<FlightInfo> flightInfos = new ArrayList<>();
        if(nodeList != null){
            for(int i = 0;i<nodeList.getLength();i++){
                Node node = nodeList.item(i);
                FlightInfo flightInfo = castNode(node,FlightInfo.class);
                if(flightInfo != null){
                    flightInfos.add(flightInfo);
                }
            }
        }
        return flightInfos;
    }

    // 利用反射将Node对象(属于xml中的flightInfo节点)转换为FlightInfo对象
    private  <T> T castNode(Node node, Class<T> clazz){
        NodeList attrs = node.getChildNodes();
        try {
            T obj = clazz.getConstructor().newInstance();
            Map<String,Field> fieldMap = new HashMap<>();
            // 将Field对象转储到HashMap中便于查找
            Field[] fields = clazz.getDeclaredFields();
            for(Field field : fields){
                fieldMap.put(field.getName(),field);
            }
            for(int i=0;i< attrs.getLength();i++){
                Node attr= attrs.item(i);
                // 忽略文本节点
                if(attr.getNodeName().equals("#text")){
                    continue;
                }
                Field field = fieldMap.get(attr.getNodeName());
                if(field != null){
                    // 存在对应字段设置值
                    field.setAccessible(true);
                    field.set(obj,attr.getTextContent());
                }
            }
            return obj;
        } catch (InstantiationException | InvocationTargetException | NoSuchMethodException | IllegalAccessException e) {
            System.err.println("转换Node节点成"+clazz.getName()+"对象失败");
            System.err.println(e.getMessage());
            return null;
        }
    }
}
