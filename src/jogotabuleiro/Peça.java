package jogotabuleiro;

public class Pe�a {
	
	protected Posicao posicao;
	
	private Tabuleiro tabuleiro;

	public Pe�a(Tabuleiro tabuleiro) {
		this.tabuleiro = tabuleiro;
		posicao = null;
	}

	protected Tabuleiro getTabuleiro() {
		return tabuleiro;
	}

	
	
	

}
