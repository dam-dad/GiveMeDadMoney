package tragaPerras;

public class Imagen {

    private String ruta;
    private int valor;

    public Imagen(String ruta, int valor) {

        this.ruta = ruta;
        this.valor = valor;

    }

    public Imagen() {

    }

    public Imagen limon() {

        Imagen limon = new Imagen("/images/TragaPerras/leamon.png", 10);

        return limon;
    }

    public Imagen cherry() {

        Imagen cherry = new Imagen("/images/TragaPerras/cherry.png", 25);

        return cherry;
    }

    public Imagen sandia() {

        Imagen sandia = new Imagen("/images/TragaPerras/sandia.png", 35);

        return sandia;
    }

    public Imagen uva() {

        Imagen uva = new Imagen("/images/TragaPerras/uva.png", 50);

        return uva;
    }

    public Imagen diamante() {

        Imagen diamante = new Imagen("/images/TragaPerras/diamond.png", 65);

        return diamante;
    }

    public Imagen bar() {

        Imagen bar = new Imagen("/images/TragaPerras/bar.png", 75);

        return bar;
    }

    public Imagen siete() {

        Imagen siete = new Imagen("/images/TragaPerras/seven.png", 120);

        return siete;
    }

    public Imagen randomImagen() {

        Imagen[] imagenes = new Imagen[7];
        Imagen imagen = new Imagen();

        imagenes[0] = bar();
        imagenes[1] = cherry();
        imagenes[2] = diamante();
        imagenes[3] = limon();
        imagenes[4] = sandia();
        imagenes[5] = siete();
        imagenes[6] = uva();

        imagen = imagenes[(int) (Math.random() * 6)];

        return imagen;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

}
