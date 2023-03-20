/** Josef Agustinus Sukimto Tangsul Alam 1872004 */
package Tugas_6;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Comparator;

public class Leaderboard {
    private int id;
    private int win;
    private int lose;
    private int draw;
    private int point;
    private String name;

    public Leaderboard (){}

    public Leaderboard (int id, Leaderboard match) {
        this.setId(id);
        this.setName(match.getName());
        this.setWin(match.getWin());
        this.setLose(match.getLose());
        this.setDraw(match.getDraw());
        this.setPoint(match.getPoint());
    }

    public Leaderboard (String name, int win, int lose, int draw, int point) {
        this.setName(name);
        this.setWin(win);
        this.setLose(lose);
        this.setDraw(draw);
        this.setPoint(point);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWin() {
        return win;
    }

    public void setWin(int win) {
        this.win = win;
    }

    public int getLose() {
        return lose;
    }

    public void setLose(int lose) {
        this.lose = lose;
    }

    public int getDraw() {
        return draw;
    }

    public void setDraw(int draw) {
        this.draw = draw;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ObservableList<Leaderboard> sort(){
        ClubDAO cDAO = new ClubDAO();
        LeaderboardDAO lDAO = new LeaderboardDAO();
        ObservableList<Club> cList = cDAO.showData();
        cList.remove(0);
        ObservableList<Leaderboard> lList = FXCollections.observableArrayList();
        ObservableList<Leaderboard> lListSort = FXCollections.observableArrayList();

        for (Club club : cList) {
            lList.add(lDAO.showData(club.toString()));
        }
        lList.sort(Comparator.comparingInt(Leaderboard::getPoint).reversed());
        for (int i = 0; i < lList.size(); i++) {
            lListSort.add(new Leaderboard(i + 1, lList.get(i)));
        }
        return lListSort;
    }
}
