/** Josef Agustinus Sukimto Tangsul Alam 1872004 */
package Tugas_6;

public class Match {
    private int matchId;
    private String name1;
    private String name2;
    private int score1;
    private int score2;
    private int id1;
    private int id2;
    private String result;

    public Match (int matchId, String name1, String name2, int score1, int score2, String result) {
        this.setMatchId(matchId);
        this.setName1(name1);
        this.setName2(name2);
        this.setScore1(score1);
        this.setScore2(score2);
        this.setResult(result);
    }

    public Match (int id1, int id2, int score1, int score2) {
        this.setId1(id1);
        this.setId2(id2);
        this.setScore1(score1);
        this.setScore2(score2);
    }

    public String getName1() {
        return name1;
    }

    public void setName1(String name1) {
        this.name1 = name1;
    }

    public String getName2() {
        return name2;
    }

    public void setName2(String name2) {
        this.name2 = name2;
    }

    public int getScore1() {
        return score1;
    }

    public void setScore1(int score1) {
        this.score1 = score1;
    }

    public int getScore2() {
        return score2;
    }

    public void setScore2(int score2) {
        this.score2 = score2;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public int getId1() {
        return id1;
    }

    public void setId1(int id1) {
        this.id1 = id1;
    }

    public int getId2() {
        return id2;
    }

    public void setId2(int id2) {
        this.id2 = id2;
    }

    public int getMatchId() {
        return matchId;
    }

    public void setMatchId(int matchId) {
        this.matchId = matchId;
    }
}
