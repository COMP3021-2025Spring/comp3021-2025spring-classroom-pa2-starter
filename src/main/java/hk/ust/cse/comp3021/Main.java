/*
 * Copyright (c) 2025.
 * Xiang Chen xchenht@connect.ust.hk
 * This project is developed only for HKUST COMP3021 Programming Assignment
 */

package hk.ust.cse.comp3021;

public class Main {
    public static void main(String[] args) {
        String user = (args.length == 0) ? "admin" : args[0];
        ChatManager.repl(user);
    }
}
