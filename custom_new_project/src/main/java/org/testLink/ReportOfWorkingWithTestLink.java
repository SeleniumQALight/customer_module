package org.testLink;

import java.io.File;
import java.util.ArrayList;

public class ReportOfWorkingWithTestLink {
    //print list to file with path ./target/testCasesNotFoundInTestLink.txt
    public static void printListToFile(ArrayList<String> casesNotFounded, ArrayList<String> casesFounded, String path){
        File file = new File(path);
        if(file.exists()){
            file.delete();
        }
        try {
            java.io.FileWriter writer = new java.io.FileWriter(file);
            if (casesNotFounded.isEmpty()){
                writer.write("All test cases found in TestLink");
                writer.write("\n");
                writer.write("Total cases " + (casesFounded.size() ));
            } else {
                writer.write("Total cases " + (casesFounded.size() + casesNotFounded.size()));
                writer.write("\n");
                writer.write("Total number of test cases not found in TestLink: " + casesNotFounded.size());
                writer.write("\n");
                writer.write("Total number of test cases found in TestLink: " + casesFounded.size());
                writer.write("\n\n");
                writer.write("Test cases not found in TestLink: \n");
                casesNotFounded.forEach(s -> {
                    try {
                        writer.write(s);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                });

                writer.write("\n\n");

                writer.write("Test cases found in TestLink: \n");
                casesFounded.forEach(s -> {
                    try {
                        writer.write(s);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                });
            }
            writer.close();
        }catch (Exception e){
            throw new RuntimeException(e);
        }

    }
}
