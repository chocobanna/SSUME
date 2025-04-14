package com.ssume.editor;

import com.ssume.ui.EditorFrame;

public class Main {
    public static void main(String[] args) {
        // Run the UI on the Swing event-dispatch thread.
        javax.swing.SwingUtilities.invokeLater(() -> {
            EditorFrame frame = new EditorFrame();
            frame.setVisible(true);
        });
    }
}
