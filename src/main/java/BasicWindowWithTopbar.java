import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class BasicWindowWithTopbar extends JFrame {
    public BasicWindowWithTopbar() {
        super("Basic Window");
        initUI();
    }

    private void initUI() {
        // Top toolbar
        JToolBar topbar = new JToolBar();
        topbar.setFloatable(false);
        topbar.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
        topbar.setBorder(new EmptyBorder(2, 2, 2, 2));

        // Dropdowns instead of plain buttons
        topbar.add(createDropdownButton("File",   new String[]{"New", "Open", "Save"}));
        topbar.add(createDropdownButton("Edit",   new String[]{"Cut", "Copy", "Paste"}));
        topbar.add(createDropdownButton("Help",   new String[]{"About", "Documentation"}));

        add(topbar, BorderLayout.NORTH);

        // Window settings
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private JButton createDropdownButton(String title, String[] options) {
        JButton btn = new JButton(title);
        styleButton(btn);
        JPopupMenu menu = new JPopupMenu();
        for (String opt : options) {
            JMenuItem item = new JMenuItem(opt);
            item.addActionListener(e -> System.out.println(opt + " selected"));
            menu.add(item);
        }
        btn.addActionListener(e -> menu.show(btn, 0, btn.getHeight()));
        return btn;
    }

    private void styleButton(JButton btn) {
        btn.setFocusPainted(false);
        btn.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        btn.setMargin(new Insets(5, 10, 5, 10));
        btn.setOpaque(true);
        btn.setBackground(UIManager.getColor("Button.background"));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new BasicWindowWithTopbar().setVisible(true));
    }
}
