package jogotabuleiro;

public abstract class Peça {
	
	protected Posicao posicao;
	
	private Tabuleiro tabuleiro;

	public Peça(Tabuleiro tabuleiro) {
		this.tabuleiro = tabuleiro;
		posicao = null;
	}

	protected Tabuleiro getTabuleiro() {
		return tabuleiro;
	}

	public abstract boolean[][] Movimentospossiveis();
	
	public boolean Movimentopossivel(Posicao posicao) {
		return Movimentospossiveis()[posicao.getLinha()][posicao.getColuna()];
	}

	public boolean ExisteMovimentoPossivel() {
		boolean[][] mat = Movimentospossiveis();
		for(int i=0; i<mat.length; i++) {
			for(int j=0; j<mat.length;j++) {
				if(mat[i][j]) {
					return true;
				}
			}
		}
		return false;
	}
}
