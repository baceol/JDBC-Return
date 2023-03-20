package Tugas_6;

import Utility.JDBCConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class LeaderboardDAO {
    public Leaderboard showData(String name) {
        int point = 0;
        int win = 0;
        int lose = 0;
        int draw = 0;

        try {
            String query = "SELECT * FROM matchresult m JOIN club c1 ON m.Id_team1 = c1.Id JOIN club c2 ON m.Id_team2 = c2.Id WHERE c1.Name = ? OR c2.Name = ?";
            PreparedStatement ps = JDBCConnection.getConnection().prepareStatement(query);
            ps.setString(1, name);
            ps.setString(2, name);
            ResultSet res = ps.executeQuery();
            while (res.next()) {
                String name1 = res.getString("c1.Name");
                String name2 = res.getString("c2.Name");
                int score1 = res.getInt("Score1");
                int score2 = res.getInt("Score2");
                if (name1.equals(name)) {
                    if (score1 > score2) {
                        win ++;
                        point += 3;
                    } else if (score2 > score1) {
                        lose++;
                        point -= 2;
                    } else {
                        draw++;
                        point += 1;
                    }
                } else if (name2.equals(name)) {
                    if (score2 > score1) {
                        win++;
                        point += 3;
                    } else if (score1 > score2) {
                        lose++;
                        point -= 2;
                    } else {
                        draw++;
                        point += 1;
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return new Leaderboard(name, win, lose, draw, point);
    }
}
