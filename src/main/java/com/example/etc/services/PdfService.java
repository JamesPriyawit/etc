//package com.example.etc.services;
//
//import org.springframework.core.io.InputStreamResource;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.Authentication;
//
//import java.io.*;
//import java.util.Date;
//
//import static javax.swing.text.StyleConstants.ALIGN_CENTER;
//
//public class PdfService {
//
//    public String getPdfFromImage(
//            AccountFile dto,
//            Authentication authentication
//    ) throws IOException, DocumentException {
//        User u = (User) authentication.getPrincipal();
//        Date birthdate = accountRepository.findBirthDateByUserId(u.getId());
//        AccountFile af = accountFileRepository.findOneByUserIdAndFileType(u.getId(), dto.getFileType());
//        if (af == null){
//            throw new BadRequestException(HttpStatus.BAD_REQUEST, "Not found image");
//        }
//        String folder = "pdf";
//        genFolder(folder);
//        String fileName = dto.getFileType() + "_" + u.getId() +"_"+ new Date().getTime() +".pdf"; // New file
//
//        OutputStream fos = new FileOutputStream(new File(fileOutputPath +"/"+ folder +"/"+ fileName));
//        String inputFile = Constants.PDF_TEMPLATE.EMPTY_TEMPLATE;
//        PdfReader pdfReader = new PdfReader(inputFile);
//        PdfStamper pdfStamper = new PdfStamper(pdfReader, fos);
//
//        setPdfPassword(birthdate, pdfStamper);
//
//        String textFront = Constants.PDF_TEMPLATE.TEXT_FRONT;
//        PdfContentByte pdfContentByte = pdfStamper.getOverContent(1);
//
//        if(dto.getFileType().equals("BankAccount")) {
//
//            if (af.getImageUrl() != null) {
//                Image image = Image.getInstance(af.getImageUrl().replace(fileBaseUrl + fileOutputUrl, fileOutputPath));
//                image.setAbsolutePosition(100, 450);
//                image.scaleAbsolute(430, 250);
//                pdfContentByte.addImage(image);
//            }
//
//            newTextRotation(pdfContentByte,textFront,160,670,20,"ใช้สำหรับเปิดบัญชี");
//            newTextRotation(pdfContentByte,textFront,170,655,20,"ซื้อขายหน่วยลงทุน เท่านั้น");
//
//            newTextImage(pdfContentByte, textFront, 280, 420, 18, "สำเนาถูกต้อง");
//
//            AccountFile signature = accountFileRepository.findOneByUserIdAndFileType(u.getId(), "Signature");
//
//            if (signature.getImageUrl() != null) {
//                Image image = Image.getInstance(signature.getImageUrl().replace(fileBaseUrl + fileOutputUrl, fileOutputPath));
//                image.setAbsolutePosition(240, 360);
//                image.scaleAbsolute(150, 50);
//                pdfContentByte.addImage(image);
//            }
//        } else {
//
//            if (af.getImageUrl() != null) {
//                Image image = Image.getInstance(af.getImageUrl().replace(fileBaseUrl + fileOutputUrl, fileOutputPath));
//                image.setAbsolutePosition(180, 500);
//                image.scaleAbsolute(270, 170);
//                pdfContentByte.addImage(image);
//            }
//
//            newTextRotation(pdfContentByte,textFront,210,655,20,"ใช้สำหรับเปิดบัญชี");
//            newTextRotation(pdfContentByte,textFront,220,640,20,"ซื้อขายหน่วยลงทุน เท่านั้น");
//
//            newTextImage(pdfContentByte, textFront, 280, 470, 18, "สำเนาถูกต้อง");
//
//            AccountFile signature = accountFileRepository.findOneByUserIdAndFileType(u.getId(), "Signature");
//            if (signature.getImageUrl() != null) {
//                Image image = Image.getInstance(signature.getImageUrl().replace(fileBaseUrl + fileOutputUrl, fileOutputPath));
//                image.setAbsolutePosition(240, 410);
//                image.scaleAbsolute(150, 50);
//                pdfContentByte.addImage(image);
//            }
//
////            AccountFile IdCardBack = accountFileRepository.findOneByUserIdAndFileType(u.getId(),"IdCardBack");
////            if (IdCardBack.getImageUrl() != null) {
////                Image image = Image.getInstance(IdCardBack.getImageUrl().replace(fileBaseUrl + fileOutputUrl, fileOutputPath));
////                image.setAbsolutePosition(180, 230);
////                image.scaleAbsolute(270, 170);
////                pdfContentByte.addImage(image);
////            }
//        }
//        pdfStamper.close();
//        System.out.println("Modified PDF created in >> "+ fileOutputPath  +"/"+ fileName);
//
//        af.setPdfUrl(fileBaseUrl + fileOutputUrl +"/"+ folder +"/"+ fileName);
////        String response = fileOutputUrl +"/"+ folder +"/"+ fileName;
//        accountFileRepository.saveAndFlush(af);
//
//        // copy pdf without password
//        savePdfWithOutPassword(u.getId(), dto.getFileType(), af.getPdfUrl());
//        return "OK";
//    }
//
//    public ResponseEntity<InputStreamResource> viewPdf(
//            String fileType,
//            Authentication authentication
//    ) throws FileNotFoundException {
//        User user = (User) authentication.getPrincipal();
//        if(user == null) {
//            throw new UnauthorizedException(HttpStatus.UNAUTHORIZED, "Not Authorized");
//        }
//        AccountFile af = accountFileRepository.findOneByUserIdAndFileType(user.getId(), fileType);
//        if (af == null){
//            throw new BadRequestException(HttpStatus.BAD_REQUEST, "File not found");
//        }
//        File file = new File(af.getPdfUrl().replace(fileBaseUrl + fileOutputUrl, fileOutputPath));
//        HttpHeaders headers = new HttpHeaders();
//        headers.add("content-disposition", "inline;filename=" + file.getAbsoluteFile());
//        headers.add("Content-Disposition", "attachment; filename=\""+fileType+".pdf\"");
//
//        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
//
//        return ResponseEntity.ok()
//                .headers(headers)
//                .contentLength(file.length())
//                .contentType(MediaType.parseMediaType("application/pdf"))
//                .body(resource);
//    }
//
//    public InputStream previewPdf(
//            String fileType,
//            Authentication authentication
//    ){
//        User user = (User) authentication.getPrincipal();
//        if(user == null) {
//            throw new UnauthorizedException(HttpStatus.UNAUTHORIZED, "Not Authorized");
//        }
//        AccountFile af = accountFileRepository.findOneByUserIdAndFileType(user.getId(), fileType);
//        if (af == null){
//            throw new BadRequestException(HttpStatus.BAD_REQUEST, "File not found");
//        }
//        File file = new File(af.getPdfUrl().replace(fileBaseUrl + fileOutputUrl, fileOutputPath));
//        InputStream resource = null;
//        try {
//            resource = new FileInputStream(file);
//        } catch (Exception e) {
//            logger.error("PreviewPdf Error"+e.getMessage());
//        }
//
//        return resource;
//    }
//
//    public void newText(
//            PdfContentByte pdfContentByte,
//            String front,
//            int x,
//            float y,
//            int size,
//            String data
//    ) throws IOException, DocumentException {
//
//        // Add text in existing PDF
////        pdfContentByte.beginText();
//        pdfContentByte.setFontAndSize(BaseFont.createFont
//                        (front, //Font name
//                                BaseFont.IDENTITY_H, //Font encoding
//                                BaseFont.EMBEDDED //Font embedded TIMES_ROMAN
//                        )
//                , size); // set font and size
//        pdfContentByte.setTextMatrix(x, y);
//        pdfContentByte.showText(data);
//    }
//
//    public void newTextImage(
//            PdfContentByte pdfContentByte,
//            String front,
//            int x,
//            float y,
//            int size,
//            String data
//    ) throws IOException, DocumentException {
//
//        // Add text in existing PDF
////        pdfContentByte.beginText();
//        pdfContentByte.setFontAndSize(BaseFont.createFont
//                        (front, //Font name
//                                BaseFont.IDENTITY_H, //Font encoding
//                                BaseFont.EMBEDDED //Font embedded TIMES_ROMAN
//                        )
//                , size); // set font and size
//        pdfContentByte.setTextMatrix(x, y);
//        pdfContentByte.setColorFill(BaseColor.RED);
//        pdfContentByte.showText(data);
//    }
//
//    public void newTextRotation(
//            PdfContentByte pdfContentByte,
//            String front,
//            int x,
//            float y,
//            int size,
//            String data
//    ) throws IOException, DocumentException {
//
//        // Add text in existing PDF
////        pdfContentByte.beginText();
//        pdfContentByte.setFontAndSize(BaseFont.createFont
//                        (front, //Font name
//                                BaseFont.IDENTITY_H, //Font encoding
//                                BaseFont.EMBEDDED //Font embedded TIMES_ROMAN
//                        )
//                , size); // set font and size
//        pdfContentByte.setColorFill(BaseColor.RED);
//        pdfContentByte.showTextAligned(	ALIGN_CENTER,data,x,y,25);
//    }
//
//    private void genFolder(
//            String folder
//    ){
//        if (folder != null && !folder.isEmpty()) {
//            File directoryToUpload = new File(this.fileOutputPath + File.separator + folder);
//            if (!directoryToUpload.exists()) {
//                directoryToUpload.mkdir();
//            }
//        }
//    }
//}
