package main;

//Fabu András

import model.LampaModell;
import nezet.GUILightOnNezet;
import vezerlo.LightOnVezerlo;


public class LightOnJatek {

    public static void main(String[] args) {
        LampaModell modell = new LampaModell();
        GUILightOnNezet nezet = new GUILightOnNezet();
        LightOnVezerlo vezerlo = new LightOnVezerlo(modell, nezet);
    }
    
}
