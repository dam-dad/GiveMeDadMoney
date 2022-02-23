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


@XmlType
@XmlRootElement
public class Score {

	private static Score instance;

	private static IntegerProperty totalScore;

	private static String user = System.getProperty("user.home");
	private static File file = new File(user + "/score.xml");

	public Score() {
		totalScore = new SimpleIntegerProperty(this, "totalScore");

	}
	private static void crear() throws Exception {
			if (file.exists()) {
				read();
			}else {
				file.createNewFile();
				save();
			}
	}

	public static void save(File file) throws Exception {
		JAXBContext context = JAXBContext.newInstance(Score.class);
		Marshaller marshaller = context.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		marshaller.marshal(getInstance(), file);
	}

	public static void save() throws Exception {
		save(file);
	}

	public static Score read(File file) throws Exception {
		JAXBContext context = JAXBContext.newInstance(Score.class);
		Unmarshaller unmarshaller = context.createUnmarshaller();
		return (Score) unmarshaller.unmarshal(file);
	}

	public static Score read() throws Exception {
		return read(file);
	}


	public final IntegerProperty totalScoreProperty() {
		return totalScore;
	}

	@XmlElement
	public final int getTotalScore() {
		return this.totalScoreProperty().get();
	}

	public final void setTotalScore(final int totalScore) {
		this.totalScoreProperty().set(totalScore);
	}


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
