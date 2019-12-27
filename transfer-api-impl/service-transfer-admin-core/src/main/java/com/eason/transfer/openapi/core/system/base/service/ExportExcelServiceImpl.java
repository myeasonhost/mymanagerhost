package com.eason.transfer.openapi.core.system.base.service;

import com.eason.transfer.openapi.core.system.utils.ExcelWriteHelper;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.poi.hssf.usermodel.*;
import org.springframework.stereotype.Service;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@Service
public class ExportExcelServiceImpl{
	/**
	 * 导出所有数据
	 */
	public void exportExcel(String[] columnTitles,String[][] columsFields,HttpServletResponse response) throws Exception {
		response.setContentType("application/vnd.ms-excel;charset=gb2312");
		response.setHeader("Content-Disposition", "attachment;filename=Excel"+ DateFormatUtils.format(new Date(),DateFormatUtils.ISO_DATE_FORMAT.getPattern()) +".xls");
		
		// 创建一个workbook 对应一个excel应用文件
		HSSFWorkbook workBook = new HSSFWorkbook();
		// 在workbook中添加一个sheet,对应Excel文件中的sheet
		HSSFSheet sheet = workBook.createSheet("Excel"+DateFormatUtils.format(new Date(),DateFormatUtils.ISO_DATE_FORMAT.getPattern()));
	    
		ExcelWriteHelper exportUtil = new ExcelWriteHelper(workBook, sheet);
		HSSFCellStyle headStyle = exportUtil.getHeadStyle();
		HSSFCellStyle bodyStyle = exportUtil.getBodyStyle();
		bodyStyle.setWrapText(true);
		headStyle.setWrapText(true);
		// 构建表头
		HSSFRow headRow = sheet.createRow(0);
		HSSFCell cell = null;
		for (int i = 0; i < columnTitles.length; i++) {
			//初始化sheet，设置列数和每列宽度  
			if(i==0){
				sheet.setColumnWidth(i, 36*100);
			}else{
				sheet.setColumnWidth(i, 36*80);
			}
			cell = headRow.createCell(i);
			cell.setCellStyle(headStyle);
			cell.setCellValue(columnTitles[i]);
		}
		// 构建表体数据
		for (int i = 0; i < columsFields.length; i++) {
			HSSFRow bodyRow = sheet.createRow(i + 1);
			for(int j=0;j<columsFields[i].length;j++){
				 cell = bodyRow.createCell(j);
				 cell.setCellStyle(bodyStyle);
				 cell.setCellValue(columsFields[i][j]);
			}
		}
		try {
			ServletOutputStream outputStream=response.getOutputStream();
			workBook.write(outputStream);
			outputStream.flush();
		} catch (IOException e) {
			throw new Exception(e);
		} 

	}

}
