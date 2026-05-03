package com.frnz7.restSpring.file.exporter.contract;

import com.frnz7.restSpring.data.dto.PersonDTO;
import org.springframework.core.io.Resource;

import java.util.List;

public interface FileExporter {

    Resource exportFile(List<PersonDTO> people) throws Exception;

}
