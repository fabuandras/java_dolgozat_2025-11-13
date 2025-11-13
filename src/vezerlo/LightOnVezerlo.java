package vezerlo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.LampaModell;
import nezet.GUILightOnNezet;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

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
    }

    private void ujJatek() {
        modell.ujJatek();
        nezetFrissitese();
    }

    private void nezetFrissitese() {
        for (int i = 0; i < 9; i++) {
            boolean lampaAllapot = modell.getLampaAllapot(i);
            nezet.beallitLampaSzin(i, lampaAllapot);
        }
    }

    private void kilepes() {
        boolean kilep = nezet.megerositesKilepes();
        if (kilep) {
            System.exit(0);
        }
    }
}
