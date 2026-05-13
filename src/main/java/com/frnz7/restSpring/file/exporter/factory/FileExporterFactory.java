package com.frnz7.restSpring.file.exporter.factory;

import com.frnz7.restSpring.exception.BadRequestException;
import com.frnz7.restSpring.file.exporter.MediaTypes;
import com.frnz7.restSpring.file.exporter.contract.PersonExporter;
import com.frnz7.restSpring.file.exporter.impl.CsvExporter;
import com.frnz7.restSpring.file.exporter.impl.PdfExporter;
import com.frnz7.restSpring.file.exporter.impl.XlsxExporter;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class FileExporterFactory {
    private final ApplicationContext context;
    public FileExporterFactory(ApplicationContext context) {
        this.context = context;
    }

    public PersonExporter getExporter(String acceptHeader) throws Exception{
        if(acceptHeader.equalsIgnoreCase(MediaTypes.APPLICATION_XLSX_VALUE)){
            //usando injeção de dependencias. Assim nao preciso ficar instaciando toda hora com o "new".
            return context.getBean(XlsxExporter.class);
        } else if (acceptHeader.equalsIgnoreCase(MediaTypes.APPLICATION_CSV_VALUE)) {
            return context.getBean(CsvExporter.class);}
        else if (acceptHeader.equalsIgnoreCase(MediaTypes.APPLICATION_PDF_VALUE)) {
            return context.getBean(PdfExporter.class);
        }else{
            throw new BadRequestException("Invalid file format.");
        }
    }
}
