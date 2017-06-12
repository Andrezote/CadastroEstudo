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
		
	}

	protected void salvar() {
		
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
