package com.example.etc.services;

import com.example.etc.exceptions.BadRequestException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ExcelService {
    private static final Logger logger = LoggerFactory.getLogger(ExcelService.class);

    @Value("${general.excel.location_path}")
    private String excelLocation;

    public String importIaFinding(MultipartFile[] excelFiles) throws IOException, ParseException {
        try {

            for (MultipartFile excelFile : excelFiles) {
                //Download file excel
                Path filepath = Paths.get(excelLocation, excelFile.getOriginalFilename());
                excelFile.transferTo(filepath);

                //Read file excel
                XSSFWorkbook workbook = new XSSFWorkbook(excelFile.getInputStream());
                XSSFSheet worksheet = workbook.getSheetAt(0);
//                List<IAFinding> iaFindings = new ArrayList<>();

                for (int index = 0; index < worksheet.getPhysicalNumberOfRows(); index++) {
                    if (index > 0) { // ไม่อ่านหัว column
                        logger.info("filename : " + excelFile.getOriginalFilename() + " | index : " + index);

                        XSSFRow row = worksheet.getRow(index);
                        if (row != null) {
                            if (row.getCell(0) != null) {
                                if(row.getCell(0) != null)
                                    row.getCell(0).setCellType(Cell.CELL_TYPE_STRING);

                                if(row.getCell(2) != null)
                                    row.getCell(2).setCellType(Cell.CELL_TYPE_STRING);

                                if(row.getCell(3) != null)
                                    row.getCell(3).setCellType(Cell.CELL_TYPE_STRING);

                                if(row.getCell(4) != null)
                                    row.getCell(4).setCellType(Cell.CELL_TYPE_STRING);

                                if(row.getCell(5) != null)
                                    row.getCell(5).setCellType(Cell.CELL_TYPE_STRING);

                                if(row.getCell(6) != null)
                                    row.getCell(6).setCellType(Cell.CELL_TYPE_STRING);

                                if(row.getCell(7) != null)
                                    row.getCell(7).setCellType(Cell.CELL_TYPE_STRING);

                                if(row.getCell(8) != null)
                                    row.getCell(8).setCellType(Cell.CELL_TYPE_STRING);

                                if(row.getCell(9) != null)
                                    row.getCell(9).setCellType(Cell.CELL_TYPE_STRING);

                                if(row.getCell(10) != null)
                                    row.getCell(10).setCellType(Cell.CELL_TYPE_STRING);

                                if(row.getCell(11) != null)
                                    row.getCell(11).setCellType(Cell.CELL_TYPE_STRING);

                                if(row.getCell(12) != null)
                                    row.getCell(12).setCellType(Cell.CELL_TYPE_STRING);

                                if(row.getCell(13) != null)
                                    row.getCell(13).setCellType(Cell.CELL_TYPE_STRING);

                                if(row.getCell(14) != null)
                                    row.getCell(14).setCellType(Cell.CELL_TYPE_STRING);

                                if(row.getCell(15) != null)
                                    row.getCell(15).setCellType(Cell.CELL_TYPE_STRING);

                                if(row.getCell(16) != null)
                                    row.getCell(16).setCellType(Cell.CELL_TYPE_STRING);

                                if(row.getCell(17) != null)
                                    row.getCell(17).setCellType(Cell.CELL_TYPE_STRING);

                                if(row.getCell(18) != null)
                                    row.getCell(18).setCellType(Cell.CELL_TYPE_STRING);

                                if(row.getCell(19) != null)
                                    row.getCell(19).setCellType(Cell.CELL_TYPE_STRING);

                                if(row.getCell(20) != null)
                                    row.getCell(20).setCellType(Cell.CELL_TYPE_STRING);

                                if(row.getCell(21) != null)
                                    row.getCell(21).setCellType(Cell.CELL_TYPE_STRING);

                                if(row.getCell(22) != null)
                                    row.getCell(22).setCellType(Cell.CELL_TYPE_STRING);

                                if(row.getCell(23) != null)
                                    row.getCell(23).setCellType(Cell.CELL_TYPE_STRING);

                                if(row.getCell(24) != null)
                                    row.getCell(24).setCellType(Cell.CELL_TYPE_STRING);

                                if(row.getCell(25) != null)
                                    row.getCell(25).setCellType(Cell.CELL_TYPE_STRING);

                                if(row.getCell(26) != null)
                                    row.getCell(26).setCellType(Cell.CELL_TYPE_STRING);

                                if(row.getCell(27) != null)
                                    row.getCell(27).setCellType(Cell.CELL_TYPE_STRING);

                                String auditEngagementCode = row.getCell(0) != null && !row.getCell(0).getStringCellValue().equals("") ? row.getCell(0).getStringCellValue() : null;
                                String auditEngagementName = row.getCell(1) != null && !row.getCell(1).getStringCellValue().equals("") ? row.getCell(1).getStringCellValue() : null;
                                String type = row.getCell(2) != null && !row.getCell(2).getStringCellValue().equals("") ? row.getCell(2).getStringCellValue() : null;
                                String auditeeUnitLink = row.getCell(3) != null && !row.getCell(3).getStringCellValue().equals("") ? row.getCell(3).getStringCellValue() : null;
                                String subProcess = row.getCell(4) != null && !row.getCell(4).getStringCellValue().equals("") ? row.getCell(4).getStringCellValue() : null;
                                String categorized = row.getCell(5) != null && !row.getCell(5).getStringCellValue().equals("") ? row.getCell(5).getStringCellValue() : null;
                                String focusObjective = row.getCell(6) != null && !row.getCell(6).getStringCellValue().equals("") ? row.getCell(6).getStringCellValue() : null;
                                String findingDetail = row.getCell(7) != null && !row.getCell(7).getStringCellValue().equals("") ? row.getCell(7).getStringCellValue() : null;
                                String findingDescription = row.getCell(8) != null && !row.getCell(8).getStringCellValue().equals("") ? row.getCell(8).getStringCellValue() : null;
                                String findingRate = row.getCell(9) != null && !row.getCell(9).getStringCellValue().equals("") ? row.getCell(9).getStringCellValue() : null;
                                String rootCause = row.getCell(10) != null && !row.getCell(10).getStringCellValue().equals("") ? row.getCell(10).getStringCellValue() : null;
                                String impact = row.getCell(11) != null && !row.getCell(11).getStringCellValue().equals("") ? row.getCell(11).getStringCellValue() : null;
                                String recommendationDescription = row.getCell(12) != null && !row.getCell(12).getStringCellValue().equals("") ? row.getCell(12).getStringCellValue() : null;
                                String issueState = row.getCell(13) != null && !row.getCell(13).getStringCellValue().equals("") ? row.getCell(13).getStringCellValue() : null;

                                String recommendationState = row.getCell(14) != null && !row.getCell(14).getStringCellValue().equals("") ? row.getCell(14).getStringCellValue() : null;
                                String targetDate = row.getCell(15) != null && !row.getCell(15).getStringCellValue().equals("") ? row.getCell(15).getStringCellValue() : null;
                                String revisedTargetDate = row.getCell(16) != null && !row.getCell(16).getStringCellValue().equals("") ? row.getCell(16).getStringCellValue() : null;
                                String closed = row.getCell(17) != null && !row.getCell(17).getStringCellValue().equals("") ? row.getCell(17).getStringCellValue() : null;
                                String closedBy = row.getCell(18) != null && !row.getCell(18).getStringCellValue().equals("") ? row.getCell(18).getStringCellValue() : null;
                                String actualStart = row.getCell(19) != null && !row.getCell(19).getStringCellValue().equals("") ? row.getCell(19).getStringCellValue() : null;
                                String actualEnd = row.getCell(20) != null && !row.getCell(20).getStringCellValue().equals("") ? row.getCell(20).getStringCellValue() : null;
                                String teamLead = row.getCell(21) != null && !row.getCell(21).getStringCellValue().equals("") ? row.getCell(21).getStringCellValue() : null;
                                String teamMember = row.getCell(22) != null && !row.getCell(22).getStringCellValue().equals("") ? row.getCell(22).getStringCellValue() : null;
                                String auditeeUnits = row.getCell(23) != null && !row.getCell(23).getStringCellValue().equals("") ? row.getCell(23).getStringCellValue() : null;
                                String remark1 = row.getCell(24) != null && !row.getCell(24).getStringCellValue().equals("") ? row.getCell(24).getStringCellValue() : null;
                                String remark2 = row.getCell(25) != null && !row.getCell(25).getStringCellValue().equals("") ? row.getCell(25).getStringCellValue() : null;
                                String isCheck = row.getCell(26) != null && !row.getCell(26).getStringCellValue().equals("") ? row.getCell(26).getStringCellValue() : null;
                                String comment = row.getCell(27) != null && !row.getCell(27).getStringCellValue().equals("") ? row.getCell(27).getStringCellValue() : null;

                                DateFormat df = new SimpleDateFormat("dd/MM/yy");

//                                IAFinding iaFinding = new IAFinding();
//                                iaFinding.setAuditEngagementCode(auditEngagementCode);
//                                iaFinding.setAuditEngagementName(auditEngagementName);
//                                iaFinding.setType(type);
//                                iaFinding.setAuditUnitLink(auditeeUnitLink.replaceAll("[\\s|\\u00A0]+", ""));
//                                iaFinding.setSubProcess(subProcess);
//                                iaFinding.setCategorizedRecommendation(categorized);
//                                iaFinding.setFocusObjective(focusObjective);
//                                iaFinding.setFindingNo(type.equalsIgnoreCase("IA finding") ? findingDetail.substring(0, 6) : null);
//                                iaFinding.setFindingName(type.equalsIgnoreCase("IA finding") ? findingDetail.substring(7) : findingDetail);
//                                iaFinding.setFindingDescription(findingDescription);
//                                iaFinding.setFindingRate(findingRate);
//                                iaFinding.setRootCause(rootCause);
//                                iaFinding.setImpact(impact);
//                                iaFinding.setRecommendationDescription(recommendationDescription != null ? recommendationDescription.replaceAll("[\\s|\\u00A0]+", "") : null);
//                                iaFinding.setIssueState(issueState);
//                                iaFinding.setRecommendationState(recommendationState);
//                                iaFinding.setClosedBy(closedBy);
//                                // ถ้าเพิ่ม record ใหม่ ค่ามันจะเป็น int และถูก code ข้างบนทำให้เป็น string ค่ามันจะติดทศนิยม เช่น 30.0 เลยใช้ try catch จัดการ
////                                try {
////                                    iaFinding.setAuditEngagementCode(Integer.valueOf(auditEngagementCode));
////                                } catch (Exception e ) {
////                                    iaFinding.setAuditEngagementCode((int) Math.round(Double.valueOf(auditEngagementCode)));
////                                }
//
////                                try {
////                                    iaFinding.setYear(Integer.valueOf(year));
////                                } catch (Exception e) {
////                                    iaFinding.setYear((int) Math.round(Double.valueOf(year)));
////                                }
//
//                                iaFinding.setTeamLead(teamLead);
//                                iaFinding.setTeamMember(teamMember != null ? teamMember.replaceAll("[\\s|\\u00A0]+", "") : null);
//                                try{
//                                    iaFinding.setRevisedTargetDate(revisedTargetDate != null ? df.parse(revisedTargetDate) : null);
//                                }catch (Exception e){
//                                    iaFinding.setRevisedTargetDate(revisedTargetDate != null ? df.parse(decimalConvert(df, revisedTargetDate)) : null);
//                                }
//                                try{
//                                    iaFinding.setTargetDate(targetDate != null ? df.parse(targetDate) : null);
//                                }catch (Exception e){
//                                    iaFinding.setTargetDate(targetDate != null ? df.parse(decimalConvert(df, targetDate)) : null);
//                                }
//                                try {
//                                    iaFinding.setClosed(closed != null ? df.parse(closed) : null);
//                                }catch (Exception e){
//                                    iaFinding.setClosed(closed != null ? df.parse(decimalConvert(df, closed)) : null);
//                                }
//                                try {
//                                    iaFinding.setActualStart(actualStart != null ? df.parse(actualStart) : null);
//                                }catch (Exception e){
//                                    iaFinding.setActualStart(actualStart != null ? df.parse(decimalConvert(df, actualStart)) : null);
//                                }
//                                try {
//                                    iaFinding.setActualEnd(actualEnd != null ? df.parse(actualEnd) : null);
//                                }catch (Exception e){
//                                    iaFinding.setActualEnd(actualEnd != null ? df.parse(decimalConvert(df, actualEnd)) : null);
//                                }
////                                iaFinding.setIsCheck(isCheck != null ? (isCheck.equals("Y") ? true : false) : null);
//                                iaFinding.setAuditUnits(auditeeUnits != null ? auditeeUnits.replaceAll("[\\s|\\u00A0]+", "") : null);
//                                iaFinding.setRemark1(remark1);
//                                iaFinding.setRemark2(remark2);
//                                iaFinding.setIsCheck(isCheck.equals("Y") ? true : (isCheck.equals("N") ? false : iaFinding.getIsCheck()));
//                                iaFinding.setComment(comment);
//
//                                iaFinding.setCreatedDate(new Timestamp((new Date().getTime())));
//                                iaFinding.setUpdatedDate(new Timestamp((new Date().getTime())));
//
//                                iaFindings.add(iaFinding);

                                //handle empty cell
                                XSSFRow row2 = worksheet.getRow(index + 1);
                                if (row2 == null || row2.getCell(0) == null) {
                                    break;
                                }
                            }
                        }
                    }
                }

//                iaFindingRepository.deleteAll();
//                iaFindingRepository.saveAll(iaFindings);
            }
        } catch (Exception e){
            throw new BadRequestException(HttpStatus.BAD_REQUEST, "ไม่สามารถ Import ได้");
        }
        return "Success";
    }

//    public ByteArrayInputStream export() {
//        List<IAFinding> iaFindings = iaFindingRepository.findAll();
//
//        try (Workbook workbook = new XSSFWorkbook()) {
//            Sheet sheet = workbook.createSheet("IA-Finding");
//            DateFormat df = new SimpleDateFormat("dd/MM/yy");
//
//            setHeader(workbook, sheet);
//            logger.info("IA Export Found : " + iaFindings.size() + " items");
//
//            int indexRow = 0;
//            for (IAFinding iaFinding : iaFindings) {
//                logger.info("IA Export indexRow : " + indexRow);
//                Row dataRow = sheet.createRow(indexRow + 1);
//
//                dataRow.createCell(0).setCellValue(iaFinding.getAuditEngagementCode());
//                dataRow.createCell(1).setCellValue(iaFinding.getAuditEngagementName());
//                dataRow.createCell(2).setCellValue(iaFinding.getType());
//                dataRow.createCell(3).setCellValue(iaFinding.getAuditUnitLink());
//                dataRow.createCell(4).setCellValue(iaFinding.getSubProcess());
//                dataRow.createCell(5).setCellValue(iaFinding.getCategorizedRecommendation());
//                dataRow.createCell(6).setCellValue(iaFinding.getFocusObjective());
//                dataRow.createCell(7).setCellValue(iaFinding.getFindingNo() != null ? iaFinding.getFindingNo() +" "+ iaFinding.getFindingName() : iaFinding.getFindingName());
//                dataRow.createCell(8).setCellValue(iaFinding.getFindingDescription());
//                dataRow.createCell(9).setCellValue(iaFinding.getFindingRate());
//                dataRow.createCell(10).setCellValue(iaFinding.getRootCause());
//
//                dataRow.createCell(11).setCellValue(iaFinding.getImpact());
//                dataRow.createCell(12).setCellValue(iaFinding.getRecommendationDescription());
//                dataRow.createCell(13).setCellValue(iaFinding.getIssueState());
//                dataRow.createCell(14).setCellValue(iaFinding.getRecommendationState());
//                dataRow.createCell(15).setCellValue(iaFinding.getTargetDate() == null ? "" : df.format(iaFinding.getTargetDate()));
//                dataRow.createCell(16).setCellValue(iaFinding.getRevisedTargetDate() == null ? "" : df.format(iaFinding.getRevisedTargetDate()));
//                dataRow.createCell(17).setCellValue(iaFinding.getClosed() == null ? "" : df.format(iaFinding.getClosed()));
//                dataRow.createCell(18).setCellValue(iaFinding.getClosedBy());
//                dataRow.createCell(19).setCellValue(iaFinding.getActualStart() == null ? "" : df.format(iaFinding.getActualStart()));
//                dataRow.createCell(20).setCellValue(iaFinding.getActualEnd() == null ? "" : df.format(iaFinding.getActualEnd()));
//
//                dataRow.createCell(21).setCellValue(iaFinding.getTeamLead());
//                dataRow.createCell(22).setCellValue(iaFinding.getTeamMember());
//                dataRow.createCell(23).setCellValue(iaFinding.getAuditUnits());
//                dataRow.createCell(24).setCellValue(iaFinding.getRemark1());
//                dataRow.createCell(25).setCellValue(iaFinding.getRemark2());
//                dataRow.createCell(26).setCellValue(iaFinding.getIsCheck() != null ? (iaFinding.getIsCheck() ? "Y" : "N") : null);
//                dataRow.createCell(27).setCellValue(iaFinding.getComment());
//
//                indexRow++;
//            }
//
//            // Making size of column auto resize to fit with data
//            sheet.autoSizeColumn(0);
//            sheet.setColumnWidth(1, 10000);
//            sheet.autoSizeColumn(2);
//            sheet.setColumnWidth(3, 10000);
//            sheet.setColumnWidth(4, 10000);
//            sheet.setColumnWidth(5, 10000);
//            sheet.setColumnWidth(6, 10000);
//            sheet.setColumnWidth(7, 10000);
//            sheet.setColumnWidth(8, 10000);
//            sheet.autoSizeColumn(9);
//            sheet.setColumnWidth(10, 10000);
//            sheet.setColumnWidth(11, 10000);
//            sheet.setColumnWidth(12, 10000);
//            sheet.autoSizeColumn(13);
//            sheet.autoSizeColumn(14);
//            sheet.autoSizeColumn(15);
//            sheet.autoSizeColumn(16);
//            sheet.autoSizeColumn(17);
//            sheet.autoSizeColumn(18);
//            sheet.autoSizeColumn(19);
//            sheet.autoSizeColumn(20);
//            sheet.setColumnWidth(21, 10000);
//            sheet.setColumnWidth(22, 10000);
//            sheet.setColumnWidth(23, 10000);
//            sheet.setColumnWidth(24, 10000);
//            sheet.setColumnWidth(25, 10000);
//            sheet.autoSizeColumn(26);
//            sheet.setColumnWidth(27, 10000);
//
//            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//            workbook.write(outputStream);
//            return new ByteArrayInputStream(outputStream.toByteArray());
//
//        } catch (IOException ex) {
//            ex.printStackTrace();
//            return null;
//        }
//    }
}
