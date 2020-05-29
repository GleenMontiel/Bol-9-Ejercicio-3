package app;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Principal extends JFrame implements ActionListener, ItemListener {
    JComboBox<String> cbA;
    JComboBox<String> cbB;
    JTextField txt1;
    JTextField txt2;
    JButton btnAnadir;
    JButton btnQuitar;
    JButton btnTraspasarA;
    JButton btnTraspasarB;
    JLabel lblTamano;
    JLabel lblIndice;
    JLabel tiempo;
    Timer temp;
    private int cont;

    public Principal() {

        super();
        setLayout(null);

        MouseHandler mh = new MouseHandler();

        cbA = new JComboBox<String>();
        cbA.setMaximumRowCount(8);
        cbA.setLocation(15, 50);
        cbA.setSize(120, cbA.getPreferredSize().height);
        cbA.setToolTipText("ComboBox A");
        cbA.addItemListener(this);
        this.add(cbA);

        cbB = new JComboBox<String>();
        cbB.setMaximumRowCount(8);
        cbB.setLocation(150, 50);
        cbB.setSize(120, cbB.getPreferredSize().height);
        cbB.setToolTipText("No hay elemento seleccionado");
        cbB.addItemListener(this);
        this.add(cbB);

        txt1 = new JTextField();
        txt1.setSize(120, txt1.getPreferredSize().height);
        txt1.setLocation(15, 110);
        txt1.setToolTipText(
                "Introduce los un valor para añadir en el ComboBox A. Pueden ser varios separando con \";\"");
        this.add(txt1);

        txt2 = new JTextField();
        txt2.setSize(120, txt2.getPreferredSize().height);
        txt2.setLocation(150, 110);
        txt2.setToolTipText("Escribe aquí como empieza el nombre de los elementos a eliminar");
        this.add(txt2);

        btnAnadir = new JButton("Añadir");
        btnAnadir.setSize(120, btnAnadir.getPreferredSize().height);
        btnAnadir.setLocation(15, 195);
        btnAnadir.setToolTipText("Añade al ComboBox A");
        btnAnadir.addActionListener(this);
        this.add(btnAnadir);

        btnQuitar = new JButton("Quitar");
        btnQuitar.setSize(120, btnQuitar.getPreferredSize().height);
        btnQuitar.setLocation(150, 195);
        btnQuitar.setToolTipText("Elimina elementos del ComboBox A");
        btnQuitar.addActionListener(this);
        btnQuitar.addMouseListener(mh);
        btnQuitar.addMouseMotionListener(mh);
        this.add(btnQuitar);

        btnTraspasarA = new JButton("Traspasar a \"A\"");
        btnTraspasarA.setSize(255, btnTraspasarA.getPreferredSize().height);
        btnTraspasarA.setLocation(15, 135);
        btnTraspasarA.setToolTipText("Envia el elemento seleccionado al Combo Box A");
        btnTraspasarA.addActionListener(this);
        this.add(btnTraspasarA);

        btnTraspasarB = new JButton("Traspasar a \"B\"");
        btnTraspasarB.setSize(255, btnTraspasarB.getPreferredSize().height);
        btnTraspasarB.setLocation(15, 165);
        btnTraspasarB.setToolTipText("Envia el elemento seleccionado al Combo Box B");
        btnTraspasarB.addActionListener(this);
        this.add(btnTraspasarB);

        lblTamano = new JLabel("Nro elementos: 0");
        lblTamano.setSize(120, lblTamano.getPreferredSize().height);
        lblTamano.setLocation(15, 15);
        lblTamano.setToolTipText("Indica la cantidad de elementos dentro del Combo Box A");
        this.add(lblTamano);

        lblIndice = new JLabel("Sin seleccionar");
        lblIndice.setSize(180, lblIndice.getPreferredSize().height);
        lblIndice.setLocation(150, 15);
        lblIndice.setToolTipText("Indica el indice del elemento seleccionado del Combo Box A");
        this.add(lblIndice);

        tiempo = new JLabel("Tiempo inactivo: 00:00");
        tiempo.setSize(150, tiempo.getPreferredSize().height);
        tiempo.setLocation(75, 230);
        add(tiempo);

        cont = 0;
        temp = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                cont++;
                if (cont >= 50 && cont < 60) {
                    tiempo.setForeground(Color.RED);
                } else {
                    tiempo.setForeground(null);
                }

                if (cont == 60) {
                    cont = 0;
                    destruccion();
                }
                tiempo.setText(String.format("Tiempo inactivo: 00:%02d", cont));
            }

        });
        temp.start();

        this.addWindowListener(new WindowAdapter() {

            public void windowClosing(WindowEvent e) {
                int res = JOptionPane.showConfirmDialog(null, "¿Quieres cerrar el programa?", "Ejercicio 3",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (res == JOptionPane.OK_OPTION)
                    e.getWindow().dispose();

            }
        });

    }

    public void destruccion() {
        try {
            cbA.removeAllItems();
            cbB.removeAllItems();
            txt1.setText("");
            txt2.setText("");
            lblTamano.setText("Nro elementos: 0");
            cbB.setToolTipText("No hay elemento seleccionado");
            lblIndice.setText("Sin seleccionar");
        } catch (NullPointerException e) {
            System.out.println("tas pasao 1");
        }
        
    }

    public void traspasar(JComboBox<String> cbm1, JComboBox<String> cbm2) {
        cont = 0;
        try {
            cbm2.addItem(cbm1.getSelectedItem().toString());
            cbm1.removeItem(cbm1.getSelectedItem());
            lblTamano.setText(String.format("Nro elementos: %d", cbA.getItemCount()));
            lblIndice.setText(String.format("Indice: %s",
                    cbA.getSelectedIndex() == -1 ? "No hay elementos" : Integer.toString(cbA.getSelectedIndex())));
            cbB.setToolTipText(String.format("Indice: %s",
                    cbB.getSelectedIndex() == -1 ? "Vacio" : Integer.toString(cbB.getSelectedIndex())));
        } catch (NullPointerException e) {
            System.err.println("El ComboBox esta vacio");
        }

    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        cont = 0;
        try {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                lblIndice.setText(String.format("Indice: %s",
                        cbA.getSelectedIndex() == -1 ? "No hay elementos" : Integer.toString(cbA.getSelectedIndex())));
                cbB.setToolTipText(String.format("Indice: %s",
                        cbB.getSelectedIndex() == -1 ? "Vacio" : Integer.toString(cbB.getSelectedIndex())));
            }
        } catch (NullPointerException exc) {
            System.out.println("tas pasao 2");
        }
        

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        cont = 0;

        if (e.getSource() == btnAnadir) {
            try {
                if (!txt1.getText().trim().isEmpty()) {
                    for (int i = 0; i < txt1.getText().trim().split(";").length; i++) {
                        cbA.addItem(txt1.getText().trim().split(";")[i]);
                    }
                    txt1.setText("");
                    lblTamano.setText(String.format("Nro elementos: %d", cbA.getItemCount()));
                }
            } catch (NullPointerException exc) {
                System.err.println("Tas pasao 2");
            }
           
        }

        if (e.getSource() == btnQuitar) {

            if (txt2.getText().trim().isEmpty()) {
                try {
                    cbA.removeItemAt(cbA.getSelectedIndex());
                    lblTamano.setText(String.format("Nro elementos: %d", cbA.getItemCount()));
                } catch (ArrayIndexOutOfBoundsException exec) {
                    System.out.println("tas pasao 3");
                } 
            } else {
                int size = cbA.getItemCount();
                for (int i = 0; i < size; i++) {
                    try {
                        if (cbA.getItemAt(i).toString().indexOf(txt2.getText().trim()) == 0) {
                            cbA.removeItemAt(i);
                            size--;
                            lblTamano.setText(String.format("Nro elementos: %d", cbA.getItemCount()));
                        }
                    } catch (NullPointerException exc) {
                        System.err.println("Tas pasao 4");
                    }
                }
            }
        }

        if (e.getSource() == btnTraspasarA) {

            traspasar(cbB, cbA);
        }

        if (e.getSource() == btnTraspasarB) {

            traspasar(cbA, cbB);
        }

    }

    private class MouseHandler extends MouseAdapter {

        @Override
        public void mouseEntered(java.awt.event.MouseEvent e) {
            cont = 0;
            btnQuitar.setBackground(Color.RED);
        }

        @Override
        public void mouseExited(java.awt.event.MouseEvent e) {
            cont = 0;
            btnQuitar.setBackground(null);
        }
    }
}