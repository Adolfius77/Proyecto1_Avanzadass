/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view;

import Controller.atencionController;
import Controller.autoridadController;
import DAO.atencionDAO;
import DAO.autoridadDAO;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import model.autoridad;
import model.ciudadano;

/**
 *
 * @author USER
 */
public class FrmAtenciones extends javax.swing.JPanel {

    private atencionController clAtencion;
    private autoridadController clAutoridad;

    /**
     * Creates new form FrmAtenciones
     */
    public FrmAtenciones() {
        initComponents();
        this.clAtencion = new atencionController(new atencionDAO());

        this.clAutoridad = new autoridadController(new autoridadDAO());

        Font nuevaFuente = new Font("Segoe UI", Font.PLAIN, 14);

        tablaAtencion.setFont(nuevaFuente);
        JTableHeader header = tablaAtencion.getTableHeader();
        header.setFont(new Font("Segoe UI", Font.BOLD, 14));
        personalizarTabla();
        cargarAtenciones();
        cargarAutoridadesEnComboBox();
        cmbEstatus.setSelectedItem(-1);//este es para que se inicie sin seleccion
        idField.setEditable(false);
    }

    private void cargarAtenciones() {
        DefaultTableModel modelo = clAtencion.obtenerTablaAtenciones();
        tablaAtencion.setModel(modelo);
    }

    private void cargarAutoridadesEnComboBox() {
        DefaultComboBoxModel<autoridad> model = new DefaultComboBoxModel<>();
        List<autoridad> autoridades = clAutoridad.listarAutoridades();
        for (autoridad aut : autoridades) {
            model.addElement(aut);
        }
        cmbAutoridad.setModel(model);
    }

    private void limpiarCampos() {
        idField.setText("");
        cmbEstatus.setSelectedIndex(-1);
        cmbAutoridad.setSelectedIndex(-1);
        fechaInicio.setDateTimeStrict(null);
        fechaSolucion.setDateTimeStrict(null);
        tablaAtencion.clearSelection();
    }

    private void cargarDatosDeTabla() {
        int fila = tablaAtencion.getSelectedRow();
        if (fila != -1) {

            Object idObj = tablaAtencion.getValueAt(fila, 0);
            idField.setText(idObj != null ? idObj.toString() : "");

            Object nombreObj = tablaAtencion.getValueAt(fila, 1);
            if (nombreObj != null) {
                String nombreAutoridad = nombreObj.toString();
                for (int i = 0; i < cmbAutoridad.getItemCount(); i++) {
                    if (cmbAutoridad.getItemAt(i).toString().equals(nombreAutoridad)) {
                        cmbAutoridad.setSelectedIndex(i);
                        break;
                    }
                }
            } else {
                cmbAutoridad.setSelectedIndex(-1);
            }

            Timestamp tsInicio = (Timestamp) tablaAtencion.getValueAt(fila, 2);
            if (tsInicio != null) {
                fechaInicio.setDateTimeStrict(tsInicio.toLocalDateTime());
            } else {
                fechaInicio.setDateTimeStrict(null);
            }

            Timestamp tsSolucion = (Timestamp) tablaAtencion.getValueAt(fila, 3);
            if (tsSolucion != null) {
                fechaSolucion.setDateTimeStrict(tsSolucion.toLocalDateTime());
            } else {
                fechaSolucion.setDateTimeStrict(null);
            }

            if (tablaAtencion.getColumnCount() > 4) {
                Object estatusObj = tablaAtencion.getValueAt(fila, 4);
                if (estatusObj != null) {
                    cmbEstatus.setSelectedItem(estatusObj.toString());
                } else {
                    cmbEstatus.setSelectedIndex(-1);
                }
            }
        }
    }

