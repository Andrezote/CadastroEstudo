package univel.br.CadastroSimples;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class TelaCadastroPrincipal extends TelaCadastroBase {

	private JPanel contentPane;
	private Contato contatoSelecionado;
	private ContatoModel modelo;
	
	
	public TelaCadastroPrincipal() {
		super();
		LimparCampos();
		configuraBotoes();
		configuraTabela();
		configuraMenu();
	}

	private void configuraMenu() {
		super.mntmImprimir.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ReportManager rm = new ReportManager();
				rm.imprimir();
				
			}
		});
		super.mntmExportarpdf.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ReportManager rm = new ReportManager();
				rm.exportar();
			}
		});
		
	}

	private void configuraTabela() {
		ContatoDao cD = new ContatoDao();
		List<Contato> lista = cD.GetTodos();
		this.modelo= new ContatoModel(lista);
		
		super.table.setModel(modelo);
		
		super.table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount() == 2){
					int idx = table.getSelectedRow();
					if(idx < 0){
						System.out.println("nao a linha selecionada");
					}else{
						System.out.println("linha " + idx);
						carregarLinha(idx);
					}
				}
			}
			
		});
		
	}

	protected void carregarLinha(int idx) {
		Contato c = this.modelo.getContato(idx);
		this.contatoSelecionado = c;
		
		this.txfId.setText(String.valueOf(c.getId()));
		this.txfNome.setText(c.getNome());
		this.txfTelefone.setText(c.getTelefone());
		
		super.btnExcluir.setEnabled(true);
		super.lblCarregadoParaAtualizar.setText(super.CARREGADO_PARA_ATUALIZAR);
		
	}

	private void configuraBotoes() {
		super.btnSalvar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("clicou em salvar");
				salvar();
			}
		});
		super.btnNovo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("clicou em novo");
				novo();
			}
		});
		super.btnExcluir.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("clicou em excluir");
				excluir();
			}
		});
		
	}

	protected void excluir() {
		this.modelo.excluir(contatoSelecionado);
	}

	protected void salvar() {
		if(contatoSelecionado == null){
			Contato c = new Contato();
			c.setId(Integer.parseInt(super.txfId.getText()));
			c.setNome(super.txfNome.getText());
			c.setTelefone(super.txfTelefone.getText());
			
			this.modelo.addContato(c);
		}else{
			contatoSelecionado.setId(Integer.parseInt(super.txfId.getText()));
			contatoSelecionado.setNome(super.txfNome.getText());
			contatoSelecionado.setTelefone(super.txfTelefone.getText());
			
			LimparCampos();
			this.modelo.fireTableDataChanged();
		}
	}

	protected void novo() {
		LimparCampos();
		this.contatoSelecionado = null;
	}

	private void LimparCampos() {
		super.txfId.setText("");
		super.txfNome.setText("");
		super.txfTelefone.setText("");
		super.lblCarregadoParaAtualizar.setText("");
		
		super.btnExcluir.setEnabled(false);
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaCadastroPrincipal frame = new TelaCadastroPrincipal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	

}
