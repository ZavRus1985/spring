package org.ruslan.part2lesson3.repository;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class SqlFileReader {

   public static String sqlQuerry() {

       Path path = Paths.get("C:\\Users\\Rus\\IdeaProjects\\spring\\spring-core\\part2lesson3\\src\\main\\resources\\sqlCreateTable.txt");
        String sql = "";
        try {
            List<String> lines = Files.readAllLines(path);
             sql = String.join("", lines);

            return sql;
        } catch (IOException e) {
            throw new RuntimeException("Error reading sql-file: " + "sqlCreateTable.txt",  e);
        }
    }
}
