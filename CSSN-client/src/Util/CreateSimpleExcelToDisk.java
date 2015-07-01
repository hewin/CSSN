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
     * @���ܣ��ֹ�����һ���򵥸�ʽ��Excel 
     */  

  
    public  CreateSimpleExcelToDisk(String infoName,String[] title,String[][] score)throws Exception  
    {  
        // ��һ��������һ��webbook����Ӧһ��Excel�ļ�  
        HSSFWorkbook wb = new HSSFWorkbook();  
        // �ڶ�������webbook�����һ��sheet,��ӦExcel�ļ��е�sheet  
        HSSFSheet sheet = wb.createSheet(infoName);  
        // ����������sheet����ӱ�ͷ��0��,ע���ϰ汾poi��Excel����������������short  
        HSSFRow row = sheet.createRow((int) 0);  
        // ���Ĳ���������Ԫ�񣬲�����ֵ��ͷ ���ñ�ͷ����  
        HSSFCellStyle style = wb.createCellStyle();  
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // ����һ�����и�ʽ  
  
        HSSFCell cell = row.createCell((short) 0);  
        for(int control=0;control<title.length;control++)
        {
        	cell.setCellValue(title[control]);  
            cell.setCellStyle(style);  
            cell = row.createCell((short) control + 1);  
//            cell.setCellValue("����");  
//            cell.setCellStyle(style);  
//            cell = row.createCell((short) 2);  
//            cell.setCellValue("����");  
//            cell.setCellStyle(style);  
//            cell = row.createCell((short) 3);  
//            cell.setCellValue("����");  
//            cell.setCellStyle(style);  
        }
        
  
        // ���岽��д��ʵ������ ʵ��Ӧ������Щ���ݴ����ݿ�õ���    
  
        for (int i = 0; i < score.length; i++)  
        {  
            row = sheet.createRow((int) i + 1);  
            // ���Ĳ���������Ԫ�񣬲�����ֵ  
            for(int control=0;control<title.length;control++){
            	row.createCell((short) control).setCellValue(score[i][control]); 
            }

        }  
        // �����������ļ��浽ָ��λ��  
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
