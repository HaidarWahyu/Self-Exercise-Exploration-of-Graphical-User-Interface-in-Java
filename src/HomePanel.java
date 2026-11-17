import  javax.swing.*;
import  java.awt.*;

public class HomePanel extends JPanel {
    private  JLabel lblTotal;

    public HomePanel(){
        setLayout(new BorderLayout());

        JLabel lblinfo = new JLabel("Total Mahasiswa", SwingConstants.CENTER);
        lblTotal = new JLabel("0", SwingConstants.CENTER);
        lblTotal.setFont(new Font("SansSerif", Font.BOLD, 40));

        add(lblinfo, BorderLayout.NORTH);
        add(lblTotal, BorderLayout.CENTER);
    }

    public void setTotal(int total){
        lblTotal.setText(String.valueOf(total));
    }
    
}
