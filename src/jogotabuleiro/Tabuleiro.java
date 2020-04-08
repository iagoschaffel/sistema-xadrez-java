package jogotabuleiro;

public class Tabuleiro {
	
	private int linhas;
	private int colunas;
	private Pe�a[][] pe�as;
	public Tabuleiro(int linhas, int colunas) {
		if(linhas < 1 || colunas < 1) {
			throw new ExcecaoTabuleiro("Erro na cria��o do tabuleiro: � necess�rio  que haja pelo menos uma linha e uma coluna");
		}
		this.linhas = linhas;
		this.colunas = colunas;
		pe�as = new Pe�a[linhas][colunas];
	}
	public int getLinhas() {
		return linhas;
	
	}
	public int getColunas() {
		return colunas;
	}
	
	public Pe�a pe�a(int linha, int coluna) {
		if(!posicaoExiste(linha, coluna)) {
			throw new ExcecaoTabuleiro("A posi��o nao existe no tabuleiro.");
		}
		return pe�as[linha][coluna];
	}
	
	public Pe�a pe�a(Posicao posicao) {
		if(!posicaoExiste(posicao)) {
			throw new ExcecaoTabuleiro("A posi��o nao existe no tabuleiro.");
		}
		return pe�as[posicao.getLinha()][posicao.getColuna()];
	}
	
	public void posicionarPe�a(Pe�a pe�a, Posicao posicao) {
		if(temUmaPe�a(posicao)) {
			throw new ExcecaoTabuleiro("J� existe uma pe�a na posi��o " + posicao);
		}
		pe�as[posicao.getLinha()][posicao.getColuna()] = pe�a;
		pe�a.posicao = posicao;
	}
	
	public Pe�a removerPe�a(Posicao posicao) {
		if(!posicaoExiste(posicao)) {
			throw new ExcecaoTabuleiro("A posi��o nao est� no tabuleiro.");
		}
		if(pe�a(posicao) == null) {
			return null;
		}
		Pe�a aux = pe�a(posicao);
		aux.posicao = null;
		pe�as[posicao.getLinha()][posicao.getColuna()] = null;
		return aux;
	}

	private boolean posicaoExiste(int linha,int coluna) {
		
		return linha >= 0 && linha < colunas && coluna >= 0 && coluna < colunas;
	}
	
	public boolean posicaoExiste(Posicao posicao) {
		
		return posicaoExiste(posicao.getLinha(),posicao.getColuna());
	}
	
	public boolean temUmaPe�a(Posicao posicao) {
		if(!posicaoExiste(posicao)) {
			throw new ExcecaoTabuleiro("A posi��o nao existe no tabuleiro.");
		}
		 return pe�a(posicao) != null;
		
	}
	
	
}
