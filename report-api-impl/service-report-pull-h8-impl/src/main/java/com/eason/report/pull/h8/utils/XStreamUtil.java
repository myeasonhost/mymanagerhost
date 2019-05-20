package com.eason.report.pull.h8.utils;



import com.thoughtworks.xstream.XStream;



public class XStreamUtil {

    /*public static void main(String[] args){
        try{
            // 读取XML文件
            Resource resource = new ClassPathResource("ag.xml");
            BufferedReader br = new BufferedReader(new InputStreamReader(resource.getInputStream(), "utf-8"));
            StringBuffer buffer = new StringBuffer();
            String line = "";
            while ((line = br.readLine()) !=null) {
                buffer.append(line);
            }
            br.close();
            // XML转为Java对象
            AgSportOrdersExVo agList = (AgSportOrdersExVo)xmlStrToOject(AgSportOrdersExVo.class, buffer.toString());

        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.print("");
    }*/
    /**
     * 将XML转为指定的POJO
     * @param clazz
     * @param xmlStr @return
     * @throws Exception
     */
    public static Object xmlStrToOject(Class<?> clazz, String xmlStr) throws Exception {
        Object xmlObject = null;
        XStream xstream = new XStream();
        xstream.processAnnotations(clazz);
        xstream.autodetectAnnotations(true);
        xmlObject= xstream.fromXML(xmlStr);
        return xmlObject;
    }
}
