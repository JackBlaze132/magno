package com.unibague.backend.service;

import com.unibague.backend.util.UtilUpload;
import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
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
                .map(Cell::getStringCellValue)
                .collect(Collectors.toList());

        int colCount = headerCells.size();

        return rowStreamSupplier.get()
                .skip(1)
                .map(row ->{
            //Given a row, get a cellStream from it
            List<String> cellList = utilUpload.getStream(row)
                    .map(Cell::getStringCellValue)
                    .collect(Collectors.toList());

            return utilUpload.cellIteratorSupplier(colCount)
                    .get()
                    .collect(Collectors.toMap(headerCells::get, cellList::get));
        })
                .collect(Collectors.toList());
    }
}
