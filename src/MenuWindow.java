import java.awt.*;
import java.awt.event.*;

public class MenuWindow extends Frame implements ActionListener, ItemListener, MouseListener, MouseMotionListener, KeyListener {

    Point p1;
    boolean isSpacePressed;
    @Override
    public void mouseClicked(MouseEvent e) {

    }

    public void mousePressed(MouseEvent e) {
        p1 = e.getPoint();
        if (e.getModifiers() == MouseEvent.BUTTON3_MASK) {
            repaint();
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    public void mouseDragged(MouseEvent e) {
        Point p2 = e.getPoint();
        Graphics g = getGraphics();
        g.setColor(Color.red);
        g.drawLine(p1.x, p1.y, p2.x, p2.y);
        p1 = p2;
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            isSpacePressed = true;
            repaint();
        }
    }

    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            isSpacePressed = false;
        }
    }
    class MyEventWindowAdapter extends WindowAdapter {
        public void windowClosing(WindowEvent e) {
            dispose();
            System.exit(0);
        }
    }


    String message, itemMessage;

    MenuItem v2, v3,v4,v5,v6;


    public void actionPerformed(ActionEvent e) {
        if (message.length() > 70) message = "Kоманди меню:";
        String actionStr = (String) e.getActionCommand();
        if (actionStr.equals("Фон")) message += "->Фон";
        else if (actionStr.equals("Межа")) message += "->Межа";
        else if (actionStr.equals("Таблицю")) message += "->Таблицю";
        else if (actionStr.equals("Стовпець")) message += "->Стовпець";
        else if (actionStr.equals("Рядок")) message += "->Рядок";
        else if (actionStr.equals("Клітинку")) message += "->Клітинку";
        else if(actionStr.equals("Малювати"))
        {
            addMouseListener(this);
            addMouseMotionListener(this);
        }
        repaint();

    }


    public void itemStateChanged(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED) itemMessage = "Рядок меню: ТАК";
        else itemMessage = "Рядок меню: HI";
        repaint();
    }


    public void paint(Graphics g) {
        if (message != null && itemMessage != null) {
            g.drawString(message, 10, 300);
            g.drawString(itemMessage, 10, 320);
        }
    }

    void makeMenu() {
        MenuBar myMenuBar = new MenuBar();
        setMenuBar(myMenuBar);

        Menu frame = new Menu("Рамка");
        MenuItem f1;
        f1 = new MenuItem("Фон");
        f1.addActionListener(this);
        frame.add(f1);

        CheckboxMenuItem f2 = new CheckboxMenuItem("Клітинку");
        f2.addItemListener(this);
        frame.add(f2);

        myMenuBar.add(frame);

        Menu view = new Menu("Таблиця");
        Menu highlight= new Menu("Виділити");

        view.add(highlight);
        v2 = new MenuItem("Таблицю");
        v2.addActionListener(this);
        highlight.add(v2);
        v3 = new MenuItem("Стовпець");
        v3.addActionListener(this);
        highlight.add(v3);
        v4 = new MenuItem("Рядок");
        v4.addActionListener(this);
        highlight.add(v4);
        v5 = new MenuItem("Клітинку");
        v5.addActionListener(this);
        highlight.add(v5);
        v6 = new MenuItem("Малювати");
        v6.addActionListener(this);
        highlight.add(v6);
        myMenuBar.add(view);
    }

    public MenuWindow(String name) {
        super(name);
        setSize(500, 400);
        setVisible(true);
        addWindowListener(new MyEventWindowAdapter());
        message = "Kоманди меню:";
        makeMenu();
    }

    public static void main(String[] args) {
        new MenuWindow("Вікно з меню");
    }
}
