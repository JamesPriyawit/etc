package com.example.etc.controllers;

import org.apache.poi.util.IOUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.text.ParseException;

public class ExcelController {

//    @GetMapping(value = "/export")
//    public void exportSummaryDepartment(
//            HttpServletResponse response
//    ) throws IOException {
//        response.setContentType("application/octet-stream");
//        response.setHeader("Content-Disposition", "attachment; filename=\"export_ia_finding.xlsx\"");
//        ByteArrayInputStream stream = iaFindingService.export();
//        IOUtils.copy(stream, response.getOutputStream());
//    }
//
//    @PostMapping(value = "/ia-finding")
//    @ResponseStatus(HttpStatus.CREATED)
//    public String importIaFinding(
//            @RequestParam("excel") MultipartFile[] excelFiles
//    ) throws IOException, ParseException {
//        return importExcelService.importIaFinding(excelFiles);
//    }
}
