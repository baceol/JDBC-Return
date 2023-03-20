package Tugas_6;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Controller_Update {
    @FXML
    public ComboBox<Club> club1;
    @FXML
    public ComboBox<Club> club2;
    @FXML
    public TextField score1;
    @FXML
    public TextField score2;
    @FXML
    private Button addBtn;

    public void initialize(Match selectedItems) {
        score1.setText("0");
        score2.setText("0");
        ClubDAO cDao = new ClubDAO();
        ObservableList<Club> cList = cDao.showData();
        cList.remove(0);
        club1.setItems(cList);
        club2.setItems(cList);
        club1.setDisable(true);
        club2.setDisable(true);
        for (int i = 0; i < cList.size(); i++) {
            if (selectedItems.getName1().equals(cList.get(i).toString())) {
                club1.getSelectionModel().select(i);
            } else if (selectedItems.getName2().equals(cList.get(i).toString())) {
                club2.getSelectionModel().select(i);
            }
        }
    }

    public void update(ActionEvent actionEvent) {
        Stage stage = (Stage) addBtn.getScene().getWindow();
        stage.close();
    }
}
