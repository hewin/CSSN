package Util;
import java.io.FileOutputStream;  


import org.apache.poi.hssf.usermodel.HSSFCell;  
import org.apache.poi.hssf.usermodel.HSSFCellStyle;  
import org.apache.poi.hssf.usermodel.HSSFRow;  
import org.apache.poi.hssf.usermodel.HSSFSheet;  
import org.apache.poi.hssf.usermodel.HSSFWorkbook;  

  
public class CreateSimpleExcelToDisk  
{  
    /** 
     * @功能：手工构建一个简单格式的Excel 
     */  

  
    public  CreateSimpleExcelToDisk(String infoName,String[] title,String[][] score)throws Exception  
    {  
        // 第一步，创建一个webbook，对应一个Excel文件  
        HSSFWorkbook wb = new HSSFWorkbook();  
        // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet  
        HSSFSheet sheet = wb.createSheet(infoName);  
        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short  
        HSSFRow row = sheet.createRow((int) 0);  
        // 第四步，创建单元格，并设置值表头 设置表头居中  
        HSSFCellStyle style = wb.createCellStyle();  
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式  
  
        HSSFCell cell = row.createCell((short) 0);  
        for(int control=0;control<title.length;control++)
        {
        	cell.setCellValue(title[control]);  
            cell.setCellStyle(style);  
            cell = row.createCell((short) control + 1);  
//            cell.setCellValue("姓名");  
//            cell.setCellStyle(style);  
//            cell = row.createCell((short) 2);  
//            cell.setCellValue("年龄");  
//            cell.setCellStyle(style);  
//            cell = row.createCell((short) 3);  
//            cell.setCellValue("生日");  
//            cell.setCellStyle(style);  
        }
        
  
        // 第五步，写入实体数据 实际应用中这些数据从数据库得到，    
  
        for (int i = 0; i < score.length; i++)  
        {  
            row = sheet.createRow((int) i + 1);  
            // 第四步，创建单元格，并设置值  
            for(int control=0;control<title.length;control++){
            	row.createCell((short) control).setCellValue(score[i][control]); 
            }

        }  
        // 第六步，将文件存到指定位置  
        try  
        {  
            FileOutputStream fout = new FileOutputStream("E:/Users/wenxin/Desktop/"+infoName+".xls");  
            wb.write(fout);  
            fout.close();  
        }  
        catch (Exception e)  
        {  
            e.printStackTrace();  
        }  
    }  
    
}  
