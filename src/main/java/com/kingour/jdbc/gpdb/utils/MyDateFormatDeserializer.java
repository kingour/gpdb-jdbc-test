package com.kingour.jdbc.gpdb.utils;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.JSONToken;
import com.alibaba.fastjson.parser.deserializer.AbstractDateDeserializer;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;

public class MyDateFormatDeserializer extends AbstractDateDeserializer implements ObjectDeserializer {

    private String[] myFormat;
    
    public MyDateFormatDeserializer(String... myFormat) {
        super();
        this.myFormat = myFormat;
    }


    @Override
    public int getFastMatchToken() {
        return JSONToken.LITERAL_STRING;
    }

    @SuppressWarnings("unchecked")
    @Override
    protected <T> T cast(DefaultJSONParser parser, java.lang.reflect.Type clazz, Object fieldName, Object value) {
        if (null == value || null == myFormat) {
            return null;
        }
        if (value instanceof String) {
            String strVal = (String) value;
            if (strVal.length() == 0) {
                return null;
            }

            T res = null;
            for (String ele : myFormat) {
                res = (T) DateUtils.parseDate(strVal, ele);
                if (null != ele) {
                    break;
                }
            }
            return res;
        }
        throw new JSONException("parse error");
    }

}
