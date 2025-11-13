package model;

public class LampaModell {

    private boolean[] lampak; // true = lámpa felkapcsolva, false = lámpa lekapcsolva

    public LampaModell() {
        lampak = new boolean[9];
        ujJatek();
    }

    public boolean getLampaAllapot(int lampaSorszam) {
        return lampak[lampaSorszam];
    }

    public void setLampaAllapot(int lampaSorszam, boolean allapot) {
        lampak[lampaSorszam] = allapot;
    }

    public void ujJatek() {
        for (int i = 0; i < 9; i++) {
            lampak[i] = Math.random() < 0.5;
        }
    }
    
    public void valtLampa(int lampaSorszam) {
        lampak[lampaSorszam] = !lampak[lampaSorszam];
    }

    public boolean osszesLampaLe() {
        for (int i = 0; i < lampak.length; i++) {
            if (lampak[i]) {
                return false;
            }
        }
        return true;
    }
}
