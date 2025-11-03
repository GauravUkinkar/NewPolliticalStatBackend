package com.pollitica_Stat.Service;

import java.io.IOException;
import java.util.List;

import com.pollitica_Stat.Model.VotersDetails;

import jakarta.servlet.http.HttpServletResponse;

public interface ExcelExportService {
    void exportVoters(List<VotersDetails> voters, String fileName, HttpServletResponse response) throws IOException;

}
