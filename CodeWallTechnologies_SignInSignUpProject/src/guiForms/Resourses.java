package guiForms;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;

public class Resourses extends JFrame implements ActionListener {
    JLabel  title, java, python, php, picture, text, javatext, pythontext, phptext;
    JTextField emailfield; JPasswordField passwordfield;
    JButton signoutbutton;
    JFrame frame = new JFrame("Let the Learning Begin");
    JPanel javapanel, pythonpanel, phppanel;
    public static void main(String[] args) {
        new Resourses();
    }

    Resourses(){
        frame.setBackground(Color.blue);
        frame.getContentPane().setBackground(new Color(51, 204, 255)); //blue green

        javapanel =new JPanel();
        javapanel.setBackground(Color.white);
        javapanel.setBounds(20,120,790,390);
        javapanel.setLayout(null);

        pythonpanel =new JPanel();
        pythonpanel.setBackground(Color.white);
        pythonpanel.setBounds(20,120,790,390);
        pythonpanel.setLayout(null);

        phppanel =new JPanel();
        phppanel.setBackground(Color.white);
        phppanel.setBounds(20,120,790,390);
        phppanel.setLayout(null);

        title   = new JLabel("About Development");
        title.setFont(new Font("Arial", Font.BOLD, 25));

        java    = new JLabel("<html><u>JAVA Development</u></html>");
        java.setFont(new Font("Arial", Font.BOLD, 18));
        java.setForeground(Color.BLUE.darker());
        //Jlable JAVA Link
        java.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent ae) {
                    javapanel(); //Call Method
            }
        });

        python  = new JLabel("<html><u>Python Development</u></html>");
        python.setFont(new Font("Arial", Font.BOLD, 18));
        python.setForeground(Color.BLUE.darker());
        //Jlable Python Link
        python.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent ae) {
                pythonpanel(); //Call Method
            }
        });

        php     = new JLabel("<html><u>PHP Development</u></html>");
        php.setFont(new Font("Arial", Font.BOLD, 18));
        php.setForeground(Color.BLUE.darker());
        //Jlable PHP Link
        php.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent ae) {
                phppanel(); //Call Method
            }
        });

        signoutbutton = new JButton(" Sign Out");
        signoutbutton.setBackground(new Color(204, 204, 204));
        signoutbutton.addActionListener(this);

        javapanel(); //Java Panel is Default
        title.setBounds     (300,20, 300, 35);
        java.setBounds      (40,80, 180, 35);
        python.setBounds    (320,80, 200, 35);
        php.setBounds       (620,80, 180, 35);
        signoutbutton.setBounds  (605, 520, 200, 30);

        frame.add(title);
        frame.add(java);
        frame.add(python);
        frame.add(php);
        frame.add(signoutbutton);

        frame.add(javapanel); //Panel
        frame.add(pythonpanel);
        frame.add(phppanel);
        frame.setBounds(250,70,850, 600);
        frame.setLayout(null);
        frame.setVisible(true);
    }

    public void javapanel(){
        pythonpanel.hide();
        phppanel.hide();
        javapanel.show();
        javatext    = new JLabel(
           "<html><pre>Java is a high-level, class-based, \n" +
                "object-oriented programming language that is \n" +
                "designed to have as few implementation \n" +
                "dependencies as possible. It is a general \n" +
                "purpose programming language intended to let \n" +
                "programmers write once, run anywhere meaning \n" +
                "that compiledJava code can run on all platforms \n" +
                "that support Java without the needto recompile.</pre></html>");
        javatext.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 14));
        javatext.setBounds     (10,20, 380, 300);
        javapanel.add(javatext);

        ImageIcon image = new ImageIcon("java.png");
        picture         = new JLabel(image);
        picture.setBounds(420,5,350,380);
        javapanel.add(picture);
    }

    public void pythonpanel(){
        javapanel.hide();
        phppanel.hide();
        pythonpanel.show();
        pythontext   = new JLabel(
                "<html><pre>Python is a high-level, general-purpose, \n" +
                        "a multi-paradigm programming language. \n" +
                        "Object-oriented programming and structured \n" +
                        "programming are fully supported, and many of \n" +
                        "their features support functional programming \n" +
                        "and aspect-oriented programming. Python users \n" +
                        "are colloquially called pythonistas.</pre></html>");
        pythontext.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 14));
        pythontext.setBounds     (10,20, 380, 300);
        pythonpanel.add(pythontext);

        ImageIcon image = new ImageIcon("python.png");
        picture         = new JLabel(image);
        picture.setBounds(420,5,350,380);
        pythonpanel.add(picture);
    }

    public void phppanel(){
        javapanel.hide();
        pythonpanel.hide();
        phppanel.show();
        phptext   = new JLabel(
                "<html><pre>PHP is a general-purpose scripting language \n" +
                        "geared towards web development. It is a server \n" +
                        "scripting language, and a powerful tool for \n" +
                        "making dynamic and interactive Web pages and \n"+
                        "widely-used, free, and efficient alternative \n" +
                        "to competitors such as Microsoft's ASP.</pre></html>");
        phptext.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 14));
        phptext.setBounds     (10,20, 380, 300);
        phppanel.add(phptext);

        ImageIcon image = new ImageIcon("php.jpg");
        picture         = new JLabel(image);
        picture.setBounds(390,5,390,380);
        phppanel.add(picture);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource()   == signoutbutton){
            System.exit(0);
            frame.dispose();
        }
    }
}
