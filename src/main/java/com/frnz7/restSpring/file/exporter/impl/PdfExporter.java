package com.frnz7.restSpring.file.exporter.impl;

import com.frnz7.restSpring.data.dto.PersonDTO;
import com.frnz7.restSpring.file.exporter.contract.FileExporter;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class PdfExporter implements FileExporter {
    @Override
    public Resource exportFile(List<PersonDTO> people) throws Exception {
        InputStream inputStream = getClass().getResourceAsStream("/templates/people.jrxml");
        if(inputStream == null ) {
            throw new RuntimeException("Template file not found: /resources/templates/people.jrxml");
        }

        JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);
        Map<String, Object> parameters = new HashMap<>();

        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(people);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters,dataSource);
        try(ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);
            return new ByteArrayResource(outputStream.toByteArray());

        }
    }
}
