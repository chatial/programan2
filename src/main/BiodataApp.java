/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

/**
 *
 * @author Perfecto
 */
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;

public class BiodataApp {
    private JFrame frame;
    private JTextField namaField;
    private JComboBox<String> jenisKelaminComboBox;
    private JTextField nomorHPField;
    private JTextArea alamatArea;
    private DefaultTableModel tableModel;
    private JTable biodataTable;

    private ArrayList<Biodata> biodataList;

    public BiodataApp() {
        frame = new JFrame("Aplikasi Biodata");
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLayout(new BorderLayout());

        frame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                if (confirmExit()) {
                    saveDataToFile();
                    System.exit(0);
                }
            }
        });

        JPanel inputPanel = new JPanel(new GridLayout(5, 2));
        JLabel namaLabel = new JLabel("Nama:");
        namaField = new JTextField();
        JLabel jenisKelaminLabel = new JLabel("Jenis Kelamin:");
        String[] jenisKelaminOptions = {"Pria", "Wanita"};
        jenisKelaminComboBox = new JComboBox<>(jenisKelaminOptions);
        JLabel nomorHPLabel = new JLabel("Nomor HP:");
        nomorHPField = new JTextField();
        JLabel alamatLabel = new JLabel("Alamat:");
        alamatArea = new JTextArea();
        JButton simpanButton = new JButton("Simpan");
        simpanButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                simpanBiodata();
            }
        });

        inputPanel.add(namaLabel);
        inputPanel.add(namaField);
        inputPanel.add(jenisKelaminLabel);
        inputPanel.add(jenisKelaminComboBox);
        inputPanel.add(nomorHPLabel);
        inputPanel.add(nomorHPField);
        inputPanel.add(alamatLabel);
        inputPanel.add(alamatArea);
        inputPanel.add(simpanButton);

        tableModel = new DefaultTableModel();
        tableModel.addColumn("Nama");
        tableModel.addColumn("Jenis Kelamin");
        tableModel.addColumn("Nomor HP");
        tableModel.addColumn("Alamat");

        biodataTable = new JTable(tableModel);

        frame.add(inputPanel, BorderLayout.NORTH);
        frame.add(new JScrollPane(biodataTable), BorderLayout.CENTER);

        biodataList = new ArrayList<>();
        loadDataFromFile();
        updateTable();

        frame.setVisible(true);
    }

    private void simpanBiodata() {
        String nama = namaField.getText();
        String jenisKelamin = (String) jenisKelaminComboBox.getSelectedItem();
        String nomorHP = nomorHPField.getText();
        String alamat = alamatArea.getText();

        if (nama.isEmpty() || nomorHP.isEmpty() || alamat.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Harap isi semua data!", "Peringatan", JOptionPane.WARNING_MESSAGE);
        } else {
            int dialogResult = JOptionPane.showConfirmDialog(frame, "Simpan biodata?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (dialogResult == JOptionPane.YES_OPTION) {
                Biodata biodata = new Biodata(nama, jenisKelamin, nomorHP, alamat);
                biodataList.add(biodata);
                updateTable();

                namaField.setText("");
                jenisKelaminComboBox.setSelectedIndex(0);
                nomorHPField.setText("");
                alamatArea.setText("");
            }
        }
    }

    private void updateTable() {
        tableModel.setRowCount(0);
        for (Biodata biodata : biodataList) {
            tableModel.addRow(new Object[]{biodata.getNama(), biodata.getJenisKelamin(), biodata.getNomorHP(), biodata.getAlamat()});
        }
    }

    private void loadDataFromFile() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("biodata.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 4) {
                    Biodata biodata = new Biodata(data[0], data[1], data[2], data[3]);
                    biodataList.add(biodata);
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveDataToFile() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("biodata.txt"));
            for (Biodata biodata : biodataList) {
                writer.write(biodata.getNama() + "," + biodata.getJenisKelamin() + "," + biodata.getNomorHP() + "," + biodata.getAlamat() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean confirmExit() {
        int dialogResult = JOptionPane.showConfirmDialog(frame, "Apakah Anda yakin ingin keluar?", "Konfirmasi Keluar", JOptionPane.YES_NO_OPTION);
        return dialogResult == JOptionPane.YES_OPTION;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new BiodataApp();
        });
    }

    private class Biodata {
        private String nama;
        private String jenisKelamin;
        private String nomorHP;
        private String alamat;

        public Biodata(String nama, String jenisKelamin, String nomorHP, String alamat) {
            this.nama = nama;
            this.jenisKelamin = jenisKelamin;
            this.nomorHP = nomorHP;
            this.alamat = alamat;
        }

        public String getNama() {
            return nama;
        }

        public String getJenisKelamin() {
            return jenisKelamin;
        }

        public String getNomorHP() {
            return nomorHP;
        }

        public String getAlamat() {
            return alamat;
        }
    }
}
