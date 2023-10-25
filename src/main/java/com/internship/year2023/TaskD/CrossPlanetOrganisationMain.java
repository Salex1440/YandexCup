package com.internship.year2023.TaskD;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CrossPlanetOrganisationMain {

    public static void main(String[] args) throws IOException {
        File file = new File("src/main/java/com/internship/year2023/TaskD/input2.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        int n = Integer.parseInt(readWord(br));
        List<Node> nodes = new ArrayList<>();
        nodes.add(new Node(0, "AB"));
        for (int i = 1; i <= n; i++) {
            nodes.add(new Node(i, readWord(br)));
        }

        br.skip(2);
        int val, curr = 0;
        while ((val = Integer.parseInt(readWord(br))) != 0 ) {
            if (val != curr) {
                Node p = nodes.get(curr);
                Node c = nodes.get(val);
                p.addChild(c);
                curr = val;
            } else {
                Node node = nodes.get(curr);
                curr = node.getParent().getIndex();
            }
        }

        String output = "src/main/java/com/internship/year2023/TaskD/output.txt";
        try (BufferedWriter writter = new BufferedWriter(new FileWriter(output))) {
            for (int i = 1; i <= n; i++) {
                writter.write(nodes.get(i).getBarrier() + " ");
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String readWord(BufferedReader br) throws IOException {
        int ch;
        CharArrayWriter caw = new CharArrayWriter();
        while ((ch = br.read()) != ' ' && ch != '\n') {
            caw.append((char) ch);
        }
        return caw.toString();
    }

}

class Node {
    private final Integer index;
    private final String language;
    private final List<Node> children = new ArrayList<>();
    private Node parent;
    private int barrier = 0;
    private int cnt = 1;

    public Node(Integer index, String language) {
        this.index = index;
        this.language = language;
    }

    public void addChild(Node node) {
        children.add(node);
        node.setParent(this);
    }

    public void setParent(Node node) {
        this.parent = node;
        if (parent.language.equals("AB")) {

        } else if (parent.language.equals(this.language)) {
            this.cnt = parent.cnt + 1;
            this.barrier = 0;
        } else {
            this.cnt = 1;
            this.barrier = parent.cnt;
        }
    }

    public Integer getIndex() {
        return index;
    }

    public Node getParent() {
        return parent;
    }

    public int getBarrier() {
        return barrier;
    }

}