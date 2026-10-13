public class Segment {
    private Pixel pixel;
    private int nombre;
    private Segment suivant;

    // Constructeur
    public Segment(Pixel p, int nbr, Segment suiv){
        this.pixel = p;
        this.nombre = nbr;
        this.suivant = suiv;
    }

    public Segment(Pixel p, int nbr){
        this.pixel = p;
        this.nombre = nbr;
        this.suivant = null;
    }

    // Getters / Setters
    public Pixel getPixel(){
        return this.pixel;
    }

    public void setCouleurPixel(Pixel clr){
        this.pixel = clr;
    }

    public int getNombrePixel(){
        return this.nombre;
    }

    public void setNombrePixel(int x){
        this.nombre = x;
    }

    public Segment getSuivant(){
        return this.suivant;
    }


    public void setSuivant(Segment elt){
        this.suivant = elt;
    }


    // Methodes
    @Override
    public String toString(){
        return "Segment "+this.pixel+", nombre : "+this.nombre;
    }


}
