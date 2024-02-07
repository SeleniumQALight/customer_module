package org.utils;



import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MergeAllTestInOneHTMLFile {
    public static void main(String[] args) {

        String path = "./custom_new_project/src/test/java";
        String outputPath = "./output.html"; // The path to the output file

        //delete file if exists in path outputPath
        try {
            boolean isDeleted = Files.deleteIfExists(Paths.get(outputPath));
            if (isDeleted) {
                System.out.println("File deleted successfully");
            } else {
                System.out.println("File does not exist");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        ArrayList<String> fileNamesForIgnore = new ArrayList<>();
        fileNamesForIgnore.add("TestLinkTest");

        ArrayList<String> fileForIgnoreByFolder = new ArrayList<>();
        fileForIgnoreByFolder.add("registrationNewUserTests");

        try {
            List<String> fileContents = new ArrayList<>();
            fileContents.add("<html><body>"); // Start of HTML file
            Files.walk(Paths.get(path))
                    .filter(Files::isRegularFile)
                    // filter file by name
                    .filter(filePath -> !filePath.toString().toLowerCase().contains("suit"))
                    // filter file by folder
                    .filter(filePath -> !fileForIgnoreByFolder.contains(filePath.getParent().getFileName().toString()))
                    // filter file by fileNamesForIgnore list
                    .filter(filePath -> !fileNamesForIgnore.contains(filePath.getFileName().toString().replace(".java", "")))
                    .forEach(filePath -> {
                        try {
                            List<String> lines = Files.lines(filePath).collect(Collectors.toList());
                            boolean startCopying = false;
                            for (String line : lines) {
                                if (line.startsWith("package") || line.startsWith("import")) {
                                    continue; // Skip the package declaration
                                }
                                if (line.startsWith("public class") || line.contains(" TR")) {
                                    startCopying = true;
                                    line = "<b>" + line + "</b>"; // Make class name bold
                                }
//                                if (startCopying) {
                                    fileContents.add(line + "<br>"); // Add HTML line break
//                                }
                            }
                            fileContents.add("<hr>"); // Add a horizontal line between files
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
            fileContents.add("</body></html>"); // End of HTML file

            Files.write(Paths.get(outputPath), fileContents, StandardOpenOption.CREATE, StandardOpenOption.WRITE);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("File with all test cases was created in path " + outputPath);
    }}
