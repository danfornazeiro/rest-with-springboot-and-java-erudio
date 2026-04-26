package com.frnz7.restSpring.file.importer.factory;

import com.frnz7.restSpring.exception.BadRequestException;
import com.frnz7.restSpring.file.importer.contract.FileImporter;
import com.frnz7.restSpring.file.importer.impl.CsvImporter;
import com.frnz7.restSpring.file.importer.impl.XlsxImporter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class FileImporterFactory {
    private Logger logger = LoggerFactory.getLogger(FileImporterFactory.class);

    private final ApplicationContext context;
    public FileImporterFactory(ApplicationContext context) {
        this.context = context;
    }

    public FileImporter getImporter(String fileName) throws Exception{
        if(fileName.endsWith(".xlsx")){
            //usando injeção de dependencias. Assim nao preciso ficar instaciando toda hora com o "new".
            return context.getBean(XlsxImporter.class);
        } else if (fileName.endsWith(".csv")) {
            return context.getBean(CsvImporter.class);
        }else{
            throw new BadRequestException("Invalid file format.");
        }
    }
}
