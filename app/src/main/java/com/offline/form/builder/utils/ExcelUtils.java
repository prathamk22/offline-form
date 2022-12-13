package com.offline.form.builder.utils;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.offline.form.builder.data.db.AnswerEntity;
import com.pradeep.form.simple_form.model.Form;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

public class ExcelUtils {

    HSSFWorkbook workbook;
    HSSFSheet sheet;
    HSSFCellStyle headerCellStyle;

    public boolean exportDataIntoWorkbook(Context context, String fileName, List<AnswerEntity> dataList) {
        boolean isWorkbookWrittenIntoStorage;

        // Check if available and not read only
        if (!isExternalStorageAvailable() || isExternalStorageReadOnly()) {
            Log.e("TAG", "Storage not available or read only");
            return false;
        }

        // Creating a New HSSF Workbook (.xls format)
        workbook = new HSSFWorkbook();

        setHeaderCellStyle();

        // Creating a New Sheet and Setting width for each column
        sheet = workbook.createSheet("survey");

        setHeaderRow(dataList);
        fillDataIntoExcel(dataList);
        isWorkbookWrittenIntoStorage = storeExcelInStorage(context, fileName);

        return isWorkbookWrittenIntoStorage;
    }

    private boolean isExternalStorageReadOnly() {
        String externalStorageState = Environment.getExternalStorageState();
        return Environment.MEDIA_MOUNTED_READ_ONLY.equals(externalStorageState);
    }

    private boolean isExternalStorageAvailable() {
        String externalStorageState = Environment.getExternalStorageState();
        return Environment.MEDIA_MOUNTED.equals(externalStorageState);
    }

    private void setHeaderCellStyle() {
        headerCellStyle = workbook.createCellStyle();
        headerCellStyle.setFillForegroundColor(HSSFColor.AQUA.index);
        headerCellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        headerCellStyle.setAlignment(CellStyle.ALIGN_CENTER);
    }

    private void setHeaderRow(List<AnswerEntity> dataList) {
        if (dataList.size() == 0)
            return;
        Row row = sheet.createRow(0);

        AnswerEntity answerEntity = dataList.get(0);
        Type typeToken = new TypeToken<Map<String, Object>>() {
        }.getType();
        Map<String, Object> map = new Gson().fromJson(answerEntity.getData(), typeToken);
        int i = 0;
        for (String item : map.keySet()) {
            Cell cell = row.createCell(i);
            cell.setCellValue(item);
            cell.setCellStyle(headerCellStyle);
            i++;
        }
    }

    private void fillDataIntoExcel(List<AnswerEntity> dataList) {
        for (int i = 0; i < dataList.size(); i++) {
            Type typeToken = new TypeToken<Map<String, String>>() {
            }.getType();
            AnswerEntity answerEntity = dataList.get(i);
            Map<String, String> map = new Gson().fromJson(answerEntity.getData(), typeToken);
            Row rowData = sheet.createRow(i + 1);

            int j = 0;
            for (String key : map.keySet()) {
                Cell cell = rowData.createCell(j);
                String data = map.get(key);
                List<TableData> tableData = null;
                if (data != null) {
                    Type tableList = new TypeToken<List<TableData>>() {
                    }.getType();
                    try{
                        tableData = new Gson().fromJson(data, tableList);
                    }catch (Exception e){

                    }
                    if (tableData == null) {
                        cell.setCellValue(data);
                        j++;
                    } else {
                        createNewTableData(tableData, answerEntity);
                    }
                }
            }
        }
    }

    private void createNewTableData(List<TableData> tableDataList, AnswerEntity data) {
        if (tableDataList.isEmpty())
            return;
        TableData temp = tableDataList.get(0);
        HSSFSheet tempSheet = workbook.getSheet(temp.getSheetName());
        if (tempSheet == null) {
            tempSheet = workbook.createSheet(temp.getSheetName());
        }
        int lastRow = tempSheet.getLastRowNum();
        Log.e("TAG", "createNewTableData: " + lastRow);
        if (lastRow == 0){
            temp.getColumnNames().add("data_id");
            Row row = tempSheet.createRow(0);
            int i = 0;
            for (String item : temp.getColumnNames()) {
                Cell cell = row.createCell(i);
                cell.setCellValue(item);
                cell.setCellStyle(headerCellStyle);
                i++;
            }
        }

        for (int p = 0; p < tableDataList.size(); p++) {
            Row rowData = tempSheet.createRow(p + 1);
            TableData tableData = tableDataList.get(p);
            int j = 0;
            for (Form item : tableData.getFormAns()) {
                Cell cell = rowData.createCell(j);
                cell.setCellValue(item.getAnswer());
                j++;
            }
            Cell cell = rowData.createCell(j);
            cell.setCellValue(String.valueOf(data.getId()));
        }
    }

    private boolean storeExcelInStorage(Context context, String fileName) {
        boolean isSuccess;
        File file = new File(context.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS), fileName);
        FileOutputStream fileOutputStream = null;

        try {
            fileOutputStream = new FileOutputStream(file);
            workbook.write(fileOutputStream);
            Log.e("TAG", "Writing file" + file);
            isSuccess = true;
        } catch (IOException e) {
            Log.e("TAG", "Error writing Exception: ", e);
            isSuccess = false;
        } catch (Exception e) {
            Log.e("TAG", "Failed to save file due to Exception: ", e);
            isSuccess = false;
        } finally {
            try {
                if (null != fileOutputStream) {
                    fileOutputStream.close();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return isSuccess;
    }
}
