package vezerlo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.LampaModell;
import nezet.GUILightOnNezet;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class LightOnVezerlo {

    private LampaModell modell;
    private GUILightOnNezet nezet;

    public LightOnVezerlo(LampaModell modell, GUILightOnNezet nezet) {
        this.modell = modell;
        this.nezet = nezet;

        esemenyekBeallitasa();
        nezetFrissitese();
        nezet.setVisible(true);
    }

    private void esemenyekBeallitasa() {

        nezet.getMnuUjJatek().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ujJatek();
            }
        });

        nezet.getBtnUjJatek().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ujJatek();
            }
        });

        nezet.getMnuKilepes().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                kilepes();
            }
        });

        nezet.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                kilepes();
            }
        });

        // Mentés fájlba menüpont
        nezet.getMnuMentesFajlba().addActionListener(e -> jatekMenteseFajlba());

        lampaGombEsemenyek();
    }

    private void lampaGombEsemenyek() {
        JButton[] gombok = nezet.getLampaGombok();
        for (int i = 0; i < gombok.length; i++) {
            veglegesitEsemeny(gombok[i], i);
        }
    }

    private void veglegesitEsemeny(JButton lampaGomb, int index) {
        lampaGomb.addActionListener(e -> lampaKattintas(index));
    }

    private void ujJatek() {
        modell.ujJatek();
        nezetFrissitese();
        nezet.setNyertUzenet("");
    }

    private void nezetFrissitese() {
        for (int i = 0; i < 9; i++) {
            boolean lampaAllapot = modell.getLampaAllapot(i);
            nezet.beallitLampaSzin(i, lampaAllapot);
        }
    }

    private void lampaKattintas(int lampaSorszam) {
        modell.lampaKattintas(lampaSorszam);
        nezetFrissitese();
        if (modell.osszesLampaLe()) {
            nezet.setNyertUzenet("Gratulálok! Sikerült lekapcsolnod az összes lámpát!");
        } else {
            nezet.setNyertUzenet("");
        }
    }

    // Mentés fájlba menüpont
    private void jatekMenteseFajlba() {
        JFileChooser fajlValaszto = new JFileChooser();
        int valasz = fajlValaszto.showSaveDialog(nezet);

        if (valasz == JFileChooser.APPROVE_OPTION) {
            File fajl = fajlValaszto.getSelectedFile();

            try (FileWriter iro = new FileWriter(fajl)) {
                String adat = modell.mentesSzoveg();
                iro.write(adat);
                nezet.mutatUzenet("Játékállás sikeresen elmentve.");
            } catch (IOException ex) {
                nezet.mutatUzenet("Hiba történt a mentés során: " + ex.getMessage());
            }
        }
    }

    private void kilepes() {
        boolean kilep = nezet.megerositesKilepes();
        if (kilep) {
            System.exit(0);
        }
    }
}
