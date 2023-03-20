/** Josef Agustinus Sukimto Tangsul Alam 1872004 */
package Utility;

import java.util.List;

public interface DAOInterface<E> {
    public int addData(E data);
    public int deleteData(E data);
    public int updateData(E data);

    public List<E> showData();
}
