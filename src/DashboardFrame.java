import javax.swing.*;
import java.awt.*;

public class DashboardFrame {
    private  JFrame frame;
    private JLabel lblJudul;
    private JTabbedPane tabs;
    private HomePanel homePanel;
    private DataPanel dataPanel;
    
    public  DashboardFrame(){
        frame = new JFrame("Dashboard Mahasiswa");
        lblJudul = new JLabel("Dasboard Mahasiswa", SwingConstants.CENTER);
        lblJudul.setFont(new Font("SansSerif", Font.BOLD, 20));
        
        tabs = new  JTabbedPane();
        homePanel = new HomePanel();
        dataPanel = new DataPanel(homePanel);
    }

    public void launchFrame(){
        tabs.addTab("Home", homePanel);
        tabs.addTab("Data", dataPanel);

        frame.setLayout(new BorderLayout());
        frame.add(lblJudul, BorderLayout.NORTH);
        frame.add(tabs, BorderLayout.CENTER);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(450, 350);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    
}
