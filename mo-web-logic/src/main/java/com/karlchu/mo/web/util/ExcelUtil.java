package com.karlchu.mo.web.util;

import com.karlchu.mo.core.dto.CellDataType;
import com.karlchu.mo.core.dto.CellValue;

import jxl.WorkbookSettings;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.write.*;
import jxl.write.Number;

import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by Ban Vien Ltd.
 * User: Vien Nguyen (vien.nguyen@banvien.com)
 * Date: 7/26/12
 * Time: 9:20 AM
 */
public class ExcelUtil {
    public static void addRow(WritableSheet sheet, int startRow, CellValue[] cellValues) throws WriteException {

        WritableCellFormat cellFormat = new WritableCellFormat();
        cellFormat.setWrap(true);
        cellFormat.setBorder(Border.ALL, BorderLineStyle.THIN);

        NumberFormat towdps = new NumberFormat("#,##0");
        WritableCellFormat towdpsFormat = new WritableCellFormat(towdps);
        towdpsFormat.setBorder(Border.ALL, BorderLineStyle.THIN);

        NumberFormat towdps2 = new NumberFormat("#,##0.00");
        WritableCellFormat towdpsFormat2 = new WritableCellFormat(towdps2);
        towdpsFormat2.setBorder(Border.ALL, BorderLineStyle.THIN);

        DateFormat customDateFormat = new DateFormat ("dd/MM/yyyy");
        WritableCellFormat dateFormat = new WritableCellFormat (customDateFormat);
        dateFormat.setBorder(Border.ALL, BorderLineStyle.THIN);

        for (int i = 0; i < cellValues.length; i++) {
            if (cellValues[i] != null && cellValues[i].getValue() != null) {
                if (cellValues[i].getType().equals(CellDataType.STRING)) {
                    Label label = new Label(i, startRow, String.valueOf(cellValues[i].getValue()), cellFormat);
                    sheet.addCell(label);
                }else if (cellValues[i].getType().equals(CellDataType.INT)) {
                    Number number = new Number(i, startRow, (Integer)cellValues[i].getValue(), towdpsFormat);
                    sheet.addCell(number);
                } else if (cellValues[i].getType().equals(CellDataType.DOUBLE)) {
                    Number number = new Number(i, startRow, (Double)cellValues[i].getValue(), towdpsFormat2);
                    sheet.addCell(number);
                } else if (cellValues[i].getType().equals(CellDataType.DATE)) {
                    Date now = new Date(((Timestamp)cellValues[i].getValue()).getTime());
                    DateTime dateCell = new DateTime(i, startRow, now, dateFormat);
                    sheet.addCell(dateCell);
                }
            }else{
                Label label = new Label(i, startRow, "", cellFormat);
                sheet.addCell(label);
            }
        }
    }
    public static void addRow(WritableSheet sheet, int startRow, CellValue[] cellValues,WritableCellFormat cellFormat,WritableCellFormat numberFormat,WritableCellFormat doubleFormat,WritableCellFormat dateFormat) throws WriteException {
        for (int i = 0; i < cellValues.length; i++) {
            if (cellValues[i] != null && cellValues[i].getValue() != null) {
                if (cellValues[i].getType().equals(CellDataType.STRING)) {
                    Label label = new Label(i, startRow, String.valueOf(cellValues[i].getValue()), cellFormat);
                    sheet.addCell(label);
                }else if (cellValues[i].getType().equals(CellDataType.INT)) {
                    Number number = new Number(i, startRow, (Integer)cellValues[i].getValue(), numberFormat);
                    sheet.addCell(number);
                } else if (cellValues[i].getType().equals(CellDataType.DOUBLE)) {
                    Number number = new Number(i, startRow, (Double)cellValues[i].getValue(), doubleFormat);
                    sheet.addCell(number);
                } else if (cellValues[i].getType().equals(CellDataType.DATE)) {
                    Date now = new Date(((Timestamp)cellValues[i].getValue()).getTime());
                    DateTime dateCell = new DateTime(i, startRow, now, dateFormat);
                    sheet.addCell(dateCell);
                }
            }else{
                Label label = new Label(i, startRow, "", cellFormat);
                sheet.addCell(label);
            }
        }
    }
    public static void addRow(WritableSheet sheet, int startRow, CellValue[] cellValues, WritableFont writableFont) throws WriteException {

        WritableCellFormat cellFormat = new WritableCellFormat(writableFont);
        cellFormat.setWrap(true);
        cellFormat.setBorder(Border.ALL, BorderLineStyle.THIN);

        NumberFormat towdps = new NumberFormat("#,##0");
        WritableCellFormat towdpsFormat = new WritableCellFormat(writableFont, towdps);
        towdpsFormat.setBorder(Border.ALL, BorderLineStyle.THIN);

        NumberFormat towdps2 = new NumberFormat("#,##0.00");
        WritableCellFormat towdpsFormat2 = new WritableCellFormat(writableFont, towdps2);
        towdpsFormat2.setBorder(Border.ALL, BorderLineStyle.THIN);

        DateFormat customDateFormat = new DateFormat ("dd MMM yyyy");
        WritableCellFormat dateFormat = new WritableCellFormat (writableFont, customDateFormat);
        dateFormat.setBorder(Border.ALL, BorderLineStyle.THIN);

        for (int i = 0; i < cellValues.length; i++) {
            if (cellValues[i] != null && cellValues[i].getValue() != null) {
                if (cellValues[i].getType().equals(CellDataType.STRING)) {
                    Label label = new Label(i, startRow, String.valueOf(cellValues[i].getValue()), cellFormat);
                    sheet.addCell(label);
                }else if (cellValues[i].getType().equals(CellDataType.INT)) {
                    Number number = new Number(i, startRow, (Integer)cellValues[i].getValue(), towdpsFormat);
                    sheet.addCell(number);
                } else if (cellValues[i].getType().equals(CellDataType.DOUBLE)) {
                    Number number = new Number(i, startRow, (Double)cellValues[i].getValue(), towdpsFormat2);
                    sheet.addCell(number);
                } else if (cellValues[i].getType().equals(CellDataType.DATE)) {
                    Date now = new Date(((Timestamp)cellValues[i].getValue()).getTime());
                    DateTime dateCell = new DateTime(i, startRow, now, dateFormat);
                    sheet.addCell(dateCell);
                }
            }else{
                Label label = new Label(i, startRow, "", cellFormat);
                sheet.addCell(label);
            }
        }
    }
    public static void setEncoding4Workbook(WorkbookSettings ws) {
    	String OS = System.getProperty("os.name").toLowerCase();
        if(OS.indexOf("nix") >= 0 || OS.indexOf("nux") >= 0 || OS.indexOf("aix") > 0 ) {
        	ws.setEncoding("CP1252");
        }
    }

