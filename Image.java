public class Image {
    private Segment premier;
    private int largeur, hauteur, valeurMax;

    // Constructeurs 
    public Image(){
        this.premier = null;
    }

    public Image(Segment elt) {
        this.premier = elt;
    }

    public Image(int l, int h, int v) {
        this.largeur = l;
        this.hauteur = h;
        this.valeurMax = v;
        this.premier = null;
    }

    public Image(int l, int h, int v, Segment s) {
        this.largeur = l;
        this.hauteur = h;
        this.valeurMax = v;
        this.premier = s;
    }


    // Getters / Setters
    public void setLargeur(int x){
        this.largeur = x;
    }

    public void setHauteur(int x){
        this.hauteur = x;
    }

    public void setValeurMax(int x){
        this.valeurMax = x;
    }

    public int getLargeur(){
        return this.largeur;
    }

    public int getHauteur(){
        return this.hauteur;
    }

    public int getValeurMax(){
        return this.valeurMax;
    }



    // Modifier la liste de segment
    public void ajouterDebut(Segment elt) {
        if (this.premier == null) {
            this.premier = elt;
        }
        else {
            elt.setSuivant(this.premier);
            this.premier = elt;
        }
    }

    public void ajouterFin(Segment sgt) {
        Segment prec = null;
        Segment ref = this.premier;

        if (this.premier == null) {
            this.premier = sgt;
            return;
        }
        else {
            while (ref != null) {
                prec = ref;
                ref = ref.getSuivant();
            }
            prec.setSuivant(sgt);
        }  
    }


    public void setPremier(Segment elt){
        this.premier = elt;
    }

    public Segment getPremier() {
            return this.premier;
    }



    // Opérations sur image
    public void afficherTaille() {
        Terminal.ecrireStringln("La taille de l'image ("+this.largeur+" x "+this.hauteur+")");
    }


    public void noirEtBlanc() {
        noirEtBlancRec(this.premier);
    }

    private void noirEtBlancRec(Segment ref) {
        if (ref == null) {
            return;
        }
        else {
            ref.getPixel().noirEtBlanc();
            noirEtBlancRec(ref.getSuivant());
        }
    }



    public void foncer(String couleur, int quantite){
        foncerRec(this.premier, couleur, quantite);
    }

    private void foncerRec(Segment ref, String c, int qtt) {
        if (ref == null) {
            return;
        }
        else {
            ref.getPixel().foncer(c, qtt);
            foncerRec(ref.getSuivant(), c, qtt);
        }
    }


    public void eclaircir(String couleur, int quantite) {
        eclaircirRec(this.premier, couleur, quantite);
    }

    private void eclaircirRec(Segment ref, String c, int qtt){
        if (ref == null) {
            return;
        }
        else {
            ref.getPixel().eclaircir(this.valeurMax, c, qtt);
            eclaircirRec(ref.getSuivant(), c, qtt);
        }
    }



    // Decouper de façon récursive pas terminée
    public void decouper(int l1, int l2, int c1, int c2){
        Image imgDecoupe = new Image(this.largeur, l2-l1+1, this.valeurMax);
        decouperRec(this.premier, imgDecoupe, l1, l2, c1, c2);
    }

    private void decouperRec(Segment sgt, Image img, int l1, int l2, int c1, int c2){}
}
