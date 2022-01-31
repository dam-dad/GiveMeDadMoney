package score;

import java.io.File;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

@XmlType
@XmlRootElement
public class Score {
	
	private static Score instance;
	
	private static IntegerProperty totalScore;
	private static ListProperty<Game> game;
	
	

	
	public Score() {
		totalScore = new SimpleIntegerProperty(this, "totalScore");
		game = new SimpleListProperty<>(this, "game", FXCollections.observableArrayList());
	}
	
	public static void save(File file) throws Exception {
		JAXBContext context = JAXBContext.newInstance(Score.class);
		Marshaller marshaller = context.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		marshaller.marshal(instance, file);
	}
	
	public static void save() throws Exception {
		save(new File("score.xml"));
	}

	public static Score read(File file) throws Exception {
		JAXBContext context = JAXBContext.newInstance(Score.class);
		Unmarshaller unmarshaller = context.createUnmarshaller();
		return (Score) unmarshaller.unmarshal(file);
	}
	
	public static Score read() throws Exception {
		return read(new File("score.xml"));
	}
	
	public static void load_total_score() {
		int suma_score = 0 ;
		for (int i = 0; i < game.size(); i++) {
			suma_score += game.get(i).getGameScore();
		}
		totalScore.set(suma_score);
		
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
	
	public final ListProperty<Game> gamesProperty() {
		return game;
	}
	
	@XmlElement
	public final ObservableList<Game> getGame() {
		return this.gamesProperty().get();
	}
	

	public final void setGames(final ObservableList<Game> game) {
		this.gamesProperty().set(game);
	}
	
	
	public static Score getInstance() {
		if (instance == null) {
			try {
				instance = new Score().read();
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
		return instance;
	}
}
