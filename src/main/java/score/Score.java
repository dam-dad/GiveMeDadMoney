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
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

@XmlType
@XmlRootElement
public class Score {
	
	
	private StringProperty userName;
	private IntegerProperty totalScore;
	private ListProperty<Game> game;

	

	public Score() {
		userName = new SimpleStringProperty(this, "userName");
		totalScore = new SimpleIntegerProperty(this, "totalScore");
		game = new SimpleListProperty<>(this, "game", FXCollections.observableArrayList());

	}
	
	public void save(File file) throws Exception {
		JAXBContext context = JAXBContext.newInstance(Score.class);
		Marshaller marshaller = context.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		marshaller.marshal(this, file);
	}
	
	public void save() throws Exception {
		
		JAXBContext context = JAXBContext.newInstance(Score.class);
		Marshaller marshaller = context.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		marshaller.marshal(this, new File("score.xml"));
	}

	public static Score read(File file) throws Exception {
		JAXBContext context = JAXBContext.newInstance(Score.class);
		Unmarshaller unmarshaller = context.createUnmarshaller();
		return (Score) unmarshaller.unmarshal(file);
	}
	
	public static Score read() throws Exception {
		JAXBContext context = JAXBContext.newInstance(Score.class);
		Unmarshaller unmarshaller = context.createUnmarshaller();
		return (Score) unmarshaller.unmarshal(new File("score.xml"));
	}

	public final StringProperty userNameProperty() {
		return this.userName;
	}
	
	@XmlElement
	public final String getUserName() {
		return this.userNameProperty().get();
	}
	

	public final void setUserName(final String userName) {
		this.userNameProperty().set(userName);
	}

	
	public final IntegerProperty totalScoreProperty() {
		return this.totalScore;
	}
	
	
	@XmlElement
	public final int getTotalScore() {
		return this.totalScoreProperty().get();
	}
	

	public final void setTotalScore(final int totalScore) {
		this.totalScoreProperty().set(totalScore);
	}
	
	public final ListProperty<Game> gamesProperty() {
		return this.game;
	}
	
	@XmlElement
	public final ObservableList<Game> getGame() {
		return this.gamesProperty().get();
	}
	

	public final void setGames(final ObservableList<Game> game) {
		this.gamesProperty().set(game);
	}
	
	@Override
	public String toString() {
		return (getUserName() + " " + getTotalScore());
	}


}
