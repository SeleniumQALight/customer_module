package org.utils;



import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class MergeAllTestInOneTxtFile {
    public static void main(String[] args) {
        String path = "./custom_new_project/src/test/java";

        String outputPath = "./output.txt"; // The path to the output file

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
                            List<String> lines = Files.readAllLines(filePath);
                            boolean startCopying = false;
                            for (String line : lines) {
                                if (line.contains("class")) {
                                    startCopying = true;
                                }
                                if (startCopying) {
                                    fileContents.add(line);
                                }
                            }
                            fileContents.add("\n"); // Add a newline between files
                            fileContents.add("-".repeat(100));
                            fileContents.add("\n"); // Add a newline between files
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });

            Files.write(Paths.get(outputPath), fileContents, StandardOpenOption.CREATE, StandardOpenOption.WRITE);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("File was created in path " + outputPath);
    }
}
