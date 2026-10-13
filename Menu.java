import java.io.*;
public class Menu implements OperationImage {
    private Image image;



    public void afficherMenu() {
        boolean ok = false;
        int reponse;
        
        lireFichier();

        while (! ok) {
            Terminal.ecrireStringln("Choisissez parmis les options suivantes : ");
            Terminal.ecrireStringln("1- Afficher la taille de l'image");
            Terminal.ecrireStringln("2- Mettre l'image en noir et blanc");
            Terminal.ecrireStringln("3- Foncer une tonalite");
            Terminal.ecrireStringln("4- Eclaircir une tonalite");   
            // Decouper pas proposée car indispo
            Terminal.ecrireStringln("5- Enregistrer l'image");
            Terminal.ecrireStringln("6- Fermer le menu");

            try {
                reponse = Terminal.lireInt();
            }
            catch (NumberFormatException e) {
                reponse = 0;
            }
            if (reponse == 1) {
                afficherTaille();
            }
            else if (reponse == 2) {
                noirEtBlanc();
            }
            else if (reponse == 3) {
                foncer();
            }
            else if (reponse == 4) {
                eclaircir();
            }
            else if (reponse == 5) {
                ecrireFichier();
            }
            else if (reponse == 6) {
                ok = true;
            }
            else {
                Terminal.ecrireStringln("Opération impossible, recommencez !");
            }
        }
    }



    // Opérations sur image
    @Override
    public void afficherTaille() {
        this.image.afficherTaille();
    }

    @Override
    public void noirEtBlanc() {
        this.image.noirEtBlanc();
    }

    @Override
    public void foncer() {
        String clr;
        int qtt;
        boolean ok = false;
    
        while (!ok) {
            try {
                Terminal.ecrireStringln("Quelle dominante ? (R/G/B)");
                clr = Terminal.lireString();
                if (!clr.equals("R") && !clr.equals("G") && !clr.equals("B")) {
                    throw new CouleurInvalide();
                }

                Terminal.ecrireStringln("De combien ?");
                qtt = Terminal.lireInt();
                if (qtt < 0) {
                    throw new ValeurInvalide();
                }
                this.image.foncer(clr, qtt);
                ok = true;
            }
            catch (CouleurInvalide e) {
                Terminal.ecrireStringln("Couleur invalide ! Entrez R, G ou B");
            }
            catch (ValeurInvalide e) {
                Terminal.ecrireStringln("Valeur doit être positive !");
            }
        }
    }

    @Override
    public void eclaircir() {
        String clr;
        int qtt;
        boolean ok = false;
        while (!ok) {
            try {
                Terminal.ecrireStringln("Quelle dominante ? (R/G/B)");
                clr = Terminal.lireString();
                if (!clr.equals("R") && !clr.equals("G") && !clr.equals("B")) {
                    throw new CouleurInvalide();
                }

                Terminal.ecrireStringln("De combien ?");
                qtt = Terminal.lireInt();
                if (qtt < 0) {
                    throw new ValeurInvalide();
                }
                this.image.eclaircir(clr, qtt);
                ok = true;
            }
            catch (CouleurInvalide e) {
                Terminal.ecrireStringln("Couleur invalide ! Entrez R, G ou B");
            }
            catch (ValeurInvalide e) {
                Terminal.ecrireStringln("Valeur doit être positive !");
            }
        }
    }

    @Override
    public void decouper(int l1, int l2, int c1, int c2) {
        this.image.decouper(l1, l2, c1, c2);
    }



    // ---- Lire / Ecrire fichier -----

    // Ecrire image dans un fichier
    public void ecrireFichier(){
        FileOutputStream fichier;
        String nomFichier;
        
        Terminal.ecrireStringln("Donnez le nom du fichier de sortie : ");
        nomFichier = Terminal.lireString();
        try {
            fichier = new FileOutputStream(nomFichier);
            ecrireEnTete(fichier, nomFichier, this.image);

            // Ecrire pixel en parcourant image
            ecrirePixel(fichier, this.image);
            fichier.close();
        }
        catch (FileNotFoundException ex) {
            Terminal.ecrireStringln("Ce fichier n'existe pas");
        }
        catch (IOException exc) {
            Terminal.ecrireStringln("Erreur d'entree - sortie");
        }
    }




