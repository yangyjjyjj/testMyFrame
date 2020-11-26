package com.starthope.jxls;
import jxl.write.WriteException;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;
import org.jxls.area.Area;
import org.jxls.command.AbstractCommand;
import org.jxls.command.Command;
import org.jxls.common.CellRef;
import org.jxls.common.Context;
import org.jxls.common.Size;
import org.jxls.transform.Transformer;
import org.jxls.transform.jexcel.JexcelTransformer;
import org.jxls.transform.poi.PoiCellData;
import org.jxls.transform.poi.PoiTransformer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 根据范围合并单元格，合并区间不能交叉
 * 例如：rang="1:3,5:7"即1到3合并，5到7合并
 */
public class MergeCommand2 extends AbstractCommand {
    /**
     * 行合并的范围
     */
    private String rowRange;
    /**
     * 列合并的范围
     */
    private String cols;
    private Area area;
    /**
     * 单元格的样式
     */
    private CellStyle cellStyle;


    @Override
    public String getName() {
        return "mg";
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
        String[] rangeArray = rowRange.split(",");
        Map<Integer, Integer> map = new HashMap<>();
        List<Integer> startRowList = new ArrayList<>();
        for (String r : rangeArray) {
            map.put(Integer.valueOf(r.split(":")[0]), Integer.valueOf(r.split(":")[1]));
            startRowList.add(Integer.valueOf(r.split(":")[0]));
        }

        int cols = 1;
        if (StringUtils.isNotBlank(this.cols)) {
            Object colsObj = getTransformationConfig().getExpressionEvaluator().evaluate(this.cols, context.toMap());
            if (colsObj != null && NumberUtils.isDigits(colsObj.toString())) {
                cols = NumberUtils.toInt(colsObj.toString());
            }
        }


        Transformer transformer = getTransformer();
        if (transformer instanceof PoiTransformer) {
            if (cellStyle == null) {
                PoiCellData cellData = (PoiCellData) transformer.getCellData(cellRef);
                cellStyle = cellData.getCellStyle();
            }
            if (startRowList.contains(cellRef.getRow())) {
                return poiMerge(cellRef, context, (PoiTransformer) transformer, cellRef.getRow(), map.get(cellRef.getRow()), cols);
            }
        } else if (transformer instanceof JexcelTransformer) {
            return jexcelMerge(cellRef, context, (JexcelTransformer) transformer, cellRef.getRow(), map.get(cellRef.getRow()), cols);
        }

        area.applyAt(cellRef, context);
        return new Size(1, 1);

    }

    protected Size poiMerge(CellRef cellRef, Context context, PoiTransformer transformer, int firstRow, int lastRow, int cols) {

        if (cellRef.getRow() == firstRow) {
            Sheet sheet = transformer.getWorkbook().getSheet(cellRef.getSheetName());
            CellRangeAddress region = new CellRangeAddress(
                    firstRow - 1,
                    lastRow - 1,
                    cellRef.getCol(),
                    cellRef.getCol() + cols - 1);
            sheet.addMergedRegion(region);
            area.applyAt(cellRef, context);
            MergeCommand.setRegionStyle(cellStyle, region, sheet);

        }
        return new Size(1, 1);
    }

    protected Size jexcelMerge(CellRef cellRef, Context context, JexcelTransformer transformer, int firstRow, int lastRow, int cols) {
        try {
            transformer.getWritableWorkbook().getSheet(cellRef.getSheetName())
                    .mergeCells(
                            firstRow - 1,
                            cellRef.getCol(),
                            lastRow - 1,
                            cellRef.getCol() + cols - 1);
            area.applyAt(cellRef, context);
        } catch (WriteException e) {
            throw new IllegalArgumentException("合并单元格失败");
        }
        return new Size(1, 1);
    }

    public String getRowRange() {
        return rowRange;
    }

    public void setRowRange(String rowRange) {
        this.rowRange = rowRange;
    }

    public String getCols() {
        return cols;
    }

    public void setCols(String cols) {
        this.cols = cols;
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