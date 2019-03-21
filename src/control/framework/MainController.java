package control.framework;

import control.LanguageToken;
import model.abitur.datenstrukturen.List;
import view.ScanParseForm;

import javax.swing.*;
import java.awt.*;

/**
 * Diese Klasse enthält die main-Methode. Von ihr wird als erstes ein Objekt innerhalb der main-Methode erzeugt,
 * die zu Programmstart aufgerufen wird und kein Objekt benötigt, da sie statisch ist.
 * Die erste Methode, die also nach der main-Methode aufgerufen wird, ist der Konstruktor dieser Klasse. Aus ihm
 * wird alles weitere erzeugt.
 * Vorgegebene Klasse des Frameworks. Modifikation auf eigene Gefahr.
 */
public class MainController {

            //Attribute

            //Referenzen
        private ScanParseForm scanParseForm;

    public static void main (String[] args){

        EventQueue.invokeLater(new Runnable() {

                @Override
                public void run() {

                    new MainController();
                }
            });
    }

    public MainController() {

        scanParseForm = new ScanParseForm(this);
        scanParseForm.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        scanParseForm.setLocationRelativeTo(null);
        scanParseForm.setVisible(true);
    }

    public void lalScanAndParse(String input) {

        if(!lalScan(input)) scanParseForm.getOutputField().append("Das Wort befindet sich in der Lal-Sprache\n");
        else scanParseForm.getOutputField().append("Error: Das Wort befindet sich nicht in der Lal-Sprache\n");
    }

    public boolean lalScan(String input) {

        char first, second;
        int pos = 0;
        input = input + '#';
        LanguageToken token = null;
        boolean fehler = false;
        List<LanguageToken> tokenList = new List<>();

        while(input.charAt(pos) != '#' && !fehler){
            first = input.charAt(pos);
            second = input.charAt(pos+1);
            if(first == 'l' && second == 'a'){
                token = new LanguageToken("EGAL", "la");
            }else if(first == 'l' && second == 'e'){
                token = new LanguageToken("EGAL", "le");
            }else if(first == 'l' && second == 'u'){
                token = new LanguageToken("ENDE", "lu");
            }else{
                fehler = true;
                return fehler;
            }
            if(!fehler) {
                pos += 2;
                tokenList.append(token);
            }
        }
        return !lalParse(tokenList);
    }

    public void mathScanAndParse(String input) {

        if(!mathScan(input)) scanParseForm.getOutputField().append("Das Wort befindet sich in der Mathe-Sprache\n");
        else scanParseForm.getOutputField().append("Error: Das Wort befindet sich nicht in der Mathe-Sprache\n");
    }

    public boolean mathScan(String input) {

        int charPos = 0;
        char selectedChar;
        boolean error = false;

        input += '#';

        while (input.charAt(charPos) != '#' && !error) {

            selectedChar = input.charAt(charPos);

            if(selectedChar == 'x') charPos += 1;
            else if(selectedChar == 'y') charPos += 1;
            else if(selectedChar == '+') {

                charPos += 1;
                selectedChar = input.charAt(charPos);

                if(selectedChar == 'x') charPos += 1;
                else if(selectedChar == 'y') charPos += 1;
                else error = true;
            } else error = true;
        }

        return error;
    }



    private boolean lalParse(List<LanguageToken> tokenList){
        tokenList.toFirst();
        if(tokenList.hasAccess() && lalPruefeS(tokenList)){
            return true;
        }
        return false;
    }

    private boolean lalPruefeS(List<LanguageToken> tokenList){
        if(tokenList.hasAccess() && (tokenList.getContent().getType().equals("EGAL") || tokenList.getContent().getType().equals("ENDE"))){
            tokenList.next();
            return lalPruefeA(tokenList);
        }
        return false;
    }

    private boolean lalPruefeA(List<LanguageToken> tokenList){
        if(tokenList.hasAccess() && (tokenList.getContent().getType().equals("EGAL") || tokenList.getContent().getType().equals("ENDE"))){
            tokenList.next();
            return lalPruefeB(tokenList);
        }
        return false;
    }

    private boolean lalPruefeB(List<LanguageToken> tokenList){
        if(tokenList.hasAccess() && tokenList.getContent().getType().equals("ENDE")){
            return true;
        }
        return false;
    }
}