    private void ecrirePixel(FileOutputStream f, Image img) throws IOException {
        Segment segment = img.getPremier();
        int refLargeur = 0;
        while (segment != null) {
            for (int i=0; i < segment.getNombrePixel(); i++) {
                String valeur = Integer.toString(segment.getPixel().getRouge()); // Integer.toString trouve sur internet
                for (int j=0; j<valeur.length(); j++) {
                    f.write(valeur.charAt(j));
                }
                f.write(' ');
                valeur = Integer.toString(segment.getPixel().getVert());
                for (int j=0; j<valeur.length(); j++) {
                    f.write(valeur.charAt(j));
                }
                f.write(' ');
                valeur = Integer.toString(segment.getPixel().getBleu());
                for (int j=0; j<valeur.length(); j++) {
                    f.write(valeur.charAt(j));
                }
                f.write(' ');
                refLargeur++;
                if(refLargeur == img.getLargeur()) {
                    f.write('\n');
                }
            }
            segment = segment.getSuivant();
        }   
    }
  


    private void ecrireEnTete(FileOutputStream f, String nomFichier, Image img) throws IOException{
        f.write('P');
        f.write('3');
        f.write('\n');            
        f.write('#');
        for (int i=0; i<nomFichier.length(); i++){
            f.write(nomFichier.charAt(i));
        }
        f.write('\n');

        String largeur = Integer.toString(img.getLargeur());
        for (int i=0; i<largeur.length(); i++) {
            f.write(largeur.charAt(i));
        }
        f.write(' ');
        String hauteur = Integer.toString(img.getHauteur());
        for (int i=0; i<hauteur.length(); i++) {
            f.write(hauteur.charAt(i));
        }
        f.write('\n'); 

        String valeurMax = Integer.toString(img.getValeurMax());
        for (int i=0; i<valeurMax.length(); i++) {
            f.write(valeurMax.charAt(i));
        }
        f.write('\n'); 
    }




    // Lecture fichier + création image
    public void lireFichier() {
        FileInputStream fichier;
        String nomFichier;
        int largeur, hauteur, valeurMax;
        Image image = null;
        boolean ok=false;

        Terminal.ecrireString("Donnez le nom du ficher : ");
        nomFichier = Terminal.lireString();
        while (! ok) {
            try {
                fichier = new FileInputStream(nomFichier);
                lireLigne(fichier);
                lireLigne(fichier);
                largeur = lireEntier(fichier);
                hauteur = lireEntier(fichier);
                valeurMax = lireEntier(fichier);

                image = new Image(largeur, hauteur, valeurMax);
                lirePixel(fichier, image); // Lire chaque pixel + créer la liste chainée (donc l'image)

                ok = true;
                fichier.close();
            }
            catch (FileNotFoundException ex) {
                Terminal.ecrireStringln("Ce fichier n'existe pas");
            }
            catch (IOException exc) {
                Terminal.ecrireStringln("Erreur d'entree - sortie");
            }
        }
        this.image = image;
    }
    



    private void lirePixel (FileInputStream f, Image img) throws IOException {
        int totalPixel = img.getLargeur() * img.getHauteur();
        Segment sgt = null;

        for(int i=0; i<totalPixel; i++) {
            int r = lireEntier(f);
            int g = lireEntier(f);
            int b = lireEntier(f);
            Pixel p = new Pixel(r,g,b);

            if (sgt != null && sgt.getPixel().comparePixel(p)) {
                sgt.setNombrePixel(sgt.getNombrePixel() + 1);
            }
            else {
                if (sgt != null) {
                    img.ajouterFin(sgt);
                }
                sgt = new Segment(p, 1);
            }
        }
        if (sgt != null) {
            img.ajouterFin(sgt);
        }
    }


    private void lireLigne(FileInputStream f) throws IOException{
        int c = f.read();
        while (c != '\n' && c != -1) {
            c = f.read();
        }
    }

    private int lireEntier(FileInputStream f) throws IOException {
        int c = f.read();
        String s = "";
        int x;

        while (c == ' ' || c == '\n' || c == '\r') {
            c = f.read();
        }
        while (c != ' ' && c != '\n' && c != '\r' && c != -1) {
            s += (char) c;
            c = f.read();
        }
        x = Integer.parseInt(s);
        return x;

    }
}
class CouleurInvalide extends Exception {}
class ValeurInvalide extends Exception {}
