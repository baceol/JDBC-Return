/** Josef Agustinus Sukimto Tangsul Alam 1872004 */
package Tugas_6;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.IOException;

public class Controller_Main {
    @FXML
    TableView<Match> TViewMatch;
    @FXML
    TableView<Leaderboard> TViewLeaderboard;
    @FXML
    TableColumn<Match, String> colClub1;
    @FXML
    TableColumn<Match, String> colClub2;
    @FXML
    TableColumn<Match, Integer> colScore1;
    @FXML
    TableColumn<Match, Integer> colScore2;
    @FXML
    TableColumn<Match, String> colWL;
    @FXML
    TableColumn<Leaderboard, Integer> colId;
    @FXML
    TableColumn<Leaderboard, String> colName;
    @FXML
    TableColumn<Leaderboard, Integer> colWin;
    @FXML
    TableColumn<Leaderboard, Integer> colLose;
    @FXML
    TableColumn<Leaderboard, Integer> colDraw;
    @FXML
    TableColumn<Leaderboard, Integer> colPoint;
    @FXML
    ListView<Club> LView;

    public void  initialize() {
        ClubDAO cDao = new ClubDAO();
        ObservableList<Club> cList = cDao.showData();
        LView.setItems(cList);

        Leaderboard leaderboard = new Leaderboard();
        ObservableList<Leaderboard> lList = leaderboard.sort();
        TViewLeaderboard.setItems(lList);
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colWin.setCellValueFactory(new PropertyValueFactory<>("win"));
        colLose.setCellValueFactory(new PropertyValueFactory<>("lose"));
        colDraw.setCellValueFactory(new PropertyValueFactory<>("draw"));
        colPoint.setCellValueFactory(new PropertyValueFactory<>("point"));

        MatchDAO mDao = new MatchDAO();
        ObservableList<Match> mList = mDao.showData();
        TViewMatch.setItems(mList);
        colClub1.setCellValueFactory(new PropertyValueFactory<>("name1"));
        colClub2.setCellValueFactory(new PropertyValueFactory<>("name2"));
        colScore1.setCellValueFactory(new PropertyValueFactory<>("score1"));
        colScore2.setCellValueFactory(new PropertyValueFactory<>("score2"));
        colWL.setCellValueFactory(new PropertyValueFactory<>("result"));

        LView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Club>() {
            @Override
            public void changed(ObservableValue<? extends Club> observable, Club oldValue, Club newValue) {
                if (newValue.getName().equals("Select All")){
                    TViewMatch.setItems(mDao.showData());
                }else {
                    TViewMatch.setItems(mDao.filterData(newValue.getName()));
                }
            }
        });
    }

    public void addClub(ActionEvent actionEvent) {
        TextInputDialog dialog = new TextInputDialog();
        ClubDAO cDao = new ClubDAO();

        dialog.setHeaderText("Confirmation");
        dialog.setTitle("Add Club");
        dialog.setContentText("Input a club name");
        dialog.showAndWait();
        if (!dialog.getResult().equals("")){
            cDao.addData(new Club(dialog.getEditor().getText()));
            ObservableList<Club> cList = cDao.showData();
            LView.setItems(cList);
        }else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Warning");
            alert.setContentText("Please Fill the Field!");
            alert.showAndWait();
        }
    }

    public void deleteClub(ActionEvent actionEvent) {
        ClubDAO cDAO = new ClubDAO();
        Leaderboard leaderboard = new Leaderboard();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Confirmation");
        alert.setContentText("Are you sure ?");
        alert.showAndWait();
        if (alert.getResult() == ButtonType.OK) {
            Club club = LView.getSelectionModel().getSelectedItem();
            cDAO.deleteData(club);
            LView.getItems().remove(club);
            ObservableList<Leaderboard> lList = leaderboard.sort();
            TViewLeaderboard.setItems(lList);
        }
    }

    public void addMatch(ActionEvent actionEvent) throws IOException {
        MatchDAO mDao = new MatchDAO();
        Leaderboard leaderboard = new Leaderboard();

        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AMD.fxml"));
        Parent root = loader.load();
        stage.setTitle("Add Match Data");
        stage.setScene(new Scene(root, 475, 200));
        Controller_Add ca = loader.getController();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
        mDao.addData(new Match(ca.club1.getSelectionModel().getSelectedIndex() + 2, ca.club2.getSelectionModel().getSelectedIndex() + 2, Integer.parseInt(ca.score1.getText()), Integer.parseInt(ca.score2.getText())));
        ObservableList<Match> mList = mDao.showData();
        TViewMatch.setItems(mList);
        ObservableList<Leaderboard> lList = leaderboard.sort();
        TViewLeaderboard.setItems(lList);
    }

    public void deleteMatch(ActionEvent actionEvent) {
        MatchDAO mDao = new MatchDAO();
        Leaderboard leaderboard = new Leaderboard();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Confirmation");
        alert.setContentText("Are you sure ?");
        alert.showAndWait();
        if (alert.getResult() == ButtonType.OK) {
            Match selectedItems = TViewMatch.getSelectionModel().getSelectedItem();
            mDao.deleteData(selectedItems);
            TViewMatch.getItems().remove(selectedItems);
            ObservableList<Leaderboard> lList = leaderboard.sort();
            TViewLeaderboard.setItems(lList);
        }
    }

    public void updateMatch(ActionEvent actionEvent) throws IOException {
        MatchDAO mDao = new MatchDAO();
        Leaderboard leaderboard = new Leaderboard();

        Match selectedItems = TViewMatch.getSelectionModel().getSelectedItem();
        if (selectedItems == null) {
            selectedItems = TViewMatch.getItems().get(0);
        }

        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("UMD.fxml"));
        Parent root = loader.load();
        stage.setTitle("Update Match Data");
        stage.setScene(new Scene(root, 475, 200));
        Controller_Update cu = loader.getController();
        cu.initialize(selectedItems);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
        selectedItems.setScore1(Integer.parseInt(cu.score1.getText()));
        selectedItems.setScore2(Integer.parseInt(cu.score2.getText()));
        mDao.updateData(selectedItems);
        ObservableList<Match> mList = mDao.showData();
        TViewMatch.setItems(mList);
        ObservableList<Leaderboard> lList = leaderboard.sort();
        TViewLeaderboard.setItems(lList);
    }

    public void close(ActionEvent actionEvent) {
        System.exit(0);
    }
}
