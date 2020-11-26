package com.starthope.jxls;

import jdk.internal.instrumentation.Logger;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.slf4j.LoggerFactory;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.math.BigDecimal;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class TestJxlsUtils {

    private static Log logger = LogFactory.getLog("TestJxlsUtils");

    public static void main(String[] args) throws Exception {
        List<Employee> employees = generateSampleEmployeeData();
        List<Employee> employees2 = generateSampleEmployeeData();
        List<Integer> mergeRows = new ArrayList<Integer>();
        List<Object> mergeContent = new ArrayList<Object>();
        mergeRows.add(employees.size());
        mergeRows.add(employees2.size());
        mergeContent.add("123");
        mergeContent.add("456");
        employees.addAll(employees2);

        OutputStream os = new FileOutputStream("object_collection_output.xls");
        Map<String , Object> model=new HashMap<String , Object>();
        model.put("employees", employees);
        model.put("nowdate", new Date());
        model.put("count", employees.size());
        model.put("mergeRows", mergeRows);
        model.put("mergeContent", mergeContent);
        new JxlsUtils() {
            @Override
            Map<String, Object> getDefinedFuncs() {
                return new HashMap<String,Object>();
            }
        }.exportExcel("jxlsTest.xls", os, model);
        //os.close();
    }

    public static List<Employee> generateSampleEmployeeData() throws ParseException {
        List<Employee> employees = new ArrayList<Employee>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MMM-dd", Locale.US);
        employees.add( new Employee("Elsa", dateFormat.parse("1970-Jul-10"), new BigDecimal(1500), new BigDecimal(0.15) ));
        employees.add( new Employee("Oleg", dateFormat.parse("1973-Apr-30"), new BigDecimal(2300), new BigDecimal(0.25)) );
        employees.add( new Employee("Neil", dateFormat.parse("1975-Oct-05"), new BigDecimal(2500), new BigDecimal(0.00)) );
        employees.add( new Employee("Maria", dateFormat.parse("1978-Jan-07"), new BigDecimal(1700), new BigDecimal(0.15)) );
        employees.add( new Employee("John", dateFormat.parse("1969-May-30"), new BigDecimal(2800), new BigDecimal(0.20)) );
        logger.debug( dateFormat.parse("1970-Jul-10"));
        return employees;
    }
}
