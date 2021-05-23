package com.cucumber.adapter;

import java.util.LinkedHashMap;
import java.util.Map;

public class ExtentReport {

    private static final String TR_STRING = "<tr style='background: #d1d1d1'>";
    private static final String TD_STRING = "<td style='word-wrap:break-word;white-space:normal;width:auto'>";
    private static final String TD = "</td>";
    private static final String FORMAT = "%s%s%s";
    private static final String TABLE = "<table class='markup-table table style='table-layout:fixed;width:100%';>";

    public static void addMapAsTable(LinkedHashMap<String,String> dataMap,String message){
        StringBuilder builder = new StringBuilder();
        builder.append("</br>");
        builder.append(TABLE);
        for(Map.Entry<String,String> data:dataMap.entrySet()){
            builder.append(TR_STRING);
            builder.append(String.format(FORMAT,TD_STRING,data.getKey(),TD));
            builder.append(String.format(FORMAT,TD_STRING,data.getValue(),TD));
            builder.append("</tr>");
        }
       builder.append("</table>");
       ExtentCucumberAdapter.addTestStepLog(message);
       ExtentCucumberAdapter.addAdditionalStepInfo(builder.toString());
    }

     




}
