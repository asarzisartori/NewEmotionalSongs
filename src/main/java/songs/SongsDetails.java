package songs;

import utilities.Utilities;
import java.awt.Font;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import utilities.Globals;
import models.Song;

/**
 * Classe che permette la visualizzazione del dettaglio di una canzone
 * 
 * @author Andrea Sarzi Sartori 726694 Varese
 * @author Samuele Cervini 726624 Varese
 */
public class SongsDetails extends javax.swing.JFrame {

    public String id;

    /**
     * Costruttore di SongsDetails vuoto
     */
    public SongsDetails() {
        initComponents();
        Utilities.setLogoES(this);
    }
    
    /**
     * Costruttore di SongsDetails con id come parametro di passaggio
     * 
     * @param id Id della canzone di cui voler vedere i dettagli
     */
    public SongsDetails(String id) {
        try {
            this.id = id;
            
            initComponents();
            Utilities.setLogoES(this);
            
            if (Globals.isUserLogged == false) {
                btn_addEmotions.setVisible(false);
                btn_addToPlaylist.setVisible(false);
            }
            
            Song song = Globals.server.getSongsById(id);
            
            detailedsong_id.setText("Id:   " + song.getId());
            detailedsong_title.setText("Titolo:   " + song.getTitle());
            detailedsong_author.setText("Autore:   " + song.getAuthor());
            detailedsong_year.setText("Anno:   " + song.getYear());
            
            ArrayList<float[]> results = Globals.server.visualizzaEmozioneBrano(id);
            Font font = new Font("Segou UI", Font.ITALIC, 16);
            
            if (results.get(0)[0] == 0.0) {
                detailedsong_potere.setFont(font);
                detailedsong_potere.setText("Potere:   Questa emozione non è stata ancora provata da nessun utente.");
            } else {
                detailedsong_potere.setText("Potere:   Questa emozione è stata provata da " + Math.round(results.get(0)[0]) + " utenti ed ha totalizzato una media di intensità di " + String.format("%.2f", results.get(0)[1]) + ".");
            }
            
            if (results.get(1)[0] == 0.0) {
                detailedsong_gioia.setFont(font);
                detailedsong_gioia.setText("Goia:   Questa emozione non è stata ancora provata da nessun utente.");
            } else {
                detailedsong_gioia.setText("Goia:   Questa emozione è stata provata da " + Math.round(results.get(1)[0]) + " utenti ed ha totalizzato una media di intensità di " + String.format("%.2f", results.get(1)[1]) + ".");
            }
            
            if (results.get(2)[0] == 0.0) {
                detailedsong_tensione.setFont(font);
                detailedsong_tensione.setText("Tensione:   Questa emozione non è stata ancora provata da nessun utente.");
            } else {
                detailedsong_tensione.setText("Tensione:   Questa emozione è stata provata da " + Math.round(results.get(2)[0]) + " utenti ed ha totalizzato una media di intensità di " + String.format("%.2f", results.get(2)[1]) + ".");
            }
            
            if (results.get(3)[0] == 0.0) {
                detailedsong_nostalgia.setFont(font);
                detailedsong_nostalgia.setText("Nostalgia:   Questa emozione non è stata ancora provata da nessun utente.");
            } else {
                detailedsong_nostalgia.setText("Nostalgia:   Questa emozione è stata provata da " + Math.round(results.get(3)[0]) + " utenti ed ha totalizzato una media di intensità di " + String.format("%.2f", results.get(3)[1]) + ".");
            }
            
            if (results.get(4)[0] == 0.0) {
                detailedsong_tristezza.setFont(font);
                detailedsong_tristezza.setText("Tristezza:   Questa emozione non è stata ancora provata da nessun utente.");
            } else {
                detailedsong_tristezza.setText("Tristezza:   Questa emozione è stata provata da " + Math.round(results.get(4)[0]) + " utenti ed ha totalizzato una media di intensità di " + String.format("%.2f", results.get(4)[1]) + ".");
            }
            
            if (results.get(5)[0] == 0.0) {
                detailedsong_stupore.setFont(font);
                detailedsong_stupore.setText("Stupore:   Questa emozione non è stata ancora provata da nessun utente.");
            } else {
                detailedsong_stupore.setText("Stupore:   Questa emozione è stata provata da " + Math.round(results.get(5)[0]) + " utenti ed ha totalizzato una media di intensità di " + String.format("%.2f", results.get(5)[1])+ ".");
            }
            
            if (results.get(6)[0] == 0.0) {
                detailedsong_solennita.setFont(font);
                detailedsong_solennita.setText("Solennità:   Questa emozione non è stata ancora provata da nessun utente.");
            } else {
                detailedsong_solennita.setText("Solennità:   Questa emozione è stata provata da " + Math.round(results.get(6)[0]) + " utenti ed ha totalizzato una media di intensità di " + String.format("%.2f", results.get(6)[1]) + ".");
            }
            
            if (results.get(7)[0] == 0.0) {
                detailedsong_tenerezza.setFont(font);
                detailedsong_tenerezza.setText("Tenerezza:   Questa emozione non è stata ancora provata da nessun utente.");
            } else {
                detailedsong_tenerezza.setText("Tenerezza:   Questa emozione è stata provata da " + Math.round(results.get(7)[0]) + " utenti ed ha totalizzato una media di intensità di " + String.format("%.2f", results.get(7)[1]) + ".");
            }
            
            if (results.get(8)[0] == 0.0) {
                detailedsong_calma.setFont(font);
                detailedsong_calma.setText("Calma:   Questa emozione non è stata ancora provata da nessun utente.");
            } else {
                detailedsong_calma.setText("Calma:   Questa emozione è stata provata da " + Math.round(results.get(8)[0]) + " utenti ed ha totalizzato una media di intensità di " + String.format("%.2f", results.get(8)[1]) + ".");
            }
        } catch (RemoteException ex) {
            Logger.getLogger(SongsDetails.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        detailedsong_id = new javax.swing.JLabel();
        detailedsong_title = new javax.swing.JLabel();
        detailedsong_author = new javax.swing.JLabel();
        detailedsong_year = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        detailedsong_potere = new javax.swing.JLabel();
        detailedsong_gioia = new javax.swing.JLabel();
        detailedsong_tensione = new javax.swing.JLabel();
        detailedsong_nostalgia = new javax.swing.JLabel();
        detailedsong_tristezza = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        detailedsong_stupore = new javax.swing.JLabel();
        detailedsong_solennita = new javax.swing.JLabel();
        detailedsong_tenerezza = new javax.swing.JLabel();
        detailedsong_calma = new javax.swing.JLabel();
        btn_addEmotions = new javax.swing.JButton();
        btn_addToPlaylist = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Dettaglio canzone");
        setResizable(false);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Canzone selezionata:");

        detailedsong_id.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        detailedsong_id.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        detailedsong_id.setText("id");

        detailedsong_title.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        detailedsong_title.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        detailedsong_title.setText("titolo");

        detailedsong_author.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        detailedsong_author.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        detailedsong_author.setText("autore");

        detailedsong_year.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        detailedsong_year.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        detailedsong_year.setText("anno");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Riassunto emozioni:");

        detailedsong_potere.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        detailedsong_potere.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        detailedsong_potere.setText("potere");

        detailedsong_gioia.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        detailedsong_gioia.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        detailedsong_gioia.setText("gioia");

        detailedsong_tensione.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        detailedsong_tensione.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        detailedsong_tensione.setText("tensione");

        detailedsong_nostalgia.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        detailedsong_nostalgia.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        detailedsong_nostalgia.setText("nostalgia");

        detailedsong_tristezza.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        detailedsong_tristezza.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        detailedsong_tristezza.setText("tristezza");

        jButton2.setText("Indietro");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        detailedsong_stupore.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        detailedsong_stupore.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        detailedsong_stupore.setText("stupore");

        detailedsong_solennita.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        detailedsong_solennita.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        detailedsong_solennita.setText("solennità");

        detailedsong_tenerezza.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        detailedsong_tenerezza.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        detailedsong_tenerezza.setText("tenerezza");

        detailedsong_calma.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        detailedsong_calma.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        detailedsong_calma.setText("calma");

        btn_addEmotions.setText("Aggiungi Emozioni");
        btn_addEmotions.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_addEmotionsActionPerformed(evt);
            }
        });

