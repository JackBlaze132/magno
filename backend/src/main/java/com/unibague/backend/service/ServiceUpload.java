package com.unibague.backend.service;

import com.unibague.backend.util.UtilUpload;
import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@Service
public class ServiceUpload {

    //https://www.youtube.com/watch?v=pCQ20erq_cg&t=7s

    private final UtilUpload utilUpload;

    public ServiceUpload(UtilUpload utilUpload){
        this.utilUpload = utilUpload;
    }
    public List<Map<String, String>> uploadExcel(MultipartFile file) throws Exception{

        Path tempDir = Files.createTempDirectory("");

        File tempFile = tempDir.resolve(file.getOriginalFilename()).toFile();

        file.transferTo(tempFile);

        Workbook workbook = WorkbookFactory.create(tempFile);

        Sheet sheet = workbook.getSheetAt(0);

        Supplier<Stream<Row>> rowStreamSupplier = utilUpload.getRowStreamSupplier(sheet);

        Row headerRow = rowStreamSupplier.get().findFirst().get();

        List<String> headerCells = utilUpload.getStream(headerRow)
                .map(this::getCellValueAsString)
                .collect(Collectors.toList());

        int colCount = headerCells.size();

        return rowStreamSupplier.get()
                .skip(1)
                .map(row ->{
            //Given a row, get a cellStream from it
            List<String> cellList = utilUpload.getStream(row)
                    .map(this::getCellValueAsString)
                    .collect(Collectors.toList());

            return utilUpload.cellIteratorSupplier(colCount)
                    .get()
                    .collect(Collectors.toMap(headerCells::get, cellList::get));
        })
                .collect(Collectors.toList());
    }

    private String getCellValueAsString(Cell cell){
        switch (cell.getCellType()){
            case STRING:
                return cell.getStringCellValue();
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)){
                    return String.valueOf(cell.getDateCellValue());
                }
                else{
                    double numericValue = cell.getNumericCellValue();
                    if (numericValue == (long) numericValue) {
                        return String.format("%d", (long) numericValue);
                    } else {
                        return BigDecimal.valueOf(numericValue).toPlainString();
                    }
                }
            default:
                return "";
        }
    }
}
