/** Josef Agustinus Sukimto Tangsul Alam 1872004 */
package Tugas_6;

import Utility.DAOInterface;
import Utility.JDBCConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MatchDAO implements DAOInterface<Match> {

    @Override
    public int addData(Match data) {
        int result = 0;

        try {
            String query = "INSERT INTO matchresult (Id_team1,Id_team2, score1, score2) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = JDBCConnection.getConnection().prepareStatement(query);
            ps.setInt(1, data.getId1());
            ps.setInt(2, data.getId2());
            ps.setInt(3, data.getScore1());
            ps.setInt(4, data.getScore2());
            result = ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return 0;
    }

    @Override
    public int deleteData(Match data) {
        int result = 0;

        try {
            String query = "DELETE FROM matchresult WHERE Id = ?";
            Connection conn = JDBCConnection.getConnection();
            conn.setAutoCommit(false);
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, data.getMatchId());
            result = ps.executeUpdate();
            if (result != 0) {
                conn.commit();
            } else {
                conn.rollback();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return 0;
    }

    @Override
    public int updateData(Match data) {
        int result = 0;

        try {
            String query = "UPDATE matchresult SET SCore1 = ?, Score2 = ? WHERE Id = ?";
            Connection conn = JDBCConnection.getConnection();
            conn.setAutoCommit(false);
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, data.getScore1());
            ps.setInt(2, data.getScore2());
            ps.setInt(3, data.getMatchId());
            result = ps.executeUpdate();
            if (result != 0) {
                conn.commit();
            } else {
                conn.rollback();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return 0;
    }

    public ObservableList<Match> filterData(String name) {
        ObservableList<Match> mList = FXCollections.observableArrayList();
        String result = "";

        try {
            String query = "SELECT * FROM matchresult m JOIN club c1 ON m.Id_team1 = c1.Id JOIN club c2 ON m.Id_team2 = c2.Id WHERE c1.Name = ? OR c2.Name = ?";
            PreparedStatement ps = JDBCConnection.getConnection().prepareStatement(query);
            ps.setString(1, name);
            ps.setString(2, name);
            ResultSet res = ps.executeQuery();
            while (res.next()) {
                int matchId = res.getInt("Id");
                String name1 = res.getString("c1.Name");
                String name2 = res.getString("c2.Name");
                int score1 = res.getInt("Score1");
                int score2 = res.getInt("Score2");
                if (name1.equals(name)) {
                    if (score1 > score2) {
                        result = "Win";
                    } else if (score2 > score1) {
                        result = "Lose";
                    } else {result = "Draw";}
                } else if (name2.equals(name)) {
                    if (score2 > score1) {
                        result = "Win";
                    } else if (score1 > score2) {
                        result = "Lose";
                    } else {result = "Draw";}
                }
                mList.add(new Match(matchId, name1, name2, score1, score2, result));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return mList;
    }

    @Override
    public ObservableList<Match> showData() {
        ObservableList<Match> mList = FXCollections.observableArrayList();
        String result;

        try {
            String query = "SELECT * FROM matchresult m JOIN club c1 ON m.Id_team1 = c1.Id JOIN club c2 ON m.Id_team2 = c2.Id";
            PreparedStatement ps = JDBCConnection.getConnection().prepareStatement(query);
            ResultSet res = ps.executeQuery();
            while (res.next()) {
                int matchId = res.getInt("Id");
                String name1 = res.getString("c1.Name");
                String name2 = res.getString("c2.Name");
                int score1 = res.getInt("Score1");
                int score2 = res.getInt("Score2");
                if (score1 > score2) {
                    result = "Win";
                } else if (score2 > score1) {
                    result = "Lose";
                } else {result = "Draw";}
                mList.add(new Match(matchId, name1, name2, score1, score2, result));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return mList;
    }
}
