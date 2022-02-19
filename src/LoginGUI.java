/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author McCowan
 * secure login graphic user interface
 * allows users to create an account with a username, valid email(contains @ and .) and valid password (greater than 4 digits, not common password)
 * user login information is stored onto text file in the format: (username);(email);(encrypted password)
 * users can login if matching username and password are inputted
 * users can reset their password using matching username and email 
 */
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;
import static jdk.nashorn.internal.objects.NativeString.substring;
import java.io.File; //file input and library output
import java.io.FileNotFoundException; //handles file not found
import java.io.IOException; // handles file input/output exceptions
import java.io.PrintWriter; // methods to write textfile
import java.util.NoSuchElementException;
import java.util.Scanner; // scanner class to read from text file
import java.util.StringTokenizer;
import java.util.ArrayList;

public class LoginGUI extends javax.swing.JFrame {
    //define array lists for all user data
    ArrayList<String> usernames = new ArrayList<String>();
    ArrayList<String> emails = new ArrayList<String>();
    ArrayList<String> passwords = new ArrayList<String>();
    //contruct files for badpasswords and userdata
    File badPasswords = new File("badPasswords.txt");
    File userData = new File("userData.txt");
    /**
     * Creates new form LoginGUI
     * and reads data from userData when program is run
     */
    public LoginGUI() {
        initComponents();
        //read data from file when program is run
        readFile(userData);
    }
    /**
     * 
     * @param database take in file from which data will be taken from 
     * read lines from userdata file
     * for each line add username, email and encrypted password into corresponding array
     */
    public void readFile(File database){
        int index = 0;
        try {
            Scanner scanData = new Scanner(database);
            try {
                while (true) {
                    //tokenize current line from userdata file
                    String thisLine = scanData.nextLine();
                    StringTokenizer tokened = new StringTokenizer(thisLine, ";", false);
                    //add data from userdata file to corresponding arrays
                    usernames.add(tokened.nextToken());
                    emails.add(tokened.nextToken());
                    passwords.add(tokened.nextToken());
                    index++;
                }
            } catch (NoSuchElementException e) {
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error: " + e);
        }
    }
    /**
     * 
     * @param database take in file from which data will be rewritten on
     * going through every element in the arrays, write the (username);(email);(encrypted password) onto the file
     */
    public void writeFile(File database){
        try {
            PrintWriter output = new PrintWriter(database);
            for(int i = 0; i<usernames.size();i++){
                //write the data onto the userdata file
                output.println(usernames.get(i)+";"+emails.get(i)+";"+passwords.get(i));
            }
            //close the file so it can be used in other programs, etc.
            output.close();
        } catch (IOException ex) {
            System.out.printf("ERROR: %s\n", ex);
        }
    }
    /**
     * 
     * @param database take in file from which bad passwords will be compared with
     * @param password take in password to compare
     * @return return boolean whether password is good or not
     * compare password with every line in file compared
     * if password matches, return that good password = false
     * if password does not match any bad passwords, return good password = true
     */
    public boolean checkPassword(File database, String password){
        boolean goodPassword = true;
        try {
            Scanner scanPasswords = new Scanner(database);
            try {
                while (true) {
                    //compare every line in file to password
                    String compare = scanPasswords.nextLine();
                    if (compare.equalsIgnoreCase(password)) {
                        goodPassword = false;
                    }
                }
            } catch (NoSuchElementException e) {
            }
        }
        catch (FileNotFoundException e) {    
            System.out.println("Error: "+ e);
        }
        return goodPassword;
    }
    /**
     * 
     * @param inputPassword take in password to digest
     * @return return digested password
     * apply MD5 encryption onto inputted password
     */
    public String digestPassword(String inputPassword){
        String digestedPassword ="";
        try {
            //apply MD5 encryption onto password
            MessageDigest encrypt = MessageDigest.getInstance("MD5");
            encrypt.update(inputPassword.getBytes());
            byte byteData[] = encrypt.digest();

            for (int i = 0; i < byteData.length; i++) {
                digestedPassword = digestedPassword + (Integer.toHexString((byteData[i] & 0xFF) | 0x100).substring(1, 3));
            }
        }
        catch (NoSuchAlgorithmException ex) {
        }
        return digestedPassword;
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        title = new javax.swing.JLabel();
        loginLabel = new javax.swing.JLabel();
        loginUsername = new javax.swing.JTextField();
        passwordLabel = new javax.swing.JLabel();
        loginPassword = new javax.swing.JTextField();
        loginButton = new javax.swing.JButton();
        registerMessageLabel = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        newUserAccountLabel = new javax.swing.JLabel();
        enterUsernameLabel = new javax.swing.JLabel();
        enterEmailLabel = new javax.swing.JLabel();
        enterPasswordLabel = new javax.swing.JLabel();
        regUsername = new javax.swing.JTextField();
        regPassword = new javax.swing.JTextField();
        regEmail = new javax.swing.JTextField();
        registerButton = new javax.swing.JButton();
        loginMessageLabel = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        newUserAccountLabel1 = new javax.swing.JLabel();
        enterUsernameLabel1 = new javax.swing.JLabel();
        enterEmailLabel1 = new javax.swing.JLabel();
        enterPasswordLabel1 = new javax.swing.JLabel();
        resetMessageLabel = new javax.swing.JLabel();
        resetUsername = new javax.swing.JTextField();
        resetEmail = new javax.swing.JTextField();
        resetPassword = new javax.swing.JTextField();
        resetButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        title.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        title.setText("ANOJAN Corporation - Hitting That NojNoj Since 2003");

        loginLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        loginLabel.setText("Login Username:");

        loginUsername.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        loginUsername.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginUsernameActionPerformed(evt);
            }
        });

        passwordLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        passwordLabel.setText("Password:");

        loginPassword.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        loginPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginPasswordActionPerformed(evt);
            }
        });

        loginButton.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        loginButton.setText("Login");
        loginButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginButtonActionPerformed(evt);
            }
        });

        registerMessageLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        registerMessageLabel.setText("Please fill in all fields");

        newUserAccountLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        newUserAccountLabel.setText("New User Account");

        enterUsernameLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        enterUsernameLabel.setText("Enter your Username:");

        enterEmailLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        enterEmailLabel.setText("Enter your Email Ad. :");

        enterPasswordLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        enterPasswordLabel.setText("Enter your Password:");

        regUsername.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        regUsername.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                regUsernameActionPerformed(evt);
            }
        });

        regPassword.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        regPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                regPasswordActionPerformed(evt);
            }
        });

        regEmail.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        regEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                regEmailActionPerformed(evt);
            }
        });

        registerButton.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        registerButton.setText("Register");
        registerButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registerButtonActionPerformed(evt);
            }
        });

        loginMessageLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        loginMessageLabel.setText("Please fill in all fields");

        newUserAccountLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        newUserAccountLabel1.setText("Forgot Password?");

        enterUsernameLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        enterUsernameLabel1.setText("Enter your Username:");

        enterEmailLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        enterEmailLabel1.setText("Enter your Email Ad. :");

        enterPasswordLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        enterPasswordLabel1.setText("Enter your Password:");

        resetMessageLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        resetMessageLabel.setText("Please fill in all fields");

        resetUsername.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        resetUsername.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetUsernameActionPerformed(evt);
            }
        });

        resetEmail.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        resetEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetEmailActionPerformed(evt);
            }
        });

        resetPassword.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        resetPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetPasswordActionPerformed(evt);
            }
        });

        resetButton.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        resetButton.setText("Reset Password");
        resetButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(title, javax.swing.GroupLayout.PREFERRED_SIZE, 663, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(loginButton)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(loginLabel)
                                    .addGap(18, 18, 18)
                                    .addComponent(loginUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(65, 65, 65)
                                    .addComponent(passwordLabel)
                                    .addGap(27, 27, 27)
                                    .addComponent(loginPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(enterUsernameLabel)
                                .addGap(18, 18, 18)
                                .addComponent(regUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(enterEmailLabel)
                                .addGap(18, 18, 18)
                                .addComponent(regEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 456, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(enterPasswordLabel)
                                .addGap(18, 18, 18)
                                .addComponent(regPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addComponent(registerMessageLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 449, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(31, 31, 31)
                                .addComponent(registerButton, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(enterUsernameLabel1)
                                .addGap(18, 18, 18)
                                .addComponent(resetUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(enterPasswordLabel1)
                                    .addComponent(enterEmailLabel1))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(resetEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 456, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(resetPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(81, 81, 81)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 491, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(240, 240, 240)
                        .addComponent(newUserAccountLabel))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(89, 89, 89)
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 491, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(237, 237, 237)
                        .addComponent(newUserAccountLabel1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addComponent(resetMessageLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 410, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 36, 36)
                        .addComponent(resetButton)))
                .addContainerGap(75, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(49, 49, 49)
                    .addComponent(loginMessageLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGap(226, 226, 226)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(title)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(loginLabel)
                    .addComponent(loginUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(passwordLabel)
                    .addComponent(loginPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(loginButton)
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(newUserAccountLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(enterUsernameLabel)
                    .addComponent(regUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(enterEmailLabel)
                    .addComponent(regEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(enterPasswordLabel)
                    .addComponent(regPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(registerButton)
                    .addComponent(registerMessageLabel))
                .addGap(18, 18, 18)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(newUserAccountLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(enterUsernameLabel1)
                    .addComponent(resetUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(enterEmailLabel1)
                    .addComponent(resetEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(enterPasswordLabel1)
                    .addComponent(resetPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(resetMessageLabel)
                    .addComponent(resetButton))
                .addContainerGap())
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(114, 114, 114)
                    .addComponent(loginMessageLabel)
                    .addContainerGap(391, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void loginUsernameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginUsernameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_loginUsernameActionPerformed

    private void loginPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginPasswordActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_loginPasswordActionPerformed

    private void loginButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginButtonActionPerformed
        boolean match = false;
        String password = loginPassword.getText();

        
        
        //compare login information with all sets of login information in arrays
        for(int i = 0; i<usernames.size();i++){
            //if login information matches, tell user they have logged in
            if(loginUsername.getText().equals(usernames.get(i)) && digestPassword(password).equals(passwords.get(i))){
                loginMessageLabel.setText("You have successfully logged in!");
                match = true;
            }
        }

        //if login information fields are empty, tell user to fill in fields
        if (loginUsername.getText().equals("") || password.equals("")) {
            loginMessageLabel.setText("Please fill in all fields");
        }
        //if there is no match, tell user there is login information mismatch
        else if(match == false){
            loginMessageLabel.setText("Username and password mismatch");
        }
        //if there is a successful login, clear fields
        else if (match == true){
            //clear fields
            loginUsername.setText("");
            loginPassword.setText("");
        }
    }//GEN-LAST:event_loginButtonActionPerformed

    private void regUsernameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_regUsernameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_regUsernameActionPerformed

    private void regPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_regPasswordActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_regPasswordActionPerformed

    private void regEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_regEmailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_regEmailActionPerformed

    private void registerButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registerButtonActionPerformed

        String password = regPassword.getText();
        //define variable for if all conditons have been met for proper registration
        boolean goodRegistration = true;
        
        //if text fields are empty, tell user to fill in all fields
        if (regUsername.getText().equals("") || regPassword.getText().equals("") || regEmail.getText().equals("")) {
            registerMessageLabel.setText("Please fill in all fields");
            goodRegistration = false;
        }
        //if password is bad, tell user to enter a stronger password
        else if(checkPassword(badPasswords, password) == false){
            goodRegistration = false;
            registerMessageLabel.setText("Please enter a stronger password");
        }
        //if new password length is less than 4 digits tell user to put longer password
        else if(password.length()<4){
            registerMessageLabel.setText("Please enter a longer password");
            goodRegistration = false;
        }
        //if email does not contain @ and . tell user to input valid email
        else if(regEmail.getText().indexOf("@")==-1 || regEmail.getText().indexOf(".")==-1){
            registerMessageLabel.setText("Please enter a valid email");
            goodRegistration = false;
        }
        

        //if all conditions for registration have been met, add registration data to arrays and write data to file
        if(goodRegistration){
            //tell user registration is successful
            registerMessageLabel.setText("You have successfully registered!");
            //add registration data to arrays
            usernames.add(regUsername.getText());
            emails.add(regEmail.getText());
            passwords.add(digestPassword(password));
            //write data to file
            writeFile(userData);
            //clear fields
            regEmail.setText("");
            regUsername.setText("");
            regPassword.setText("");
        }
        
    }//GEN-LAST:event_registerButtonActionPerformed

    private void resetUsernameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetUsernameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_resetUsernameActionPerformed

    private void resetEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetEmailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_resetEmailActionPerformed

    private void resetPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetPasswordActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_resetPasswordActionPerformed

    private void resetButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetButtonActionPerformed
        
        String newPassword = resetPassword.getText();
        boolean match = false;
        boolean goodReset = false;
        
        //check through all elements in the arrays
        for(int i = 0; i<usernames.size();i++){
            //if usernames and emails match at same index of arrays then it is a match
            if(resetUsername.getText().equals(usernames.get(i)) && resetEmail.getText().equals(emails.get(i))){
                match = true;
                //if the new password is shorter than 4 digits, tell user to input longer password
                if(newPassword.length()<4){
                    resetMessageLabel.setText("Please enter a longer password");
                    goodReset = false;
                }
                //if password is good, change password to new password and write data to file
                else if(checkPassword(badPasswords,newPassword)){
                    resetMessageLabel.setText("You have successfully changed your password!");
                    passwords.set(i, digestPassword(newPassword));
                    writeFile(userData);
                    goodReset= true;
                    
                }
                //if password is bad, tell user to input stronger password
                else if(checkPassword(badPasswords, newPassword)==false){
                    resetMessageLabel.setText("Please enter a stronger password");
                    goodReset = false;
                }
                
            }
        }
        //if fields are empty, tell user to fill in fields
        if (resetUsername.getText().equals("") || resetPassword.getText().equals("") || resetEmail.getText().equals("")) {
            resetMessageLabel.setText("Please fill in all fields");
        }
        //if username and email do not match, tell user there is mismatch
        else if(match == false){
            resetMessageLabel.setText("Username and email mismatch");
        }
        //if proper conditions have been met to reset password, clear fields
        else if(match == true && goodReset == true){
            resetEmail.setText("");
            resetUsername.setText("");
            resetPassword.setText("");
        }
        
    }//GEN-LAST:event_resetButtonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(LoginGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LoginGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LoginGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LoginGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LoginGUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel enterEmailLabel;
    private javax.swing.JLabel enterEmailLabel1;
    private javax.swing.JLabel enterPasswordLabel;
    private javax.swing.JLabel enterPasswordLabel1;
    private javax.swing.JLabel enterUsernameLabel;
    private javax.swing.JLabel enterUsernameLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JButton loginButton;
    private javax.swing.JLabel loginLabel;
    private javax.swing.JLabel loginMessageLabel;
    private javax.swing.JTextField loginPassword;
    private javax.swing.JTextField loginUsername;
    private javax.swing.JLabel newUserAccountLabel;
    private javax.swing.JLabel newUserAccountLabel1;
    private javax.swing.JLabel passwordLabel;
    private javax.swing.JTextField regEmail;
    private javax.swing.JTextField regPassword;
    private javax.swing.JTextField regUsername;
    private javax.swing.JButton registerButton;
    private javax.swing.JLabel registerMessageLabel;
    private javax.swing.JButton resetButton;
    private javax.swing.JTextField resetEmail;
    private javax.swing.JLabel resetMessageLabel;
    private javax.swing.JTextField resetPassword;
    private javax.swing.JTextField resetUsername;
    private javax.swing.JLabel title;
    // End of variables declaration//GEN-END:variables
}
