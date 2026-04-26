package com.frnz7.restSpring.file.importer.contract;

import com.frnz7.restSpring.data.dto.PersonDTO;

import java.io.InputStream;
import java.util.List;

public interface FileImporter {

    List<PersonDTO> importFile(InputStream inputStream) throws Exception;

}
