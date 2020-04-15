package xadrez;

import jogotabuleiro.Peça;
import jogotabuleiro.Posicao;
import jogotabuleiro.Tabuleiro;
import xadrez.peças.Rei;
import xadrez.peças.Torre;

public class PartidaXadrez {
	
	private int turno;
	private Cor Jogadoratual;
	private Tabuleiro tabuleiro;
	
	public PartidaXadrez() {
		tabuleiro = new Tabuleiro(8,8);
		turno = 1;
		Jogadoratual = Cor.BRANCO;
		Configuracaoinicial();
	}
	
	public int getTurno() {
		return turno;
	}
	
	public Cor getJogadorAtual() {
		return Jogadoratual;
	}
	
	public PeçaXadrez[][] getPeças() {
		
		PeçaXadrez[][] mat = new PeçaXadrez[tabuleiro.getLinhas()][tabuleiro.getColunas()];
		for(int i=0;i<tabuleiro.getLinhas();i++) {
			for(int j=0;j<tabuleiro.getColunas();j++) {
				mat[i][j] = (PeçaXadrez) tabuleiro.peça(i,j);
			}
		}
		return mat;
	}
	
	public boolean[][] Movimentospossiveis(PosicaoXadrez origemPosicao){
		Posicao posicao = origemPosicao.paraPosicao();
		validarOrigemPosicao(posicao);
		return tabuleiro.peça(posicao).Movimentospossiveis();
	}
	
	public PeçaXadrez realizarMovimentoXadrez(PosicaoXadrez origemPosicao, PosicaoXadrez alvoPosicao) {
		Posicao origem = origemPosicao.paraPosicao();
		Posicao alvo = alvoPosicao.paraPosicao();
		validarOrigemPosicao(origem);
		validarAlvoPosicao(origem, alvo);
		Peça Peçacapturada = fazerMovimento(origem, alvo);
		proximoTurno();
		return (PeçaXadrez)Peçacapturada;
	}
	
	private void validarOrigemPosicao(Posicao posicao) {
		if(!tabuleiro.temUmaPeça(posicao)) {
			throw new ExcecaoXadrez("Nao existe peça na posição de origem.");
		}
		if(Jogadoratual != ((PeçaXadrez)tabuleiro.peça(posicao)).getCor()){
			throw new ExcecaoXadrez("A peça escolhida nao é sua.");
		}
		if(!tabuleiro.peça(posicao).ExisteMovimentoPossivel()) {
			throw new ExcecaoXadrez("Nao existem movimentos possiveis para a peça escolhida.");
		}
		
	}
	
	private void validarAlvoPosicao(Posicao origem, Posicao alvo) {
		if(!tabuleiro.peça(origem).Movimentopossivel(alvo)) {
		throw new ExcecaoXadrez("A peça escolhida não pode se mover para a posição destino.");
		}
	}
	
	private Peça fazerMovimento(Posicao origem, Posicao alvo) {
		Peça p = tabuleiro.removerPeça(origem);
		Peça peçaCapturada = tabuleiro.removerPeça(alvo);
		tabuleiro.posicionarPeça(p, alvo);
		return peçaCapturada;
	}
	
	private void proximoTurno() {
		turno++;
		Jogadoratual = (Jogadoratual == Cor.BRANCO) ? Cor.PRETO : Cor.BRANCO;
	}
	
	private void posicionarPeçaNova(char coluna, int linha, PeçaXadrez peça) {
		tabuleiro.posicionarPeça(peça, new PosicaoXadrez(coluna,linha).paraPosicao());
	}
	
	private void Configuracaoinicial() {
		
		posicionarPeçaNova('c', 1, new Torre(tabuleiro, Cor.BRANCO));
		posicionarPeçaNova('c', 2, new Torre(tabuleiro, Cor.BRANCO));
		posicionarPeçaNova('d', 2, new Torre(tabuleiro, Cor.BRANCO));
		posicionarPeçaNova('e', 2, new Torre(tabuleiro, Cor.BRANCO));
		posicionarPeçaNova('e', 1, new Torre(tabuleiro, Cor.BRANCO));
		posicionarPeçaNova('d', 1, new Rei(tabuleiro, Cor.BRANCO));

		posicionarPeçaNova('c', 7, new Torre(tabuleiro, Cor.PRETO));
		posicionarPeçaNova('c', 8, new Torre(tabuleiro, Cor.PRETO));
		posicionarPeçaNova('d', 7, new Torre(tabuleiro, Cor.PRETO));
		posicionarPeçaNova('e', 7, new Torre(tabuleiro, Cor.PRETO));
		posicionarPeçaNova('e', 8, new Torre(tabuleiro, Cor.PRETO));
		posicionarPeçaNova('d', 8, new Rei(tabuleiro, Cor.PRETO));
	}

}