    private void agregarAtencion() {
        autoridad autoridadSeleccionada = (autoridad) cmbAutoridad.getSelectedItem();
        if (autoridadSeleccionada == null) {
            JOptionPane.showMessageDialog(this, "Por favor, seleccione una autoridad.", "Dato Faltante", JOptionPane.WARNING_MESSAGE);
            return;
        }

        LocalDateTime fechaInicioLDT = fechaInicio.getDateTimeStrict();
        if (fechaInicioLDT == null) {
            JOptionPane.showMessageDialog(this, "La fecha de inicio es obligatoria.", "Dato Faltante", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String estatusSeleccionado = (String) cmbEstatus.getSelectedItem();
        if (estatusSeleccionado == null) {
            JOptionPane.showMessageDialog(this, "Por favor, seleccione un estatus.", "Dato Faltante", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Timestamp fechaInicioTS = Timestamp.valueOf(fechaInicioLDT);
        Timestamp fechaSolucionTS = null;
        if (fechaSolucion.getDateTimeStrict() != null) {
            fechaSolucionTS = Timestamp.valueOf(fechaSolucion.getDateTimeStrict());
        }

        boolean exito = clAtencion.agregarAtencion(autoridadSeleccionada.getId_autoridad(), fechaInicioTS, fechaSolucionTS, estatusSeleccionado);

        if (exito) {
            JOptionPane.showMessageDialog(this, "Atencion registrada correctamente.", "exito", JOptionPane.INFORMATION_MESSAGE);
            cargarAtenciones();
            limpiarCampos();
        } else {
            JOptionPane.showMessageDialog(this, "No se pudo registrar la atencion.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void actualizarAtencion() {
        if (idField.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Seleccione una atención de la tabla para actualizar.", "Sin Selección", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            int idAtencion = Integer.parseInt(idField.getText());
            autoridad autoridadSeleccionada = (autoridad) cmbAutoridad.getSelectedItem();
            if (autoridadSeleccionada == null) {
                JOptionPane.showMessageDialog(this, "Debe seleccionar una autoridad.", "Dato Faltante", JOptionPane.WARNING_MESSAGE);
                return;
            }

            LocalDateTime ldtInicio = fechaInicio.getDateTimeStrict();
            if (ldtInicio == null) {
                JOptionPane.showMessageDialog(this, "La fecha de inicio no puede estar vacía.", "Dato Faltante", JOptionPane.WARNING_MESSAGE);
                return;
            }

            String estatusSeleccionado = (String) cmbEstatus.getSelectedItem();
            if (estatusSeleccionado == null) {
                JOptionPane.showMessageDialog(this, "Por favor, seleccione un estatus.", "Dato Faltante", JOptionPane.WARNING_MESSAGE);
                return;
            }

            Timestamp tsInicio = Timestamp.valueOf(ldtInicio);
            Timestamp tsSolucion = (fechaSolucion.getDateTimeStrict() != null) ? Timestamp.valueOf(fechaSolucion.getDateTimeStrict()) : null;

            int idAutoridad = autoridadSeleccionada.getId_autoridad();

            boolean exito = clAtencion.actualizarAtencion(idAtencion, idAutoridad, tsInicio, tsSolucion, estatusSeleccionado);

            if (exito) {
                JOptionPane.showMessageDialog(this, "Atención actualizada correctamente.");
                cargarAtenciones();
                limpiarCampos();
            } else {
                JOptionPane.showMessageDialog(this, "Error al actualizar la atención.");
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "El ID no es válido.", "Error de Formato", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Ocurrió un error inesperado: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void eliminarAtencion() {
        if (idField.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Seleccione una atención para eliminar.", "Sin selección", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            int id = Integer.parseInt(idField.getText());
            int confirm = JOptionPane.showConfirmDialog(this,
                    "¿Seguro que quieres eliminar esta atención?",
                    "Confirmar eliminación",
                    JOptionPane.YES_NO_OPTION);

            if (confirm == JOptionPane.YES_OPTION) {
                boolean exito = clAtencion.eliminarAtencion(id);
                if (exito) {
                    JOptionPane.showMessageDialog(this, "La atención ha sido eliminada.");
                    cargarAtenciones();
                    limpiarCampos();
                } else {
                    JOptionPane.showMessageDialog(this, "Ocurrió un error al eliminar la atención.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void personalizarTabla() {
        Color colorFondoEncabezado = new Color(101, 85, 143);
        Color colorLetraEncabezado = Color.WHITE;
        Color colorFondoFilaPar = new Color(240, 237, 247);
        Color colorFondoFilaImpar = Color.WHITE;
        Color colorFondoSeleccion = new Color(188, 178, 217);
        Color colorLetraSeleccion = Color.BLACK;
        Color colorCuadricula = new Color(221, 221, 221);

        Font fuenteEncabezado = new Font("Segoe UI", Font.BOLD, 14);
        Font fuenteCeldas = new Font("Segoe UI", Font.PLAIN, 14);

        JTableHeader header = tablaAtencion.getTableHeader();
        header.setFont(fuenteEncabezado);
        header.setBackground(colorFondoEncabezado);
        header.setForeground(colorLetraEncabezado);
        header.setOpaque(false);

        tablaAtencion.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(javax.swing.JTable table, Object value,
                    boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                if (isSelected) {
                    c.setBackground(colorFondoSeleccion);
                    c.setForeground(colorLetraSeleccion);
                } else {
                    c.setBackground(row % 2 == 0 ? colorFondoFilaPar : colorFondoFilaImpar);
                    c.setForeground(Color.BLACK);
                }
                return c;
            }
        });

        tablaAtencion.setFont(fuenteCeldas);
        tablaAtencion.setGridColor(colorCuadricula);
        tablaAtencion.setRowHeight(25);
        tablaAtencion.getTableHeader().setReorderingAllowed(false);
    }

    private void buscar() {
        String filtro = txtBuscar.getText();
        DefaultTableModel modelo = clAtencion.obtenerTablaAtencionesPorFiltro(filtro);
        tablaAtencion.setModel(modelo);
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
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        fechaSolucion = new com.github.lgooddatepicker.components.DateTimePicker();
        fechaInicio = new com.github.lgooddatepicker.components.DateTimePicker();
        jLabel6 = new javax.swing.JLabel();
        idField = new javax.swing.JTextField();
        cmbAutoridad = new javax.swing.JComboBox<>();
        cmbEstatus = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        btnAgregar = new javax.swing.JButton();
        btnActualizar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnLimpiar3 = new javax.swing.JButton();
        jiji = new javax.swing.JScrollPane();
        tablaAtencion = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        txtBuscar = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(217, 202, 218));
        setForeground(new java.awt.Color(217, 202, 218));
        setPreferredSize(new java.awt.Dimension(854, 1000));

        jPanel1.setBackground(new java.awt.Color(101, 85, 143));

        jLabel1.setText("ATENCIONES");
        jLabel1.setFont(new java.awt.Font("Comic Sans MS", 1, 30)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(68, 68, 68)
                .addComponent(jLabel1)
                .addContainerGap(90, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel3.setText("Autoridad:");
        jLabel3.setFont(new java.awt.Font("Comic Sans MS", 1, 15)); // NOI18N

        jLabel4.setText("Fecha inicio:");
        jLabel4.setFont(new java.awt.Font("Comic Sans MS", 1, 15)); // NOI18N

        jLabel5.setText("Fecha solución:");
        jLabel5.setFont(new java.awt.Font("Comic Sans MS", 1, 15)); // NOI18N

        jLabel6.setText("Id:");
        jLabel6.setFont(new java.awt.Font("Comic Sans MS", 1, 15)); // NOI18N

        idField.setEnabled(false);
        idField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                idFieldActionPerformed(evt);
            }
        });

        cmbEstatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pendiente", "En proceso", "Reparado", "Cancelado" }));

        jLabel9.setFont(new java.awt.Font("Comic Sans MS", 1, 15)); // NOI18N
        jLabel9.setText("Estatus:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(idField)
                            .addComponent(fechaSolucion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addComponent(fechaInicio, javax.swing.GroupLayout.DEFAULT_SIZE, 346, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel2)))
                        .addContainerGap())
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(cmbAutoridad, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(12, 12, 12))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(cmbEstatus, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(idField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addGap(11, 11, 11)
                .addComponent(cmbAutoridad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(fechaInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addGap(8, 8, 8)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(fechaSolucion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(cmbEstatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(89, Short.MAX_VALUE))
        );

        btnAgregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/save_all.png"))); // NOI18N
        btnAgregar.setText("Agregar");
        btnAgregar.setBackground(new java.awt.Color(35, 41, 50));
        btnAgregar.setFont(new java.awt.Font("Comic Sans MS", 1, 15)); // NOI18N
        btnAgregar.setForeground(new java.awt.Color(255, 255, 255));
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });

        btnActualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/revert.png"))); // NOI18N
        btnActualizar.setText("Actualizar");
        btnActualizar.setBackground(new java.awt.Color(35, 41, 50));
        btnActualizar.setFont(new java.awt.Font("Comic Sans MS", 1, 15)); // NOI18N
        btnActualizar.setForeground(new java.awt.Color(255, 255, 255));
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarActionPerformed(evt);
            }
        });

        btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/button_cancel.png"))); // NOI18N
        btnEliminar.setText("Eliminar");
        btnEliminar.setBackground(new java.awt.Color(35, 41, 50));
        btnEliminar.setFont(new java.awt.Font("Comic Sans MS", 1, 15)); // NOI18N
        btnEliminar.setForeground(new java.awt.Color(255, 255, 255));
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        btnLimpiar3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/limpia.png"))); // NOI18N
        btnLimpiar3.setText("Limpiar");
        btnLimpiar3.setBackground(new java.awt.Color(35, 41, 50));
        btnLimpiar3.setFont(new java.awt.Font("Comic Sans MS", 1, 15)); // NOI18N
        btnLimpiar3.setForeground(new java.awt.Color(255, 255, 255));
        btnLimpiar3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiar3ActionPerformed(evt);
            }
        });

        jiji.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jijiMouseClicked(evt);
            }
        });

        tablaAtencion.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tablaAtencion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaAtencionMouseClicked(evt);
            }
        });
        jiji.setViewportView(tablaAtencion);

        jPanel3.setBackground(new java.awt.Color(101, 85, 143));

        jLabel7.setText("ATENCIONES REGISTRADAS  ");
        jLabel7.setFont(new java.awt.Font("Comic Sans MS", 1, 30)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel7)
                .addGap(102, 102, 102))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        txtBuscar.setBackground(new java.awt.Color(255, 255, 255));
        txtBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBuscarActionPerformed(evt);
            }
        });
        txtBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarKeyReleased(evt);
            }
        });

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/lupa.png"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(btnAgregar)
                        .addGap(43, 43, 43)
                        .addComponent(btnActualizar)
                        .addGap(89, 89, 89)
                        .addComponent(btnEliminar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                        .addComponent(btnLimpiar3)
                        .addGap(33, 33, 33))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jiji)
                                .addContainerGap())))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 522, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtBuscar)
                            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jiji, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnLimpiar3))))
                .addGap(0, 114, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        agregarAtencion();
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        actualizarAtencion();
    }//GEN-LAST:event_btnActualizarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        eliminarAtencion();
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnLimpiar3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiar3ActionPerformed
        limpiarCampos();
    }//GEN-LAST:event_btnLimpiar3ActionPerformed

    private void idFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_idFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_idFieldActionPerformed

    private void tablaAtencionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaAtencionMouseClicked
        cargarDatosDeTabla();
    }//GEN-LAST:event_tablaAtencionMouseClicked

    private void jijiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jijiMouseClicked

    }//GEN-LAST:event_jijiMouseClicked

    private void txtBuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyReleased
        buscar();
    }//GEN-LAST:event_txtBuscarKeyReleased

    private void txtBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnLimpiar3;
    private javax.swing.JComboBox<model.autoridad> cmbAutoridad;
    private javax.swing.JComboBox<String> cmbEstatus;
    private com.github.lgooddatepicker.components.DateTimePicker fechaInicio;
    private com.github.lgooddatepicker.components.DateTimePicker fechaSolucion;
    private javax.swing.JTextField idField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jiji;
    private javax.swing.JTable tablaAtencion;
    private javax.swing.JTextField txtBuscar;
    // End of variables declaration//GEN-END:variables

}
