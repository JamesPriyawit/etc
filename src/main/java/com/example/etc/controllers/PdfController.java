package com.example.etc.controllers;

import io.swagger.annotations.ApiParam;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class PdfController {
//    @PostMapping("/image")
//    public String getPdfFromImage(
//            @ApiParam(value = "IdCardFront,BankAccount")
//            @RequestBody AccountFile dto,
//            Authentication authentication
//    ) throws IOException, DocumentException {
//        return pdfService.getPdfFromImage(dto, authentication);
//    }
//
//    @GetMapping("/view/{fileType}")
//    public ResponseEntity<InputStreamResource> viewPdf(
//            @PathVariable("fileType") String fileType,
//            Authentication authentication
//    ) throws FileNotFoundException {
//        return pdfService.viewPdf(fileType, authentication);
//    }
//
//    @GetMapping("/preview/{fileType}")
//    public @ResponseBody
//    byte[] getImageWithMediaType(
//            @PathVariable("fileType") String fileType,
//            Authentication authentication
//    ) throws IOException {
//        InputStream in = pdfService.previewPdf(fileType, authentication);
//        return IOUtils.toByteArray(in);
//    }
}
