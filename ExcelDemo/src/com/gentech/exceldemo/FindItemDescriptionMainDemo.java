package com.gentech.exceldemo;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FindItemDescriptionMainDemo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the Alphabet from A to Z or a to z:");
        String itemName = scanner.next();
//        Pattern pattern = Pattern.compile("[A-Za-z]");
//        Matcher matcher =  pattern.matcher(itemName);
//        System.out.println(matcher.find());

//        boolean b = Pattern.matches("[A-Za-z]", itemName);

        if(Pattern.matches("[A-Za-z]", itemName)){
            String resultString = getItemDescription(itemName);
            System.out.println(resultString);
        }else {
            System.out.println("Alphabet cannot be other than A to Z or a to z");
        }
    }

    private static String getItemDescription(String itemName){
        FileInputStream fileInputStream = null;
        Workbook workbook = null;
        Sheet sheet = null;
        Row row = null;
        Cell cell = null;
        String finalResult = "";
        try {
                fileInputStream = new FileInputStream("E:\\ExcelForJavaPoi\\ItemFile.xlsx");
                workbook=new XSSFWorkbook(fileInputStream);
                sheet = workbook.getSheet("Sheet1");
                String itemDescription = "";
                String itemInCell = "";
                boolean isItemFount = false;
            int rowCount = sheet.getPhysicalNumberOfRows();
            for(int r=0;r<rowCount;r++){
                    row = sheet.getRow(r);
                    cell = row.getCell(0);
                    itemInCell =  cell.getStringCellValue();
                if(isItemFount = itemInCell.compareToIgnoreCase(itemName)==0){
                    cell = row.getCell(1);
                    itemDescription = cell.getStringCellValue();
                    break;
                }
            }
            if(isItemFount) {
                finalResult =  itemInCell + " For " + itemDescription;
            }else {
                finalResult =  "Could not find any match with "+'"'+itemName+'"';
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                workbook.close();
                fileInputStream.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return finalResult;
    }
}
