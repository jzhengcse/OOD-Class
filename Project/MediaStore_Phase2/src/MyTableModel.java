
import java.util.Vector;
import javax.swing.table.DefaultTableModel;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Jason
 */
public class MyTableModel extends DefaultTableModel{
    
    
//    public MyTableModel(Vector data, Vector columnNames) {
//        super(data,columnNames);
//    }
//    
//    public MyTableModel(Object[][] data, Object[] columnNames){
//        super(data,columnNames);
//    }
    
    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }
}
