package univel.br.CadastroSimples;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public class ContatoModel extends AbstractTableModel{
	
	private List<Contato> lista;
	
	public ContatoModel() {
		this((List<Contato>)null);
		
		for(int i = 0; i< 100; i++){
			Contato c = new Contato();
			c.setId(i);
			c.setNome("Contato " + i);
			c.setTelefone("Telefone " + i);
			this.lista.add(i, c);
		}
	}
	
	public ContatoModel(List<Contato> list) {
		if(list == null){
			this.lista = new ArrayList<>();
		}else{
			this.lista = list;
		}
	}

	@Override
	public int getRowCount() {
		return lista.size();
	}

	@Override
	public int getColumnCount() {
		return 3;
	}

	@Override
	public Object getValueAt(int row, int column) {
		Contato c = this.lista.get(row);
		switch (column) {
		case 0:
			return c.getId();
		case 1:
			return c.getNome();
		case 2:
			return c.getTelefone();
		}
		
		return new Exception("Falta incluir alguma coluna");
	}

	public Contato getContato(int idx) {
		return lista.get(idx);
	}

	public void excluir(Contato contato) {
		this.lista.remove(contato);
		this.fireTableDataChanged();
	}

	public void addContato(Contato c) {
		this.lista.add(c);
		super.fireTableDataChanged();
		
	}


}
