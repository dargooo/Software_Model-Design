import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.InetAddress;

public class AddUserController implements ActionListener {

    AddUserView aView;
    DataAdapter db;

    public AddUserController(AddUserView view, DataAdapter da) {
        aView = view;
        this.db = da;
        aView.btnCancel.addActionListener(this);
        aView.btnSave.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == aView.btnSave) {
            addUser();
        }

        if (e.getSource() == aView.btnCancel) {
            Application.adminView.show();
            Application.addUserView.setVisible(false);
            Application.addUserView.clear();
        }

    }


    private void addUser() {
        UserModel user = new UserModel();

        try {
            user.userId = db.maxUserID() + 1;
            user.userName = aView.txtUserName.getText();
            user.role = aView.txtRole.getText();
            user.password = aView.txtPassword1.getText();

            if (!user.password.equals(aView.txtPassword2.getText())) {
                JOptionPane.showMessageDialog(null, "Passwords don't match!");
            }
            else {
                db.addUser(user);
                JOptionPane.showMessageDialog(null, "User added successfully!");
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Invalid format for User");
            ex.printStackTrace();
        }
    }


}