    public static void addColumn(WritableSheet sheet, int startCol,int startRow, CellValue[] cellValues) throws WriteException {

        WritableCellFormat cellFormat = new WritableCellFormat();
        cellFormat.setWrap(true);
        cellFormat.setBorder(Border.ALL, BorderLineStyle.THIN);

        NumberFormat towdps = new NumberFormat("#,##0");
        WritableCellFormat towdpsFormat = new WritableCellFormat(towdps);
        towdpsFormat.setBorder(Border.ALL, BorderLineStyle.THIN);

        NumberFormat towdps2 = new NumberFormat("#,##0.00");
        WritableCellFormat towdpsFormat2 = new WritableCellFormat(towdps2);
        towdpsFormat2.setBorder(Border.ALL, BorderLineStyle.THIN);

        DateFormat customDateFormat = new DateFormat ("dd MMM yyyy");
        WritableCellFormat dateFormat = new WritableCellFormat (customDateFormat);
        dateFormat.setBorder(Border.ALL, BorderLineStyle.THIN);

        for (int i = startRow; i < cellValues.length; i++) {
            if (cellValues[i] != null && cellValues[i].getValue() != null) {
                if (cellValues[i].getType().equals(CellDataType.STRING)) {
                    Label label = new Label(startCol, i, String.valueOf(cellValues[i].getValue()), cellFormat);
                    sheet.addCell(label);
                }else if (cellValues[i].getType().equals(CellDataType.INT)) {
                    Number number = new Number(startCol, i, (Integer)cellValues[i].getValue(), towdpsFormat);
                    sheet.addCell(number);
                } else if (cellValues[i].getType().equals(CellDataType.DOUBLE)) {
                    Number number = new Number(startCol, i, (Double)cellValues[i].getValue(), towdpsFormat);
                    sheet.addCell(number);
                }else if (cellValues[i].getType().equals(CellDataType.FLOAT)) {
                    Number number = new Number(startCol, i, (Float)cellValues[i].getValue(), towdpsFormat);
                    sheet.addCell(number);
                }
                else if (cellValues[i].getType().equals(CellDataType.DATE)) {
                    Date now = new Date(((Timestamp)cellValues[i].getValue()).getTime());
                    DateTime dateCell = new DateTime(startCol, i, now, dateFormat);
                    sheet.addCell(dateCell);
                }
            }else{
                Label label = new Label(startCol, i, "", cellFormat);
                sheet.addCell(label);
            }
        }

    }

    public static void addRowAtCol(WritableSheet sheet, int startCol, int startRow, CellValue[] cellValues, WritableCellFormat cellFormat, WritableCellFormat numberFormat, WritableCellFormat doubleFormat, WritableCellFormat dateFormat) throws WriteException {
        int col = startCol;
        for (int i = 0; i < cellValues.length; i++) {
            if (cellValues[i] != null && cellValues[i].getValue() != null) {
                if (cellValues[i].getType().equals(CellDataType.STRING)) {
                    Label label = new Label(col++, startRow, String.valueOf(cellValues[i].getValue()), cellFormat);
                    sheet.addCell(label);
                }else if (cellValues[i].getType().equals(CellDataType.INT)) {
                    Number number = new Number(col++, startRow, (Integer)cellValues[i].getValue(), numberFormat);
                    sheet.addCell(number);
                } else if (cellValues[i].getType().equals(CellDataType.DOUBLE)) {
                    Number number = new Number(col++, startRow, (Double)cellValues[i].getValue(), doubleFormat);
                    sheet.addCell(number);
                } else if (cellValues[i].getType().equals(CellDataType.DATE)) {
                    Date now = new Date(((Timestamp)cellValues[i].getValue()).getTime());
                    DateTime dateCell = new DateTime(col++, startRow, now, dateFormat);
                    sheet.addCell(dateCell);
                }
            }else{
                Label label = new Label(i, startRow, "", cellFormat);
                sheet.addCell(label);
            }
        }
    }
}
