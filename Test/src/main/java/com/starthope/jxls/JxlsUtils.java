package com.starthope.jxls;

import org.jxls.common.Context;
import org.jxls.expression.JexlExpressionEvaluator;
import org.jxls.builder.xls.XlsCommentAreaBuilder;
import org.jxls.transform.Transformer;
import org.jxls.util.JxlsHelper;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class JxlsUtils{

    private static final String TEMPLATE_PATH="";
    static {
        XlsCommentAreaBuilder.addCommandMapping("each", EachCommand.class);
        XlsCommentAreaBuilder.addCommandMapping("merge", MergeCommand.class);
        XlsCommentAreaBuilder.addCommandMapping("mg", MergeCommand2.class);
    }

    public void exportExcel(InputStream is, OutputStream os, Map<String, Object> model) throws Exception {
        try {
            Context context = new Context();
            if (model != null) {
                for (String key : model.keySet()) {
                    context.putVar(key, model.get(key));
                }
            }
            JxlsHelper jxlsHelper = JxlsHelper.getInstance();
            Transformer transformer  = jxlsHelper.createTransformer(is, os);
            JexlExpressionEvaluator evaluator = (JexlExpressionEvaluator)transformer.getTransformationConfig().getExpressionEvaluator();
            Map<String, Object> funcs = new HashMap<String, Object>();
            Map<String, Object> funcs1 = this.getDefalutFuncs();
            Map<String, Object> funcs2 = this.getDefinedFuncs();
            funcs.putAll(funcs1);
            funcs.putAll(funcs2);
            evaluator.getJexlEngine().setFunctions(funcs);
            jxlsHelper.processTemplate(context, transformer);
        }catch (Exception e){
            throw  e;
        }finally {
            os.flush();
            os.close();
            is.close();
        }

    }

    /**
     * 自定义实现功能
     * @return
     */
    abstract  Map<String, Object> getDefinedFuncs();

    private Map<String, Object> getDefalutFuncs(){
        Map<String, Object> funcs = new HashMap<String, Object>();
        funcs.put("utils", this);    //添加自定义功能
        return  funcs;
    };

    public void exportExcel(File xls, File out, Map<String, Object> model) throws Exception {
        exportExcel(new FileInputStream(xls), new FileOutputStream(out), model);
    }

    public void exportExcel(String templateName, OutputStream os, Map<String, Object> model) throws Exception {
        File template = getTemplate(templateName);
        if(template!=null){
            exportExcel(new FileInputStream(template), os, model);
        }
    }


    //获取jxls模版文件

    public File getTemplate(String name){
        String templatePath = JxlsUtils.class.getClassLoader().getResource(TEMPLATE_PATH).getPath();
        File template = new File(templatePath, name);
        if(template.exists()){
            return template;
        }
        return null;
    }

    // 日期格式化
    public String dateFmt(Date date, String fmt) {
        if (date == null) {
            return "";
        }
        try {
            SimpleDateFormat dateFmt = new SimpleDateFormat(fmt);
            return dateFmt.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    //获取合并的数量


    //判断集合是否非空
    public boolean notEmpty(List<Object> list){
        if(list == null || list.size() == 0){
            return false;
        }
        return true;
    }


    // if判断
    public Object ifelse(boolean b, Object o1, Object o2) {
        return b ? o1 : o2;
    }

}