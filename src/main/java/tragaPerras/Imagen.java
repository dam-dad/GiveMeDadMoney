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

    public Imagen bar() {

        Imagen bar = new Imagen("/images/TragaPerras/bar.png", 1);

        return bar;
    }

    public Imagen cherry() {

        Imagen cherry = new Imagen("/images/TragaPerras/cherry.png", 2);

        return cherry;
    }

    public Imagen diamante() {

        Imagen diamante = new Imagen("/images/TragaPerras/diamond.png", 5);

        return diamante;
    }

    public Imagen iconoSiete() {

        Imagen icon = new Imagen("/images/TragaPerras/icono.png", 10);

        return icon;
    }

    public Imagen limon() {

        Imagen limon = new Imagen("/images/TragaPerras/leamon.png", 4);

        return limon;
    }

    public Imagen sandia() {

        Imagen sandia = new Imagen("/images/TragaPerras/sandia.png", 2);

        return sandia;
    }

    public Imagen siete() {

        Imagen siete = new Imagen("/images/TragaPerras/seven.png", 3);

        return siete;
    }

    public Imagen uva() {

        Imagen uva = new Imagen("/images/TragaPerras/uva.png", 6);

        return uva;
    }

    public Imagen randomImagen() {

        Imagen[] imagenes = new Imagen[8];
        Imagen imagen = new Imagen();

        imagenes[0] = bar();
        imagenes[1] = cherry();
        imagenes[2] = diamante();
        imagenes[3] = iconoSiete();
        imagenes[4] = limon();
        imagenes[5] = sandia();
        imagenes[6] = siete();
        imagenes[7] = uva();

        imagen = imagenes[(int) (Math.random() * 7)];

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
