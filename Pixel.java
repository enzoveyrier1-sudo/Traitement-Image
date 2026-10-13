public class Pixel {
    private int R, G, B;

    // Constructeur
    public Pixel(int r, int g, int b){
        this.R = r;
        this.G = g;
        this.B = b;
    }


    // Getters / Setters
    public void setRouge(int x) {
        this.R = x;
    }

    public void setVert(int x) {
        this.G = x;
    }

    public void setBleu(int x) {
        this.B = x;
    }

    public int getRouge(){
        return this.R;
    }

    public int getVert(){
        return this.G;
    }

    public int getBleu(){
        return this.B;
    }


    // Methodes
    @Override
    public String toString(){
        return this.R +" "+this.G+" "+this.B;
    }


    public boolean comparePixel(Pixel p) {
        boolean b = true;
        if (this.R != p.getRouge()) {
            b = false;
        }
        if (this.G != p.getVert()) {
            b = false;
        }
        if (this.B != p.getBleu()) {
            b = false;
        }
        return b;
    }



    public String couleurDominante(){
        if (this.R >= this.G && this.R >= this.B) {
            return "R";
        }
        else if (this.G >= this.R && this.G >= this.B) {
            return "G";
        }
        else {
            return "B";
        }
    }

    public void foncer(String aFoncer, int combien) {
        if(this.couleurDominante().equals(aFoncer)) {
            if (this.R - combien >= 0) {
                this.R -= combien;
            }
            else {
                this.R = 0;
            }

            if (this.G - combien >= 0) {
                this.G -= combien;
            }
            else {
                this.G = 0;
            }

            if (this.B - combien >= 0) {
                this.B -= combien;
            }
            else {
                this.B = 0;
            } 
        }
    }


    public void eclaircir(int valeurMax, String aEclaircir, int combien) {
        if(this.couleurDominante().equals(aEclaircir)) {
            if (this.R + combien <= valeurMax) {
                this.R += combien;
            }
            else {
                this.R = valeurMax;
            }

            if (this.G + combien <= valeurMax) {
                this.G += combien;
            }
            else {
                this.G = valeurMax;
            }

            if (this.B + combien <= valeurMax) {
                this.B += combien;
            }
            else {
                this.B = valeurMax;
            } 
        }
    }


    public void noirEtBlanc() {
        int moyenne = (this.R + this.G + this.B) / 3;
        this.R = moyenne;
        this.G = moyenne;
        this.B = moyenne;
    }


}
