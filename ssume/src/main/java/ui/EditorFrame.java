package com.ssume.ui;

import com.ssume.model.ModDescriptor;
import com.ssume.io.ModDescriptorIO;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class EditorFrame extends JFrame {
    // UI components for mod descriptor fields.
    private JTextField idField, nameField, authorField, versionField, gameVersionField, modPluginField;
    private JTextArea descriptionArea, replaceArea, jarsArea;
    private JCheckBox totalConversionBox, utilityBox;
    
    // The currently loaded file (if any)
    private File currentFile = null;
    
    public EditorFrame() {
        setTitle("SSUME Starsector Universal Mod Editor");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 600);
        setLocationRelativeTo(null);
        
        initMenu();
        initUI();
    }
    
    private void initMenu() {
        JMenuBar menubar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");

        // Instead of using a file chooser, we ask for a specific file path.
        JMenuItem loadItem = new JMenuItem("Load Mod File");
        loadItem.addActionListener(e -> loadModFile());
        JMenuItem saveItem = new JMenuItem("Save Mod File");
        saveItem.addActionListener(e -> saveModFile());
        
        fileMenu.add(loadItem);
        fileMenu.add(saveItem);
        menubar.add(fileMenu);
        setJMenuBar(menubar);
    }
    
    private void initUI() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5,5,5,5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        
        int y = 0;
        // Field for "id"
        gbc.gridx = 0; gbc.gridy = y;
        panel.add(new JLabel("ID:"), gbc);
        idField = new JTextField();
        gbc.gridx = 1; gbc.gridy = y++;
        panel.add(idField, gbc);
        
        // Field for "name"
        gbc.gridx = 0; gbc.gridy = y;
        panel.add(new JLabel("Name:"), gbc);
        nameField = new JTextField();
        gbc.gridx = 1; gbc.gridy = y++;
        panel.add(nameField, gbc);
        
        // Field for "author"
        gbc.gridx = 0; gbc.gridy = y;
        panel.add(new JLabel("Author:"), gbc);
        authorField = new JTextField();
        gbc.gridx = 1; gbc.gridy = y++;
        panel.add(authorField, gbc);
        
        // Checkbox for "totalConversion"
        gbc.gridx = 0; gbc.gridy = y;
        panel.add(new JLabel("Total Conversion:"), gbc);
        totalConversionBox = new JCheckBox();
        gbc.gridx = 1; gbc.gridy = y++;
        panel.add(totalConversionBox, gbc);
        
        // Checkbox for "utility"
        gbc.gridx = 0; gbc.gridy = y;
        panel.add(new JLabel("Utility:"), gbc);
        utilityBox = new JCheckBox();
        gbc.gridx = 1; gbc.gridy = y++;
        panel.add(utilityBox, gbc);
        
        // Field for "version"
        gbc.gridx = 0; gbc.gridy = y;
        panel.add(new JLabel("Version:"), gbc);
        versionField = new JTextField();
        gbc.gridx = 1; gbc.gridy = y++;
        panel.add(versionField, gbc);
        
        // Text area for "description"
        gbc.gridx = 0; gbc.gridy = y;
        panel.add(new JLabel("Description:"), gbc);
        descriptionArea = new JTextArea(4, 20);
        JScrollPane descScroll = new JScrollPane(descriptionArea);
        gbc.gridx = 1; gbc.gridy = y++;
        panel.add(descScroll, gbc);
        
        // Field for "gameVersion"
        gbc.gridx = 0; gbc.gridy = y;
        panel.add(new JLabel("Game Version:"), gbc);
        gameVersionField = new JTextField();
        gbc.gridx = 1; gbc.gridy = y++;
        panel.add(gameVersionField, gbc);
        
        // Text area for "replace" (comma-separated)
        gbc.gridx = 0; gbc.gridy = y;
        panel.add(new JLabel("Replace (comma-separated):"), gbc);
        replaceArea = new JTextArea(2, 20);
        JScrollPane replaceScroll = new JScrollPane(replaceArea);
        gbc.gridx = 1; gbc.gridy = y++;
        panel.add(replaceScroll, gbc);
        
        // Text area for "jars" (comma-separated)
        gbc.gridx = 0; gbc.gridy = y;
        panel.add(new JLabel("Jars (comma-separated):"), gbc);
        jarsArea = new JTextArea(2, 20);
        JScrollPane jarsScroll = new JScrollPane(jarsArea);
        gbc.gridx = 1; gbc.gridy = y++;
        panel.add(jarsScroll, gbc);
        
        // Field for "modPlugin"
        gbc.gridx = 0; gbc.gridy = y;
        panel.add(new JLabel("Mod Plugin:"), gbc);
        modPluginField = new JTextField();
        gbc.gridx = 1; gbc.gridy = y++;
        panel.add(modPluginField, gbc);
        
        JScrollPane scrollPane = new JScrollPane(panel);
        add(scrollPane, BorderLayout.CENTER);
    }
    
    /**
     * Prompts the user to enter a specific file path and then attempts to load the mod file.
     */
    private void loadModFile() {
        String filePath = JOptionPane.showInputDialog(
                this,
                "Enter the full file path for the mod descriptor (e.g., mod_info.json):",
                "Input File Path",
                JOptionPane.QUESTION_MESSAGE);
        if (filePath != null && !filePath.trim().isEmpty()) {
            File file = new File(filePath.trim());
            if (!file.exists() || !file.isFile()) {
                JOptionPane.showMessageDialog(this, "The file path is invalid or the file does not exist.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            currentFile = file;
            try {
                ModDescriptor modDesc = com.ssume.io.ModDescriptorIO.loadFromFile(currentFile);
                populateFields(modDesc);
                JOptionPane.showMessageDialog(this, "Mod file loaded successfully!");
            } catch(Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error loading mod file: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    /**
     * Prompts the user to enter a file path if none is selected and then saves the mod file.
     */
    private void saveModFile() {
        if(currentFile == null) {
            String filePath = JOptionPane.showInputDialog(
                    this,
                    "Enter the full file path where you wish to save the mod descriptor:",
                    "Save File Path",
                    JOptionPane.QUESTION_MESSAGE);
            if (filePath == null || filePath.trim().isEmpty()) {
                return;
            }
            currentFile = new File(filePath.trim());
        }
        try {
            ModDescriptor modDesc = gatherFields();
            com.ssume.io.ModDescriptorIO.saveToFile(modDesc, currentFile);
            JOptionPane.showMessageDialog(this, "Mod file saved successfully!");
        } catch(Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error saving mod file: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void populateFields(ModDescriptor modDesc) {
        idField.setText(modDesc.getId());
        nameField.setText(modDesc.getName());
        authorField.setText(modDesc.getAuthor());
        totalConversionBox.setSelected(modDesc.isTotalConversion());
        utilityBox.setSelected(modDesc.isUtility());
        versionField.setText(modDesc.getVersion());
        descriptionArea.setText(modDesc.getDescription());
        gameVersionField.setText(modDesc.getGameVersion());
        replaceArea.setText(String.join(", ", modDesc.getReplace()));
        jarsArea.setText(String.join(", ", modDesc.getJars()));
        modPluginField.setText(modDesc.getModPlugin());
    }
    
    private ModDescriptor gatherFields() {
        ModDescriptor modDesc = new ModDescriptor();
        modDesc.setId(idField.getText());
        modDesc.setName(nameField.getText());
        modDesc.setAuthor(authorField.getText());
        modDesc.setTotalConversion(totalConversionBox.isSelected());
        modDesc.setUtility(utilityBox.isSelected());
        modDesc.setVersion(versionField.getText());
        modDesc.setDescription(descriptionArea.getText());
        modDesc.setGameVersion(gameVersionField.getText());
        modDesc.setReplace(splitAndTrim(replaceArea.getText()));
        modDesc.setJars(splitAndTrim(jarsArea.getText()));
        modDesc.setModPlugin(modPluginField.getText());
        return modDesc;
    }
    
    private List<String> splitAndTrim(String input) {
        List<String> list = new ArrayList<>();
        if (input != null && !input.trim().isEmpty()) {
            for (String s : input.split("\\s*,\\s*")) {
                if (!s.isEmpty()) {
                    list.add(s);
                }
            }
        }
        return list;
    }
}
