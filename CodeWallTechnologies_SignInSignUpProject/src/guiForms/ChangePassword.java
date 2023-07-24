package guiForms;

import dbConnection.DBConnection;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ChangePassword extends JFrame implements ActionListener  {
    JLabel  email, password, picture, confirmpassword, newuser, signin, user, signup;
    JTextField emailfield; JPasswordField passwordfield, confirmpasswordfield;
    JButton resetpasswordbutton;
    JCheckBox passwordbox, confirmpasswordbox;
    JFrame frame = new JFrame("Change Password");
    String passwordtype = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&-+=()]).{8,20}$"; //(?=.*[0-9]) = 1 digit at least
    DBConnection dbconnect = new DBConnection();

    public static void main(String[] args) {
        new ChangePassword();
    }

    ChangePassword(){
        frame.getContentPane().setBackground(new Color(51, 204, 255));
        ImageIcon image = new ImageIcon("JavaData.png");
        picture         = new JLabel(image);
        picture.setBounds(20,20,400,400);
        frame.add(picture);

        email               = new JLabel("Email");
        emailfield          = new JTextField();
        password            = new JLabel("Password");
        passwordfield       = new JPasswordField();
        passwordbox         = new JCheckBox();
        passwordbox.setBackground(new Color(51, 204, 255));
        confirmpassword     = new JLabel("Confirm Password");
        confirmpasswordfield= new JPasswordField();
        confirmpasswordbox  = new JCheckBox();
        confirmpasswordbox.setBackground(new Color(51, 204, 255));
        user                = new JLabel("Already have an account?");
        signin              = new JLabel("<HTML><U>Sign In</U></HTML>");
        newuser             = new JLabel("Doesn't have an account yet?");
        signup              = new JLabel("<HTML><U>Sign Up</U></HTML>");

        resetpasswordbutton = new JButton(" Reset Password");
        resetpasswordbutton.setBackground(new Color(204, 204, 204));
        resetpasswordbutton.addActionListener(this);

        //Insert PasswordField Show or Hide
        passwordbox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(passwordbox.isSelected()){passwordfield.setEchoChar((char)0);}//Show Password
                else{passwordfield.setEchoChar('*');} //Hide Password
            }
        });

        //Insert ConfirmPasswordField Show or Hide
        confirmpasswordbox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(confirmpasswordbox.isSelected()){confirmpasswordfield.setEchoChar((char)0);}//Show Password
                else{confirmpasswordfield.setEchoChar('*');} //Hide Password
            }
        });

        //Jlable guiForms.SignIn Link
        signin.setForeground(Color.BLUE.darker());
        signin.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new SignIn();
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

        email.setBounds             (440, 50, 100, 20);
        emailfield.setBounds        (440, 80, 220, 30);
        password.setBounds          (440, 130, 100, 20);
        passwordbox.setBounds       (500, 130, 20, 20); //Show or Hide Password
        passwordfield.setBounds     (440, 160, 220, 30);
        confirmpassword.setBounds   (440, 210, 140, 20);
        confirmpasswordbox.setBounds  (550, 210, 20, 20); //Show or Hide Password
        confirmpasswordfield.setBounds(440, 240, 220, 30);
        resetpasswordbutton.setBounds (440, 295, 220, 30);
        user.setBounds              (440, 340, 170, 20);
        signin.setBounds            (615, 340, 50, 20);
        newuser.setBounds           (440, 370, 170, 20);
        signup.setBounds            (615, 370, 50, 20);

        frame.add(email);
        frame.add(emailfield);
        frame.add(password);
        frame.add(passwordbox);
        frame.add(passwordfield);
        frame.add(confirmpassword);
        frame.add(confirmpasswordbox);
        frame.add(confirmpasswordfield);
        frame.add(resetpasswordbutton);
        frame.add(user);
        frame.add(signin);
        frame.add(newuser);
        frame.add(signup);

        frame.setBounds(250,70,700, 480);
        frame.setLayout(null);
        frame.setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource()   == resetpasswordbutton){
            String email            = emailfield.getText().trim();
            String password         = passwordfield.getText().trim();
            String confirmpassword  = confirmpasswordfield.getText().trim();
            if(!email.equals("") && !password.equals("") && !confirmpassword.equals("")){ //Email & password inserted
                boolean datacount= dbconnect.CheckLoginData(email,password);
                if(datacount){
                    if(confirmpassword.matches(passwordtype)) { //Check with passwordtype
                        boolean save=dbconnect.updatePassword(confirmpassword,email); //New Password Update
                        if(save){
                            JOptionPane.showMessageDialog(this,"Successfully change password!","Change Password",JOptionPane.INFORMATION_MESSAGE);
                            new SignIn();
                            frame.setVisible(false);
                        }else{
                            JOptionPane.showMessageDialog(this,"Failed to save record!","Cannot Save",JOptionPane.INFORMATION_MESSAGE);
                        }
                    }else{ //incorrect Password type
                        JOptionPane.showMessageDialog(this,"Please enter the correct confirm password!","Error", JOptionPane.ERROR_MESSAGE);
                    }
                }else{
                    JOptionPane.showMessageDialog(this, "Incorrect email or password!",
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            }else{ //Email or password or confirmpassword is not inserted
                if(email.equals("")){
                    emailfield.requestFocus();
                    JOptionPane.showMessageDialog(this,"Enter Email!");
                }else if(password.equals("")){
                    passwordfield.requestFocus();
                    JOptionPane.showMessageDialog(this,"Enter Password!");
                }else if(confirmpassword.equals("")) {
                    confirmpasswordfield.requestFocus();
                    JOptionPane.showMessageDialog(this, "Enter Confirm Password!");
                }
            }
        }
    }//ActionListener
}
