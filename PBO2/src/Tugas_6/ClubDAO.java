/** Josef Agustinus Sukimto Tangsul Alam 1872004 */
package Tugas_6;

import Utility.DAOInterface;
import Utility.JDBCConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClubDAO implements DAOInterface<Club> {

    @Override
    public int addData(Club data) {
        int result = 0;

        try {
            String query = "INSERT INTO club (Name) VALUES (?)";
            PreparedStatement ps = JDBCConnection.getConnection().prepareStatement(query);
            ps.setString(1, data.getName());
            result = ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return 0;
    }

    @Override
    public int deleteData(Club data) {
        int result = 0;

        try {
            String query = "DELETE FROM matchresult WHERE Id_team1 = (SELECT Id FROM club WHERE Name = ?) OR Id_team2 = (SELECT Id FROM club WHERE Name = ?)";
            PreparedStatement ps = JDBCConnection.getConnection().prepareStatement(query);
            ps.setString(1, data.getName());
            ps.setString(2, data.getName());
            result = ps.executeUpdate();
            query = "DELETE FROM club WHERE Name = ?";
            ps = JDBCConnection.getConnection().prepareStatement(query);
            ps.setString(1, data.getName());
            result = ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return 0;
    }

    @Override
    public int updateData(Club data) {
        return 0;
    }

    @Override
    public ObservableList<Club> showData() {
        ObservableList<Club> cList = FXCollections.observableArrayList();

        try {
            String query = "SELECT * FROM club";
            PreparedStatement ps = JDBCConnection.getConnection().prepareStatement(query);
            ResultSet res = ps.executeQuery();
            while (res.next()) {
                String name = res.getString("Name");
                cList.add(new Club(name));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return cList;
    }
}
