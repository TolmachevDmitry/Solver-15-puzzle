package com.company;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import com.util.ArrayUtils;
import com.util.JTableUtils;
import ru.vsu.cs.util.SwingUtils;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.ArrayList;

import static com.util.ArrayUtils.copy;


public class ShowSolution extends JFrame {
    private JPanel panelMain;
    private JTable tableInput;
    private JButton buttonRandomInput;
    private JButton buttonReverseRows;

    private int screenY = 0;
    private int tY = 0;

    private int n;
    private int m;

    private int r = 72;

    private ArrayList<int[][]> solution = new ArrayList<>();

    public ShowSolution(ArrayList<int[][]> solution, int n, int m) {
        this.setTitle("Решение");
        this.setContentPane(panelMain);
//        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setSize(1200, 700);
        this.setVisible(true);

        this.solution = solution;
        this.n = n;
        this.m = m;

        panelMain.addMouseListener(new MouseEvents() {
            @Override
            public void mousePressed(MouseEvent e) {
                tY = e.getY();
            }
            @Override
            public void mouseReleased(MouseEvent e) {}
        });

        // Передвигаем наши решения, чтобы все просмотреть
        panelMain.addMouseMotionListener(new MouseEvents() {
            public void mouseDragged(MouseEvent e) {
                changeCoordinate(e.getY());
            }
        });

        panelMain.addMouseWheelListener(new MouseEvents() {
            public void mouseWheelMoved(MouseWheelEvent e) {
                int direction = -(int) e.getPreciseWheelRotation();
                changeCoordinate(tY + direction * 70);
            }
        });
    }

    private void callRepaint() {
        repaint();
    }

    private void changeCoordinate(int getY) {
        int delY = getY - tY;

        if (screenY + delY <= 0) {
            screenY += delY;

            callRepaint();
        }
        tY = getY;
    }

    private void printSolution(Graphics gr) {
        int indR = m * 10 + 2;    // indent Right
        int indD = 35;            // indent Down

        int xStart = 50;

        // Угловые координаты для каждой "коробки"
        int x = xStart;
        int y = 100;

        Color mainColor = new Color(0x85C8D7);
        // Обходим сами состояния
        for (int numState = 0; numState < solution.size(); numState++) {
            int[][] directlyState = solution.get(numState);

            String number = (numState) + (numState == 0 ? (" - Старт") :
                    ((numState == solution.size() - 1) ? (" - Финиш") : ("")));

            gr.setColor(Color.BLACK);
            gr.drawString(number, x, y - 10 + screenY);   // Отображаем порядковые номера состояний
            for (int r_i = y, i = 0; i < n; r_i += r, i++) {
                for (int r_j = x, j = 0; j < m; r_j += r, j++) {
                    String value = directlyState[i][j] + "";

                    int c_r = r / 2;   // Чтобы можно было отобразить значение в середине "квадратика"

                    // Оформляем ячейки
                    gr.setColor((!value.equals("-1")) ? (mainColor) : (new Color(0xB0ECFF)));
                    gr.fillRect(r_j, r_i + screenY, r, r);

                    gr.setColor(Color.BLACK);
                    gr.drawRect(r_j, r_i + screenY, r, r);

                    if (!value.equals("-1")) {
                        gr.drawString(value, r_j + c_r, r_i + c_r + screenY);  // Отображаем значение "квадратика"
                    }
                }
            }

            x += m * r + indR;
            if (x + m * r >= 1338) {
                x = xStart;
                y += n * r + indD;
            }
        }
    }

    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D gr = (Graphics2D) g;

        printSolution(gr);
//        gr.fillRect(390, 100 + screenY, 288, 288);
//        gr.fillRect(720, 100 + screenY, 288, 288);
//        gr.fillRect(1050, 100 + screenY, 288, 288);
//
//        gr.fillRect(390, 400 + screenY, 216, 216);
//        gr.fillRect(638, 400 + screenY, 216, 216);
//        gr.fillRect(886, 400 + screenY, 216, 216);
//        gr.fillRect(1134, 400 + screenY, 216, 216);
//
//        gr.fillRect(390, 600 + screenY, 144, 144);
//        gr.fillRect(556, 600 + screenY, 144, 144);
//        gr.fillRect(722, 600 + screenY, 144, 144);
//        gr.fillRect(888, 600 + screenY, 144, 144);
//        gr.fillRect(1054, 600 + screenY, 144, 144);
//        gr.fillRect(1220, 600 + screenY, 144, 144);
    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        panelMain = new JPanel();
        panelMain.setLayout(new GridLayoutManager(5, 2, new Insets(10, 10, 10, 10), 10, 10));
        final JScrollPane scrollPane1 = new JScrollPane();
        scrollPane1.setVerticalScrollBarPolicy(21);
        panelMain.add(scrollPane1, new GridConstraints(0, 0, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(-1, 200), null, 0, false));
        tableInput = new JTable();
        scrollPane1.setViewportView(tableInput);
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayoutManager(1, 4, new Insets(0, 0, 0, 0), -1, -1));
        panelMain.add(panel1, new GridConstraints(1, 0, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        buttonRandomInput = new JButton();
        buttonRandomInput.setText("Заполнить случайными числами");
        panel1.add(buttonRandomInput, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer1 = new Spacer();
        panel1.add(spacer1, new GridConstraints(0, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, new Dimension(100, -1), null, 0, false));
        final JScrollPane scrollPane2 = new JScrollPane();
        scrollPane2.setVerticalScrollBarPolicy(21);
        panelMain.add(scrollPane2, new GridConstraints(3, 0, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(-1, 200), null, 0, false));
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayoutManager(1, 3, new Insets(0, 0, 0, 0), -1, -1));
        panelMain.add(panel2, new GridConstraints(2, 0, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        buttonReverseRows = new JButton();
        buttonReverseRows.setText("Выполнить сортировку");
        panel2.add(buttonReverseRows, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer2 = new Spacer();
        panel2.add(spacer2, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final JPanel panel3 = new JPanel();
        panel3.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        panelMain.add(panel3, new GridConstraints(4, 0, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final Spacer spacer3 = new Spacer();
        panel3.add(spacer3, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return panelMain;
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}

