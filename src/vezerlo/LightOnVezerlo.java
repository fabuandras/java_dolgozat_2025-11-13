package vezerlo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.LampaModell;
import nezet.GUILightOnNezet;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JButton;

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

    private void kilepes() {
        boolean kilep = nezet.megerositesKilepes();
        if (kilep) {
            System.exit(0);
        }
    }
}
