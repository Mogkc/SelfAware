package edu.gcccd.csis;

import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;

public interface Language {
    // Java reserved words are keywords that are reserved by Java functions or other uses that cannot be used
    // as identifiers (e.g., variable names, function names, class names).
    String[] ReservedWords = {
            "abstract",
            "assert",
            "boolean",
            "break",
            "byte",
            "case",
            "catch",
            "char",
            "class",
            "const",
            "default",
            "do",
            "double",
            "else",
            "enum",
            "extends",
            "false",
            "final",
            "finally",
            "float",
            "for",
            "goto",
            "if",
            "implements",
            "import",
            "instanceof",
            "int",
            "interface",
            "long",
            "native",
            "new",
            "null",
            "package",
            "private",
            "protected",
            "public",
            "return",
            "short",
            "static",
            "strictfp",
            "super",
            "switch",
            "synchronized",
            "this",
            "throw",
            "throws",
            "transient",
            "true",
            "try",
            "void",
            "volatile",
            "while",
            "continue"};

    // sorts the ReservedWords string array, longest reserved word 1st.
    static void sort() {
        Arrays.sort(ReservedWords, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o2.length() - o1.length();
            }
        });
    }

    /**
     * Counts the total number of occurrences of all Java keyword in the file.
     *
     * @param sourceFile {@link String} path to a java source file
     * @return {@link int} number of times java keyword occur in the source file.
     * @throws Exception not a java file or no file maybe ...
     */
    int occurrences(String sourceFile) throws Exception;

    /**
     * Appends the provided file with the provided message
     *
     * @param sourceFile {@link String} path to a java source file
     * @param message    {@link String} message to be appended
     * @throws IOException things didn't go too well ...
     */
    void append(String sourceFile, String message) throws IOException;
}