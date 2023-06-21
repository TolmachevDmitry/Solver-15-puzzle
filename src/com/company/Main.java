package com.company;

//import org.apache.commons.cli.CommandLine;

import com.util.ArrayUtils;
import ru.vsu.cs.util.SwingUtils;

import javax.swing.*;
import java.io.PrintStream;
import java.util.Locale;

import static jdk.nashorn.internal.runtime.JSType.toInteger;

class Main {

    public static void main(String[] args) throws Exception {
        Locale.setDefault(Locale.ROOT);
        SwingUtils.setDefaultFont("Microsoft Sans Serif", 18);
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrameMain();
            }
        });
    }
}
