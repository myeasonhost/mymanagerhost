package com.eason.report.pull.platform.ag.utils;


import com.eason.report.pull.platform.ag.model.common.AgAdditionModel;
import com.eason.report.pull.platform.ag.model.agin.AginListModel;
import com.eason.report.pull.platform.ag.model.agin.AginModel;
import com.eason.report.pull.platform.ag.model.xin.XinListModel;
import com.eason.report.pull.platform.ag.model.xin.XinModel;
import com.eason.report.pull.platform.ag.model.yoplay.YoplayListModel;
import com.eason.report.pull.platform.ag.model.yoplay.YoplayModel;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.converters.basic.BigDecimalConverter;
import com.thoughtworks.xstream.converters.basic.DateConverter;
import com.thoughtworks.xstream.io.naming.NoNameCoder;
import com.thoughtworks.xstream.io.xml.Xpp3Driver;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.util.TimeZone;

public class XMLUtil {

    /**
     * 将XML转为指定的POJO
     * @param clazz
     * @param xmlStr @return
     * @throws Exception
     */
    public static <T> T xmlStrToOject(Class<T> clazz, String xmlStr){
        T xmlObject = null;
        XStream xstream = new XStream(new Xpp3Driver(new NoNameCoder())); //不修改属性名
        xstream.registerConverter(new DateConverter("yyyy-MM-dd HH:mm:ss", null, TimeZone.getTimeZone("GMT+8")));
        xstream.aliasSystemAttribute(null,"class");//去掉class属性
        xstream.autodetectAnnotations(true);//自动探测注解
        xstream.ignoreUnknownElements();//忽略未知元素
        XStream.setupDefaultSecurity(xstream);
        xstream.processAnnotations(clazz);
        xstream.allowTypes(new Class[]{AgAdditionModel.class, AginListModel.class, AginModel.class,
                YoplayListModel.class, YoplayModel.class, XinListModel.class, XinModel.class});
        xmlObject= (T)xstream.fromXML(xmlStr);
        return xmlObject;
    }

    /**
     * 将对象直接转换成String类型的 XML输出
     */
    public static String convertToXml(Object obj) {
        // 创建输出流
        StringWriter sw = new StringWriter();
        try {
            // 利用jdk中自带的转换类实现
            JAXBContext context = JAXBContext.newInstance(obj.getClass());
            Marshaller marshaller = context.createMarshaller();
            // 格式化xml输出的格式
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            // 将对象转换成输出流形式的xml
            marshaller.marshal(obj, sw);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return sw.toString();
    }

    /**
     * 将对象根据路径转换成xml文件
     */
    public static void convertToXml(Object obj, String path) throws IOException {
        try {
            // 利用jdk中自带的转换类实现
            JAXBContext context = JAXBContext.newInstance(obj.getClass());
            Marshaller marshaller = context.createMarshaller();
            // 格式化xml输出的格式
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            // 将对象转换成输出流形式的xml
            // 创建输出流
            FileWriter fw = new FileWriter(path);
            marshaller.marshal(obj, fw);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

}