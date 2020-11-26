package com.starthope.jxls;

import jxl.write.WriteException;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.jxls.area.Area;
import org.jxls.command.AbstractCommand;
import org.jxls.command.Command;
import org.jxls.common.CellRef;
import org.jxls.common.Context;
import org.jxls.common.Size;
import org.jxls.transform.Transformer;
import org.jxls.transform.jexcel.JexcelTransformer;
import org.jxls.transform.poi.PoiTransformer;

/**
 *jx:merge(rows="4" lastCell="A3")//合并几行,合并单元格的截止范围
 * 合并单元格命令
 */
public class MergeCommand extends AbstractCommand {
    /**
     * 合并的列数
     */
    private String cols;
    /**
     * 合并的行数
     */
    private String rows;
    private Area area;
    /**
     * 单元格的样式
     */
    private CellStyle cellStyle;

    public MergeCommand() {
    }



    @Override
    public String getName() {
        return "merge";
    }

    @Override
    public Command addArea(Area area) {
        if (super.getAreaList().size() >= 1) {
            throw new IllegalArgumentException("You can add only a single area to 'merge' command");
        }
        this.area = area;
        return super.addArea(area);
    }

    @Override
    public Size applyAt(CellRef cellRef, Context context) {
        int rows = 1, cols = 1;
        if (StringUtils.isNotBlank(this.rows)) {
            Object rowsObj = getTransformationConfig().getExpressionEvaluator().evaluate(this.rows, context.toMap());
            if (rowsObj != null && NumberUtils.isDigits(rowsObj.toString())) {
                rows = NumberUtils.toInt(rowsObj.toString());
            }
        }
        if (StringUtils.isNotBlank(this.cols)) {
            Object colsObj = getTransformationConfig().getExpressionEvaluator().evaluate(this.cols, context.toMap());
            if (colsObj != null && NumberUtils.isDigits(colsObj.toString())) {
                cols = NumberUtils.toInt(colsObj.toString());
            }
        }

        if (rows > 1 || cols > 1) {
            Transformer transformer = this.getTransformer();
            if (transformer instanceof PoiTransformer) {
                return poiMerge(cellRef, context, (PoiTransformer) transformer, rows, cols);
            } else if (transformer instanceof JexcelTransformer) {
                return jexcelMerge(cellRef, context, (JexcelTransformer) transformer, rows, cols);
            }
        }
        area.applyAt(cellRef, context);
        return new Size(1, 1);
    }

    protected Size poiMerge(CellRef cellRef, Context context, PoiTransformer transformer, int rows, int cols) {
        Sheet sheet = transformer.getWorkbook().getSheet(cellRef.getSheetName());
        CellRangeAddress region = new CellRangeAddress(
                cellRef.getRow(),
                cellRef.getRow() + rows - 1,
                cellRef.getCol(),
                cellRef.getCol() + cols - 1);
        sheet.addMergedRegion(region);

        area.applyAt(cellRef, context);
        if (cellStyle == null) {
            setPoiCellStyle(transformer);
        }
        setRegionStyle(cellStyle, region, sheet);
        return new Size(cols, rows);
    }

    protected Size jexcelMerge(CellRef cellRef, Context context, JexcelTransformer transformer, int rows, int cols) {
        try {
            transformer.getWritableWorkbook().getSheet(cellRef.getSheetName())
                    .mergeCells(
                            cellRef.getRow(),
                            cellRef.getCol(),
                            cellRef.getRow() + rows - 1,
                            cellRef.getCol() + cols - 1);
            area.applyAt(cellRef, context);
        } catch (WriteException e) {
            throw new IllegalArgumentException("合并单元格失败");
        }
        return new Size(cols, rows);
    }

    public static void setRegionStyle(CellStyle cs, CellRangeAddress region, Sheet sheet) {
        for (int i = region.getFirstRow(); i <= region.getLastRow(); i++) {
            Row row = sheet.getRow(i);
            if (row == null) {
                row = sheet.createRow(i);
            }
            for (int j = region.getFirstColumn(); j <= region.getLastColumn(); j++) {
                Cell cell = row.getCell(j);
                if (cell == null) {
                    cell = row.createCell(j);
                }
                cell.setCellStyle(cs);
            }
        }
    }

    /**
     * 设置单元格格式
     * 根据自己需求修改
     * @param transformer
     * @return
     */
    private void setPoiCellStyle(PoiTransformer transformer) {
        cellStyle = transformer.getWorkbook().createCellStyle();
        cellStyle.setBorderRight(BorderStyle.THIN);
        cellStyle.setBorderRight(BorderStyle.THIN);
        cellStyle.setBorderBottom(BorderStyle.THIN);
        cellStyle.setAlignment(HorizontalAlignment.CENTER_SELECTION);
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
    }

    public String getCols() {
        return cols;
    }

    public void setCols(String cols) {
        this.cols = cols;
    }

    public String getRows() {
        return rows;
    }

    public void setRows(String rows) {
        this.rows = rows;
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    public CellStyle getCellStyle() {
        return cellStyle;
    }

    public void setCellStyle(CellStyle cellStyle) {
        this.cellStyle = cellStyle;
    }
}