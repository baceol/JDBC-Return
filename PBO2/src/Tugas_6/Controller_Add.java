/** Josef Agustinus Sukimto Tangsul Alam 1872004 */
package Tugas_6;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Controller_Add {
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

    public void  initialize() {
        ClubDAO cDao = new ClubDAO();
        ObservableList<Club> cList = cDao.showData();
        cList.remove(0);
        club1.setItems(cList);
        club2.setItems(cList);
    }

    public void add(ActionEvent actionEvent) {
        if (club1.getValue().toString().equals(club2.getValue().toString())){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error");
            alert.setContentText("Cannot pick the same team");
            alert.showAndWait();
        } else {
            Stage stage = (Stage) addBtn.getScene().getWindow();
            stage.close();
        }
    }
}
