package score;

import java.io.File;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 * The type Score. Controllador del xml donde se guardan los puntos que se usan
 * en la aplicacion
 */
@XmlType
@XmlRootElement
public class Score {

	private static Score instance;

	private static IntegerProperty totalScore;

	private static String user = System.getProperty("user.home");
	private static File file = new File(user + "/.GiveMeDADMoney/score.xml");

	/**
	 * Instantiates a new Score.
	 */
	public Score() {
		totalScore = new SimpleIntegerProperty(this, "totalScore");

	}

	/**
	 * Comprueba si el fichero de puntos en la carpeta del usuario existe y si no lo
	 * crea.
	 * 
	 * @throws Exception
	 */
	private static void crear() throws Exception {
		if (file.exists()) {
			read();
		} else {
			File carpeta = new File(user + "/.GiveMeDADMoney/");
			carpeta.mkdir();
			file.createNewFile();
			save();
		}
	}

	/**
	 * Save. Guarda el fichero de puntos con los cambios que tenga
	 *
	 * @param file the file
	 * @throws Exception the exception
	 */
	public static void save(File file) throws Exception {
		JAXBContext context = JAXBContext.newInstance(Score.class);
		Marshaller marshaller = context.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		marshaller.marshal(getInstance(), file);
	}

	/**
	 * Save. Guarda el fichero de puntos predeterminado, sin determinar File
	 *
	 * @throws Exception the exception
	 */
	public static void save() throws Exception {
		save(file);
	}

	/**
	 * Read score. Lee el fichero de puntos predeterminado
	 *
	 * @param file the file
	 * @return the score
	 * @throws Exception the exception
	 */
	public static Score read(File file) throws Exception {
		JAXBContext context = JAXBContext.newInstance(Score.class);
		Unmarshaller unmarshaller = context.createUnmarshaller();
		return (Score) unmarshaller.unmarshal(file);
	}

	/**
	 * Read score. Lee el fichero de puntos predeterminado, sin determinar File
	 *
	 * @return the score
	 * @throws Exception the exception
	 */
	public static Score read() throws Exception {
		return read(file);
	}

	/**
	 * Total score property integer property.
	 *
	 * @return the integer property
	 */
	public final IntegerProperty totalScoreProperty() {
		return totalScore;
	}

	/**
	 * Gets total score.
	 *
	 * @return the total score
	 */
	@XmlElement
	public final int getTotalScore() {
		return this.totalScoreProperty().get();
	}

	/**
	 * Sets total score.
	 *
	 * @param totalScore the total score
	 */
	public final void setTotalScore(final int totalScore) {
		this.totalScoreProperty().set(totalScore);
	}

	/**
	 * Gets instance. Genera la instancia usando el metodo singleton.
	 *
	 * @return the instance
	 */
	@SuppressWarnings("static-access")
	public static Score getInstance() {
		if (instance == null) {
			try {
				instance = new Score();
				crear();
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
		return instance;
	}
}
