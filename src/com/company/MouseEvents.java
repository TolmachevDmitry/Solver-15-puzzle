package com.company;

import java.awt.*;
import java.awt.event.*;
import java.applet.*;

public class MouseEvents extends Applet implements MouseListener, MouseMotionListener, MouseWheelListener {

    String msg = "";
    int mouseX = 0, mouseY = 0;

    public void init() {
        addMouseListener(this);
        addMouseMotionListener(this);
    }

    public void mouseWheelMoved(MouseWheelEvent me) {
        mouseX = 0;
        mouseY = 10;
        msg = "Wheel moved"; // Курсор отведен
        repaint();
    }

    // обработать событие от щелчка кнопкой мыши
    public void mouseClicked(MouseEvent me) {
        // сохранить координаты
        mouseX = 0;
        mouseY = 10;
        msg = "Mouse clicked."; // Произведен щелчок кнопкой мыши
        repaint();
    }
    // обработать событие наведения курсора мыши
    public void mouseEntered(MouseEvent me) {
        // сохранить координаты
        mouseX = 0;
        mouseY = 10;
        msg = "Mouse entered."; // Курсор наведен
        repaint();
    }

    // обработать событие отведения курсора мыши
    public void mouseExited(MouseEvent me) {
        // сохранить координаты
        mouseX = 0;
        mouseY = 10;
        msg = "Mouse exited."; // Курсор отведен
        repaint();
    }

    // обработать событие нажатия кнопки мыши
    public void mousePressed(MouseEvent me) {
        // сохранить координаты
        mouseX = me.getX();
        mouseY = me.getY();
        msg = "Down"; // Кнопка мыши зажата
        repaint();
    }

    // обработать событие отпускания кнопки мыши
    public void mouseReleased(MouseEvent me) {
        // сохранить координаты
        mouseX = me.getX();
        mouseY = me.getY();
        msg = "Up"; // Кнопка мыши отпущена
        repaint();
    }

    // обработать событие перетаскивания курсора мыши
    public void mouseDragged(MouseEvent me) {
        // сохранить координаты
        mouseX = me.getX();
        mouseY = me.getY();
        msg = "*";
//        showStatus("Dragging mouse at " + mouseX + ", " + mouseY);
        // Перетаскивание курсора мыши с точку с указанными координатами
        repaint();
    }

    // обработать событие перемещения мыши
    public void mouseMoved(MouseEvent me) {
        // показать состояние
        mouseX = me.getX();
        mouseY = me.getY();
//        showStatus("Moving mouse at " + me.getX() + ", " + me.getY());
        // Перемещение курсора мыши в точку с указанными координатами
        repaint();
    }

    // вывести сообщение из переменной msg на текущей позиции
    // с координатами X, Y в окне аплета
//    public void paint(Graphics g) {
//        g.drawString(msg, mouseX, mouseY);
//    }

}