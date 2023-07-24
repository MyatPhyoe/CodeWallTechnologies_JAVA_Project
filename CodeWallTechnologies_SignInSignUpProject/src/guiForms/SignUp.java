package guiForms;

import dbConnection.DBConnection;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class SignUp extends JFrame implements ActionListener {
    JLabel  name, phone, email, password, picture, signin, user;
    JTextField namefield, phonefield, emailfield; JPasswordField passwordfield;
    JComboBox phonedata;
    JCheckBox passwordbox;
    JButton signupbutton;
    JFrame frame = new JFrame("Sign Up");
    String phonetype    = "09[0-9]{9}";
    String emailtype    = "[a-z 1-9]{6,20}@[a-z]{5,9}.com";
    String passwordtype = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&-+=()]).{8,20}$"; //(?=.*[0-9]) = 1 digit at least
    DBConnection dbconnect = new DBConnection();

    public static void main(String[] args) {
        new SignUp();
    }

    SignUp(){
        frame.getContentPane().setBackground(new Color(51, 204, 255)); //blue green
        ImageIcon image = new ImageIcon("JavaData.png");
        picture         = new JLabel(image);
        picture.setBounds(20,20,400,400);
        frame.add(picture);

        name            = new JLabel("Enter Name");
        namefield       = new JTextField();
        phone           = new JLabel("Enter Phone Number");
        phonefield      = new JTextField();
        email           = new JLabel("Enter Email");
        emailfield      = new JTextField();
        password        = new JLabel("Enter Password");
        passwordfield   = new JPasswordField();
        passwordbox     = new JCheckBox();
        passwordbox.setBackground(new Color(51, 204, 255));
        user            = new JLabel("Already have an account?");
        signin          = new JLabel("<HTML><U>Sign In</U></HTML>");

        signupbutton    = new JButton(" Sign Up");
        signupbutton.setBackground(new Color(204, 204, 204));
        signupbutton.addActionListener(this);

        //Insert PasswordField Show or Hide
        passwordbox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(passwordbox.isSelected()){passwordfield.setEchoChar((char)0);}//Show Password
                else{passwordfield.setEchoChar('*');} //Hide Password
            }
        });

        //Jlable Link
        signin.setForeground(Color.BLUE.darker());
        signin.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new SignIn();
                frame.setVisible(false);
            }
        });

        name.setBounds          (440, 30, 100, 20);
        namefield.setBounds     (440, 60, 220, 30);
        phone.setBounds         (440, 100, 140, 20);
        phonefield.setBounds    (440, 130, 220, 30);
        email.setBounds         (440, 170, 100, 20);
        emailfield.setBounds    (440, 200, 220, 30);
        password.setBounds      (440, 240, 200, 20);
        passwordbox.setBounds   (533, 240, 20, 20); //Show or Hide Password
        passwordfield.setBounds (440, 270, 220, 30);
        signupbutton.setBounds  (440, 325, 220, 30);
        user.setBounds          (440, 370, 170, 20);
        signin.setBounds        (600, 370, 50, 20);

        frame.add(name);
        frame.add(namefield);
        frame.add(phone);
        frame.add(phonefield);
        frame.add(email);
        frame.add(emailfield);
        frame.add(password);
        frame.add(passwordfield);
        frame.add(passwordbox);
        frame.add(signupbutton);
        frame.add(user);
        frame.add(signin);

        frame.setBounds(250,70,700, 480);
        frame.setLayout(null);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource()   == signupbutton) {
            String name     = namefield.getText().trim();
            String phone    = phonefield.getText().trim();
            String email    = emailfield.getText().trim();
            String password = passwordfield.getText().trim();
            if(!name.equals("") && !phone.equals("") && !email.equals("") && !password.equals("")) { //All data is inserted
                if(phone.matches(phonetype) && email.matches(emailtype) && password.matches(passwordtype)){
                    String[] str = new String[4];
                    str[0]       = name; //str[0]  = String.valueOf(4);
                    str[1]       = phone;
                    str[2]       = email;
                    str[3]       = password;
                    boolean br=dbconnect.IsDuplicate(str); //Check Duplicate
                    if(br){ //Duplicate
                        JOptionPane.showMessageDialog(this,"Email is already exited!","Error", JOptionPane.ERROR_MESSAGE);
                    }else{ //No Duplicate
                        boolean save=dbconnect.InsertData(str); //Data Insert
                        if(save){
                            JOptionPane.showMessageDialog(this,"Successfully save record!","Save Record",JOptionPane.INFORMATION_MESSAGE);
                            Reset(); //All Clear
                        }else{
                            JOptionPane.showMessageDialog(this,"Failed to save record!","Cannot Save",JOptionPane.INFORMATION_MESSAGE);
                        }
                    }//No Duplicate
                }else{ //incorrect type
                    JOptionPane.showMessageDialog(this,"Please enter the correct data!","Error", JOptionPane.ERROR_MESSAGE);
                }

            }else{//All data is not inserted
                if(name.equals("")) {
                    namefield.requestFocus();
                    JOptionPane.showMessageDialog(this, "Enter Name!");
                }else if(phone.equals("")){
                    phonefield.requestFocus();
                    JOptionPane.showMessageDialog(this, "Enter Phone Number!");
                }else if(email.equals("")){
                    emailfield.requestFocus();
                    JOptionPane.showMessageDialog(this, "Enter Email!");
                }else if(password.equals("")){
                    passwordfield.requestFocus();
                    JOptionPane.showMessageDialog(this, "Enter Password!");
                }
            }
        }
    }

    //All Data Clear
    public void Reset(){
        namefield.requestFocus();//Cusor move
        namefield.setText("");
        phonefield.setText("");
        emailfield.setText("");
        passwordfield.setText("");
    }
}