        btn_addToPlaylist.setText("Aggiungi a Playlist");
        btn_addToPlaylist.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_addToPlaylistActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(381, 381, 381)
                .addComponent(jButton2)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGap(166, 166, 166)
                        .addComponent(btn_addEmotions)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_addToPlaylist)
                        .addGap(173, 173, 173))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(detailedsong_id, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(detailedsong_title, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(detailedsong_year, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(detailedsong_author, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(detailedsong_gioia, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(detailedsong_potere, javax.swing.GroupLayout.PREFERRED_SIZE, 823, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(detailedsong_nostalgia, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(detailedsong_tensione, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(detailedsong_stupore, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(detailedsong_tristezza, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(detailedsong_calma, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(detailedsong_tenerezza, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(detailedsong_solennita, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(detailedsong_id)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(detailedsong_title)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(detailedsong_author)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(detailedsong_year)
                .addGap(30, 30, 30)
                .addComponent(jLabel5)
                .addGap(18, 18, 18)
                .addComponent(detailedsong_potere)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(detailedsong_gioia)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(detailedsong_tensione)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(detailedsong_nostalgia)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(detailedsong_tristezza)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(detailedsong_stupore)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(detailedsong_solennita)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(detailedsong_tenerezza)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(detailedsong_calma)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_addToPlaylist)
                    .addComponent(btn_addEmotions))
                .addGap(27, 27, 27)
                .addComponent(jButton2)
                .addGap(27, 27, 27))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        dispose();
        Search search = new Search();
        search.setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void btn_addEmotionsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_addEmotionsActionPerformed
        dispose();
        AddEmotions addEmotions = new AddEmotions(id);
        addEmotions.setVisible(true);
    }//GEN-LAST:event_btn_addEmotionsActionPerformed

    private void btn_addToPlaylistActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_addToPlaylistActionPerformed

        try {
            ArrayList<String> results = Globals.server.getAllUserPlaylist(Globals.currentUsername);
            
            if (results == null) {
                javax.swing.JOptionPane.showMessageDialog(getContentPane(), "Non è possibile effettuare questa operazione, nessuna playlist disponibile.", "Attenzione", javax.swing.JOptionPane.WARNING_MESSAGE);
            } else {
                JComboBox playlistList = new JComboBox();
                
                for (String playlistname : results) {
                    playlistList.addItem(playlistname);
                }
                
                final JComponent[] inputs = new JComponent[] {
                    new JLabel("Scegli fra una delle tue playlist:"),
                    playlistList,
                };
                
                int result = JOptionPane.showConfirmDialog(null, inputs, "Scelta playlist", JOptionPane.PLAIN_MESSAGE);
                if (result == JOptionPane.OK_OPTION) {
                    
                    String playlist_name = playlistList.getSelectedItem().toString();
                    int playlist_id = Globals.server.getPlaylistIdByName(playlist_name, Globals.currentUsername);
                    Boolean check = Globals.server.checkIfSongInPlaylist(id, playlist_id);
                    
                    if (check) {
                        javax.swing.JOptionPane.showMessageDialog(getContentPane(), "Non è possibile effettuare questa operazione, in questa playlist è già presente questa canzone!", "Attenzione", javax.swing.JOptionPane.WARNING_MESSAGE);
                    } else {
                        Globals.server.insertSongInPlaylist(id, playlist_id);
                        javax.swing.JOptionPane.showMessageDialog(getContentPane(), "Inserimento della canzone nella playlist completato.", "Completato", javax.swing.JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }
        } catch (RemoteException ex) {
            Logger.getLogger(SongsDetails.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btn_addToPlaylistActionPerformed

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
            java.util.logging.Logger.getLogger(SongsDetails.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SongsDetails.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SongsDetails.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SongsDetails.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SongsDetails().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_addEmotions;
    private javax.swing.JButton btn_addToPlaylist;
    private javax.swing.JLabel detailedsong_author;
    private javax.swing.JLabel detailedsong_calma;
    private javax.swing.JLabel detailedsong_gioia;
    private javax.swing.JLabel detailedsong_id;
    private javax.swing.JLabel detailedsong_nostalgia;
    private javax.swing.JLabel detailedsong_potere;
    private javax.swing.JLabel detailedsong_solennita;
    private javax.swing.JLabel detailedsong_stupore;
    private javax.swing.JLabel detailedsong_tenerezza;
    private javax.swing.JLabel detailedsong_tensione;
    private javax.swing.JLabel detailedsong_title;
    private javax.swing.JLabel detailedsong_tristezza;
    private javax.swing.JLabel detailedsong_year;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}

