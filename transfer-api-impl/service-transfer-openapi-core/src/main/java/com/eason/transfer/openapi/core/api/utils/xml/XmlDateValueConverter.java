package com.eason.transfer.openapi.core.api.utils.xml;

import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class XmlDateValueConverter implements Converter {

    private String format = "yyyy-MM-dd HH:mm:ss";

    private static final Log log = LogFactory
            .getLog(XmlDateValueConverter.class);

    @SuppressWarnings("rawtypes")
    public boolean canConvert(Class clazz) {
        return Date.class.isAssignableFrom(clazz);
    }

    public void marshal(Object value, HierarchicalStreamWriter writer,
                        MarshallingContext context) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        writer.setValue(sdf.format((Date) value));

    }

    public Object unmarshal(HierarchicalStreamReader reader,
                            UnmarshallingContext context) {

        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return sdf.parse(reader.getValue());
        } catch (ParseException e) {
            log.error("---XmlDateValueConverter--error--");
        }
        return null;

    }

}
