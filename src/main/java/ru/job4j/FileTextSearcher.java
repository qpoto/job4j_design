package ru.job4j;

import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

@Slf4j
public class FileTextSearcher {
    List<String> pathFromFullSearch = new ArrayList<>();

    public static void main(String[] args) {
        FileTextSearcher fse = new FileTextSearcher();
        List<String> paths = new ArrayList<>();
        paths.add("C:\\Users\\s.kondratiev\\.AAA\\Документ МойОфис.odt");
        paths.add("C:\\Users\\s.kondratiev\\.AAA\\Таблица МойОфис.ods");
        paths.add("C:\\Users\\s.kondratiev\\.AAA\\Новый текстовый документ.txt");

        String searchString = "Ночевала тучлотая";

        for (String path : paths) {
            if (getFileExtension(path).equals(".odt") || getFileExtension(path).equals("ods")) {
                if (searchInLibraOfficeDoc(path, searchString)) {
                    fse.pathFromFullSearch.add(path);
                }
            } else {
                if (searchInOtherDoc(path, searchString)) {
                    fse.pathFromFullSearch.add(path);
                }
            }
        }
        System.out.println(pathsSearchResultForOpen(fse.pathFromFullSearch));
    }

    private static String getFileExtension(String filePath) {
        int dotIndex = filePath.lastIndexOf(".");
        if (dotIndex > 0 && dotIndex < filePath.length() - 1) {
            return filePath.substring(dotIndex + 1).toLowerCase();
        }
        return "";
    }

    public static boolean searchInLibraOfficeDoc(String filePath, String searchString) {
        try (ZipFile zipFile = new ZipFile(filePath)) {
            ZipEntry entry = zipFile.getEntry("content.xml");
            if (entry != null) {
                Path tempFile = Files.createTempFile("temp", ".xml");
                Files.copy(zipFile.getInputStream(entry), tempFile, StandardCopyOption.REPLACE_EXISTING);

                String content = new String(Files.readAllBytes(tempFile));
                if (content.contains(searchString) || searchString.contains(content)) {
                    return true;
                }
            }
        } catch (IOException e) {
            log.error("Ошибка при открытии LO файлов");
        }
        return false;
    }

    public static boolean searchInOtherDoc(String filePath, String searchString) {
        try {
            String content = new String(Files.readAllBytes(Paths.get(filePath)));
            if (content.contains(searchString)) {
                return true;
            }
        } catch (IOException e) {
            log.error("Ошибка при открытии Текстовых файлов");
        }
        return false;
    }

    public static List<String> pathsSearchResultForOpen(List<String> pathFromFullSearch) {
        JsonMapper mapper = new JsonMapper();
        ArrayNode arrayForGrid = mapper.createArrayNode();
        int id = 1;

        ObjectNode result = mapper.createObjectNode();
        result.put("ID", id++);
        result.put("Тип", "РЕЗУЛЬТАТ");
        arrayForGrid.add(result);

        for (String pathString : pathFromFullSearch) {
            Path path = Paths.get(pathString);
            ObjectNode rowFile = mapper.createObjectNode();
            rowFile.put("ID", id++);
            rowFile.put("Тип", "Файл");
            rowFile.put("Имя", path.getFileName().toString());
            rowFile.put("Путь", path.toString());
            arrayForGrid.add(rowFile);
        }

        List<String> jsonStringList = new ArrayList<>();
        String arrayForGridToString = arrayForGrid.toString();
        jsonStringList.add(arrayForGridToString);
        return jsonStringList;
    }


}
