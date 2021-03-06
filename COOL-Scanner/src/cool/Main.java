package cool;

import cool.scanner.Scanner;
import cool.scanner.Token;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.charset.Charset;
import java.util.LinkedList;

public class Main {
    public static void main(String[] args) throws IOException {
        String source = "";
        if (args.length > 1) {
            System.out.println("Usage: Main [filename]");
        } else if (args.length == 1) {
            String path = args[0];
            System.out.println("#name \"" + path + "\"");
            byte[] bytes = Files.readAllBytes(Paths.get(path));
            source = new String(bytes, Charset.defaultCharset());
        } else {
            System.out.print("> ");

            InputStreamReader input = new InputStreamReader(System.in);
            BufferedReader reader = new BufferedReader(input);
            source = reader.readLine();
        }

        Scanner scanner = new Scanner(source);
        LinkedList<Token> tokens = scanner.scanTokens();

        // For now, just print the tokens.
        for (Token token : tokens) {
            System.out.println(token);
        }
    }
}