package giaoDien;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JTable;

public class TableUI {
    private static SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");

    public static void updateTable(JTable table, String computerName) {
        for (int i = 0; i < table.getRowCount(); i++) {
            if (table.getValueAt(i, 0).equals(computerName)) {
                if (table.getValueAt(i, 1) == null) {
                    table.setValueAt(sdf.format(new Date()), i, 1);
                } else if (table.getValueAt(i, 2) == null) {
                    table.setValueAt(sdf.format(new Date()), i, 2);
                } else {
                    // Nếu cả thời gian vào và thời gian ra đều đã được điền, thì xóa thông tin của máy tính
                    clearComputerInfo(table, i);
                }
                break;
            }
        }
    }

    private static void clearComputerInfo(JTable table, int rowIndex) {
        table.setValueAt(null, rowIndex, 1); // Reset thời gian vào
        table.setValueAt(null, rowIndex, 2); // Reset thời gian ra
    }
}
