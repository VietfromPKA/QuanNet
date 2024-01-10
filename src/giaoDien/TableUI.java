package  giaoDien;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JTable;

public class TableUI{
    private static SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");

    public static void updateTable(JTable table, String computerName) {
        for (int i = 0; i < table.getRowCount(); i++) {
            if (table.getValueAt(i, 0).equals(computerName)) {
                if (table.getValueAt(i, 1) == null) {
                    
                    table.setValueAt(sdf.format(new Date()), i, 1);
                } else if (table.getValueAt(i, 2) == null) {
                    
                    table.setValueAt(sdf.format(new Date()), i, 2);
                } else {
                    
                    table.setValueAt(sdf.format(new Date()), i, 1);
                    table.setValueAt(null, i, 2);
                }
                break;
            }
        }
    }
}
