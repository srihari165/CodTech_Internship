package Programs;

import java.io.*;
import java.nio.file.*;
import java.util.List;
import java.util.stream.Collectors;

public class FileHandlingUtility {

    private static final String FILE_PATH = "sample.txt";

    public static void main(String[] args) {
        try {
            writeFile("Hello, this is a sample text file.\nLet's modify it later!");
            readFile();
            modifyFile("sample", "updated sample");
            readFile();
        } catch (IOException e) {
            System.err.println("Error handling file: " + e.getMessage());
        }
    }

    public static void writeFile(String content) throws IOException {
        Files.write(Paths.get(FILE_PATH), content.getBytes(), StandardOpenOption.CREATE,
                StandardOpenOption.TRUNCATE_EXISTING);
        System.out.println("File written successfully.");
    }

    public static void readFile() throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(FILE_PATH));
        System.out.println("File Contents:");
        lines.forEach(System.out::println);
    }

    public static void modifyFile(String target, String replacement) throws IOException {
        Path path = Paths.get(FILE_PATH);
        List<String> updatedLines = Files.readAllLines(path)
                .stream()
                .map(line -> line.replace(target, replacement))
                .collect(Collectors.toList());
        Files.write(path, updatedLines, StandardOpenOption.WRITE, StandardOpenOption.TRUNCATE_EXISTING);
        System.out.println("File modified successfully.");
    }
}
