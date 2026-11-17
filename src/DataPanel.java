import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class DataPanel extends JPanel{
    private JTextField txtNama;
    private JTextField txtNim;
    private JButton btnTambah;
    private JTextArea txtOutput;

    private ArrayList<Mahasiswa> daftar;
    private HomePanel homePanel;

    public DataPanel(HomePanel homePanel){
        this.homePanel = homePanel;
        daftar = new ArrayList<>();

        txtNama = new JTextField();
        txtNim = new JTextField();
        btnTambah = new JButton("Tambah");
        txtOutput = new JTextArea();
        txtOutput.setEditable(false);

        JPanel form = new  JPanel(new GridLayout(3,2,5,5));
        form.add(new JLabel("Nama"));
        form.add(txtNama);
        form.add(new JLabel("NIM"));
        form.add(txtNim);
        form.add(new JLabel(""));
        form.add(btnTambah);

        setLayout(new BorderLayout(5,5));
        add(form, BorderLayout.NORTH);
        add(new JScrollPane(txtOutput), BorderLayout.CENTER);

        TambahHandler handler = new TambahHandler();
        btnTambah.addActionListener(handler);
        txtNim.addActionListener(handler);

    }

    private void tambahData(){
        String nama = txtNama.getText();
        String nim = txtNim.getText();

        if (nama.isEmpty() || nim.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nama dan NIM harus diisi!!");
            return;
        }

        Mahasiswa m = new  Mahasiswa(nama, nim);
        daftar.add(m);

        txtOutput.append(m+ "\n");
        homePanel.setTotal(daftar.size());

        txtNama.setText("");
        txtNim.setText("");
    }


    private class TambahHandler implements ActionListener{
        public void  actionPerformed (ActionEvent e){
            tambahData();
        }
    }

    
}
