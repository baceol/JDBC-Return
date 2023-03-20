/** Josef Agustinus Sukimto Tangsul Alam 1872004 */
package Tugas_6;

public class Club {
    private String name;

    public Club (String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
