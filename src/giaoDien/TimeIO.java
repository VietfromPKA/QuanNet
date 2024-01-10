package giaoDien;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import tinhNang.TinhTien;


public class TimeIO{
    private static final SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
    
    public static void calculateFee(JTable table) throws ParseException{
        TinhTien tt = new TinhTien();
        for(int i = 0; i < table.getRowCount(); ++i){
            if(table.getValueAt(i, 1) != null && table.getValueAt(i, 2) != null){
                try{
                    Date inTime = sdf.parse((String) table.getValueAt(i, 1));
                    Date outTime = sdf.parse((String) table.getValueAt(i, 2));
                    long fee = tt.tinhTien(inTime, outTime);
                    JOptionPane.showMessageDialog(null, "Số tiền cho " + table.getValueAt(i, 0) + " là " +fee );
                }catch(ParseException e){
                    e.printStackTrace();
                }
            }
        }
    }
    
}