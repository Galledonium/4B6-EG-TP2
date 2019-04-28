package modele;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

public class ModeleArtiste extends AbstractTableModel {
	private static final long serialVersionUID = 1L;
	
	private ArrayList<Artiste> data;
	
	private final String [] columns = {"No", "Nom", "Membre"};
	
	public ModeleArtiste(ArrayList<Artiste> data) {
		this.data = data;
	}
	
	@Override
	public int getColumnCount() {
		return columns.length;
	}

	@Override
	public int getRowCount() {
		return data.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		switch (columnIndex) {
			case 0:
				return data.get(rowIndex).getID();
			case 1:
				return data.get(rowIndex).getNom();
			case 2:
				return data.get(rowIndex).getMembre();
			default:
				return null;
		}
	}
	
	@Override
	public String getColumnName(int columnIndex) {
		return columns[columnIndex];
	}
	
	@Override
	public Class<?> getColumnClass(int columnIndex){
		switch (columnIndex) {
        case 0: return int.class;
        case 1: return String.class;
        case 2: return Boolean.class;
        default: throw new IllegalArgumentException(" index de colonne invalide: " + columnIndex);
	  }
	}
	
	public static void main(String[] args) {
		
	}
}
