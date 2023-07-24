package guiForms;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import dbConnection.*;

public class SignIn extends JFrame implements ActionListener {
    JLabel  email, password, picture, changepassword, newuser, signup;
    JTextField emailfield; JPasswordField passwordfield;
    JCheckBox passwordbox;
    JButton signinbutton;
    JFrame frame = new JFrame("Sign In");
    DBConnection dbconnect = new DBConnection();

    public static void main(String[] args) {
        new SignIn();
    }

    SignIn(){ //Constructor
        frame.getContentPane().setBackground(new Color(51, 204, 255));
        ImageIcon image = new ImageIcon("JavaData.png");
        picture         = new JLabel(image);
        picture.setBounds(20,20,400,400);
        frame.add(picture);

        email           = new JLabel("Email");
        emailfield      = new JTextField();
        password        = new JLabel("Password");
        passwordfield   = new JPasswordField(); //Limit only 8 password
        passwordbox     = new JCheckBox();
        passwordbox.setBackground(new Color(51, 204, 255));
        changepassword  = new JLabel("<HTML><U>Change Password?</U></HTML>");
        newuser         = new JLabel("Doesn't have an account yet?");
        signup          = new JLabel("<HTML><U>Sign Up</U></HTML>");

        signinbutton    = new JButton(" Sign In");
        signinbutton.setBackground(new Color(204, 204, 204));
        signinbutton.addActionListener(this);

        //Insert PasswordField Show or Hide
        passwordbox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(passwordbox.isSelected()){passwordfield.setEchoChar((char)0);}//Show Password
                else{passwordfield.setEchoChar('*');} //Hide Password
            }
        });

        //Jlable ForgotPassword Link
        changepassword.setForeground(Color.BLUE.darker());
        changepassword.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new ChangePassword();
                frame.setVisible(false);
            }
        });

        //Jlable guiForms.SignUp Link
        signup.setForeground(Color.BLUE.darker());
        signup.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new SignUp();
                frame.setVisible(false);
            }
        });

        email.setBounds         (440, 60, 100, 20);
        emailfield.setBounds    (440, 90, 220, 30);
        password.setBounds      (440, 140, 100, 20);
        passwordbox.setBounds   (500, 140, 20, 20); //Show or Hide Password
        passwordfield.setBounds (440, 170, 220, 30);
        changepassword.setBounds(440, 215, 200, 20);
        signinbutton.setBounds  (440, 250, 220, 30);
        newuser.setBounds       (440, 295, 170, 20);
        signup.setBounds        (615, 295, 50, 20);

        frame.add(email);
        frame.add(emailfield);
        frame.add(password);
        frame.add(passwordbox);
        frame.add(passwordfield);
        frame.add(changepassword);
        frame.add(signinbutton);
        frame.add(newuser);
        frame.add(signup);

        frame.setBounds(250,70,700, 480);
        frame.setLayout(null);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource()   == signinbutton){
            String email    = emailfield.getText().trim();
            String password = passwordfield.getText().trim();
            if(!email.equals("") && !password.equals("")){ //Email & password inserted
               boolean datacount= dbconnect.CheckLoginData(email,password);
               if(datacount){
                   new Resourses();
                   frame.setVisible(false);
               }else{
                   JOptionPane.showMessageDialog(this, "Incorrect email or password!",
                           "Error", JOptionPane.ERROR_MESSAGE);
               }
            }else{ //Email or password is not inserted
                if(email.equals("")){
                    emailfield.requestFocus();
                    JOptionPane.showMessageDialog(this,"Enter Email!");
                }else if(password.equals("")){
                    passwordfield.requestFocus();
                    JOptionPane.showMessageDialog(this,"Enter Password!");
                }
            }
        }
    }
}
