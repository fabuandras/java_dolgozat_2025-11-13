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

    public void lampaKattintas(int lampaSorszam) {
        valtLampa(lampaSorszam);

        switch (lampaSorszam) {
            case 0:
                valtLampa(1);
                valtLampa(3);
                break;
            case 1:
                valtLampa(0);
                valtLampa(2);
                valtLampa(4);
                break;
            case 2:
                valtLampa(1);
                valtLampa(5);
                break;
            case 3:
                valtLampa(0);
                valtLampa(4);
                valtLampa(6);
                break;
            case 4:
                valtLampa(1);
                valtLampa(3);
                valtLampa(5);
                valtLampa(7);
                break;
            case 5:
                valtLampa(2);
                valtLampa(4);
                valtLampa(8);
                break;
            case 6:
                valtLampa(3);
                valtLampa(7);
                break;
            case 7:
                valtLampa(4);
                valtLampa(6);
                valtLampa(8);
                break;
            case 8:
                valtLampa(5);
                valtLampa(7);
                break;
        }
    }

    //Mentés fájlból menüpont
    
    public String mentesSzoveg() {
        String eredmeny = "";
        for (int i = 0; i < lampak.length; i++) {
            eredmeny += lampak[i] ? "1" : "0";
        }
        return eredmeny;
    }

    public void betoltesSzoveg(String adat) {
        if (adat == null || adat.length() < lampak.length) {
            return;
        }
        for (int i = 0; i < lampak.length; i++) {
            lampak[i] = adat.charAt(i) == '1';
        }
    }

}
