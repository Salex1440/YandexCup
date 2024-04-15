package com.tinkoff.year2024.summer.taskC;

import java.io.*;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

public class TaskC {
    public static void main(String[] args) throws IOException {
        File file = new File("src/main/java/com/tinkoff/year2024/summer/taskC/input1.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(readWord(br));
        Folder root = null;
        for (int i = 0; i < n; i++) {
            String path = readWord(br);
            String[] names = path.split("/");
            if (root == null) {
                root = new Folder(names[0], 0);
            }
            Folder folder = root;
            for (int j = 1; j < names.length; j++) {
                folder = folder.addFolder(names[j]);
            }
        }

        System.out.println(root);
    }

    private static String readWord(BufferedReader br) throws IOException {
        int ch;
        CharArrayWriter caw = new CharArrayWriter();
        while ((ch = br.read()) != ' ' && ch != '\n') {
            caw.append((char) ch);
        }
        return caw.toString();
    }

    private static class Folder {

        private final String name;
        private final int level;
        private final SortedMap<String, Folder> folders = new TreeMap<>();

        public Folder(String name, int level) {
            this.name = name;
            this.level = level;
        }

        public Folder addFolder(String name) {
            if (folders.containsKey(name)) return folders.get(name);
            Folder folder = new Folder(name, this.level + 1);
            folders.put(name, folder);
            return folder;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("  ".repeat(level)).append(name).append("\n");
            for (Map.Entry<String, Folder> e : folders.entrySet()) {
                sb.append(e.getValue());
            }
            return sb.toString();
        }
    }

}
